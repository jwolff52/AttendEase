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
