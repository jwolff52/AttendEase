/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package attendease.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author timothy.chandler
 */
public class InputOutput {
    
    private static String logLoc;
    private static TFileReader tfr;
    private static TFileWriter tfw;
    private static EFileReader xfr;
    
    public InputOutput(){
        tfr=new TFileReader();
        tfw=new TFileWriter();
        xfr=new EFileReader();
        logLoc="./.log";
    }
    public static ArrayList<String> readFile(File f){
        try {
            return tfr.readFile(f);
        } catch (FileNotFoundException ex) {
            Start.createLog(ex, "Could not find file: "+f.getPath());
        }
        return new ArrayList<>();
    }

    public static void writeFile(File f, String output, boolean overwrite){
        if(overwrite){
            try {
                tfw.overWriteFile(f, output);
            } catch (IOException ex) {
                Start.createLog(ex, "Could not find file: "+f.getPath());
            }
        }else{
            try{
                tfw.writeFile(f, output);
            }catch(IOException ex){
                Start.createLog(ex, "Could not find file: "+f.getPath());
            }
        }
    }
    
    public static void writeFile(File f, ArrayList<String> output, boolean overwrite){
        if(overwrite){
            try {
                tfw.overWriteFile(f, output);
            } catch (IOException ex) {
                Start.createLog(ex, "Could not find file: "+f.getPath());
            }
        }else{
            try{
                tfw.writeFile(f, output);
            }catch(IOException ex){
                Start.createLog(ex, "Could not find file: "+f.getPath());
            }
        }
    }

    public static void getInfo(EFile e){
        try {
            xfr.readFile(e);
        } catch (IOException ex) {
            Start.createLog(ex, "Could not find file: "+e.getPath());
        }
    }
    
    public static ArrayList<String> getNames(){
        return xfr.getNames();
    }
    
    public static ArrayList<Double> getIdNums(){
        return xfr.getIdNums();
    }
}
