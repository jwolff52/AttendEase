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
public class FileUtilities {
    private ArrayList<String> names;
    private ArrayList<Double> idNums;
    private boolean sameSize;

    public FileUtilities(){
        names=new ArrayList<>();
        idNums=new ArrayList<>();
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

    public boolean isSameSize() {
        return sameSize;
    }

    public void setSameSize(boolean sameSize) {
        this.sameSize = sameSize;
    }
}
