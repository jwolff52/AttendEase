package attendease.database;

import attendease.util.Start;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author James
 */
public class Database {
    private static Connection conn;
    private static Statement stmt;
    private static ArrayList<String> studentColumns, meetingColumns;
    private static final String DEFAULT_SCHEMA="ATTENDEASE";
    
    public Database() throws InstantiationException, ClassNotFoundException, IllegalAccessException{
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
        try{
            conn=DriverManager.getConnection("jdbc:derby:AttendEase");
            conn.setAutoCommit(false);
        }catch(SQLException e){
            SQLException f=e;
            while(f.getNextException()!=null){
                f=f.getNextException();
            }
            if(f.getSQLState().equals("XSDB6")){
                Start.removeSplash();
                javax.swing.JOptionPane.showMessageDialog(null, "The Program is already running in another instance!");
                System.exit(0);
            }
            try {
                conn=DriverManager.getConnection("jdbc:derby:AttendEase;create=true");
            } catch (SQLException ex) {
            Start.createLog(ex, "An Internal Communication Error Occurred With the Database");
            }
        }
        studentColumns=new ArrayList<>();
        studentColumns.add("ID");
        studentColumns.add("NAME");
        studentColumns.add("POINTS");
        meetingColumns=new ArrayList<>();
        meetingColumns.add("NAME");
        meetingColumns.add("POINTS");
    }
    
    public void getDb(){
        try {
            stmt=conn.createStatement();
            stmt.executeUpdate("CREATE SCHEMA "+DEFAULT_SCHEMA);
            stmt.close();
        } catch (SQLException ex) {
            if(!ex.getSQLState().equals("X0Y68")){
                Start.createLog(ex, "Unable To Create Necessary Database Resources");
            }
        }
        try{
            stmt=conn.createStatement();
            stmt.executeQuery("SELECT * FROM "+DEFAULT_SCHEMA+".Clubs");
        }catch(SQLException e){
            try{
                stmt=conn.createStatement();
                stmt.executeUpdate("CREATE TABLE "+DEFAULT_SCHEMA+".Clubs(clubName varchar(25), points BOOLEAN, PRIMARY KEY (clubName))");
                stmt.close();
            }catch(SQLException ex){
                Start.createLog(ex, "Unable to create table Clubs!");
            }
        }
        try {
            stmt.close();
        } catch (SQLException ex) {
            Start.createLog(ex, "An Internal Communication Error Occurred With the Database");
        }
    }
    
    /*
     * returns true if table exsists, false otherwise
     */
    public boolean createTables(String tblName){
        reconnect();
        try{
            stmt=conn.createStatement();
            stmt.executeQuery("SELECT * FROM "+DEFAULT_SCHEMA+"."+tblName+"Meetings");
            return true;
        }catch(SQLException e){
            String ss="CREATE TABLE "+DEFAULT_SCHEMA+"."+tblName+"Students(ID varchar(10), name varchar(50), meetings varchar(22), points INTEGER, PRIMARY KEY(ID))";
            String ms="CREATE TABLE "+DEFAULT_SCHEMA+"."+tblName+"Meetings(ID varchar(50), startTime varchar(5), endTime varchar(5), pointsGiven INTEGER, pointsRequired INTEGER, latePoints INTEGER, PRIMARY KEY(ID))";
            try {
                stmt.close();
                stmt=conn.createStatement();
                stmt.executeUpdate(ms);
            } catch (SQLException ex) {
                Start.createLog(ex, "Failed to create table "+tblName+"Meetings");
            }
            try {
                stmt.close();
                stmt=conn.createStatement();
                stmt.executeUpdate(ss);
            } catch (SQLException ex) {
                Start.createLog(ex, "Failed to create table "+tblName+"Students");
            }
        }
        try {
            stmt.close();
        } catch (SQLException ex) {
            Start.createLog(ex, "An Internal Communication Error Occurred With the Database");
        }
        return false;
    }
    
