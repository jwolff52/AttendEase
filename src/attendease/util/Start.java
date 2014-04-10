/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package attendease.util;

import attendease.database.Database;
import attendease.gui.Splash;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author timothy.chandler
 */
public class Start {
    public static Database d;
    
    private static ArrayList<Object>[] errors;
    private static File[] logs;
    private static Splash s;
    private static String nextString;
    private static ProcessBuilder p;
    
    
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public static void main(String[]args){
        errors=new ArrayList[2];
        errors[0]=new ArrayList<>();
        errors[1]=new ArrayList<>();
        s=new Splash("Starting up");
        new Thread(new Runnable(){
            @Override
            public void run() {
                s.startSplash();
            }
        }).start();
        try {
            Thread.sleep(1800);
        } catch (InterruptedException ex) {
            preLogError(ex, "Error in Loading");
        }
        initVariables();
        s.setDoneLoading(true);
        try {
            Thread.sleep(20);
        } catch (InterruptedException ex) {
            createLog(ex, "Error in Loading");
        }
        removeSplash();
        FrameController.changeFrameState("mf");
        try{
            p.start();
        }catch(NullPointerException | IOException ex){}
    }
    
    public static void removeSplash(){
        s.dispose();
    }
    
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    private static void initVariables() {
        new InputOutput();
        s.updateString("Locating log files");
        logs=new File[2];
        logs[0]=new File("Developer.log");
        logs[1]=new File("User.log");
        for (File log : logs) {
            if (!log.exists()) {
                try {
                    log.createNewFile();
                } catch (IOException ex) {
                    preLogError(ex, "Unable to create file: " + log.getPath());
                }
            }
        }
        s.updateString("Log files located");
        logErrors();
        s.updateString("Connecting to Database");
        initDatabase();
        s.updateString("Connected to Database");
        try {
            Thread.sleep(90);
        } catch (InterruptedException ex) {
            createLog(ex, "Error in Loading");
        }
        s.updateString("Loading Interface");
        new FrameController();
        s.updateString("Finishing up");
        try {
            Thread.sleep(180);
        } catch (InterruptedException ex) {
            createLog(ex, "Error in Loading");
        }
    }

    private static void initDatabase() {
        try{
            d=new Database();
        }catch(ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            createLog(ex, "A Database Error occurred!");
        }
        d.getDb();
    }
    
    public static void createLog(Exception e, String laymansTerm){
        if(!e.getClass().getName().equals("java.sql.SQLIntegrityConstraintViolationException")){
            String s=new Date().toString()+" :\n\t"+e.toString()+"\n";
            for(StackTraceElement ste:e.getStackTrace()){
                s+="\t"+ste.toString()+"\n";
            }
            s+="----------";
            InputOutput.writeFile(logs[0], s, false);
            InputOutput.writeFile(logs[1], new Date().toString()+" : "+laymansTerm, false);
        }
    }
    
    public static void preLogError(Exception e, String laymansTerms){
        errors[0].add(e);
        errors[1].add(laymansTerms);
    }
    
    private static void logErrors(){
        for (int i=0; i<errors[0].size(); i++) {
            createLog((Exception)errors[0].get(i), (String)errors[1].get(i));
        }
    }
    
    public static void updateSplashString(String s1){
        nextString=s1;
        new Thread(new Runnable(){
            @Override
            public void run() {
                s.updateString(nextString);
            }
        }).start();
    }
    
    public static void firstRunSetup(){
        try {
            final String HOME_DIR=new JFileChooser().getFileSystemView().getDefaultDirectory().getPath();
            TFileWriter.writeFile(new File(HOME_DIR+"/AttendEase/README.md"), TFileReader.readFile(new File("README.md")));
            EFileWriter.writeFile(new EFile(HOME_DIR+"/AttendEase/XLSExample.xls"), EFileReader.readFile(new EFile("XLSExample.xls")));
            EFileWriter.writeFile(new EFile(HOME_DIR+"/AttendEase/XLSXExample.xls"), EFileReader.readFile(new EFile("XLSXExample.xlsx")));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void showReadMe(){
        p = new ProcessBuilder("Notepad.exe", new JFileChooser().getFileSystemView().getDefaultDirectory().getPath()+"/AttendEase/README.md");
    }
}
