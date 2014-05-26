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
 * @author James
 * @date Apr 21, 2014
 */
public class AttendedMeeting extends Meeting{
    private final String arrivalTime;
    
    public AttendedMeeting(String i, String d, String sTime, String eTime, int a, int g, int r, int l, boolean m, String aM){
        super(i,d,sTime,eTime,a,g,r,l,m);
        arrivalTime=aM;
    }
    
    public AttendedMeeting(Meeting m, String aM){
        super(m);
        arrivalTime=aM;
    }
    
    public AttendedMeeting(String[] v){
        super(v);
        arrivalTime=v[v.length-1];
    }
    
    public String getArrivalTime(){
        return arrivalTime;
    }
    
    public ArrayList<Student> getAttendedStudents(String group){
        ArrayList<Student> groupStews=FrameController.getInv().getGroup(group).getStudents();
        ArrayList<Student> attendedStews=new ArrayList<Student>();
        for (Student student : groupStews) {
            for (AttendedMeeting m: student.getMeetingsAttended()) {
                if(m.getIdentifier().equals(this.getIdentifier())){
                    attendedStews.add(student);
                    break;
                }
            }
        }
        return attendedStews;
    }
    
    @Override
    public String[] getValues(){
        return new String[]{super.getIdentifier(),super.getName(),super.getDate(),super.getStartTime(),super.getEndTime(),super.getgPoints()+"",super.getrPoints()+"",super.getlPoints()+"",super.isMeatHeld()+"", arrivalTime};
    }
}