    public void deleteTable(String tblName){
        reconnect();
        try {
            stmt=conn.createStatement();
            stmt.executeUpdate("DROP TABLE "+DEFAULT_SCHEMA+"."+tblName);
        } catch (SQLException ex) {
            Start.createLog(ex, "Unable to delete table: "+tblName);
        }
        try {
            stmt.close();
        } catch (SQLException ex) {
            Start.createLog(ex, "An Internal Communication Error Occurred With the Database");
        }
    }
    
    public ResultSet readTable(String tblName){
        reconnect();
        try {
            stmt=conn.createStatement();
            return stmt.executeQuery("SELECT * FROM "+DEFAULT_SCHEMA+"."+tblName);
        } catch (SQLException ex) {
            Start.createLog(ex, "Unable to read from table: "+tblName);
        }
        try {
            stmt.close();
        } catch (SQLException ex) {
            Start.createLog(ex, "An Internal Communication Error Occurred With the Database");
        }
        return null;
    }
    
    public ResultSet readTable(String tblName, String[] cols){
        reconnect();
        String columns="";
        for(String s:cols){
            columns+=s+",";
        }
        columns=columns.substring(0, columns.length()-1);
        try {
            stmt=conn.createStatement();
            return stmt.executeQuery("SELECT "+columns+" FROM "+DEFAULT_SCHEMA+"."+tblName);
        } catch (SQLException ex) {
            Start.createLog(ex, "Unable to read from table: "+tblName);
        }
        try {
            stmt.close();
        } catch (SQLException ex) {
            Start.createLog(ex, "An Internal Communication Error Occurred With the Database");
        }
        return null;
    }
    
    public ResultSet readTable(String tblName, String constraints){
        reconnect();
        try {
            stmt=conn.createStatement();
            return stmt.executeQuery("SELECT * FROM "+DEFAULT_SCHEMA+"."+tblName+" WHERE "+constraints);
        } catch (SQLException ex) {
            Start.createLog(ex, "Unable to read from table: "+tblName);
        }
        try {
            stmt.close();
        } catch (SQLException ex) {
            Start.createLog(ex, "An Internal Communication Error Occurred With the Database");
        }
        return null;
    }
    
    public ResultSet readTable(String tblName, String[] cols, String constraints){
        reconnect();
        String columns="";
        for(String s:cols){
            columns+=s+",";
        }
        columns=columns.substring(0, columns.length()-1);
        try {
            stmt=conn.createStatement();
            return stmt.executeQuery("SELECT "+columns+" FROM "+DEFAULT_SCHEMA+"."+tblName+" WHERE "+constraints);
        } catch (SQLException ex) {
            Start.createLog(ex, "Unable to read from table: "+tblName);
        }
        try {
            stmt.close();
        } catch (SQLException ex) {
            Start.createLog(ex, "An Internal Communication Error Occurred With the Database");
        }
        return null;
    }
    
    
    
    private void writeTable(String tblName, String value, String col) throws SQLException{
        reconnect();
        conn.setAutoCommit(true);
        stmt=conn.createStatement();
        stmt.executeUpdate("INSERT INTO "+DEFAULT_SCHEMA+"."+tblName+" ("+col+") VALUES (\'"+value+"\')");
        conn.setAutoCommit(false);
        stmt.close();
    }
    
