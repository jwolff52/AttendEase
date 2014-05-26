/************************************************************************
    AttendEase - A simple, point-and-click attendance program.
    Copyright (C) 2013-2014  James Wolff, Timothy Chandler, Sterling Long, Cole Howe

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*************************************************************************/

package attendease.util;

import attendease.database.Database;
import attendease.gui.Splash;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author timothy.chandler
 */
public class Start {
    public static Database d;
    
    private static ArrayList<Object>[] errors;
    private static File[] logs;
    private static Splash s;
    private static ProcessBuilder p;
    
    private static final String VERSION="Version: v0.08-alpha";
    
    
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public static void main(String[]args){
        errors=new ArrayList[2];
        errors[0]=new ArrayList<Object>();
        errors[1]=new ArrayList<Object>();
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
        }catch(NullPointerException ex){
        }catch(IOException ex){}
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
        new FrameController();
        s.updateString("Finishing up");
        try {
            Thread.sleep(180);
        } catch (InterruptedException ex) {
            createLog(ex, "Error in Loading");
        }
    }

    private static void initDatabase() {
        try {
            d=new Database();
        } catch (InstantiationException ex) {
            Start.createLog(ex, "A Database Error Occurred");
        } catch (ClassNotFoundException ex) {
            Start.createLog(ex, "A Database Error Occurred");
        } catch (IllegalAccessException ex) {
            Start.createLog(ex, "A Database Error Occurred");
        }
        d.getDb();
    }
    
    public static void createLog(Exception e, String laymansTerm){
        if(!e.getClass().getName().equals("java.sql.SQLIntegrityConstraintViolationException")){
            String s1=new Date().toString()+" :\n\t"+e.toString()+"\n";
            for(StackTraceElement ste:e.getStackTrace()){
                s1+="\t"+ste.toString()+"\n";
            }
            s1+="----------";
            InputOutput.writeFile(logs[0], s1, false);
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
        s.updateString(s1);
    }
    
    public static String getVersion(){
        return VERSION;
    }
}
