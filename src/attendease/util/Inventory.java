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
    
    public Inventory(){
        groups=new ArrayList<>();
    }
    
    public Inventory(ArrayList<Group> g){
        groups=g;
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
}
