package attendease.util;

import attendease.gui.*;
import java.awt.Rectangle;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.GroupLayout;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

/**
 * @author james.wolff
 */
public class FrameController {
    
    private static ArrayList<String> frames;
    private static ArrayList<String> names;
    
    private static AddRemoveMembersWarningGUI armwg;
    private static DeleteMeetingWarningGUI dmwg;
    private static EndMeetingPanel emp;
    private static GroupGUI gg;
    private static GroupOptionsPanel gop;
    private static MeetingEditPanel mep;
    private static MainFrame mf;
    private static MeetingGUI mg;
    private static MeetingPanel mp;
    private static SelectMGPanel smgp;
    private static StudentPanel sp;
    private static UpdateMembersGUI umg;
    
    private static JFileChooser fc;
    private static Inventory inv;
    
    public FrameController(){   
        initInventory(); 
        initPanelsAndFrames();
        initArrayLists();
        initFileChooser();
        setCurrentPanel("smgp");
    }
    
    private static void initPanelsAndFrames(){
        emp=new EndMeetingPanel();
        gop=new GroupOptionsPanel();
        mep=new MeetingEditPanel();
        mp=new MeetingPanel();
        smgp=new SelectMGPanel();
        sp=new StudentPanel();
        
        armwg=new AddRemoveMembersWarningGUI();
        dmwg=new DeleteMeetingWarningGUI();
        gg=new GroupGUI();
        mf=new MainFrame();
        mg=new MeetingGUI();
        umg=new UpdateMembersGUI();
        
        armwg.setVisible(false);
        dmwg.setVisible(false);
        gg.setVisible(false);
        mf.setVisible(false);
        mg.setVisible(false);
        umg.setVisible(false);
    }
    
    private static void initArrayLists(){
        names=new ArrayList<>();
        names.add("armwg");
        names.add("dmwg");
        names.add("emp");
        names.add("gg");
        names.add("gop");
        names.add("mg");
        names.add("mep");
        names.add("mf");
        names.add("smgp");
        names.add("sp");
        names.add("umg");
        
        frames=new ArrayList<>();
        frames.add("armwg");
        frames.add("dmwg");
        frames.add("fb");
        frames.add("gg");
        frames.add("mf");
        frames.add("mg");
        frames.add("umg");
    }
    
    private static void initFileChooser(){
        fc=new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fc.setFileFilter(new FileFilter(){

            @Override
            public boolean accept(File file) {
                return file.getPath().endsWith(".xls")||file.getPath().endsWith(".xlsx")||file.isDirectory();
            }

            @Override
            public String getDescription() {
                return "Excel files (*.xls;*.xlsx)";
            }
        });
    }
    
    private static void initInventory(){
        inv=new Inventory();
        ResultSet crs=Start.d.readGroupsTable();
        String club;
        try {
            while(crs.next()){
                club=crs.getString("clubName");
                ResultSet mrs=Start.d.readMeetingsTable(club);
                ArrayList<Meeting> meats=new ArrayList<>();
                while(mrs.next()){
                    String name=mrs.getString("name");
                    String date=mrs.getString("DATE");
                    try{
                        if(name.substring(0, date.length()).equals(date)){
                            name=date+name.substring(date.length(), name.length()-6)+":"+name.substring(name.length()-5);
                        }
                    }catch(StringIndexOutOfBoundsException e){
                    }
                    meats.add(new Meeting(name, date, Validator.replaceSpaces(mrs.getString("STARTTIME")), Validator.replaceSpaces(mrs.getString("ENDTIME")), mrs.getInt("ATTENDANCE"),mrs.getString("REOCURRINGDAYS"), mrs.getInt("POINTSGIVEN"), mrs.getInt("POINTSREQUIRED"), mrs.getInt("LATEPOINTS"), mrs.getBoolean("MEETINGHELD")));
                }
                ResultSet srs=Start.d.readStudentsTable(club);
                ArrayList<Student> stews=new ArrayList<>();
                while(srs.next()){
                    stews.add(new Student(srs.getString(2), srs.getInt(1), srs.getInt(4), srs.getInt(3)));
                }
                String eFile=crs.getString(2);
                boolean usePoints=crs.getBoolean(3);
                if(usePoints){
                    if(!(eFile==null||eFile.equals(""))){
                        inv.add(new Group(club, meats, stews, eFile, true));
                    }else{
                        inv.add(new Group(club, meats, stews, null, true));
                    }
                }else{
                    if(!(eFile==null||eFile.equals(""))){
                        inv.add(new Group(club, meats, stews, eFile, false));
                    }else{
                        inv.add(new Group(club, meats, stews, null, false));
                    }
                }
                
            }
        } catch (SQLException ex) {
            Start.createLog(ex, "Error retreiving existing Groups!");
        }
    }
    
