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
    private static ArrayList<String> studentColumns;
    private static final String DEFAULT_SCHEMA="ATTENDEASE";
    
    public Database() throws InstantiationException, ClassNotFoundException, IllegalAccessException{
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
        try{
            conn=DriverManager.getConnection("jdbc:derby:AttendEase;");
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
                conn=DriverManager.getConnection("jdbc:derby:AttendEase;create=true;");
            } catch (SQLException ex) {
            Start.createLog(ex, "An Internal Communication Error Occurred With the Database");
            }
        }
        studentColumns=new ArrayList<>();
        studentColumns.add("ID");
        studentColumns.add("NAME");
        studentColumns.add("POINTS");
    }
    
    public void getDb(){
        Statement stmt;
        Statement stmt1;
        Statement stmt2;
        try {
            stmt=conn.createStatement();
            stmt.closeOnCompletion();
            stmt.executeUpdate("CREATE SCHEMA "+DEFAULT_SCHEMA);
        } catch (SQLException ex) {
            if(!ex.getSQLState().equals("X0Y68")){
                Start.createLog(ex, "Unable To Create Necessary Database Resources");
            }
        }
        try{
            stmt1=conn.createStatement();
            stmt1.closeOnCompletion();
            stmt1.executeQuery("SELECT * FROM "+DEFAULT_SCHEMA+".Clubs");
        }catch(SQLException e){
            try{
                stmt2=conn.createStatement();
                stmt2.closeOnCompletion();
                stmt2.executeUpdate("CREATE TABLE "+DEFAULT_SCHEMA+".Clubs(clubName varchar(25), ePath varchar(255), points BOOLEAN, PRIMARY KEY (clubName))");
            }catch(SQLException ex){
                Start.createLog(ex, "Unable to create table Clubs!");
            }
        }
    }
    
    /*
     * returns false if club exsists, true otherwise
     */
    public boolean addClub(String clubName, String ePath, boolean points){
        Statement stmt;
        Statement stmt1;
        Statement stmt2;
        Statement stmt3;
        try {
            stmt=conn.createStatement();
            stmt.closeOnCompletion();
            stmt.executeUpdate("INSERT INTO "+DEFAULT_SCHEMA+".CLUBS VALUES (\'"+clubName+"\',\'"+ePath+"\',"+points+")");
            stmt.close();
        } catch (SQLException ex) {
            Start.createLog(ex, "Error creating Club!");
        }
        try{
            stmt1=conn.createStatement();
            stmt1.closeOnCompletion();
            stmt1.executeQuery("SELECT * FROM "+DEFAULT_SCHEMA+"."+clubName+"Meetings");
            return false;
        }catch(SQLException e){
            String ss="CREATE TABLE "+DEFAULT_SCHEMA+"."+clubName+"Students(ID varchar(10), name varchar(50), meetings varchar(22), points INTEGER, PRIMARY KEY(ID))";
            String ms="CREATE TABLE "+DEFAULT_SCHEMA+"."+clubName+"Meetings(ID varchar(255), date varchar(25), startTime varchar(10), endTime varchar(10), reocurringDays INTEGER, pointsGiven INTEGER, pointsRequired INTEGER, latePoints INTEGER, PRIMARY KEY(ID))";
            try {
                stmt2=conn.createStatement();
                stmt2.closeOnCompletion();
                stmt2.executeUpdate(ms);
            } catch (SQLException ex) {
                Start.createLog(ex, "Failed to create table "+clubName+"Meetings");
            }
            try {
                stmt3=conn.createStatement();
                stmt3.closeOnCompletion();
                stmt3.executeUpdate(ss);
            } catch (SQLException ex) {
                Start.createLog(ex, "Failed to create table "+clubName+"Students");
            }
        }
        return true;
    }
    
    public void addMeeting(String clubName, String[] values){
        clubName+="Meetings";
        Statement stmt;
        try {
            stmt=conn.createStatement();
            stmt.executeUpdate("INSERT INTO "+DEFAULT_SCHEMA+"."+clubName+
                    " VALUES (\'"+values[0]+"\',\'"+values[1]+"\',\'"+values[2]+"\',\'"
                    +values[3]+"\',"+values[4]+","+values[5]+","+values[6]+","+values[7]+")");
            stmt.close();
        } catch(SQLException ex) {
            Start.createLog(ex, "A Database Error Occurred");
        }
    }
    
    public void deleteClub(String clubName){
        Statement stmt;
        try {
            stmt=conn.createStatement();
            stmt.closeOnCompletion();
            stmt.executeUpdate("DELETE FROM "+DEFAULT_SCHEMA+".CLUBS WHERE clubName=\'"+clubName+"\'");
        } catch (SQLException ex) {
            Start.createLog(ex, "Unable to delete \""+clubName+"\"");
        }
        deleteTable(clubName+"Meetings");
        deleteTable(clubName+"Students");
    }
    
    public void deleteTable(String tblName){
        Statement stmt;
        try {
            stmt=conn.createStatement();
            stmt.closeOnCompletion();
            stmt.executeUpdate("DROP TABLE "+DEFAULT_SCHEMA+"."+tblName);
        } catch (SQLException ex) {
            Start.createLog(ex, "Unable to delete table: "+tblName);
        }
    }
    
    public ResultSet readClubsTable(){
        ResultSet rs=null;
        Statement stmt;
        try {
            stmt=conn.createStatement();
            stmt.closeOnCompletion();
            rs=stmt.executeQuery("SELECT * FROM "+DEFAULT_SCHEMA+".CLUBS");
            
        } catch (SQLException ex) {
            Start.createLog(ex, "Unable to read from table: CLUBS");
        }
        return rs;
    }
    
    public ResultSet readMeetingsTable(String clubName){
        clubName+="Meetings";
        ResultSet rs=null;
        Statement stmt;
        try {
            stmt=conn.createStatement();
            stmt.closeOnCompletion();
            rs=stmt.executeQuery("SELECT * FROM "+DEFAULT_SCHEMA+"."+clubName);
        } catch (SQLException ex) {
            Start.createLog(ex, "Unable to read from table: "+clubName);
        }
        return rs;
    }
    
    public ResultSet readStudentsTable(String clubName){
        clubName+="Students";
        ResultSet rs=null;
        Statement stmt;
        try {
            stmt=conn.createStatement();
            stmt.closeOnCompletion();
            rs=stmt.executeQuery("SELECT * FROM "+DEFAULT_SCHEMA+"."+clubName);
        } catch (SQLException ex) {
            Start.createLog(ex, "Unable to read from table: "+clubName);
        }
        return rs;
    }
    
    public void closeConnection(){
        try {
            conn.close();
        } catch (SQLException ex) {
            Start.createLog(ex, "Error closing connecction to database");
        }
    }
}
