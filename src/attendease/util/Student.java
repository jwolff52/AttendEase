/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package attendease.util;

/**
 *
 * @author timothy.chandler
 */
public class Student implements Comparable{
    private final String name;
    private final int ID;
    private int points;
    private int meetingsAttended;
    
    public Student(String n,int id){
        name=n;
        ID=id;
        points=0;
        meetingsAttended=0;
    }
    
    public Student(String n, int id, int p, int ma){
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
    
    public int getMeetingsAttended(){
        return meetingsAttended;
    }
    
    public void addPoints(int p){
        points+=p;
    }
    
    public void addMeetingsAttended(int i){
        meetingsAttended+=i;
    }
    
    public void incrementMeetingsAttended(){
        addMeetingsAttended(1);
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
