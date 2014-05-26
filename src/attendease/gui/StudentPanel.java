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

package attendease.gui;

import attendease.util.APanel;
import attendease.util.ATableModel;
import attendease.util.EFile;
import attendease.util.EFileWriter;
import attendease.util.FrameController;
import attendease.util.Meeting;
import attendease.util.Start;
import attendease.util.Student;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author sterling.long
 */
public class StudentPanel extends APanel {

    public StudentPanel() {
        preInit();
        initComponents();
        initTable();
    }

    private void preInit(){
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                        break;
                }
            }
        } catch (ClassNotFoundException ex) {
            Start.createLog(ex, "Unable to set proper look and feel");
        } catch (InstantiationException ex) {
            Start.createLog(ex, "Unable to set proper look and feel");
        } catch (IllegalAccessException ex) {
            Start.createLog(ex, "Unable to set proper look and feel");
        } catch (UnsupportedLookAndFeelException ex) {
            Start.createLog(ex, "Unable to set proper look and feel");
        }
        currentList=new ArrayList<Student>();
    }
    
    private void initTable(){
        sTableModel=new ATableModel();
        sTableModel.addColumn("ID Number");
        sTableModel.addColumn("Name");
        sTableModel.addColumn("Points");
    }
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        doneButton = new javax.swing.JButton();
        searchLabel = new javax.swing.JLabel();
        studentTableScrollPane = new javax.swing.JScrollPane();
        studentTable = new javax.swing.JTable();
        searchTextField = new javax.swing.JTextField();
        exportButton = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(800, 600));

        doneButton.setText("Done");
        doneButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                doneButtonMouseReleased(evt);
            }
        });

        searchLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        searchLabel.setText("Search Student:");

        studentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        studentTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        studentTable.setAutoscrolls(false);
        studentTable.setRowHeight(24);
        studentTable.getTableHeader().setReorderingAllowed(false);
        studentTableScrollPane.setViewportView(studentTable);

        searchTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchTextFieldKeyReleased(evt);
            }
        });

        exportButton.setText("Export");
        exportButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                exportButtonMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(studentTableScrollPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(exportButton, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(doneButton, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(searchLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(studentTableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(doneButton, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                    .addComponent(exportButton, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
                .addContainerGap(58, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void doneButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_doneButtonMouseReleased
        FrameController.setCurrentPanel("gop");
    }//GEN-LAST:event_doneButtonMouseReleased

    private void searchTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTextFieldKeyReleased
        if(searchTextField.getText()==null||searchTextField.getText().equals("")){
            fillStudentTable();
        }else{
            ArrayList<Student> newList=new ArrayList<Student>();
            fillStudentTable();
            for(Student s:currentList){
                if(s.getName().toLowerCase().contains(searchTextField.getText().toLowerCase())||(s.getID()+"").contains(searchTextField.getText())){
                    newList.add(s);
                }
            }
            fillStudentTable(newList);
        }
    }//GEN-LAST:event_searchTextFieldKeyReleased

    private void exportButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exportButtonMouseReleased
        javax.swing.JOptionPane.showMessageDialog(this, "Please choose a location to export the attendance files to.", "AttendEase", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        String loc=FrameController.chooseFolder();
        EFile newFile=new EFile(loc);
        if(newFile.getType().equalsIgnoreCase("xls")){
            newFile=new EFile(loc.substring(0, loc.lastIndexOf("."))+".xlsx");
        }
        if(!newFile.exists()){
            try {
                newFile.createNewFile();
            } catch (IOException ex) {
                Start.createLog(ex, "Unable to create attendance file at "+loc.substring(loc.lastIndexOf(File.separatorChar)+1));
            }
        }
        EFileWriter.writeAttendanceFiles(FrameController.getSmgp().getCurrentGroupName(), newFile, FrameController.getGroup(FrameController.getSmgp().getCurrentGroupName()).getStudents(), FrameController.getGroup(FrameController.getSmgp().getCurrentGroupName()).getMeetings());
    }//GEN-LAST:event_exportButtonMouseReleased
    
    public void fillStudentTable(){
        initTable();
        ArrayList<Meeting> meats=initMeetingColumns(FrameController.getGroup(FrameController.getSmgp().getCurrentGroupName()).getMeetings());
        currentList=FrameController.getGroup(FrameController.getSmgp().getCurrentGroupName()).getStudents();
        Collections.sort(currentList, new Comparator<Student>(){
                @Override
                public int compare(Student s1, Student s2){
                    Integer i1=s1.getID();
                    Integer i2=s2.getID();
                    return i1.compareTo(i2);
                }
            });
        Student s;
        for(int i=0;i<currentList.size();i++){
            s=currentList.get(i);
            sTableModel.addRow(new Object[]{s.getID(),s.getName(),s.getPoints()});
            int j=3;
            for(Meeting m:meats) {
                String arrivalTime;
                try{
                    arrivalTime=s.getAttendedMeeting(m).getArrivalTime();
                }catch(NullPointerException e){
                    arrivalTime="ABSENT";
                }
                if(arrivalTime.matches("[0-9: APM]")){
                    char[] temp=arrivalTime.substring(0, arrivalTime.indexOf(":")).toCharArray();
                    int[] ia=new int[2];
                    int k=0;
                    if(temp.length>1){
                        ia[0]=Integer.valueOf(temp[0]);
                        ia[1]=Integer.valueOf(temp[1]);
                        if(ia[0]>0){
                            if(ia[1]>2){
                                k=Integer.valueOf(temp[0]+""+temp[1])-12;
                            }
                        }
                    }
                    arrivalTime=arrivalTime.replaceFirst(temp[0]+"", "");
                    arrivalTime=arrivalTime.replaceFirst(temp[1]+"", k+"");
                }
                sTableModel.setValueAt(arrivalTime, i, j);
                j++;
            }
        }
        studentTable.setModel(sTableModel);
    }
    
    private ArrayList<Meeting> initMeetingColumns(ArrayList<Meeting> meats){
        ArrayList<Meeting> temp=new ArrayList<Meeting>();
        for(Meeting m: meats){
            if(m.isMeatHeld()){
                sTableModel.addColumn(m.getName());
                temp.add(m);
            }
        }
        return temp;
    }
    
    public void fillStudentTable(ArrayList<Student> newStudents){
        initTable();
        currentList=newStudents;
        ArrayList<Meeting> attendedMeats=initMeetingColumns(FrameController.getGroup(FrameController.getSmgp().getCurrentGroupName()).getMeetings());
        Collections.sort(currentList, new Comparator<Student>(){
                @Override
                public int compare(Student s1, Student s2){
                    Integer i1=s1.getID();
                    Integer i2=s2.getID();
                    return i1.compareTo(i2);
                }
            });
        Student s;
        for(int i=0;i<currentList.size();i++){
            s=currentList.get(i);
            sTableModel.addRow(new Object[]{s.getID(),s.getName(),s.getPoints()});
            int j=3;
            for(Meeting m:attendedMeats) {
                String arrivalTime;
                try{
                    arrivalTime=s.getAttendedMeeting(m).getArrivalTime();
                }catch(NullPointerException e){
                    arrivalTime="ABSENT";
                }
                sTableModel.setValueAt(arrivalTime, i, j);
                j++;
            }
        }
        studentTable.setModel(sTableModel);
    }
    
    private ATableModel sTableModel;
    private ArrayList<Student> currentList;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton doneButton;
    private javax.swing.JButton exportButton;
    private javax.swing.JLabel searchLabel;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JTable studentTable;
    private javax.swing.JScrollPane studentTableScrollPane;
    // End of variables declaration//GEN-END:variables

}
