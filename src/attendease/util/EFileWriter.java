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
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author James
 * @date Mar 17, 2014
 */
public class EFileWriter {
    
    public static void writeAttendanceFile(EFile e, ArrayList<Student> stews, ArrayList<String> meetingNames, ArrayList<Integer> numberOfStudents){
        Workbook newWb=new XSSFWorkbook();
        CellStyle present=newWb.createCellStyle();
        present.setFillBackgroundColor(HSSFColor.GREEN.index);
        Sheet sheet1=newWb.createSheet("Student Attendance");
        Row row0=sheet1.createRow(0);
        row0.createCell(0).setCellValue("Name (Last, First)");
        row0.createCell(1).setCellValue("ID Number");
        row0.createCell(2).setCellValue("Points (Optional)");
        for(int i=3;i<meetingNames.size();i++) {
            row0.createCell(i).setCellValue(meetingNames.get(i-3));
        }
        for(int i=1;i<stews.size();i++) {
            Row row1=sheet1.createRow(i);
            row1.createCell(0).setCellValue(stews.get(i).getName());
            row1.createCell(1).setCellValue(stews.get(i).getID());
            row1.createCell(2).setCellValue(stews.get(i).getPoints());
            int[] ia=stews.get(i).getMeetingsAttendedAsIntArray();
            int iacount=0;
            for(int j=3;j<meetingNames.size();j++) {
                if(j-3==ia[iacount]){
                    row1.createCell(j).setCellStyle(present);
                    iacount++;
                }
            }
        }
        
        Sheet sheet2=newWb.createSheet("Meetings Attendance");
        Row row3=sheet2.createRow(0);
        row3.createCell(0).setCellValue("Meeting Name");
        row3.createCell(1).setCellValue("Number of Students");
        try{
            for(int i=1;i<meetingNames.size();i++) {
                Row row4=sheet2.createRow(i);
                row4.createCell(0).setCellValue(meetingNames.get(i-1));
                row4.createCell(1).setCellValue(numberOfStudents.get(i-1));
            }
        }catch(ArrayIndexOutOfBoundsException ex){
            Start.createLog(ex, "Unable to Export Students, An Internal Error Occurred");
        }
        Row row5=sheet1.createRow(stews.size());
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
    
    public static void writeFile(EFile n, EFileUtilities f){
        HSSFWorkbook newWb=new HSSFWorkbook();
        HSSFSheet sheet1=newWb.createSheet("Student Attendance");
        HSSFRow row0=sheet1.createRow(0);
        Random rand=new Random();
        row0.createCell(0).setCellValue("Name (Last, First)");
        row0.createCell(1).setCellValue("ID Number");
        row0.createCell(2).setCellValue("Points (Optional)");
        for(int i=1;i<f.getNames().size();i++) {
            HSSFRow row1=sheet1.createRow(i);
            row1.createCell(0).setCellValue(f.getNames().get(i));
            row1.createCell(1).setCellValue(f.getIdNums().get(i));
            row1.createCell(2).setCellValue(rand.nextInt(99999)+1);
        }
    }
}
