/************************************************************************
    AttendEase - A simple, point-and-click attendance program.
    Copyright (C) 2013-2014  James Wolff, Timothy Chandler, Sterling Long, Cole Howe

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*************************************************************************/

package attendease.database;

import attendease.util.FrameController;
import attendease.util.Group;
import attendease.util.MiscUtils;
import attendease.util.Start;
import attendease.util.Student;
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
    
    public Database() throws InstantiationException, ClassNotFoundException, IllegalAccessException{
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
//        Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
        try{
            conn=DriverManager.getConnection("jdbc:derby:"+System.getenv("ATTENDEASE_HOME")+"/Database/AttendEase;");
//            conn=DriverManager.getConnection("jdbc:derby://localhost:1527/AttendEase;");
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
                Start.updateSplashString("Creating new Database");
                try {
                    conn=DriverManager.getConnection("jdbc:derby:"+System.getenv("ATTENDEASE_HOME")+"/Database/AttendEase;create=true;");
//                    conn=DriverManager.getConnection("jdbc:derby://localhost:1527/AttendEase;");
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
                stmt2.executeUpdate("CREATE TABLE "+DEFAULT_SCHEMA+".Clubs(groupID varchar(3), clubName varchar(25), ePath varchar(255), points BOOLEAN, PRIMARY KEY (clubName))");
            }catch(SQLException ex){
                Start.createLog(ex, "Unable to create table Clubs!");
            }
        }
    }
    
    /*
     * returns false if club exsists, true otherwise
     */
    public boolean addGroup(Group g, String ePath, boolean points){
        Statement stmt;
        Statement stmt1;
        Statement stmt2;
        Statement stmt3;
        try {
            stmt=conn.createStatement();
            stmt.closeOnCompletion();
            stmt.executeUpdate("INSERT INTO "+DEFAULT_SCHEMA+".CLUBS VALUES (\'"+g.getIdentifier()+"\',\'"+MiscUtils.replaceSpacesForDatabase(g.getName())+"\',\'"+ePath+"\',"+points+")");
            stmt.close();
        } catch (SQLException ex) {
            Start.createLog(ex, "Error creating Club!");
        }
        try{
            stmt1=conn.createStatement();
            stmt1.closeOnCompletion();
            stmt1.executeQuery("SELECT * FROM "+DEFAULT_SCHEMA+"."+MiscUtils.replaceSpacesForDatabase(g.getName())+"Meetings");
            return false;
        }catch(SQLException e){
            String ss="CREATE TABLE "+DEFAULT_SCHEMA+"."+MiscUtils.replaceSpacesForDatabase(g.getName())+"Students(ID INTEGER, name varchar(50), points INTEGER, PRIMARY KEY(ID))";
            String ms="CREATE TABLE "+DEFAULT_SCHEMA+"."+MiscUtils.replaceSpacesForDatabase(g.getName())+"Meetings(identifier varchar(3), name varchar(255), date varchar(25), startTime varchar(10), endTime varchar(10), attendance INTEGER, pointsGiven INTEGER, pointsRequired INTEGER, latePoints INTEGER, meetingHeld BOOLEAN, PRIMARY KEY(name))";
            try {
                stmt2=conn.createStatement();
                stmt2.closeOnCompletion();
                stmt2.executeUpdate(ms);
            } catch (SQLException ex) {
                Start.createLog(ex, "Failed to create table "+g.getName()+"Meetings");
            }
            try {
                stmt3=conn.createStatement();
                stmt3.closeOnCompletion();
                stmt3.executeUpdate(ss);
            } catch (SQLException ex) {
                Start.createLog(ex, "Failed to create table "+g.getName()+"Students");
            }
        }
        return true;
    }
    
    public void addMeeting(String clubName, String[] values){
        clubName=MiscUtils.replaceSpacesForDatabase(clubName)+"Meetings";
        Statement stmt;
        try {
            stmt=conn.createStatement();
            stmt.executeUpdate("INSERT INTO "+DEFAULT_SCHEMA+"."+clubName+
                    " VALUES (\'"+values[0]+"\',\'"+values[1]+"\',\'"+values[2]+"\',\'"
                    +values[3]+"\',\'"+values[4]+"\',"+values[5]+","+values[6]+","+values[7]+","+values[8]+","+values[9]+")");
            stmt.close();
        } catch(SQLException ex) {
            Start.createLog(ex, "A Database Error Occurred");
        }
    }
    
    public boolean addStudents(String clubName, ArrayList<Student> addedStudents){
        boolean successful=true;
        for(Student newStudent:addedStudents){
            boolean temp=addStudent(clubName,newStudent);
            if(successful){
                successful=temp;
            }
        }
        return successful;
    }
    
    public boolean addStudent(String clubName, Student s){
        clubName=MiscUtils.replaceSpacesForDatabase(clubName)+"Students";
        Statement stmt;
        try {
            stmt=conn.createStatement();
            stmt.executeUpdate("INSERT INTO "+DEFAULT_SCHEMA+"."+clubName+
                    " VALUES ("+s.getID()+",\'"+s.getName()+"\',"+s.getPoints()+")");
        } catch (SQLException ex) {
            Start.createLog(ex, "Failed to add "+s.getName()+" to "+s.getGroup());
            return false;
        }
        Statement stmt1;
        try {
            stmt1=conn.createStatement();
            stmt1.executeUpdate("CREATE TABLE "+DEFAULT_SCHEMA+"."+MiscUtils.replaceCommas(MiscUtils.replaceSpacesForDatabase(s.getName()))+"AttendedMeetings(groupID varchar(3), meetingIdentifier varchar(3), arrivalTime varchar(10), PRIMARY KEY(meetingIdentifier))");
        } catch (SQLException ex) {
            Start.createLog(ex, "Failed to create table "+s.getName()+"AttendedMeetings");
            return false;
        }
        return true;
    }
    
    public void addAttendedMeeting(String student, String groupIdentifier, String meetingIdentifier, String arrivalTime){
        student=MiscUtils.replaceCommas(MiscUtils.replaceSpacesForDatabase(student))+"AttendedMeetings";
        Statement stmt;
        try {
            stmt=conn.createStatement();
            stmt.executeUpdate("INSERT INTO "+DEFAULT_SCHEMA+"."+student+
                    " VALUES (\'"+groupIdentifier+"\',\'"+meetingIdentifier+"\',\'"+arrivalTime+"\')");
        } catch (SQLException ex) {
            Start.createLog(ex, "Failed to add the Meeting with the identifier \""+meetingIdentifier+"\" to "+student);
        }
    }
    
    public void editGroup(String oldName, Group g, String ePath, boolean points, ArrayList<Student> stews){
        Statement stmt;
        Statement stmt1;
        Statement stmt2;
        try {
            stmt=conn.createStatement();
            stmt.closeOnCompletion();
            stmt.executeUpdate("UPDATE "+DEFAULT_SCHEMA+".Clubs"+
                    " SET groupID=\'"+g.getIdentifier()+"\',"
                    + "clubName=\'"+g.getName()+"\',"
                    + "ePath=\'"+ePath+"\',"
                    + "points="+points
                    + " WHERE clubName=\'"+oldName+"\'");
        } catch (SQLException ex) {
            Start.createLog(ex, "Error editing Club!");
        }
        try {
            stmt1=conn.createStatement();
            stmt1.closeOnCompletion();
            stmt1.executeUpdate("RENAME TABLE "+DEFAULT_SCHEMA+"."+(oldName+"Students")+" TO "+(g.getName()+"Students"));
        } catch (SQLException ex) {
            Start.createLog(ex, "Error editing Club!");
        }
        try {
            stmt2=conn.createStatement();
            stmt2.closeOnCompletion();
            stmt2.executeUpdate("RENAME TABLE "+DEFAULT_SCHEMA+"."+(oldName+"Meetings")+" TO "+(g.getName()+"Meetings"));
        } catch (SQLException ex) {
            Start.createLog(ex, "Error editing Club!");
        }
    }
    
    public void editMeeting(String clubName, String[] values){
        clubName=MiscUtils.replaceSpacesForDatabase(clubName)+"Meetings";
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
                        + "pointsGiven="+values[6]+","
                        + "pointsRequired="+values[7]+","
                        + "latePoints="+values[8]+","
                        + "meetingHeld="+values[9]
                        + " WHERE identifier=\'"+values[0]+"\'");
            stmt.close();
        } catch(SQLException ex) {
            Start.createLog(ex, "A Database Error Occurred");
        }
    }
    
    public void editStudents(String clubName, ArrayList<Student> stews){
        for (Student student : stews) {
            editStudent(clubName, student);
        }
    }
    
    public void editStudent(String clubName, String[] values){
        clubName=MiscUtils.replaceSpacesForDatabase(clubName)+"Students";
        Statement stmt;
        try {
            stmt=conn.createStatement();
            stmt.executeUpdate("UPDATE "+DEFAULT_SCHEMA+"."+clubName+
                    " SET ID="+values[0]+","
                    + "name=\'"+values[1]+"\',"
                    + "POINTS="+values[2]
                    + " WHERE ID="+values[0]);
            stmt.close();
        } catch(SQLException ex) {
            Start.createLog(ex, "A Database Error Occurred");
        }
    }
    
    public void editStudent(String clubName, Student s){
        clubName=MiscUtils.replaceSpacesForDatabase(clubName)+"Students";
        Statement stmt;
        try {
            stmt=conn.createStatement();
            stmt.executeUpdate("UPDATE "+DEFAULT_SCHEMA+"."+clubName+
                    " SET ID="+s.getID()+","
                    + "name=\'"+s.getName()+"\',"
                    + "POINTS="+s.getPoints()
                    + " WHERE ID=\'"+s.getID()+"\'");
            stmt.close();
        } catch(SQLException ex) {
            Start.createLog(ex, "A Database Error Occurred");
        }
    }
    
    public void editAttendedMeeting(String student, String groupIdentifier, String meetingIdentifier, String arrivalTime){
        student=MiscUtils.replaceCommas(MiscUtils.replaceSpacesForDatabase(student))+"AttendedMeetings";
        Statement stmt;
        try {
            stmt=conn.createStatement();
            stmt.executeUpdate("UPDATE "+DEFAULT_SCHEMA+"."+student+
                    " SET groupIdentifier=\'"+groupIdentifier+"\',"
                    + "meetingIdentifier=\'"+meetingIdentifier+"\',"
                    + "arrivalTime=\'"+arrivalTime+"\'"
                    + " WHERE meetingIdentifier=\'"+meetingIdentifier+"\' AND \'groupIdentifier="+groupIdentifier+"\'");
        } catch (SQLException ex) {
            Start.createLog(ex, "Failed to edit meeting with identifier \""+meetingIdentifier+"\" in "+student);
        }
    }
    
    public void deleteGroup(String clubName){
        Statement stmt;
        Statement stmt1;
        if(!(FrameController.getInv().getGroups().size()<=1)){
            try {
                stmt1=conn.createStatement();
                ResultSet rs=stmt1.executeQuery("SELECT * FROM "+DEFAULT_SCHEMA+"."+clubName+"Students");
                while(rs.next()){
                    deleteTable(MiscUtils.replaceCommas(MiscUtils.replaceSpacesForDatabase(rs.getString("NAME")))+"AttendedMeetings");
                }
            } catch (SQLException ex) {
                Start.createLog(ex, "Unable to delete some database tables");
            }
        }
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
        clubName=MiscUtils.replaceSpacesForDatabase(clubName)+"Meetings";
        try {
            stmt=conn.createStatement();
            stmt.closeOnCompletion();
            stmt.executeUpdate("DELETE FROM "+DEFAULT_SCHEMA+"."+clubName+" WHERE name=\'"+meetingName+"\'");
        } catch (SQLException ex) {
            Start.createLog(ex, "Unable to delete \""+meetingName+"\"");
        }
    }
    
    public void deleteStudents(String clubName, ArrayList<Student> students){
        for (Student student : students) {
            deleteStudent(clubName, student);
        }
    }
    
    public void deleteStudent(String clubName, Student s){
        Statement stmt;
        String tableName=clubName+"Students";
        try {
            stmt=conn.createStatement();
            stmt.closeOnCompletion();
            stmt.executeUpdate("DELETE FROM "+DEFAULT_SCHEMA+"."+tableName+" WHERE ID="+s.getID()+"");
            deleteTable(MiscUtils.replaceCommas(MiscUtils.replaceSpacesForDatabase(s.getName()))+"AttendedMeetings");
        } catch (SQLException ex) {
            Start.createLog(ex, "Unable to delete \""+s.getName()+"\" from \""+clubName+"\"");
        }
    }
    
    public void deleteStudentMeetingAttended(String student, String identifier){
        Statement stmt;
        String tableName=student+"AttendedMeetings";
        try {
            stmt=conn.createStatement();
            stmt.closeOnCompletion();
            stmt.executeUpdate("DELETE FROM "+DEFAULT_SCHEMA+"."+tableName+" WHERE identifier="+identifier);
        } catch (SQLException ex) {
            Start.createLog(ex, "Unable to delete the meeting with identifier \""+identifier+"\" from \""+tableName+"\"");
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
        clubName=MiscUtils.replaceSpacesForDatabase(clubName)+"Meetings";
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
        clubName=MiscUtils.replaceSpacesForDatabase(clubName)+"Students";
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
    
    public ResultSet readMeetingsAttendedTable(String student){
        student=MiscUtils.replaceCommas(MiscUtils.replaceSpacesForDatabase(student))+"AttendedMeetings";
        ResultSet rs=null;
        Statement stmt;
        try {
            stmt=conn.createStatement();
            stmt.closeOnCompletion();
            rs=stmt.executeQuery("SELECT * FROM "+DEFAULT_SCHEMA+"."+student);
        } catch (SQLException ex) {
            Start.createLog(ex, "Unable to read from table: "+student);
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
