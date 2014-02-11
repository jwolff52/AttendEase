/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package attendease.util;

/**
 *
 * @author timothy.chandler
 */
public class Student {
    private final String name;
    private final int ID;
    private final int points;
    
    public Student(String n,int id){
        name=n;
        ID=id;
        points=0;
    }
    
    public Student(String n,int id, int p){
        name=n;
        ID=id;
        points=p;
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
}
