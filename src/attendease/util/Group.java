/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package attendease.util;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author timothy.chandler
 */
public class Group {
    private final ArrayList<Meeting> meats;
    private final ArrayList<Student> stews;
    private final ArrayList<String> identifiers;
    
    private final String name;
    private EFile excel;
    private FileUtilities f;
    
    private final boolean usePoints;
    
    public Group(String n){
        name=n;
        meats=new ArrayList<>();
        stews=new ArrayList<>();
        excel=new EFile("");
        usePoints=false;
        identifiers=new ArrayList<>();
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
        identifiers=new ArrayList<>();
        if(meats.size()>0){
            for(Meeting meeting : meats) {
                identifiers.add(meeting.getIdentifier());
            }
        }
    }
    
    public void populateStudents(){
        try {
            f = EFileReader.readFile(excel);
        } catch (IOException ex) {
            Start.createLog(ex, "Error reading Students File located at: "+excel.getPath());
        }
        for (int i=0;i<f.getNames().size();i++) {
            stews.add(new Student(f.getNames().get(i),f.getIdNums().get(i).intValue()));
        }
    }
    
    public void populateStudents(FileUtilities f){
        Student temp;
        for (int i=0;i<f.getNames().size();i++) {
            temp=new Student(f.getNames().get(i),f.getIdNums().get(i).intValue());
            if(!isMember(temp)){
                stews.add(temp);
            }
        }
    }
    
    public void addMeeting(Meeting m){
        meats.add(m);
        identifiers.add(m.getIdentifier());
    }
    
    public void removeMeeting(String name){
        for(int x=0;x<meats.size();x++){
            if(meats.get(x).getName().equals(name)){
                identifiers.remove(meats.remove(x).getIdentifier());
            }
        }
    }
    
    public void removeMeeting(int i){
        identifiers.remove(meats.remove(i).getIdentifier());
    }
    
    public String getName(){
        return name;
    }
    
    public Meeting getMeeting(int m){
        return meats.get(m);
    }
    
    public Meeting getMeeting(String m){
        for(int x=0;x<meats.size();x++){
            if(m.equalsIgnoreCase(meats.get(x).getName())){
                return meats.get(x);
            }
        }
        return null;
    }
    
    public ArrayList<Meeting> getMeetings(){
        return meats;
    }
    
    public ArrayList<String> getIdentifiers(){
        return identifiers;
    }
    
    public Student getStudent(int s){
        return stews.get(s);
    }

    public ArrayList<Student> getStudents() {
        return stews;
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

    public boolean isMember(Student s) {
        for(Student m:stews){
            if(s.equals(m)){
                return true;
            }
        }
        return false;
    }
    
    public ArrayList<Student> getNonMembers(ArrayList<Student> ss){
        ArrayList<Student> temp=new ArrayList<>();
        for(Student s : ss){
            if(!isMember(s)){
                temp.add(s);
            }
        }
        return temp;
    }
    
    public void updateStudents(ArrayList<Student> s, boolean add){
        if(add){
            stews.addAll(s);
        }else{
            stews.removeAll(s);
        }
    }
    
    public void updateStudents(Student s, boolean add){
        if(add){
            stews.add(s);
        }else{
            stews.remove(s);
        }
    }
    
    public ArrayList<Student> getStudentsFromName(ArrayList<String> names){
        ArrayList<Student> temp=new ArrayList<>();
        for (String name : names) {
            for (Student student : stews) {
                if(student.getName().equals(name)){
                    temp.add(student);
                }
            }
        }
        return temp;
    }
    
    public int getNumberOfMeetings(){
        return meats.size();
    }
}