    public static void setCurrentPanel(String p){
        GroupLayout layout;
        mf.getContentPane().removeAll();
        switch(p){
            case "emp":
                layout=new GroupLayout(mf.getContentPane());
                layout.setHorizontalGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(emp)
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(emp)
                );
                mf.setLayout(layout);
                mf.setBounds(new Rectangle(808,636));
                break;
            case "gop":
                layout=new GroupLayout(mf.getContentPane());
                layout.setHorizontalGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(gop)
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(gop)
                );
                mf.setLayout(layout);
                mf.setBounds(new Rectangle(808,636));
                gop.setCurrentGroup(smgp.getCurrentGroup());
                break;
            case "mep":
                layout=new GroupLayout(mf.getContentPane());
                layout.setHorizontalGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(mep)
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(mep)
                );
                mf.setLayout(layout);
                mf.setBounds(new Rectangle(808,636));
                break;
            case "mp":
                layout=new GroupLayout(mf.getContentPane());
                layout.setHorizontalGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(getMp())
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(getMp())
                );
                mf.setLayout(layout);
                mf.setBounds(new Rectangle(402,625));
                mp.initMeeting(inv.getGroup(smgp.getCurrentGroup()).getMeeting(smgp.getCurrentMeeting()),inv.getGroup(smgp.getCurrentGroup()).getStudents());
                break;
            case "smgp":
                layout=new GroupLayout(mf.getContentPane());
                layout.setHorizontalGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(smgp)
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(smgp)
                );
                mf.setLayout(layout);
                mf.setBounds(new Rectangle(808,636));
                break;
            case "sp":
                layout=new javax.swing.GroupLayout(mf.getContentPane());
                layout.setHorizontalGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(sp)
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(sp)
                );
                mf.setLayout(layout);
                break;
        }
    }
    
    public static void changeFrameState(String g){
        switch(g){
            case "armwg":
                armwg.setVisible(!armwg.isVisible());
                break;
            case "dmwg":
                dmwg.setVisible(!dmwg.isVisible());
                break;
            case "gg":
                gg.setVisible(!gg.isVisible());
                break;
            case "mf":
                mf.setVisible(!mf.isVisible());
                break;
            case "mg":
                mg.setVisible(!mg.isVisible());
                break;
            case "umg":
                umg.setVisible(!umg.isVisible());
                break;
        }
    }
    
    public static String chooseFile(){
        int returnVal;
        returnVal = fc.showOpenDialog(mf);
        try{
            fc.setCurrentDirectory(new File(Start.d.getLastUsedDirectory(getSmgp().getCurrentGroup())));
        }catch(NullPointerException e){
            fc.setCurrentDirectory(null);
        }
        if(returnVal==JFileChooser.APPROVE_OPTION){
            InputOutput.readFile(fc.getSelectedFile());
            Start.d.setLastUsedDirectory(getSmgp().getCurrentGroup(), fc.getSelectedFile().getParent());
            return fc.getSelectedFile().getPath();
        }
        return null;
    }
    
    public static void addGroup(Group g){
        inv.add(g);
    }
    
    public static Group getGroup(int i){
        return inv.getGroup(i);
    }
    
    public static Group getGroup(String s){
        return inv.getGroup(s);
    }
    
    public static void dispose(){
        Start.d.closeConnection();
        System.exit(1);
    }
    
    public static AddRemoveMembersWarningGUI getArmwg() {
        return armwg;
    }

    public static DeleteMeetingWarningGUI getDmwg() {
        return dmwg;
    }

    public static EndMeetingPanel getEmp() {
        return emp;
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
