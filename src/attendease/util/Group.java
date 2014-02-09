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
public class Group {
    private final ArrayList<Meeting> meats;
    private final ArrayList<Student> stews;
    
    private final String name;
    private EFile excel;
    
    private final boolean usePoints;
    
    public Group(String n){
        name=n;
        meats=new ArrayList<>();
        stews=new ArrayList<>();
        excel=new EFile("");
        usePoints=false;
    }
    
    public Group(String n, ArrayList<Meeting> m, ArrayList<Student>s, String path, boolean p){
        name=n;
        meats=m;
        stews=s;
        if(!(path==null||path.equals(""))){
            excel=new EFile(path);
        }else{
            excel=new EFile("");
        }
        usePoints=p;
    }
    
    public void addMeeting(Meeting m){
        meats.add(m);
    }
    
    public String getName(){
        return name;
    }
    
    public Meeting getMeeting(int m){
        return meats.get(m);
    }
    
    public Student getStudent(int s){
        return stews.get(s);
    }

    public String getEPath(){
        return excel.getPath();
    }
    
    public void setEPath(String ePath){
        excel=new EFile(ePath);
    }
    
    public boolean usesPoints() {
        return usePoints;
    }
}
