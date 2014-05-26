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

import attendease.gui.*;
import java.awt.Component;
import java.awt.Rectangle;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.GroupLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;

/**
 * @author james.wolff
 */
public class FrameController {
    
    private static ArrayList<JFrame> frames;
    private static ArrayList<Component> guis;
    
    private static AddRemoveMembersWarningGUI armwg;
    private static DeleteMeetingWarningGUI dmwg;
    private static GroupGUI gg;
    private static GroupOptionsPanel gop;
    private static MeetingEditPanel mep;
    private static MainFrame mf;
    private static MeetingGUI mg;
    private static MeetingPanel mp;
    private static SelectMGPanel smgp;
    private static StudentPanel sp;
    private static UpdateMembersGUI umg;
    
    private static JFileChooser filec;
    private static JFileChooser folderc;
    private static Inventory inv;
    
    public FrameController(){
        Start.updateSplashString("Retrieving Groups from Database");
        initInventory(); 
        Start.updateSplashString("Loading Interface");
        initPanelsAndFrames();
        initArrayLists();
        initFileChoosers();
        setCurrentPanel("smgp");
    }
    
    private static void initPanelsAndFrames(){
        Start.updateSplashString("Initializing GroupOptionsPanel");
        gop=new GroupOptionsPanel();
        Start.updateSplashString("Initializing MeetingEditPanel");
        mep=new MeetingEditPanel();
        Start.updateSplashString("Initializing MeetingPanel");
        mp=new MeetingPanel();
        Start.updateSplashString("Initializing SelectMGPanel");
        smgp=new SelectMGPanel();
        Start.updateSplashString("Initializing StudentPanel");
        sp=new StudentPanel();
        
        Start.updateSplashString("Initializing MainFrame");
        mf=new MainFrame();
        Start.updateSplashString("Initializing ARMWGUI");
        armwg=new AddRemoveMembersWarningGUI();
        Start.updateSplashString("Initializing DMWGUI");
        dmwg=new DeleteMeetingWarningGUI();
        Start.updateSplashString("InitializingGroupGUI");
        gg=new GroupGUI();
        Start.updateSplashString("Initializing MeetingGUI");
        mg=new MeetingGUI();
        Start.updateSplashString("Initializing UpdateMembersGUI");
        umg=new UpdateMembersGUI();
        
        Start.updateSplashString("Hiding unwanted GUIs");
        armwg.setVisible(false);
        dmwg.setVisible(false);
        gg.setVisible(false);
        mf.setVisible(false);
        mg.setVisible(false);
        umg.setVisible(false);
    }
    
    private static void initArrayLists(){
        Start.updateSplashString("Adding GUIs to list");
        guis=new ArrayList<Component>();
        guis.add(armwg);
        guis.add(dmwg);
        guis.add(gg);
        guis.add(gop);
        guis.add(mg);
        guis.add(mep);
        guis.add(mf);
        guis.add(mg);
        guis.add(smgp);
        guis.add(sp);
        guis.add(umg);
        
        Start.updateSplashString("Adding panels to list");
        frames=new ArrayList<JFrame>();
        frames.add(armwg);
        frames.add(dmwg);
        frames.add(gg);
        frames.add(mf);
        frames.add(mg);
        frames.add(umg);
    }
    
    private static void initFileChoosers(){
        Start.updateSplashString("Initializing File Chooser");
        filec=new JFileChooser();
        filec.setFileSelectionMode(JFileChooser.FILES_ONLY);
        filec.setFileFilter(new FileFilter(){

            @Override
            public boolean accept(File file) {
                return file.getPath().endsWith(".xls")||file.getPath().endsWith(".xlsx")||file.isDirectory();
            }

            @Override
            public String getDescription() {
                return "Excel files (*.xls;*.xlsx)";
            }
        });
        
        Start.updateSplashString("Initializing Folder Chooser");
        folderc=new JFileChooser();
        folderc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        folderc.setFileFilter(new FileFilter(){

            @Override
            public boolean accept(File file) {
                return file.isDirectory();
            }

            @Override
            public String getDescription() {
                return "Directories";
            }
        });
    }
    
    private static void initInventory(){
        inv=new Inventory();
        ResultSet crs=Start.d.readGroupsTable();
        String club;
        try {
            while(crs.next()){
                club=crs.getString("CLUBNAME");
                ResultSet mrs=Start.d.readMeetingsTable(club);
                ArrayList<Meeting> meats=new ArrayList<Meeting>();
                while(mrs.next()){
                    String name=mrs.getString("NAME");
                    String date=mrs.getString("DATE");
                    try{
                        if(name.substring(0, date.length()).equals(date)){
                            name=date+name.substring(date.length(), name.length()-6)+":"+name.substring(name.length()-5);
                        }
                    }catch(StringIndexOutOfBoundsException e){
                    }
                    meats.add(new Meeting(mrs.getString("IDENTIFIER"),name, date, MiscUtils.replaceSpaces(mrs.getString("STARTTIME")), MiscUtils.replaceSpaces(mrs.getString("ENDTIME")), mrs.getInt("ATTENDANCE"), mrs.getInt("POINTSGIVEN"), mrs.getInt("POINTSREQUIRED"), mrs.getInt("LATEPOINTS"), mrs.getBoolean("MEETINGHELD")));
                }
                ResultSet srs=Start.d.readStudentsTable(club);
                ArrayList<Student> stews=new ArrayList<Student>();
                while(srs.next()){
                    stews.add(new Student(srs.getString("NAME"), club, srs.getInt("ID"), srs.getInt("POINTS")));
                }
                String eFile=crs.getString("EPATH");
                boolean usePoints=crs.getBoolean("POINTS");
                if(!(eFile==null||eFile.equals(""))){
                    inv.addGroup(new Group(crs.getString("groupID"), club, meats, stews, eFile, usePoints));
                }else{
                    inv.addGroup(new Group(crs.getString("groupID"), club, meats, stews, null, usePoints));
                }
                for (Student student : stews) {
                    student.fillMeetingsAttended();
                }
            }
        } catch (NullPointerException ex) {
            Start.createLog(ex, "Error retreiving existing Groups!");
        } catch (SQLException ex){
            Start.createLog(ex, "Error retreiving existing Groups!");
        }
    }
    
