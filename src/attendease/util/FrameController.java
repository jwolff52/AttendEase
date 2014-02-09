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
    
    private static AddMembersWarningGUI amwg;
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
        initPanelsAndFrames();
        initArrayLists();
        initFileChooser();
        initInventory();
        setCurrentPanel("smgp");
    }
    
    private static void initPanelsAndFrames(){
        emp=new EndMeetingPanel();
        gop=new GroupOptionsPanel();
        mep=new MeetingEditPanel();
        mp=new MeetingPanel();
        smgp=new SelectMGPanel();
        sp=new StudentPanel();
        
        amwg=new AddMembersWarningGUI();
        dmwg=new DeleteMeetingWarningGUI();
        gg=new GroupGUI();
        mf=new MainFrame();
        mg=new MeetingGUI();
        umg=new UpdateMembersGUI();
        
        amwg.setVisible(false);
        dmwg.setVisible(false);
        gg.setVisible(false);
        mf.setVisible(false);
        mg.setVisible(false);
        umg.setVisible(false);
    }
    
    private static void initArrayLists(){
        names=new ArrayList<>();
        names.add("amwg");
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
                return file.getPath().endsWith(".xls")/*||file.getPath().endsWith(".xlsx")*/||file.isDirectory();
            }

            @Override
            public String getDescription() {
                return "Excel files (*.xls)";
            }
        });
    }
    
    private static void initInventory(){
        inv=new Inventory();
        ResultSet crs=Start.d.readClubsTable();
        String club="InvalidClub";
        try {
            while(crs.next()){
                club=crs.getString("clubName");
                ResultSet mrs=Start.d.readMeetingsTable(club);
                ArrayList<Meeting> meats=new ArrayList<>();
                while(mrs.next()){
                    meats.add(new Meeting(mrs.getString(1), mrs.getString(2), mrs.getString(3), mrs.getString(4), mrs.getString(5), (Integer)mrs.getObject(6), (Integer)mrs.getObject(7), (Integer)mrs.getObject(8)));
                }
                ResultSet srs=Start.d.readStudentsTable(club);
                ArrayList<Student> stews=new ArrayList<>();
                while(srs.next()){
                    stews.add(new Student(srs.getString(1), (Integer)srs.getObject(2), (Integer)srs.getObject(3)));
                }
                String eFile=crs.getString(2);
                boolean usePoints=crs.getBoolean(3);
                if(usePoints){
                    inv.add(new Group(club, meats, stews, eFile, true));
                }else{
                    inv.add(new Group(club, meats, stews, eFile, false));
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
                        layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(mp)
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(mp)
                );
                mf.setLayout(layout);
                mf.setBounds(new Rectangle(473,450));
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
                sp.changeDone(true);
                sp.changeSize(800, 600);
                mf.setLayout(layout);
                mf.setBounds(new Rectangle(808,636));
                break;
        }
    }
    
    public static void changeFrameState(String g){
        switch(g){
            case "amwg":
                amwg.setVisible(!amwg.isVisible());
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
                sp.changeDone(false);
                sp.changeSize(569, 416);
                break;
        }
    }
    
    public static String chooseFile(){
        int returnVal;
        returnVal = fc.showOpenDialog(mf);
        if(returnVal==JFileChooser.APPROVE_OPTION){
            InputOutput.readFile(fc.getSelectedFile());
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
    
    public static void dispose(){
        System.exit(1);
    }
    
    public static AddMembersWarningGUI getArmwg() {
        return amwg;
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
