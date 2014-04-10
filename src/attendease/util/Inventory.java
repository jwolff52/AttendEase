/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package attendease.util;

import java.util.ArrayList;

/**
 *
 * @author timothy.chandler
 */
public class Inventory {
    private static ArrayList<Group> groups;
    private static ArrayList<String[]> meetingIdentifiers;
    
    public Inventory(){
        groups=new ArrayList<>();
        meetingIdentifiers=new ArrayList<>();
    }
    
    public Inventory(ArrayList<Group> g){
        groups=g;
        meetingIdentifiers=new ArrayList<>();
        fillMeetingIdentifiers();
    }
    
    public void add(Group g){
        groups.add(g);
    }
    
    public void delete(Group g){
        groups.remove(g);
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
    
    public void fillMeetingIdentifiers(){
        for (Group group : groups) {
            for (Meeting meeting : group.getMeetings()) {
                meetingIdentifiers.add(new String[]{meeting.getName(),meeting.getIdentifier()});
            }
        }
    }
    
    public void addIdentifier(String name, String i){
        meetingIdentifiers.add(new String[]{name,i});
    }
    
    public void removeIdentifier(String name, String i){
        meetingIdentifiers.remove(new String[]{name,i});
    }
    
    public void removeIdentifier(int i){
        meetingIdentifiers.remove(i);
    }
    
    public String getIdentifier(int i){
        return meetingIdentifiers.get(i)[1];
    }
    
    public String getIdentifier(String i){
        for (String[] s : meetingIdentifiers) {
            if(i.equals(s[1])){
                return s[1];
            }
        }
        return null;
    }
    
    public String getIdentifier(String name, int dnu){
        for (String[] s : meetingIdentifiers) {
            if(name.equals(s[0])){
                return s[1];
            }
        }
        return null;
    }
    
    public boolean identifierExisits(String i){
        return getIdentifier(i)!=null;
    }
}
