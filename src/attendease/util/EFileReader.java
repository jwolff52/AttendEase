package attendease.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

/**
 * @author james.wolff
 * @date Oct 2, 2013
 */

public class EFileReader {
    private final ArrayList<String> names;
    private final ArrayList<Double> idNums;
    public EFileReader(){
        names=new ArrayList<>();
        idNums=new ArrayList<>();
    }
    
    public static EFileUtilities readFile(EFile e) throws IOException{
        EFileUtilities f=new EFileUtilities();
        Workbook wb=null;
        Sheet sheet;
        Iterator rows;
        Row row;
        Iterator cells;
        Cell cell;
        switch(e.getType()){
            case "xls":
                InputStream input;
                POIFSFileSystem fs;
                try {
                    input=new BufferedInputStream(new FileInputStream(e.getPath()));
                    fs=new POIFSFileSystem(input);
                    wb=new HSSFWorkbook(fs);
                } catch (IOException ex) {
                    Start.createLog(ex, "Unable to find excel file: "+e.getPath());
                    javax.swing.JOptionPane.showMessageDialog(null, "Could not find excel file: "+e.getName()+" located at: "+e.getPath());
                }
                sheet = (HSSFSheet) wb.getSheetAt(0);
                rows = sheet.rowIterator();
                HSSFRow row0=(HSSFRow) rows.next();
                cells=row0.cellIterator();
                HSSFCell cell0=(HSSFCell) cells.next();
                if(!cell0.getStringCellValue().equals("Name (Last, First)")){
                    javax.swing.JOptionPane.showMessageDialog(FrameController.getMf(), "The Excel file provided was not in the correct format,\nplease use the example files located in \"My Documents/AttendEase\" as a base.", "Excel File Error", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                }
                int i=0;
                while(rows.hasNext()) {    
                    row = (HSSFRow) rows.next();
                    cells = row.cellIterator(); 
                    while(cells.hasNext()){ 
                        cell = (HSSFCell) cells.next();
                        i++;
                        if(i%3==1&&cell.getCellType()==Cell.CELL_TYPE_NUMERIC) {
                            f.getIdNums().add(cell.getNumericCellValue());
                        }else if(i%3==2&&cell.getCellType()==Cell.CELL_TYPE_STRING){
                            f.getNames().add(cell.getStringCellValue());
                        }else if(i%3==0&&cell.getCellType()==Cell.CELL_TYPE_NUMERIC){
                            f.getPoints().add(cell.getNumericCellValue());
                        }else{
                            javax.swing.JOptionPane.showConfirmDialog(FrameController.getMf(), "The excel file you provided contains an invalid value in cell: "+cell.getStringCellValue(), "Student File Error", javax.swing.JOptionPane.OK_OPTION, javax.swing.JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
                return f;
            case "xlsx":
                try {
                    wb = WorkbookFactory.create(new FileInputStream(e.getPath()));
                }catch(IOException | InvalidFormatException ex){
                    Start.createLog(ex, "Unable to find excel file: "+e.getPath());
                    javax.swing.JOptionPane.showMessageDialog(null, "Could not find excel file: "+e.getName()+" located at: "+e.getPath());
                }
                sheet = (XSSFSheet) wb.getSheetAt(0);
                rows = sheet.iterator();
                rows.next();
                i=0;
                while (rows.hasNext()) {
                    row = (XSSFRow) rows.next();
                    cells = row.cellIterator();
                    while (cells.hasNext()) {
                        cell = (XSSFCell) cells.next();
                        i++;
                        if(i%3==1&&cell.getCellType()==Cell.CELL_TYPE_NUMERIC) {
                            f.getIdNums().add(cell.getNumericCellValue());
                        }else if(i%3==2&&cell.getCellType()==Cell.CELL_TYPE_STRING){
                            f.getNames().add(cell.getStringCellValue());
                        }else if(i%3==0&&cell.getCellType()==Cell.CELL_TYPE_NUMERIC){
                            f.getPoints().add(cell.getNumericCellValue());
                        }else{
                            javax.swing.JOptionPane.showConfirmDialog(FrameController.getMf(), "The excel file you provided contains an invalid value in cell: "+cell.getStringCellValue(), "Student File Error", javax.swing.JOptionPane.OK_OPTION, javax.swing.JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
                return f;
            default:
                Start.createLog(new IOException("Must be an Excel file!"), "An invalid file type was provided for the students");
                return null;
        }
    }

    public ArrayList<String> getNames() {
        return names;
    }

    public ArrayList<Double> getIdNums() {
        return idNums;
    }
}
