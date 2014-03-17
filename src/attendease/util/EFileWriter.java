package attendease.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * @author James
 * @date Mar 17, 2014
 */
public class EFileWriter {
    
    public static void writeAttendanceFile(EFile e, ArrayList<Student> stews, ArrayList<String> meetingNames, ArrayList<Integer> numberOfStudents){
        HSSFWorkbook newWb=new HSSFWorkbook();
        HSSFSheet sheet1=newWb.createSheet("Student Attendance");
        HSSFRow row0=sheet1.createRow(0);
        row0.createCell(0).setCellValue("Name (Last, First)");
        row0.createCell(1).setCellValue("ID Number");
        row0.createCell(2).setCellValue("Points (Optional)");
        row0.createCell(3).setCellValue("Meetings Attended");
        for(int i=1;i<stews.size();i++) {
            HSSFRow row1=sheet1.createRow(i);
            row1.createCell(0).setCellValue(stews.get(i).getName());
            row1.createCell(1).setCellValue(stews.get(i).getID());
            row1.createCell(2).setCellValue(stews.get(i).getPoints());
            row1.createCell(3).setCellValue(stews.get(i).getMeetingsAttended());
        }
        HSSFRow row2=sheet1.createRow(stews.size());
        row2.createCell(0).setCellValue("Total Attendance for all meetings");
        row2.createCell(3).setCellFormula("SUM(D1:D"+(stews.size()-1)+")");
        
        HSSFSheet sheet2=newWb.createSheet("Meetings");
        HSSFRow row3=sheet2.createRow(0);
        row3.createCell(0).setCellValue("Meeting Name");
        row3.createCell(1).setCellValue("Number of Students");
        try{
            for(int i=1;i<meetingNames.size();i++) {
                HSSFRow row4=sheet2.createRow(i);
                row4.createCell(0).setCellValue(meetingNames.get(i));
                row4.createCell(1).setCellValue(numberOfStudents.get(i));
            }
        }catch(ArrayIndexOutOfBoundsException ex){
            Start.createLog(ex, "Unable to Export Students, An Internal Error Occurred");
        }
        HSSFRow row5=sheet1.createRow(stews.size());
        row5.createCell(0).setCellValue("Total Attendance for all meetings");
        row5.createCell(1).setCellFormula("SUM(B1:B"+(stews.size()-1)+")");
        try {
            try (FileOutputStream fos = new FileOutputStream(e.getPath())) {
                newWb.write(fos);
            }
        } catch (IOException ex) {
            Start.createLog(ex, "Unable to create attendance file: "+e.getPath().substring(e.getPath().lastIndexOf(File.separatorChar)+1));
        }
    }
}
