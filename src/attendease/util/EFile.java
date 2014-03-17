/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package attendease.util;

import java.io.File;

/**
 *
 * @author timothy.chandler
 */
public class EFile extends File{
    private String type;
    
    public EFile(String path){
        super(path);
        if(!path.equals("")){
            if(path.endsWith("s")){
                type=path.substring(path.length()-3);
            }else{
                type=path.substring(path.length()-4);
            }
        }
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
}
