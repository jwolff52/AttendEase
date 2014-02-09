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
    private File file;
    private TFileReader tfr;
    
    public TFileWriter(){
        tfr=new TFileReader();
    }
    
    public TFileWriter(File f){
        file=f;
        tfr=new TFileReader();
    }
    
    public void writeFile(File f, ArrayList<String> strings) throws FileNotFoundException, IOException{
        ArrayList<String> temp=new ArrayList<>();
        if(!f.exists()){
            f.createNewFile();
        }else{
            temp=tfr.readFile(f);
            strings.addAll(0,temp);
        }
        try (BufferedWriter writer = Files.newBufferedWriter(f.toPath(), StandardCharsets.UTF_8)){
            for(String s : strings){
                writer.write(s);
                writer.newLine();
            }
        }
    }

    public void writeFile(File f, String output) throws FileNotFoundException, IOException{;
        ArrayList<String> strings=new ArrayList<>();
        if(!f.exists()){
            f.createNewFile();
        }else{
            strings=tfr.readFile(f);
        }
        strings.add(output);
        try (BufferedWriter writer = Files.newBufferedWriter(f.toPath(), StandardCharsets.UTF_8)){
            for(String s : strings){
                writer.write(s);
                writer.newLine();
            }
        }
    }
    
    public void writeFile(String output) throws FileNotFoundException, IOException{
        ArrayList<String> strings=new ArrayList<>();
        if(!file.exists()){
            file.createNewFile();
        }else{
            strings=tfr.readFile(file);
        }
        strings.add(output);
        try (BufferedWriter writer = Files.newBufferedWriter(file.toPath(), StandardCharsets.UTF_8)){
            for(String s : strings){
                writer.write(s);
                writer.newLine();
            }
        }
    }
    
    public void writeFile(ArrayList<String> strings) throws FileNotFoundException, IOException{
        ArrayList<String> temp=new ArrayList<>();
        if(!file.exists()){
            file.createNewFile();
        }else{
            temp=tfr.readFile(file);
            strings.addAll(0,temp);
        }
        try (BufferedWriter writer = Files.newBufferedWriter(file.toPath(), StandardCharsets.UTF_8)){
            for(String s : strings){
                writer.write(s);
                writer.newLine();
            }
        }
    }
    
    public void overWriteFile(File f, ArrayList<String> strings) throws IOException{
        f.createNewFile();
        writeFile(f, strings);
    }
    
    public void overWriteFile(ArrayList<String> strings) throws IOException{
        file.createNewFile();
        writeFile(strings);
    }
    public void overWriteFile(File f, String output) throws IOException{
        f.createNewFile();
        writeFile(output);
    }
    public void overWriteFile(String output) throws IOException{
        file.createNewFile();
        writeFile(output);
    }
}
