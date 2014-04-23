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

import java.util.ArrayList;

/**
 *
 * @author timothy.chandler
 */
public class Student implements Comparable{
    private final String groupID;
    private final String name;
    private final String group;
    private final int ID;
    private int points;
    private ArrayList<AttendedMeeting> meetingsAttended;
    
    public Student(String gID, String n,String g,int id){
        groupID=gID;
        name=n;
        group=g;
        ID=id;
        points=0;
        meetingsAttended=null;
    }
    
    public Student(String gID, String n, String g, int id, int p, String ma){
        groupID=gID;
        name=n;
        group=g;
        ID=id;
        points=p;
        fillMeetingsAttended(ma);
    }
    
    public Student(Object s){
        Student temp=(Student)s;
        groupID=temp.getGroupID();
        name=temp.getName();
        group=temp.getGroup();
        ID=temp.getID();
        points=temp.getPoints();
        meetingsAttended=temp.getMeetingsAttended();
    }
    
    private void fillMeetingsAttended(String ma){
        String temp;
        String identifier;
        String timeDiff;
        int[] delineators=MiscUtils.getDelineatorIndicies(ma, '~');
        int prev=0;
        for(int i=0;i<delineators.length; i++) {
            temp=ma.substring(prev, delineators[i]);
            try{
                timeDiff=temp.substring(temp.lastIndexOf('+'));
                identifier=temp.substring(0,temp.lastIndexOf('+'));
            }catch(StringIndexOutOfBoundsException e){
                timeDiff=temp.substring(temp.lastIndexOf('-'));
                identifier=temp.substring(0,temp.lastIndexOf('-'));
            }
            for (Meeting m : FrameController.getInv().getGroup(group).getMeetings()) {
                if(identifier.equals(m.getIdentifier())){
                    meetingsAttended.add(new AttendedMeeting(m, timeDiff));
                    break;
                }
            }
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
    }
    
    public void addPoints(int p){
        points+=p;
    }

    public String[] getValues() {
        return new String[]{ID+"",name,"",points+""};
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
<<<<<<< HEAD
=======
    
    public String getGroupID(){
        return groupID;
    }
>>>>>>> Mid-Week Update
}
