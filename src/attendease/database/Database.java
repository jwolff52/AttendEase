package attendease.database;

import attendease.util.Start;
import attendease.util.Student;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
    private static final String DEFAULT_SCHEMA="ATTENDEASE";
    
    public Database() throws InstantiationException, ClassNotFoundException, IllegalAccessException, UnsupportedEncodingException{
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
        String loc=URLDecoder.decode(Start.class.getProtectionDomain().getCodeSource().getLocation().getPath(), "UTF-8");
        loc=loc.substring(1, loc.lastIndexOf("/"));
        try{
            conn=DriverManager.getConnection("jdbc:derby:"+loc+"/Database/AttendEase;");
        }catch(SQLException e){
            if(e instanceof SQLException){
                SQLException f=(SQLException) e;
                while(f.getNextException()!=null){
                    f=f.getNextException();
                }
                if(f.getSQLState().equals("XSDB6")){
                    Start.removeSplash();
                    javax.swing.JOptionPane.showMessageDialog(null, "The Program is already running in another instance!");
                    System.exit(0);
                }
                try {
                    System.out.println("\n\n\n\n\n"+loc);
                    conn=DriverManager.getConnection("jdbc:derby:"+loc+"/Database/AttendEase;create=true;");
                    System.out.println("\n\n\n\n\n"+loc);
                    Start.firstRunSetup();
                } catch (SQLException ex) {
                    Start.createLog(ex, "An Internal Communication Error Occurred With the Database");
                }
            }else{
                Start.createLog(e, "An Internal Communication Error Occurred With the Database");
            }
        }
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
    public boolean addGroup(String clubName, String ePath, boolean points){
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
            String ss="CREATE TABLE "+DEFAULT_SCHEMA+"."+clubName+"Students(ID INTEGER, name varchar(50), meetings varchar(225), points INTEGER, PRIMARY KEY(ID))";
            String ms="CREATE TABLE "+DEFAULT_SCHEMA+"."+clubName+"Meetings(identifier varchar(3), name varchar(255), date varchar(25), startTime varchar(10), endTime varchar(10), attendance INTEGER, reocurringDays INTEGER, pointsGiven INTEGER, pointsRequired INTEGER, latePoints INTEGER, meetingHeld BOOLEAN, PRIMARY KEY(name))";
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
                    +values[3]+"\',"+values[4]+","+values[5]+","+values[6]+","+values[7]+","+values[8]+","+values[9]+")");
            stmt.close();
        } catch(SQLException ex) {
            Start.createLog(ex, "A Database Error Occurred");
        }
    }
    
    public void addStudents(String clubName, ArrayList<Student> addedStudents){
        for(Student newStudent:addedStudents){
            addStudent(clubName,newStudent);
        }
    }
    
    public void addStudent(String clubName, Student s){
        clubName+="Students";
        Statement stmt;
        try {
            stmt=conn.createStatement();
            stmt.executeUpdate("INSERT INTO "+DEFAULT_SCHEMA+"."+clubName+
                    " VALUES ("+s.getID()+",\'"+s.getName()+"\',"+s.getMeetingsAttended()+","+s.getPoints()+")");
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void editGroup(String oldName, String clubName, String ePath, boolean points){
        Statement stmt;
        Statement stmt1;
        Statement stmt2;
        try {
            stmt=conn.createStatement();
            stmt.closeOnCompletion();
            stmt.executeUpdate("UPDATE "+DEFAULT_SCHEMA+".Clubs"+
                    " SET clubName=\'"+clubName+"\',"
                    + "ePath=\'"+ePath+"\',"
                    + "points="+points
                    + " WHERE clubName=\'"+oldName+"\'");
        } catch (SQLException ex) {
            Start.createLog(ex, "Error editing Club!");
        }
        try {
            stmt1=conn.createStatement();
            stmt1.closeOnCompletion();
            stmt1.executeUpdate("RENAME TABLE "+DEFAULT_SCHEMA+"."+(oldName+"Students")+" TO "+(clubName+"Students"));
        } catch (SQLException ex) {
            Start.createLog(ex, "Error editing Club!");
        }
        try {
            stmt2=conn.createStatement();
            stmt2.closeOnCompletion();
            stmt2.executeUpdate("RENAME TABLE "+DEFAULT_SCHEMA+"."+(oldName+"Meetings")+" TO "+(clubName+"Meetings"));
        } catch (SQLException ex) {
            Start.createLog(ex, "Error editing Club!");
        }
    }
    
    public void editMeeting(String clubName, String[] values){
        clubName+="Meetings";
        Statement stmt;
        try {
            stmt=conn.createStatement();
            stmt.executeUpdate("UPDATE "+DEFAULT_SCHEMA+"."+clubName+
                    " SET identifier=\'"+values[0]+"\',"
                        + "name=\'"+values[1]+"\',"
                        + "date=\'"+values[2]+"\',"
                        + "startTime=\'"+values[3]+"\',"
                        + "endTime=\'"+values[4]+"\',"
                        + "attendance="+values[5]+","
                        + "reocurringDays="+values[6]+","
                        + "pointsGiven="+values[7]+","
                        + "pointsRequired="+values[8]+","
                        + "latePoints="+values[9]+","
                        + "meetingHeld="+values[10]
                        + " WHERE identifier=\'"+values[0]+"\'");
            stmt.close();
        } catch(SQLException ex) {
            Start.createLog(ex, "A Database Error Occurred");
        }
    }
    
    public void editStudent(String clubName, String[] values){
        clubName+="Students";
        Statement stmt;
        try {
            stmt=conn.createStatement();
            stmt.executeUpdate("UPDATE "+DEFAULT_SCHEMA+"."+clubName+
                    " SET ID="+values[0]+","
                    + "name=\'"+values[1]+"\',"
                    + "meetings=\'"+values[2]+"\',"
                    + "POINTS="+values[3]
                    + " WHERE ID=\'"+values[0]+"\'");
            stmt.close();
        } catch(SQLException ex) {
            Start.createLog(ex, "A Database Error Occurred");
        }
    }
    
    public void deleteGroup(String clubName){
        Statement stmt;
        try {
            stmt=conn.createStatement();
            stmt.closeOnCompletion();
            stmt.executeUpdate("DELETE FROM "+DEFAULT_SCHEMA+".CLUBS WHERE clubName=\'"+clubName+"\'");
            deleteTable(clubName+"Meetings");
            deleteTable(clubName+"Students");
        } catch (SQLException ex) {
            Start.createLog(ex, "Unable to delete \""+clubName+"\"");
        }
    }
    
    public void deleteMeeting(String clubName, String meetingName) {
        Statement stmt;
        clubName+="Meetings";
        try {
            stmt=conn.createStatement();
            stmt.closeOnCompletion();
            stmt.executeUpdate("DELETE FROM "+DEFAULT_SCHEMA+"."+clubName+" WHERE name=\'"+meetingName+"\'");
        } catch (SQLException ex) {
            Start.createLog(ex, "Unable to delete \""+meetingName+"\"");
        }
    }
    
    public void deleteStudent(String clubName, Student s){
        Statement stmt;
        String tableName=clubName+"Students";
        try {
            stmt=conn.createStatement();
            stmt.closeOnCompletion();
            stmt.executeUpdate("DELETE FROM "+DEFAULT_SCHEMA+"."+tableName+" WHERE ID="+s.getID()+"");
        } catch (SQLException ex) {
            Start.createLog(ex, "Unable to delete \""+s.getName()+"\" from \""+clubName+"\"");
        }
    }
    
    public void deleteStudents(String clubName, ArrayList<Student> students){
        for (Student student : students) {
            deleteStudent(clubName, student);
        }
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
    
    public ResultSet readGroupsTable(){
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
