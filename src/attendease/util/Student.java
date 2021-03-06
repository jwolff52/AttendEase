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

package attendease.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author timothy.chandler
 */
public class Student implements Comparable{
    private final ArrayList<String> groupIDS;
    private final String name;
    private final String group;
    private final int ID;
    private int points;
    private final ArrayList<AttendedMeeting> meetingsAttended;
    
    public Student(String n,String g,int id){
        groupIDS=new ArrayList<String>();
        name=n;
        group=g;
        ID=id;
        points=0;
        meetingsAttended=new ArrayList<AttendedMeeting>();
    }
    
    public Student(String n, String g, int id, int p){
        groupIDS=new ArrayList<String>();
        name=n;
        group=g;
        ID=id;
        points=p;
        meetingsAttended=new ArrayList<AttendedMeeting>();
    }
    
    public Student(Object s){
        Student temp=(Student)s;
        groupIDS=temp.getGroupIDS();
        name=temp.getName();
        group=temp.getGroup();
        ID=temp.getID();
        points=temp.getPoints();
        meetingsAttended=temp.getMeetingsAttended();
    }
    
    public void fillMeetingsAttended(){
        ResultSet amrs=Start.d.readMeetingsAttendedTable(name);
        Meeting m;
        try {
            while(amrs.next()){
                m=FrameController.getInv().getGroup(group).getMeetingByIdentifier(amrs.getString("identifier"));
                meetingsAttended.add(new AttendedMeeting(m, amrs.getString("arrivalTime")));
            }
        } catch (SQLException ex) {
            Start.createLog(ex, "An Error occured while retrieving Attended Meetings for student \""+name+"\" in the group \""+group+"\"!");
        }
    }
    
    public String getName() {
        return name;
    }
    
    public int getID() {
        return ID;
    }
    
    public int getPoints() {
        return points;
    }
    
    public ArrayList<AttendedMeeting> getMeetingsAttended(){
        return meetingsAttended;
    }
    
    public String getMeetingsAttendedAsString(){
        String ma="";
        for (AttendedMeeting attendedMeeting : meetingsAttended) {
            ma+=attendedMeeting.getIdentifier()+"~";
        }
        return ma;
    }
    
    public AttendedMeeting getAttendedMeeting(Meeting m){
        for (AttendedMeeting attendedMeeting : meetingsAttended) {
            if(m.getIdentifier().equals(attendedMeeting.getIdentifier())){
                return attendedMeeting;
            }
        }
        return null;
    }
    
    public void addAttendedMeeting(AttendedMeeting am){
            meetingsAttended.add(am);
            Start.d.addAttendedMeeting(name, FrameController.getGroup(FrameController.getSmgp().getCurrentGroupName()).getIdentifier(), am.getIdentifier(), am.getArrivalTime());
    }
    
    public void addPoints(int p){
        points+=p;
    }

    public String[] getValues() {
        return new String[]{ID+"",name,points+""};
    }
    
    @Override
    public String toString(){
        return name+"\t"+ID+"\t"+points;
    }
    
    @Override
    public boolean equals(Object other){
        if(other instanceof Student){
            return this.getID()==new Student(other).getID();
        }else{
            return false;
        }
    }
    
    @Override
    public int compareTo(Object other){
        return this.name.compareTo(((Student)other).getName());
    }
    
    public String getGroup() {
        return group;
    }
    
    public ArrayList<String> getGroupIDS(){
        return groupIDS;
    }
}
