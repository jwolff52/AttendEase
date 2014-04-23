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
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author James
 * @date Mar 17, 2014
 */
public class EFileWriter {
    
    public static void writeAttendanceFiles(String group, EFile folder, ArrayList<Student> stews, ArrayList<Meeting> meats, ArrayList<AttendedMeeting> attendedMeats){
        writeStudentAttendanceFile(folder, stews, meats);
        writeMeetingAttendanceFile(group, folder, meats, attendedMeats);
    }
    
    private static void writeStudentAttendanceFile(EFile folder, ArrayList<Student> stews, ArrayList<Meeting> meats){
        EFile sFile=new EFile(folder.getPath()+"/Student Attendance.xlsx");
        Workbook newWb=new XSSFWorkbook();
        Sheet sheet=newWb.createSheet("Student Attendance");
        Row row=sheet.createRow(0);
        row.createCell(0).setCellValue("Name (Last, First)");
        row.createCell(1).setCellValue("ID Number");
        row.createCell(2).setCellValue("Points (Optional)");
        for(int i=1;i<stews.size();i++) {
            row=sheet.createRow(i);
            row.createCell(0).setCellValue(stews.get(i).getName());
            row.createCell(1).setCellValue(stews.get(i).getID());
            row.createCell(2).setCellValue(stews.get(i).getPoints());
        }
        for(int i=0;i<3;i++){
            sheet.setColumnWidth(i, 7500);
        }
        int cellcount=2;
        Row nameRow;
        Row timeRow;
        for (Student s : stews) {
            sheet=newWb.createSheet(s.getName());
            nameRow=sheet.createRow(0);
            timeRow=sheet.createRow(1);
            for(int i=0;i<meats.size();i++) {
                nameRow.createCell(cellcount).setCellValue(meats.get(i).getName());
                timeRow.createCell(cellcount).setCellValue(s.getMeetingsAttended().get(i).getArrivalTime());
                cellcount++;
            }
            sheet.createRow(2).createCell(0).setCellValue("Name: "+s.getName());
            sheet.createRow(3).createCell(0).setCellValue("ID Number: "+s.getID());
            sheet.createRow(4).createCell(0).setCellValue("Points: "+s.getPoints());
            for(int i=0;i<cellcount;i++){
                sheet.setColumnWidth(i, 7500);
            }
        }
        try (FileOutputStream fos = new FileOutputStream(sFile.getPath())) {
                newWb.write(fos);
        } catch (IOException ex) {
            Start.createLog(ex, "Unable to create attendance file: "+sFile.getPath().substring(sFile.getPath().lastIndexOf(File.separatorChar)+1));
        }
    }
    
    public static void writeMeetingAttendanceFile(String group, EFile folder, ArrayList<Meeting> meats, ArrayList<AttendedMeeting> attendedMeats){
        EFile mFile=new EFile(folder.getPath()+"/Meeting Attendance.xlsx");
        Workbook newWb=new XSSFWorkbook();
        Sheet sheet=newWb.createSheet("Meetings Attendance");
        Row row=sheet.createRow(0);
        row.createCell(0).setCellValue("Meeting Name");
        row.createCell(1).setCellValue("Number of Students");
        try{
            for(int i=1;i<meats.size();i++) {
                row=sheet.createRow(i);
                row.createCell(0).setCellValue(meats.get(i-1).getName());
                row.createCell(1).setCellValue(meats.get(i-1).getAttendance());
            }
        }catch(ArrayIndexOutOfBoundsException ex){
            Start.createLog(ex, "Unable to Export Students, An Internal Error Occurred");
        }
        ArrayList<Student> stews;
        for (AttendedMeeting m : attendedMeats) {
            stews=m.getAttendedStudents(group);
            sheet=newWb.createSheet(m.getName());
            row=sheet.createRow(0);
            row.createCell(0).setCellValue("Student Name");
            row.createCell(1).setCellValue("Arrival Time");
            row.createCell(2).setCellValue("Student Points");
            for(int i=1;i<stews.size();i++){
                row=sheet.createRow(i);
                row.createCell(0).setCellValue(stews.get(i).getName());
                row.createCell(1).setCellValue(stews.get(i).getAttendedMeeting(m).getArrivalTime());
                row.createCell(2).setCellValue(stews.get(i).getPoints());
            }
        }
        try (FileOutputStream fos = new FileOutputStream(mFile.getPath())) {
                newWb.write(fos);
        } catch (IOException ex) {
            Start.createLog(ex, "Unable to create attendance file: "+mFile.getPath().substring(mFile.getPath().lastIndexOf(File.separatorChar)+1));
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
