/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
        try (BufferedWriter writer = Files.newBufferedWriter(f.toPath(), StandardCharsets.UTF_8)){
            for(String s : strings){
                writer.write(s);
                writer.newLine();
            }
        }
    }

    public static void writeFile(File f, String output) throws FileNotFoundException, IOException{;
        ArrayList<String> strings=new ArrayList<>();
        if(!f.exists()){
            f.createNewFile();
        }else{
            strings=TFileReader.readFile(f);
        }
        strings.add(output);
        try (BufferedWriter writer = Files.newBufferedWriter(f.toPath(), StandardCharsets.UTF_8)){
            for(String s : strings){
                writer.write(s);
                writer.newLine();
            }
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