    public static void setCurrentFrame(String f){
        for (JFrame frame : frames) {
            if(!frame.getName().equalsIgnoreCase(f)){
                frame.setVisible(false);
            }else{
                frame.setVisible(true);
            }
        }
    }
    
    public static void setCurrentPanel(String p){
        GroupLayout layout;
        mf.getContentPane().removeAll();
        p=p.toLowerCase();
        if(p.equals("gop")){
            layout=new GroupLayout(mf.getContentPane());
            layout.setHorizontalGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(gop)
            );
            layout.setVerticalGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(gop)
            );
            mf.setLayout(layout);
            mf.setBounds(new Rectangle(808,636));
            gop.setCurrentGroup(smgp.getCurrentGroupName());
        }else if(p.equals("gop")){
            layout=new GroupLayout(mf.getContentPane());
            layout.setHorizontalGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(mep)
            );
            layout.setVerticalGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(mep)
            );
            mf.setLayout(layout);
            mf.setBounds(new Rectangle(808,636));
        }else if(p.equals("mp")){
            layout=new GroupLayout(mf.getContentPane());
            layout.setHorizontalGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(getMp())
            );
            layout.setVerticalGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(getMp())
            );
            mf.setLayout(layout);
            mf.setBounds(new Rectangle(402,625));
            mp.initMeeting(inv.getGroup(smgp.getCurrentGroupName()).getMeeting(smgp.getCurrentMeetingName()),inv.getGroup(smgp.getCurrentGroupName()).getStudents());
        }else if(p.equals("smgp")){
            layout=new GroupLayout(mf.getContentPane());
            layout.setHorizontalGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(smgp)
            );
            layout.setVerticalGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(smgp)
            );
            mf.setLayout(layout);
            mf.setBounds(new Rectangle(808,636));
        }else if(p.equals("sp")){
            layout=new javax.swing.GroupLayout(mf.getContentPane());
            layout.setHorizontalGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(sp)
            );
            layout.setVerticalGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(sp)
            );
            mf.setLayout(layout);
        }
    }
    
    public static void changeFrameState(String g){
        g=g.toLowerCase();
        if(g.equals("armwg")){
            armwg.setVisible(!armwg.isVisible());
        }else if(g.equals("dmwg")){
            dmwg.setVisible(!dmwg.isVisible());
        }else if(g.equals("gg")){
            gg.setVisible(!gg.isVisible());
        }else if(g.equals("mf")){
            mf.setVisible(!mf.isVisible());
        }else if(g.equals("mg")){
            mg.setVisible(!mg.isVisible());
        }else if(g.equals("umg")){
            umg.setVisible(!umg.isVisible());
        }
    }
    
    public static void clearAll(){
        for (Component c : guis) {
            if(c instanceof AFrame){
                ((AFrame)c).clear();
            }else if(c instanceof APanel){
                ((APanel)c).clear();
            }
        }
    }
    
    public static String chooseFile(){
        if(filec.showOpenDialog(mf)==JFileChooser.APPROVE_OPTION){
            InputOutput.readFile(filec.getSelectedFile());
            return filec.getSelectedFile().getPath();
        }
        return null;
    }
    
    public static String chooseFolder(){
        if(folderc.showSaveDialog(mf)==JFileChooser.APPROVE_OPTION){
            return folderc.getSelectedFile().getPath();
        }
        return null;
    }
    
    public static void addGroup(Group g){
        inv.addGroup(g);
    }
    
    public static Group getGroup(int i){
        return inv.getGroup(i);
    }
    
    public static Group getGroup(String s){
        return inv.getGroup(s);
    }
    
    public static Group removeGroup(String s){
        return inv.deleteGroup(inv.getGroup(s));
    }
    
    public static Group removeGroup(int i){
        return inv.deleteGroup(inv.getGroup(i));
    }
    
    public static void dispose(){
        Start.d.closeConnection();
        System.exit(100);
    }
    
    public static AddRemoveMembersWarningGUI getArmwg() {
        return armwg;
    }

    public static DeleteMeetingWarningGUI getDmwg() {
        return dmwg;
    }

    public static GroupGUI getGg() {
        return gg;
    }

    public static GroupOptionsPanel getGop() {
        return gop;
    }
    
    public static Inventory getInv(){
        return inv;
    }

    public static MainFrame getMf(){
        return mf;
    }
    
    public static MeetingGUI getMg(){
        return mg;
    }
    
    public static MeetingEditPanel getMep() {
        return mep;
    }

    public static MeetingPanel getMp() {
        return mp;
    }
    
    public static SelectMGPanel getSmgp() {
        return smgp;
    }

    public static StudentPanel getSp() {
        return sp;
    }

    public static UpdateMembersGUI getUmg() {
        return umg;
    }
}