    public void writeTable(String tblName, String value, boolean bValue, String[] cols){
        try {
            reconnect();
            conn.setAutoCommit(true);
            stmt=conn.createStatement();
            stmt.executeUpdate("INSERT INTO "+DEFAULT_SCHEMA+"."+tblName+" ("+cols[0]+", "+cols[1]+") VALUES (\'"+value+"\', \'"+bValue+"\')");
            conn.setAutoCommit(false);
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void writeTable(String tblName, String[] values){
        try {
            for(int i=0;i<values.length;i++){
                writeTable(tblName, values[i], studentColumns.get(i%studentColumns.size()));
            }
        } catch (SQLException | ArrayIndexOutOfBoundsException ex) {
            if(ex.getClass().getName().equals("java.sql.SQLException")){
                Start.createLog(ex, "A Database Error Occurred");
            }else{
                Start.createLog(ex, "A Logic Error Occurred");
            }
        }
    }
    
    public void writeTable(String tblName, String[] values, ArrayList<String> cols){
        try {
            if(values.length==cols.size()){
                for(int i=0;i<values.length;i++){
                    writeTable(tblName, values[i], cols.get(i));
                }
            }else{
                for(int i=0;i<values.length;i++){
                    writeTable(tblName, values[i], cols.get(i%cols.size()));
                }
            }
        } catch (SQLException | ArrayIndexOutOfBoundsException ex) {
            if(ex.getClass().getName().equals("java.sql.SQLException")){
                Start.createLog(ex, "A Database Error Occurred");
            }else{
                Start.createLog(ex, "A Logic Error Occurred");
            }
        }
    }
    
    public void writeTable(String tblName, String[] values, String col){
        try {
            for(int i=0;i<values.length;i++){
                writeTable(tblName, values[i], col);
            }
        } catch (SQLException | ArrayIndexOutOfBoundsException ex) {
            Start.createLog(ex, "A Database Error Occurred");
        }
    }
    
    public void writeTable(String tblName, String[] values, String[] cols){
        try {
            for(int i=0;i<values.length;i++){
                writeTable(tblName, values[i], cols[i]);
            }
        } catch (SQLException | ArrayIndexOutOfBoundsException ex) {
            Start.createLog(ex, "A Database Error Occurred");
        }
    }
    
    public void writeTable(String tblName, ArrayList<String> values){
        try {
            for(int i=0;i<values.size();i++){
                writeTable(tblName, values.get(i), studentColumns.get(i%studentColumns.size()));
            }
        } catch (SQLException | ArrayIndexOutOfBoundsException ex) {
            if(ex.getClass().getName().equals("java.sql.SQLException")){
                Start.createLog(ex, "A Database Error Occurred");
            }else{
                Start.createLog(ex, "A Logic Error Occurred");
            }
        }
    }
    
    public void writeTable(String tblName, ArrayList<String> values, ArrayList<String> cols){
        try {
            if(values.size()==cols.size()){
                for(int x=0;x<values.size();x++){
                    writeTable(tblName, values.get(x), cols.get(x));
                }
            }else{
                for(int x=0;x<values.size();x++){
                    writeTable(tblName, values.get(x), cols.get(x%cols.size()));
                }
            }
        } catch (SQLException | ArrayIndexOutOfBoundsException ex) {
            if(ex.getClass().getName().equals("java.sql.SQLException")){
                Start.createLog(ex, "A Database Error Occurred");
            }else{
                Start.createLog(ex, "A Logic Error Occurred");
            }
        }
    }
    
    public void writeTable(String tblName, ArrayList<String> values, String col){
        try {
            for(int x=0;x<values.size();x++){
                writeTable(tblName, values.get(x), col);
            }
        } catch (SQLException ex) {
            Start.createLog(ex, "A Database Error Occurred");
        }
    }
    
    public void deleteTableValue(String tblName, String colName, String value){
        reconnect();
        try {
            stmt=conn.createStatement();
            stmt.executeUpdate("DELETE FROM "+DEFAULT_SCHEMA+"."+tblName+" WHERE "+colName+"=\'"+value+"\'");
        } catch (SQLException ex) {
            Start.createLog(ex, "Unable to delete \""+value+"\" from table: "+tblName);
        }
        try {
            stmt.close();
        } catch (SQLException ex) {
            Start.createLog(ex, "An Internal Communication Error Occurred With the Database");
        }
    }
    
    private void reconnect(){
        try {
            conn.close();
            conn=DriverManager.getConnection("jdbc:derby:AttendEase");
        } catch (SQLException ex) {
            Start.createLog(ex, "An Internal Communication Error Occurred With the Database");
        }
    }
}
