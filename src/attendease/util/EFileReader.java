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
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 * @author james.wolff
 * @date Oct 2, 2013
 */
public class EFileReader {
    private ArrayList<String> names;
    private ArrayList<Double> idNums;
    private boolean correctSize;
    public EFileReader(){
        names=new ArrayList<>();
        idNums=new ArrayList<>();
        correctSize=false;
    }
    
    public void readFile(EFile e) throws IOException{
        switch(e.getType()){
            case "xls":
                InputStream input;
                POIFSFileSystem fs;
                HSSFWorkbook wb=null;
                try {
                    input=new BufferedInputStream(new FileInputStream(e.getPath()));
                    fs=new POIFSFileSystem(input);
                    wb = new HSSFWorkbook(fs);
                } catch (IOException ex) {
                    Start.createLog(ex, "Unable to find excel file: "+e.getPath());
                    javax.swing.JOptionPane.showMessageDialog(null, "Could not find excel file: "+e.getName());
                }
                HSSFSheet sheet = wb.getSheetAt(0);
                Iterator rows = sheet.rowIterator();
                int loop=0;
                while(rows.hasNext()) {    
                    HSSFRow row = (HSSFRow) rows.next();
                    Iterator cells = row.cellIterator(); 
                    while(cells.hasNext()){ 
                        HSSFCell cell = (HSSFCell) cells.next(); 
                        if(HSSFCell.CELL_TYPE_NUMERIC==cell.getCellType()) {
                            getIdNums().add(new Double(cell.getNumericCellValue()));
                        }else if(HSSFCell.CELL_TYPE_STRING==cell.getCellType()){
                            getNames().add(cell.getStringCellValue());
                        }else{
                            System.out.print("Unknown cell type"); 
                        }
                    }
                    loop++;
                }
                if(getNames().size()==loop&&getIdNums().size()==loop){
                    correctSize=true;
                }else{
                    correctSize=false;
                }
                break;
            case "xlsx":
                break;
            default:
                throw new IOException("Must be an Excel file!");
        }
    }

    /**
     * @return the names
     */
    public ArrayList<String> getNames() {
        return names;
    }

    /**
     * @return the idNums
     */
    public ArrayList<Double> getIdNums() {
        return idNums;
    }

    /**
     * @return the correctSize
     */
    public boolean isCorrectSize() {
        return correctSize;
    }
}
