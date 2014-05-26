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
public class Inventory {
    private static ArrayList<Group> groups;
    private static ArrayList<String[]> meetingIdentifiers;
    private static ArrayList<String[]> groupIdentifiers;
    
    public Inventory(){
        groups=new ArrayList<Group>();
        meetingIdentifiers=new ArrayList<String[]>();
        groupIdentifiers=new ArrayList<String[]>();
        fillMeetingIdentifiers();
        fillGroupIdentifiers();
    }
    
    public Inventory(ArrayList<Group> g){
        groups=g;
        meetingIdentifiers=new ArrayList<String[]>();
        groupIdentifiers=new ArrayList<String[]>();
        fillMeetingIdentifiers();
        fillGroupIdentifiers();
    }
    
    public void addGroup(Group g){
        groups.add(g);
    }
    
    public Group deleteGroup(Group g){
        groups.remove(g);
        return g;
    }
    
    public Group getGroup(int i){
        return groups.get(i);
    }
    
    public Group getGroup(String name){
        for(int x=0;x<groups.size();x++){
            if(name.equalsIgnoreCase(groups.get(x).getName())){
                return groups.get(x);
            }
        }
        return null;
    }
    
    public ArrayList<Group> getGroups(){
        return groups;
    }
    
    private void fillMeetingIdentifiers(){
        for (Group group : groups) {
            for (Meeting meeting : group.getMeetings()) {
                meetingIdentifiers.add(new String[]{meeting.getName(),meeting.getIdentifier()});
            }
        }
    }
    
    private void fillGroupIdentifiers(){
        for (Group group : groups) {
            groupIdentifiers.add(new String[]{group.getName(), group.getIdentifier()});
        }
    }
    
    public void addMeetingIdentifier(String name, String i){
        meetingIdentifiers.add(new String[]{name,i});
    }
    
    public void removeMeetingIdentifier(String name, String i){
        meetingIdentifiers.remove(new String[]{name,i});
    }
    
    public void removeMeetingIdentifier(int i){
        meetingIdentifiers.remove(i);
    }
    
    public String getMeetingIdentifier(int i){
        return meetingIdentifiers.get(i)[1];
    }
    
    public String getMeetingIdentifier(String i){
        for (String[] s : meetingIdentifiers) {
            if(i.equals(s[1])){
                return s[1];
            }
        }
        return null;
    }
    
    public String getMeetingIdentifierByName(String name){
        for (String[] s : meetingIdentifiers) {
            if(name.equals(s[0])){
                return s[1];
            }
        }
        return null;
    }
    
    public boolean meetingIdentifierExisits(String i){
        return getMeetingIdentifier(i)!=null;
    }
    
    public void addGroupIdentifier(String name, String i){
        groupIdentifiers.add(new String[]{name,i});
    }
    
    public void removeGroupIdentifier(String name, String i){
        groupIdentifiers.remove(new String[]{name,i});
    }
    
    public void removeGroupIdentifier(int i){
        groupIdentifiers.remove(i);
    }
    
    public String getgroupIdentifier(int i){
        return groupIdentifiers.get(i)[1];
    }
    
    public String getGroupIdentifier(String i){
        for (String[] s : groupIdentifiers) {
            if(i.equals(s[1])){
                return s[1];
            }
        }
        return null;
    }
    
    public String getGroupIdentifierByName(String name){
        for (String[] s : groupIdentifiers) {
            if(name.equals(s[0])){
                return s[1];
            }
        }
        return null;
    }
    
    public boolean groupIdentifierExisits(String i){
        return getGroupIdentifier(i)!=null;
    }
}
