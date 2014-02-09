package attendease.database;

import attendease.util.Start;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
        meetingColumns.add("ID");
        meetingColumns.add("DATE");
        meetingColumns.add("STARTTIME");
        meetingColumns.add("ENDTIME");
        meetingColumns.add("REOCURRINGDAYS");
        meetingColumns.add("POINTSNEEDED");
        meetingColumns.add("POINTSREQUIRED");
        meetingColumns.add("LATEPOINTS");
        
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
                stmt.executeUpdate("CREATE TABLE "+DEFAULT_SCHEMA+".Clubs(clubName varchar(25), ePath varchar(255), points BOOLEAN, PRIMARY KEY (clubName))");
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
     * returns false if club exsists, true otherwise
     */
    public boolean addClub(String clubName, String ePath, boolean points){
        try {
            stmt=conn.createStatement();
            stmt.executeUpdate("INSERT INTO "+DEFAULT_SCHEMA+".CLUBS VALUES (\'"+clubName+"\',\'"+ePath+"\',"+points+")");
            stmt.close();
        } catch (SQLException ex) {
            Start.createLog(ex, "Error creating Club!");
        }
        try{
            stmt=conn.createStatement();
            stmt.executeQuery("SELECT * FROM "+DEFAULT_SCHEMA+"."+clubName+"Meetings");
            return false;
        }catch(SQLException e){
            String ss="CREATE TABLE "+DEFAULT_SCHEMA+"."+clubName+"Students(ID varchar(10), name varchar(50), meetings varchar(22), points INTEGER, PRIMARY KEY(ID))";
            String ms="CREATE TABLE "+DEFAULT_SCHEMA+"."+clubName+"Meetings(ID varchar(255), date varchar(25), startTime varchar(10), endTime varchar(10), reocurringDays INTEGER, pointsGiven INTEGER, pointsRequired INTEGER, latePoints INTEGER, PRIMARY KEY(ID))";
            try {
                stmt.close();
                stmt=conn.createStatement();
                stmt.executeUpdate(ms);
            } catch (SQLException ex) {
                Start.createLog(ex, "Failed to create table "+clubName+"Meetings");
            }
            try {
                stmt.close();
                stmt=conn.createStatement();
                stmt.executeUpdate(ss);
            } catch (SQLException ex) {
                Start.createLog(ex, "Failed to create table "+clubName+"Students");
            }
        }
        try {
            stmt.close();
        } catch (SQLException ex) {
            Start.createLog(ex, "An Internal Communication Error Occurred With the Database");
        }
        return true;
    }
    
    public void addMeeting(String clubName, String[] values){
        clubName+="Meetings";
        try {
            for(int i=0;i<values.length;i++){
                writeTable(clubName, values[i], meetingColumns.get(i%meetingColumns.size()));
            }
        } catch (SQLException | ArrayIndexOutOfBoundsException ex) {
            if(ex.getClass().getName().equals("java.sql.SQLException")){
                Start.createLog(ex, "A Database Error Occurred");
            }else{
                Start.createLog(ex, "A Logic Error Occurred");
            }
        }
    }
    
    public void deleteClub(String clubName){
        try {
            stmt=conn.createStatement();
            stmt.executeUpdate("DELETE FROM "+DEFAULT_SCHEMA+".CLUBS WHERE clubName=\'"+clubName+"\'");
        } catch (SQLException ex) {
            Start.createLog(ex, "Unable to delete \""+clubName+"\"");
        }
        try {
            stmt.close();
        } catch (SQLException ex) {
            Start.createLog(ex, "An Internal Communication Error Occurred With the Database");
        }
        deleteTable(clubName+"Meetings");
        deleteTable(clubName+"Students");
    }
    
    public void deleteTable(String tblName){
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
    
    public ResultSet readClubsTable(){
        try {
            stmt=conn.createStatement();
            return stmt.executeQuery("SELECT * FROM "+DEFAULT_SCHEMA+".CLUBS");
        } catch (SQLException ex) {
            Start.createLog(ex, "Unable to read from table: CLUBS");
        }
        try {
            stmt.close();
        } catch (SQLException ex) {
            Start.createLog(ex, "An Internal Communication Error Occurred With the Database");
        }
        return null;
    }
    
    public ResultSet readMeetingsTable(String clubName){
        clubName+="Meetings";
        try {
            stmt=conn.createStatement();
            return stmt.executeQuery("SELECT * FROM "+DEFAULT_SCHEMA+"."+clubName);
        } catch (SQLException ex) {
            Start.createLog(ex, "Unable to read from table: "+clubName);
        }
        try {
            stmt.close();
        } catch (SQLException ex) {
            Start.createLog(ex, "An Internal Communication Error Occurred With the Database");
        }
        return null;
    }
    
    public ResultSet readStudentsTable(String clubName){
        clubName+="Students";
        try {
            stmt=conn.createStatement();
            return stmt.executeQuery("SELECT * FROM "+DEFAULT_SCHEMA+"."+clubName);
        } catch (SQLException ex) {
            Start.createLog(ex, "Unable to read from table: "+clubName);
        }
        try {
            stmt.close();
        } catch (SQLException ex) {
            Start.createLog(ex, "An Internal Communication Error Occurred With the Database");
        }
        return null;
    }
   
    private void writeTable(String tblName, String value, String col) throws SQLException{
        stmt=conn.createStatement();
        stmt.executeUpdate("INSERT INTO "+DEFAULT_SCHEMA+"."+tblName+" ("+col+") VALUES (\'"+value+"\')");
        stmt.close();
    }
}
