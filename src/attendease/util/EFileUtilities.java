/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package attendease.util;

import java.util.ArrayList;

/**
 *
 * @author james.wolff
 */
public class EFileUtilities {
    private ArrayList<String> names;
    private ArrayList<Double> idNums;
    private ArrayList<Double> points;
    private boolean sameSize;

    public EFileUtilities(){
        names=new ArrayList<>();
        idNums=new ArrayList<>();
        points=new ArrayList<>();
        sameSize=false;
    }
    
    public ArrayList<String> getNames() {
        return names;
    }

    public void setNames(ArrayList<String> names) {
        this.names = names;
    }

    public ArrayList<Double> getIdNums() {
        return idNums;
    }

    public void setIdNums(ArrayList<Double> idNums) {
        this.idNums = idNums;
    }
    
    public ArrayList<Double> getPoints() {
        return idNums;
    }

    public void setPoints(ArrayList<Double> idNums) {
        this.idNums = idNums;
    }
}
