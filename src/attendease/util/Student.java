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

/**
 *
 * @author timothy.chandler
 */
public class Student implements Comparable{
    private final String name;
    private final int ID;
    private int points;
    private String meetingsAttended;
    
    public Student(String n,int id){
        name=n;
        ID=id;
        points=0;
        meetingsAttended="";
    }
    
    public Student(String n, int id, int p, String ma){
        name=n;
        ID=id;
        points=p;
        meetingsAttended=ma;
    }
    
    public Student(Object s){
        Student temp=(Student)s;
        name=temp.getName();
        ID=temp.getID();
        points=temp.getPoints();
        meetingsAttended=temp.getMeetingsAttended();
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
    
    public String getMeetingsAttended(){
        return meetingsAttended;
    }
    
    public void addPoints(int p){
        points+=p;
    }
    
    public void addMeetingsAttended(String i){
        meetingsAttended+=i;
    }
    
    public int[] getMeetingsAttendedAsIntArray(){
        int[] ia=new int[getMeetingsAttendedAsInt()];
        char[] ca=meetingsAttended.toCharArray();
        String temp="";
        int count=0;
        for(int i=0;i<ca.length;i++){
            if(ca[i]!=','){
                temp+=ca[i];
            }else{
                ia[count]=MiscUtils.thirtySixTo10(temp);
                temp="";
                count++;
            }
        }
        return ia;
    }
    
    private int getMeetingsAttendedAsInt(){
        int i=0;
        for (char c : meetingsAttended.toCharArray()) {
            if(c==','){
                i++;
            }
        }
        return i;
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
}
