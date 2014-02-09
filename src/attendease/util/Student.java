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
    private String name;
    private int ID;
    private int points;
    
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

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the ID
     */
    public int getID() {
        return ID;
    }

    /**
     * @return the points
     */
    public int getPoints() {
        return points;
    }
}
