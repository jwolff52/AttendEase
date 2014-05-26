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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;

/**
 *
 * @author timothy.chandler
 */
public class TFileWriter {
    
    public static void writeFile(File f, ArrayList<String> strings) throws FileNotFoundException, IOException{
        if(!f.exists()){
            f.createNewFile();
        }else{
            strings.addAll(0,TFileReader.readFile(f));
        }
        try{
            BufferedWriter writer = Files.newBufferedWriter(f.toPath(), StandardCharsets.UTF_8);
            for(String s : strings){
                writer.write(s);
                writer.newLine();
            }
            writer.close();
        }catch(IOException e){
            Start.createLog(e, "Error writing the file at location: "+f.getName());
        }
    }

    public static void writeFile(File f, String output) throws FileNotFoundException, IOException{;
        ArrayList<String> strings=new ArrayList<String>();
        if(!f.exists()){
            f.createNewFile();
        }else{
            strings=TFileReader.readFile(f);
        }
        strings.add(output);
        try{
            BufferedWriter writer = Files.newBufferedWriter(f.toPath(), StandardCharsets.UTF_8);
            for(String s : strings){
                writer.write(s);
                writer.newLine();
            }
            writer.close();
        }catch(IOException e){
            Start.createLog(e, "Error writing the file at location: "+f.getName());
        }
    }
    
    public static void overWriteFile(File f, ArrayList<String> strings) throws IOException{
        f.createNewFile();
        writeFile(f, strings);
    }
    
    public static void overWriteFile(File f, String output) throws IOException{
        f.createNewFile();
        writeFile(f, output);
    }
}
