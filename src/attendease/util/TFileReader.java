package attendease.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author timothy.chandler
 * @date Sep 16, 2013
 */
public class TFileReader {
    private File file;
    public TFileReader(){}
    public TFileReader(File f){
        this.file=f;
    }
    public ArrayList readFile(File f) throws FileNotFoundException{
        FileInputStream fis=new FileInputStream(f);
        ArrayList<String> buffer=new ArrayList<>();
        try(Scanner scanner = new Scanner(fis)) {
            while(scanner.hasNext()){
                buffer.add(scanner.nextLine());
            }
        }
        return buffer;
    }
    public ArrayList readFile() throws FileNotFoundException{
        FileInputStream fis=new FileInputStream(file);
        ArrayList<String> buffer=new ArrayList<>();
        try(Scanner scanner = new Scanner(fis)) {
            while(scanner.hasNext()){
                buffer.add(scanner.nextLine());
            }
        }
        return buffer;
    }
}
