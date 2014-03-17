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
    
    public static FileUtilities readFile(EFile e) throws IOException{
        FileUtilities f=new FileUtilities();
        Workbook wb=null;
        Sheet sheet;
        Iterator rows;
        int loop;
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
                loop=0;
                rows.next();
                while(rows.hasNext()) {    
                    row = (HSSFRow) rows.next();
                    cells = row.cellIterator(); 
                    while(cells.hasNext()){ 
                        cell = (HSSFCell) cells.next(); 
                        if(Cell.CELL_TYPE_NUMERIC==cell.getCellType()) {
                            f.getIdNums().add(new Double(cell.getNumericCellValue()));
                        }else if(Cell.CELL_TYPE_STRING==cell.getCellType()){
                            f.getNames().add(cell.getStringCellValue());
                        }else{
                            javax.swing.JOptionPane.showConfirmDialog(FrameController.getMf(), "The excel file you provided contains an invalid value in cell: "+cell.getStringCellValue(), "Student File Error", javax.swing.JOptionPane.OK_OPTION, javax.swing.JOptionPane.WARNING_MESSAGE);
                        }
                    }
                    loop++;
                }
                f.setSameSize(f.getNames().size()==f.getIdNums().size());
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
                while (rows.hasNext()) {
                    row = (XSSFRow) rows.next();
                    cells = row.cellIterator();
                    loop = 0;
                    while (cells.hasNext()) {
                        cell = (XSSFCell) cells.next();
                        if(Cell.CELL_TYPE_NUMERIC==cell.getCellType()) {
                            f.getIdNums().add(new Double(cell.getNumericCellValue()));
                        }else if(Cell.CELL_TYPE_STRING==cell.getCellType()){
                            f.getNames().add(cell.getStringCellValue());
                        }else{
                            javax.swing.JOptionPane.showConfirmDialog(FrameController.getMf(), "The excel file you provided contains an invalid value in cell: "+cell.getStringCellValue(), "Student File Error", javax.swing.JOptionPane.OK_OPTION, javax.swing.JOptionPane.WARNING_MESSAGE);
                        }
                    }
                    loop++;
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
