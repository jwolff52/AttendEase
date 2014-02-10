/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package attendease.gui;

import attendease.util.Meeting;
import attendease.util.Start;
import attendease.util.Validator;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTabbedPane;

/**
 *
 * @author timothy.chandler
 */
public class MeetingEditPanel extends javax.swing.JPanel {

    /**
     * Creates new form Meeting
     */
    public MeetingEditPanel() {
        preInit();
        initComponents();
        postInit();
    }
    
    private void preInit(){
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            Start.createLog(ex, "Unable to set proper look and feel");
        }
        c=Calendar.getInstance(TimeZone.getTimeZone("CST"));
        years=new DefaultComboBoxModel();
        months=new DefaultComboBoxModel();
        days=new DefaultComboBoxModel();
    }
    
    private void postInit(){
        iET24RadioButtonAdv.setVisible(false);
        setLastSelected();
        initModels();
    }
    
    private void initModels(){
        for(int i=c.get(Calendar.YEAR);i<=c.get(Calendar.YEAR)+10;i++){
            years.addElement(i);
        }
        sDYear.setSelectedIndex(0);
        refreshMonths();
        sDMonth.setSelectedIndex(0);
        refreshDays();
    }
    
    private void refreshMonths(){
        months=new DefaultComboBoxModel();
        if(!((Integer)sDYear.getSelectedItem()<c.get(Calendar.YEAR))){
            for(int i=getBeginningMonth();i<=c.getMaximum(Calendar.MONTH)+1;i++){
                months.addElement(getMonthName(i));
            }
        }
        sDMonth.setModel(months);
        sDMonth.repaint();
        sDMonth.setSelectedIndex(0);
        refreshDays();
    }
    
    private void refreshDays(){
        days=new DefaultComboBoxModel();
        for(int i=getBeginningDay();i<=getMaxDays();i++){
            days.addElement(i);
        }
        sDDay.setModel(days);
        sDDay.repaint();
    }
    
    private int getBeginningMonth(){
        if(sDYear.getSelectedItem().equals(c.get(Calendar.YEAR))){
            return c.get(Calendar.MONTH)+1;
        }else{
            return c.get(Calendar.JANUARY);
        }
    }
    
    private int getBeginningDay(){
        int i=getMonthNumber((String)sDMonth.getSelectedItem());
        if(i-1==c.get(Calendar.MONTH)&&(Integer)sDYear.getSelectedItem()==c.get(Calendar.YEAR)){
            return c.get(Calendar.DAY_OF_MONTH);
        }else{
            return 1;
        }
    }
    
    private int getMonthNumber(String month){
        switch(month){
            case "January":
                return 1;
            case "February":
                return 2;
            case "March":
                return 3;
            case "April":
                return 4;
            case "May":
                return 5;
            case "June":
                return 6;
            case "July":
                return 7;
            case "August":
                return 8;
            case "September":
                return 9;
            case "October":
                return 10;
            case "November":
                return 11;
            case "December":
                return 12;
            default:
                return 13;
        }
    }
    
    private String getMonthName(int month){
        switch(month){
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "December";
            default:
                return "Undecimber";
        }
    }
    
    private int getMaxDays(){
        int y=(Integer)sDYear.getSelectedItem();
        switch(getMonthNumber((String)sDMonth.getSelectedItem())){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 2:
                if(isLeapYear(y)){
                    return 29;
                }else{
                    return 28;
                }
            default:
                return 30;
        }
    }
    
    private boolean isLeapYear(int y){
        if(y%4==0){
            if(y%100==0&&y%400!=0){
                return false;
            }
            else{
                return true;
            }
        }
        else{
            return false;
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        sTimeBasicGroup = new javax.swing.ButtonGroup();
        sTimeAdvGroup = new javax.swing.ButtonGroup();
        eTimeAdvGroup = new javax.swing.ButtonGroup();
        meetingTabPane = new javax.swing.JTabbedPane();
        basicTab = new javax.swing.JPanel();
        titleTextBox = new javax.swing.JTextField();
        titleLabel = new javax.swing.JLabel();
        startTimeLabel = new javax.swing.JLabel();
        sHTextBoxBasic = new javax.swing.JTextField();
        pointsLabel = new javax.swing.JLabel();
        givenLabelBasic = new javax.swing.JLabel();
        requiredLabelBasic = new javax.swing.JLabel();
        givenTextBoxBasic = new javax.swing.JTextField();
        requiredTextBoxBasic = new javax.swing.JTextField();
        startDateLabel = new javax.swing.JLabel();
        sDMonth = new javax.swing.JComboBox();
        sDDay = new javax.swing.JComboBox();
        sDYear = new javax.swing.JComboBox();
        sTCLabelBasic = new javax.swing.JLabel();
        sMTextBoxBasic = new javax.swing.JTextField();
        sTAMRadioButtonBasic = new javax.swing.JRadioButton();
        sTPMRadioButtonBasic = new javax.swing.JRadioButton();
        sT24RadioButtonBasic = new javax.swing.JRadioButton();
        advancedTab = new javax.swing.JPanel();
        sundayLabel = new javax.swing.JLabel();
        mondayLabel = new javax.swing.JLabel();
        tuesdayLabel = new javax.swing.JLabel();
        wednesdayLabel = new javax.swing.JLabel();
        thursdayLabel = new javax.swing.JLabel();
        saturdayButton = new javax.swing.JRadioButton();
        mondayButton = new javax.swing.JRadioButton();
        tuesdayButton = new javax.swing.JRadioButton();
        wednesdayButton = new javax.swing.JRadioButton();
        sundayButton = new javax.swing.JRadioButton();
        thursdayButton = new javax.swing.JRadioButton();
        fridayButton = new javax.swing.JRadioButton();
        fridayLabel = new javax.swing.JLabel();
        saturdayLabel = new javax.swing.JLabel();
        sTLabelAdv = new javax.swing.JLabel();
        endTimeLabelAdv = new javax.swing.JLabel();
        invisibleHelper = new javax.swing.JLabel();
        eHTextBoxAdv = new javax.swing.JTextField();
        eMTextBoxAdv = new javax.swing.JTextField();
        eTCLabelAdv = new javax.swing.JLabel();
        sHTextBoxAdv = new javax.swing.JTextField();
        sTCLabelAdv = new javax.swing.JLabel();
        sMTextBoxAdv = new javax.swing.JTextField();
        sTAMRadioButtonAdv = new javax.swing.JRadioButton();
        sTPMRadioButtonAdv = new javax.swing.JRadioButton();
        sT24RadioButtonAdv = new javax.swing.JRadioButton();
        eTAMRadioButtonAdv = new javax.swing.JRadioButton();
        eTPMRadioButtonAdv = new javax.swing.JRadioButton();
        iET24RadioButtonAdv = new javax.swing.JRadioButton();
        requiredPointsTab = new javax.swing.JPanel();
        givenLabelPoints = new javax.swing.JLabel();
        requiredLabel = new javax.swing.JLabel();
        givenTextBoxPoints = new javax.swing.JTextField();
        requiredTextBoxPoints = new javax.swing.JTextField();
        lateLabelPoints = new javax.swing.JLabel();
        lateTextBoxPoints = new javax.swing.JTextField();

        setName("Metting SetUp"); // NOI18N
        setPreferredSize(new java.awt.Dimension(261, 407));

        meetingTabPane.setMinimumSize(new java.awt.Dimension(152, 290));
        meetingTabPane.setPreferredSize(new java.awt.Dimension(261, 275));

        basicTab.setPreferredSize(new java.awt.Dimension(256, 260));
        basicTab.setRequestFocusEnabled(false);
        basicTab.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.ipadx = 183;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 5, 0, 0);
        basicTab.add(titleTextBox, gridBagConstraints);

        titleLabel.setText("Title");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 6, 0, 0);
        basicTab.add(titleLabel, gridBagConstraints);

        startTimeLabel.setText("Start Time");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 6, 0, 0);
        basicTab.add(startTimeLabel, gridBagConstraints);

        sHTextBoxBasic.setBackground(new java.awt.Color(255, 0, 0));
        sHTextBoxBasic.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        sHTextBoxBasic.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                sHTextBoxBasicKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.ipadx = 26;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 0);
        basicTab.add(sHTextBoxBasic, gridBagConstraints);

        pointsLabel.setText("Points:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 0);
        basicTab.add(pointsLabel, gridBagConstraints);

        givenLabelBasic.setText("Given");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.ipadx = 16;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 0);
        basicTab.add(givenLabelBasic, gridBagConstraints);

        requiredLabelBasic.setText("Required");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 10, 0, 0);
        basicTab.add(requiredLabelBasic, gridBagConstraints);

        givenTextBoxBasic.setBackground(java.awt.Color.green);
        givenTextBoxBasic.setText("0");
        givenTextBoxBasic.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                givenTextBoxBasicKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 37;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(7, 6, 7, 0);
        basicTab.add(givenTextBoxBasic, gridBagConstraints);

        requiredTextBoxBasic.setBackground(new java.awt.Color(0, 255, 0));
        requiredTextBoxBasic.setText("0");
        requiredTextBoxBasic.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                requiredTextBoxBasicKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 37;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 10, 7, 0);
        basicTab.add(requiredTextBoxBasic, gridBagConstraints);

        startDateLabel.setText("Start Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 0);
        basicTab.add(startDateLabel, gridBagConstraints);

        sDMonth.setModel(months);
        sDMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sDMonthActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 70, 0, 0);
        basicTab.add(sDMonth, gridBagConstraints);

        sDDay.setModel(days);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 167, 0, 0);
        basicTab.add(sDDay, gridBagConstraints);

        sDYear.setModel(years);
        sDYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sDYearActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 5, 0, 0);
        basicTab.add(sDYear, gridBagConstraints);

        sTCLabelBasic.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        sTCLabelBasic.setText(":");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new java.awt.Insets(4, 18, 0, 5);
        basicTab.add(sTCLabelBasic, gridBagConstraints);

        sMTextBoxBasic.setBackground(new java.awt.Color(255, 0, 0));
        sMTextBoxBasic.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                sMTextBoxBasicKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipadx = 26;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        basicTab.add(sMTextBoxBasic, gridBagConstraints);

        sTimeBasicGroup.add(sTAMRadioButtonBasic);
        sTAMRadioButtonBasic.setSelected(true);
        sTAMRadioButtonBasic.setText("AM");
        sTAMRadioButtonBasic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sTAMRadioButtonBasicMouseReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        basicTab.add(sTAMRadioButtonBasic, gridBagConstraints);

        sTimeBasicGroup.add(sTPMRadioButtonBasic);
        sTPMRadioButtonBasic.setText("PM");
        sTPMRadioButtonBasic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sTPMRadioButtonBasicMouseReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        basicTab.add(sTPMRadioButtonBasic, gridBagConstraints);

        sTimeBasicGroup.add(sT24RadioButtonBasic);
        sT24RadioButtonBasic.setText("24hr");
        sT24RadioButtonBasic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sT24RadioButtonBasicMouseReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        basicTab.add(sT24RadioButtonBasic, gridBagConstraints);

        meetingTabPane.addTab("Basic", basicTab);

        java.awt.GridBagLayout advancedTabLayout = new java.awt.GridBagLayout();
        advancedTabLayout.columnWidths = new int[] {0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0};
        advancedTabLayout.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        advancedTab.setLayout(advancedTabLayout);

        sundayLabel.setText("S");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        advancedTab.add(sundayLabel, gridBagConstraints);

        mondayLabel.setText("M");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        advancedTab.add(mondayLabel, gridBagConstraints);

        tuesdayLabel.setText("T");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        advancedTab.add(tuesdayLabel, gridBagConstraints);

        wednesdayLabel.setText("W");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        advancedTab.add(wednesdayLabel, gridBagConstraints);

        thursdayLabel.setText("Th");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 14);
        advancedTab.add(thursdayLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        advancedTab.add(saturdayButton, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        advancedTab.add(mondayButton, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        advancedTab.add(tuesdayButton, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        advancedTab.add(wednesdayButton, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        advancedTab.add(sundayButton, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 13);
        advancedTab.add(thursdayButton, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.insets = new java.awt.Insets(0, 26, 0, 0);
        advancedTab.add(fridayButton, gridBagConstraints);

        fridayLabel.setText("F");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.insets = new java.awt.Insets(0, 59, 0, 0);
        advancedTab.add(fridayLabel, gridBagConstraints);

        saturdayLabel.setText("S");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.insets = new java.awt.Insets(0, 39, 0, 0);
        advancedTab.add(saturdayLabel, gridBagConstraints);

        sTLabelAdv.setText("Start Time");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        advancedTab.add(sTLabelAdv, gridBagConstraints);

        endTimeLabelAdv.setText("End Time");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 8);
        advancedTab.add(endTimeLabelAdv, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.ipady = 125;
        advancedTab.add(invisibleHelper, gridBagConstraints);

        eHTextBoxAdv.setBackground(new java.awt.Color(0, 255, 0));
        eHTextBoxAdv.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        eHTextBoxAdv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                eHTextBoxAdvKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 26;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        advancedTab.add(eHTextBoxAdv, gridBagConstraints);

        eMTextBoxAdv.setBackground(new java.awt.Color(0, 255, 0));
        eMTextBoxAdv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                eMTextBoxAdvKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 26;
        gridBagConstraints.insets = new java.awt.Insets(2, 3, 0, 0);
        advancedTab.add(eMTextBoxAdv, gridBagConstraints);

        eTCLabelAdv.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        eTCLabelAdv.setText(":");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.insets = new java.awt.Insets(2, 19, 4, 0);
        advancedTab.add(eTCLabelAdv, gridBagConstraints);

        sHTextBoxAdv.setBackground(new java.awt.Color(255, 0, 0));
        sHTextBoxAdv.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        sHTextBoxAdv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                sHTextBoxAdvKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 26;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        advancedTab.add(sHTextBoxAdv, gridBagConstraints);

        sTCLabelAdv.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        sTCLabelAdv.setText(":");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.insets = new java.awt.Insets(2, 19, 4, 0);
        advancedTab.add(sTCLabelAdv, gridBagConstraints);

        sMTextBoxAdv.setBackground(new java.awt.Color(255, 0, 0));
        sMTextBoxAdv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                sMTextBoxAdvKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 26;
        gridBagConstraints.insets = new java.awt.Insets(2, 3, 0, 0);
        advancedTab.add(sMTextBoxAdv, gridBagConstraints);

        sTimeAdvGroup.add(sTAMRadioButtonAdv);
        sTAMRadioButtonAdv.setSelected(true);
        sTAMRadioButtonAdv.setText("AM");
        sTAMRadioButtonAdv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sTAMRadioButtonAdvMouseReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.insets = new java.awt.Insets(0, 11, 0, 29);
        advancedTab.add(sTAMRadioButtonAdv, gridBagConstraints);

        sTimeAdvGroup.add(sTPMRadioButtonAdv);
        sTPMRadioButtonAdv.setText("PM");
        sTPMRadioButtonAdv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sTPMRadioButtonAdvMouseReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.insets = new java.awt.Insets(0, 27, 0, 8);
        advancedTab.add(sTPMRadioButtonAdv, gridBagConstraints);

        sTimeAdvGroup.add(sT24RadioButtonAdv);
        sT24RadioButtonAdv.setText("24hr");
        sT24RadioButtonAdv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sT24RadioButtonAdvMouseReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.insets = new java.awt.Insets(0, 39, 0, 0);
        advancedTab.add(sT24RadioButtonAdv, gridBagConstraints);

        eTimeAdvGroup.add(eTAMRadioButtonAdv);
        eTAMRadioButtonAdv.setSelected(true);
        eTAMRadioButtonAdv.setText("AM");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.insets = new java.awt.Insets(0, 11, 0, 29);
        advancedTab.add(eTAMRadioButtonAdv, gridBagConstraints);

        eTimeAdvGroup.add(eTPMRadioButtonAdv);
        eTPMRadioButtonAdv.setText("PM");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.insets = new java.awt.Insets(0, 27, 0, 8);
        advancedTab.add(eTPMRadioButtonAdv, gridBagConstraints);

        eTimeAdvGroup.add(iET24RadioButtonAdv);
        iET24RadioButtonAdv.setText("24");
        iET24RadioButtonAdv.setFocusable(false);
        iET24RadioButtonAdv.setOpaque(false);
        iET24RadioButtonAdv.setRequestFocusEnabled(false);
        iET24RadioButtonAdv.setRolloverEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.insets = new java.awt.Insets(0, 47, 0, 0);
        advancedTab.add(iET24RadioButtonAdv, gridBagConstraints);

        meetingTabPane.addTab("Advanced", advancedTab);

        givenLabelPoints.setText("Given:");

        requiredLabel.setText("Required:");

        givenTextBoxPoints.setBackground(new java.awt.Color(0, 255, 0));
        givenTextBoxPoints.setText("0");
        givenTextBoxPoints.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                givenTextBoxPointsKeyReleased(evt);
            }
        });

        requiredTextBoxPoints.setBackground(new java.awt.Color(0, 255, 0));
        requiredTextBoxPoints.setText("0");
        requiredTextBoxPoints.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                requiredTextBoxPointsKeyReleased(evt);
            }
        });

        lateLabelPoints.setText("Late:");

        lateTextBoxPoints.setBackground(new java.awt.Color(0, 255, 0));
        lateTextBoxPoints.setText("0");
        lateTextBoxPoints.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lateTextBoxPointsKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout requiredPointsTabLayout = new javax.swing.GroupLayout(requiredPointsTab);
        requiredPointsTab.setLayout(requiredPointsTabLayout);
        requiredPointsTabLayout.setHorizontalGroup(
            requiredPointsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(requiredPointsTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(requiredPointsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(givenLabelPoints)
                    .addComponent(requiredLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lateLabelPoints)
                    .addComponent(givenTextBoxPoints)
                    .addComponent(requiredTextBoxPoints)
                    .addComponent(lateTextBoxPoints))
                .addContainerGap(199, Short.MAX_VALUE))
        );
        requiredPointsTabLayout.setVerticalGroup(
            requiredPointsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(requiredPointsTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(givenLabelPoints)
                .addGap(4, 4, 4)
                .addComponent(givenTextBoxPoints, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(requiredLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(requiredTextBoxPoints, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lateLabelPoints)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lateTextBoxPoints, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(86, Short.MAX_VALUE))
        );

        meetingTabPane.addTab("Points", requiredPointsTab);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(meetingTabPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(meetingTabPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void sDYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sDYearActionPerformed
        refreshMonths();
    }//GEN-LAST:event_sDYearActionPerformed

    private void sDMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sDMonthActionPerformed
        refreshDays();
    }//GEN-LAST:event_sDMonthActionPerformed

    private void givenTextBoxBasicKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_givenTextBoxBasicKeyReleased
        areValidPoints("given");
        givenTextBoxPoints.setText(givenTextBoxBasic.getText());
    }//GEN-LAST:event_givenTextBoxBasicKeyReleased

    private void requiredTextBoxPointsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_requiredTextBoxPointsKeyReleased
        areValidPoints("required");
        requiredTextBoxBasic.setText(requiredTextBoxPoints.getText());
    }//GEN-LAST:event_requiredTextBoxPointsKeyReleased

    private void givenTextBoxPointsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_givenTextBoxPointsKeyReleased
        areValidPoints("given");
        givenTextBoxBasic.setText(givenTextBoxPoints.getText());
    }//GEN-LAST:event_givenTextBoxPointsKeyReleased

    private void sHTextBoxBasicKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sHTextBoxBasicKeyReleased
        colorTimes("startBasic");
    }//GEN-LAST:event_sHTextBoxBasicKeyReleased

    private void requiredTextBoxBasicKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_requiredTextBoxBasicKeyReleased
        areValidPoints("required");
        requiredTextBoxPoints.setText(requiredTextBoxBasic.getText());
    }//GEN-LAST:event_requiredTextBoxBasicKeyReleased

    private void lateTextBoxPointsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lateTextBoxPointsKeyReleased
        areValidPoints("late");
    }//GEN-LAST:event_lateTextBoxPointsKeyReleased

    private void sHTextBoxAdvKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sHTextBoxAdvKeyReleased
        colorTimes("startAdvanced");
    }//GEN-LAST:event_sHTextBoxAdvKeyReleased

    private void sMTextBoxAdvKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sMTextBoxAdvKeyReleased
        colorTimes("startAdvanced");
    }//GEN-LAST:event_sMTextBoxAdvKeyReleased

    private void sMTextBoxBasicKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sMTextBoxBasicKeyReleased
        colorTimes("startBasic");
    }//GEN-LAST:event_sMTextBoxBasicKeyReleased

    private void sTAMRadioButtonBasicMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sTAMRadioButtonBasicMouseReleased
        sTAMRadioButtonAdv.setSelected(sTAMRadioButtonBasic.isSelected());
        eTAMRadioButtonAdv.setFocusable(true);
        eTPMRadioButtonAdv.setFocusable(true);
        tFormatChange();
    }//GEN-LAST:event_sTAMRadioButtonBasicMouseReleased

    private void sTPMRadioButtonBasicMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sTPMRadioButtonBasicMouseReleased
        sTPMRadioButtonAdv.setSelected(sTPMRadioButtonBasic.isSelected());
        eTAMRadioButtonAdv.setFocusable(true);
        eTPMRadioButtonAdv.setFocusable(true);
        tFormatChange();
    }//GEN-LAST:event_sTPMRadioButtonBasicMouseReleased

    private void sT24RadioButtonBasicMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sT24RadioButtonBasicMouseReleased
        sT24RadioButtonAdv.setSelected(sT24RadioButtonBasic.isSelected());
        iET24RadioButtonAdv.setSelected(sT24RadioButtonBasic.isSelected());
        eTAMRadioButtonAdv.setFocusable(false);
        eTPMRadioButtonAdv.setFocusable(false);
        tFormatChange();
    }//GEN-LAST:event_sT24RadioButtonBasicMouseReleased

    private void sTAMRadioButtonAdvMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sTAMRadioButtonAdvMouseReleased
        sTAMRadioButtonBasic.setSelected(sTAMRadioButtonAdv.isSelected());
        eTAMRadioButtonAdv.setFocusable(true);
        eTPMRadioButtonAdv.setFocusable(true);
        tFormatChange();
    }//GEN-LAST:event_sTAMRadioButtonAdvMouseReleased

    private void sTPMRadioButtonAdvMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sTPMRadioButtonAdvMouseReleased
        sTPMRadioButtonBasic.setSelected(sTPMRadioButtonAdv.isSelected());
        eTAMRadioButtonAdv.setFocusable(true);
        eTPMRadioButtonAdv.setFocusable(true);
        tFormatChange();
    }//GEN-LAST:event_sTPMRadioButtonAdvMouseReleased

    private void sT24RadioButtonAdvMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sT24RadioButtonAdvMouseReleased
        sT24RadioButtonBasic.setSelected(sT24RadioButtonAdv.isSelected());
        iET24RadioButtonAdv.setSelected(sT24RadioButtonBasic.isSelected());
        eTAMRadioButtonAdv.setFocusable(false);
        eTPMRadioButtonAdv.setFocusable(false);
        tFormatChange();
    }//GEN-LAST:event_sT24RadioButtonAdvMouseReleased

    private void eHTextBoxAdvKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eHTextBoxAdvKeyReleased
        colorTimes("end");
    }//GEN-LAST:event_eHTextBoxAdvKeyReleased

    private void eMTextBoxAdvKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eMTextBoxAdvKeyReleased
        colorTimes("end");
    }//GEN-LAST:event_eMTextBoxAdvKeyReleased
    
    public String getCreateButton(){
        return CREATE_BUTTON;
    }
    
    public String getFinishButton(){
        return FINISH_BUTTON;
    }
    
    public JTabbedPane getMeetingTabbedPane(){
        return meetingTabPane;
    }
    
    public void clearData(){
        eHTextBoxAdv.setText("");
        eMTextBoxAdv.setText("");
        sHTextBoxAdv.setText("");
        sMTextBoxAdv.setText("");
        sHTextBoxBasic.setText("");
        sMTextBoxBasic.setText("");
        fridayButton.setSelected(false);
        givenTextBoxBasic.setText("");
        givenTextBoxPoints.setText("");
        lateTextBoxPoints.setText("");
        mondayButton.setSelected(false);
        requiredTextBoxBasic.setText("");
        requiredTextBoxPoints.setText("");
        initModels();
        saturdayButton.setSelected(false);
        sundayButton.setSelected(false);
        thursdayButton.setSelected(false);
        sHTextBoxBasic.setText("");
        titleTextBox.setText("");
        tuesdayButton.setSelected(false);
        wednesdayButton.setSelected(false);
        sTAMRadioButtonBasic.setSelected(true);
        sTAMRadioButtonBasic.setSelected(false);
        sTAMRadioButtonAdv.setSelected(true);
        sTAMRadioButtonAdv.setSelected(false);
        eTAMRadioButtonAdv.setSelected(true);
        eTAMRadioButtonAdv.setSelected(false);
        colorTimes("startBasic");
        colorTimes("startAdvanced");
        colorTimes("end");
    }
    
    public void putData(Meeting m) {
        ArrayList<Object> p=parseMeetingData(m);
        titleTextBox.setText((String)p.get(0));
        sDYear.setSelectedItem(Integer.parseInt((String)p.get(1)));
        refreshMonths();
        sDMonth.setSelectedIndex(getMonthNumber((String)p.get(2))-getBeginningMonth());
        refreshDays();
        sDDay.setSelectedItem(Integer.parseInt((String)p.get(3)));
        sHTextBoxBasic.setText((String)p.get(4));
        sMTextBoxBasic.setText((String)p.get(5));
        String sampm=(String)p.get(6);
        switch(sampm.toLowerCase()){
            case "pm":
                sTPMRadioButtonBasic.setSelected(true);
                sTPMRadioButtonAdv.setSelected(true);
                break;
            case "":
                sT24RadioButtonAdv.setSelected(true);
                iET24RadioButtonAdv.setSelected(true);
                eTAMRadioButtonAdv.setFocusable(false);
                eTPMRadioButtonAdv.setFocusable(false);
                break;
        }
        eHTextBoxAdv.setText((String)p.get(7));
        eMTextBoxAdv.setText((String)p.get(8));
        if(!sT24RadioButtonBasic.isSelected()){
            String eampm=(String)p.get(9);
            switch(eampm.toLowerCase()){
                case "pm":
                    eTPMRadioButtonAdv.setSelected(true);
                    break;
            }
        }
        colorTimes("startBasic");
        colorTimes("startAdvanced");
        colorTimes("end");
        String r=(Integer)p.get(10)+"";
        sundayButton.setSelected(r.contains("1"));
        mondayButton.setSelected(r.contains("2"));
        tuesdayButton.setSelected(r.contains("3"));
        wednesdayButton.setSelected(r.contains("4"));
        thursdayButton.setSelected(r.contains("5"));
        fridayButton.setSelected(r.contains("6"));
        saturdayButton.setSelected(r.contains("7"));
        givenTextBoxBasic.setText((Integer)p.get(11)+"");
        givenTextBoxPoints.setText((Integer)p.get(11)+"");
        requiredTextBoxBasic.setText((Integer)p.get(12)+"");
        requiredTextBoxPoints.setText((Integer)p.get(12)+"");
        lateTextBoxPoints.setText((Integer)p.get(13)+"");
    }
    
    private ArrayList<Object> parseMeetingData(Meeting m){
        ArrayList<Object> p=new ArrayList<>();
        String temp=m.getDate();
        ArrayList<Character> d=new ArrayList<>();
        for(int i=0;i<temp.length();i++){
            d.add(temp.charAt(i));
        }
        ArrayList<Character> y=new ArrayList<>();
        for(int i=d.size()-1;i>=0;i--){
           if(d.get(i)!='/'){
               y.add(d.get(i));
               d.remove(i);
           } else{
               d.remove(i);
               break;
           }
        }
        ArrayList<Character> da=new ArrayList<>();
        for(int i=d.size()-1;i>=0;i--){
           if(d.get(i)!='/'){
               da.add(d.get(i));
               d.remove(i);
           } else{
               d.remove(i);
               break;
           }
        }
        ArrayList<Character> mo=new ArrayList<>();
        for(int i=d.size()-1;i>=0;i--){
           if(d.get(i)!='/'){
               mo.add(d.get(i));
               d.remove(i);
           }
        }
        ArrayList<Character> atemp=new ArrayList<>();
        for(int i=0;i<y.size();i++){
            atemp.add(y.get(i));
        }
        y=new ArrayList<>();
        for(int i=atemp.size()-1;i>=0;i--){
            y.add(atemp.get(i));
        }
        String sy="";
        for(char c:y){
            sy+=c;
        }
        atemp=new ArrayList<>();
        for(int i=0;i<mo.size();i++){
            atemp.add(mo.get(i));
        }
        mo=new ArrayList<>();
        for(int i=atemp.size()-1;i>=0;i--){
            mo.add(atemp.get(i));
        }
        String smo="";
        for(char c:mo){
            smo+=c;
        }
        atemp=new ArrayList<>();
        for(int i=0;i<da.size();i++){
            atemp.add(da.get(i));
        }
        da=new ArrayList<>();
        for(int i=atemp.size()-1;i>=0;i--){
            da.add(atemp.get(i));
        }
        String sda="";
        for(char c:da){
            sda+=c;
        }
        String s=m.getStartTime().substring(0, m.getStartTime().length()-3);
        String sampm=m.getStartTime().substring(m.getStartTime().length()-2);
        String et;
        String et1;
        String et2;
        if(m.getEndTime().equals("")){
            et=m.getEndTime();
            et1="";
            et2="";
        }else{
            String e=m.getEndTime().substring(0, m.getEndTime().length()-3);
            et2=m.getEndTime().substring(m.getEndTime().length()-2);
            et=s.substring(0,e.indexOf(' '));
            et1=s.substring(e.indexOf(' ')+1);
        }
        p.add(m.getName());
        p.add(sy);
        p.add(smo);
        p.add(sda);
        p.add(s.substring(0,s.indexOf(' ')));
        p.add(s.substring(s.indexOf(' ')+1));
        p.add(sampm);
        p.add(et);
        p.add(et1);
        p.add(et2);
        p.add(m.getReocurringDays());
        p.add(m.getgPoints());
        p.add(m.getrPoints());
        p.add(m.getlPoints());
        return p;
    }
    
    /*
     * @return false if time is not given(text box is empty)
     */
    public boolean isStartTimeGiven(){
        return getStartTime(false).equals(":");
    }
    
    public String getStartTime(boolean ampm){
        if(ampm){
            if(sTAMRadioButtonBasic.isSelected()){
                return sHTextBoxBasic.getText()+":"+sMTextBoxBasic.getText()+" AM";
            }else if(sTPMRadioButtonBasic.isSelected()){
                return sHTextBoxBasic.getText()+":"+sMTextBoxBasic.getText()+" PM";
            }
        }
        return sHTextBoxBasic.getText()+":"+sMTextBoxBasic.getText();
    }
    
    public String getEndTime(boolean ampm){
        if(ampm){
            if(eTAMRadioButtonAdv.isSelected()){
                return eHTextBoxAdv.getText()+":"+eMTextBoxAdv.getText()+" AM";
            }else if(eTPMRadioButtonAdv.isSelected()){
                return eHTextBoxAdv.getText()+":"+eMTextBoxAdv.getText()+" PM";
            }
        }
        return eHTextBoxAdv.getText()+":"+eMTextBoxAdv.getText();
    }
    
    public String getStartHour(){
        return sHTextBoxBasic.getText();
    }
    
    public String getStartMinute(){
        return sMTextBoxBasic.getText();
    }
    
    public String getEndHour(){
        return eHTextBoxAdv.getText();
    }
    
    public String getEndMinute(){
        return eMTextBoxAdv.getText();
    }
    
    public String[] getValues() {
        String[] values=new String[9];
        if(Validator.isValidName(titleTextBox.getText())){
            values[0]=titleTextBox.getText();
            values[8]="false";
        }else{
            values[0]=sDMonth.getSelectedItem()+"/"+sDDay.getSelectedItem()+"/"+sDYear.getSelectedItem()+"    "+Validator.replaceColons(getStartTime(true));
            values[8]="true";
        }
        values[1]=sDMonth.getSelectedItem()+"/"+sDDay.getSelectedItem()+"/"+sDYear.getSelectedItem();
        values[2]=Validator.replaceColons(getStartTime(true));
        if(!(eHTextBoxAdv.getText().equals("")||eHTextBoxAdv.getText()==null)){
            values[3]=Validator.replaceColons(getEndTime(true));
        }else{
            values[3]="";
        }
        values[4]=getReocurring();
        if(areValidPoints("all")){ 
            values[5]=givenTextBoxBasic.getText();
            values[6]=requiredTextBoxBasic.getText();
            values[7]=lateTextBoxPoints.getText();
        }
        return values;
    }
    
    public String getReocurring(){
        String temp="";
        if(sundayButton.isSelected()){
            temp+=1;
        }
        if(mondayButton.isSelected()){
            temp+=2;
        }
        if(tuesdayButton.isSelected()){
            temp+=3;
        }
        if(wednesdayButton.isSelected()){
            temp+=4;
        }
        if(thursdayButton.isSelected()){
            temp+=5;
        }
        if(fridayButton.isSelected()){
            temp+=6;
        }
        if(saturdayButton.isSelected()){
            temp+=7;
        }
        if(temp.equals("")){
            return 0+"";
        }
        return temp;
    }
    
    public boolean is24Hour(){
        return sT24RadioButtonBasic.isSelected();
    }
    
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    private boolean areValidPoints(String type){
        switch(type.toLowerCase()){
            case "given":
                if(!Validator.isValidPoints(givenTextBoxBasic.getText())){
                    givenTextBoxBasic.setBackground(Color.RED);
                    givenTextBoxPoints.setBackground(Color.RED);
                    return false;
                }else{
                    givenTextBoxBasic.setBackground(Color.GREEN);
                    givenTextBoxPoints.setBackground(Color.GREEN);
                }
                break;
            case "late":
                if(!Validator.isValidPoints(lateTextBoxPoints.getText())){
                    lateTextBoxPoints.setBackground(Color.RED);
                    return false;
                }else{
                    lateTextBoxPoints.setBackground(Color.GREEN);
                }
                break;
            case "required":
                if(!Validator.isValidPoints(requiredTextBoxBasic.getText())){
                    requiredTextBoxBasic.setBackground(Color.RED);
                    requiredTextBoxPoints.setBackground(Color.RED);
                    return false;
                }else{
                    requiredTextBoxBasic.setBackground(Color.GREEN);
                    requiredTextBoxPoints.setBackground(Color.GREEN);
                }
                break;
            default:
                areValidPoints("given");
                areValidPoints("late");
                areValidPoints("required");
        }
        return true;
    }
    
    private void tFormatChange(){
        if(!(Validator.isValidTime(sHTextBoxBasic.getText(), sMTextBoxBasic.getText(), true, is24Hour())&&Validator.isValidTime(eHTextBoxAdv.getText(), eMTextBoxAdv.getText(), false, is24Hour()))){
            if(!(Validator.isValidTime(sHTextBoxBasic.getText(), sMTextBoxBasic.getText(), true, !is24Hour())&&Validator.isValidTime(eHTextBoxAdv.getText(), eMTextBoxAdv.getText(), false, !is24Hour()))){
                return;
            }
        }
        if(startLastSelected==2){
            int i=new Integer(sHTextBoxBasic.getText());
            if(i>12){
                sHTextBoxBasic.setText(i-12+"");
                sHTextBoxAdv.setText(i-12+"");
            }else if(i==0){
                sHTextBoxBasic.setText(12+"");
                sHTextBoxAdv.setText(12+"");
            }
            i=new Integer(eHTextBoxAdv.getText());
            if(i>12){
                eHTextBoxAdv.setText(i-12+"");
            }else if(i==0){
                eHTextBoxAdv.setText(12+"");
            }
        }else if(is24Hour()){
            if(startLastSelected==1){
                int i=new Integer(sHTextBoxBasic.getText());
                sHTextBoxBasic.setText(i+12+"");
                sHTextBoxAdv.setText(i+12+"");
            }
            if(endLastSelected==1){
                int i=new Integer(eHTextBoxAdv.getText());
                eHTextBoxAdv.setText(i+12+"");
            }
        }
        setLastSelected();
    }
    
    private void setLastSelected(){
        if(is24Hour()){
            startLastSelected=2;
            endLastSelected=2;
        }else{
            if(sTAMRadioButtonBasic.isSelected()){
                startLastSelected=0;
            }else{
                startLastSelected=1;
            }
            if(eTAMRadioButtonAdv.isSelected()){
                endLastSelected=0;
            }else{
                endLastSelected=1;
            }
        }
    }
    
    private void colorTimes(String timePeriod){
        switch(timePeriod.toLowerCase()){
            case "startbasic":
                sHTextBoxAdv.setText(sHTextBoxBasic.getText());
                sMTextBoxAdv.setText(sMTextBoxBasic.getText());
                if(Validator.isValidTime(sHTextBoxBasic.getText(), sMTextBoxBasic.getText(), true, is24Hour())){
                    sMTextBoxBasic.setBackground(Color.GREEN);
                    sMTextBoxAdv.setBackground(Color.GREEN);
                    sHTextBoxBasic.setBackground(Color.GREEN);
                    sHTextBoxAdv.setBackground(Color.GREEN);
                }else{
                    sHTextBoxBasic.setBackground(Color.RED);
                    sHTextBoxAdv.setBackground(Color.RED);
                    sMTextBoxBasic.setBackground(Color.RED);
                    sMTextBoxAdv.setBackground(Color.RED);
                }
                break;
            case "startadvanced":
                sHTextBoxBasic.setText(sHTextBoxAdv.getText());
                sMTextBoxBasic.setText(sMTextBoxAdv.getText());
                if(Validator.isValidTime(sHTextBoxBasic.getText(), sMTextBoxBasic.getText(), true, is24Hour())){
                    sMTextBoxBasic.setBackground(Color.GREEN);
                    sMTextBoxAdv.setBackground(Color.GREEN);
                    sHTextBoxBasic.setBackground(Color.GREEN);
                    sHTextBoxAdv.setBackground(Color.GREEN);
                }else{
                    sHTextBoxBasic.setBackground(Color.RED);
                    sHTextBoxAdv.setBackground(Color.RED);
                    sMTextBoxBasic.setBackground(Color.RED);
                    sMTextBoxAdv.setBackground(Color.RED);
                }
                break;
            case "end":
                if(Validator.isValidTime(eHTextBoxAdv.getText(), eMTextBoxAdv.getText(), false, is24Hour())){
                    eMTextBoxAdv.setBackground(Color.GREEN);
                    eHTextBoxAdv.setBackground(Color.GREEN);
                }else{
                    eHTextBoxAdv.setBackground(Color.RED);
                    eMTextBoxAdv.setBackground(Color.RED);
                }
                break;
        }
    }
    
    private static int startLastSelected;
    private static int endLastSelected;
    private static Calendar c;
    private static DefaultComboBoxModel days;
    private static DefaultComboBoxModel months;
    private static DefaultComboBoxModel years;
    private final String CREATE_BUTTON="Create";
    private final String FINISH_BUTTON="Finish";
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel advancedTab;
    private javax.swing.JPanel basicTab;
    private javax.swing.JTextField eHTextBoxAdv;
    private javax.swing.JTextField eMTextBoxAdv;
    private javax.swing.JRadioButton eTAMRadioButtonAdv;
    private javax.swing.JLabel eTCLabelAdv;
    private javax.swing.JRadioButton eTPMRadioButtonAdv;
    private javax.swing.ButtonGroup eTimeAdvGroup;
    private javax.swing.JLabel endTimeLabelAdv;
    private javax.swing.JRadioButton fridayButton;
    private javax.swing.JLabel fridayLabel;
    private javax.swing.JLabel givenLabelBasic;
    private javax.swing.JLabel givenLabelPoints;
    private javax.swing.JTextField givenTextBoxBasic;
    private javax.swing.JTextField givenTextBoxPoints;
    private javax.swing.JRadioButton iET24RadioButtonAdv;
    private javax.swing.JLabel invisibleHelper;
    private javax.swing.JLabel lateLabelPoints;
    private javax.swing.JTextField lateTextBoxPoints;
    private javax.swing.JTabbedPane meetingTabPane;
    private javax.swing.JRadioButton mondayButton;
    private javax.swing.JLabel mondayLabel;
    private javax.swing.JLabel pointsLabel;
    private javax.swing.JLabel requiredLabel;
    private javax.swing.JLabel requiredLabelBasic;
    private javax.swing.JPanel requiredPointsTab;
    private javax.swing.JTextField requiredTextBoxBasic;
    private javax.swing.JTextField requiredTextBoxPoints;
    private javax.swing.JComboBox sDDay;
    private javax.swing.JComboBox sDMonth;
    private javax.swing.JComboBox sDYear;
    private javax.swing.JTextField sHTextBoxAdv;
    private javax.swing.JTextField sHTextBoxBasic;
    private javax.swing.JTextField sMTextBoxAdv;
    private javax.swing.JTextField sMTextBoxBasic;
    private javax.swing.JRadioButton sT24RadioButtonAdv;
    private javax.swing.JRadioButton sT24RadioButtonBasic;
    private javax.swing.JRadioButton sTAMRadioButtonAdv;
    private javax.swing.JRadioButton sTAMRadioButtonBasic;
    private javax.swing.JLabel sTCLabelAdv;
    private javax.swing.JLabel sTCLabelBasic;
    private javax.swing.JLabel sTLabelAdv;
    private javax.swing.JRadioButton sTPMRadioButtonAdv;
    private javax.swing.JRadioButton sTPMRadioButtonBasic;
    private javax.swing.ButtonGroup sTimeAdvGroup;
    private javax.swing.ButtonGroup sTimeBasicGroup;
    private javax.swing.JRadioButton saturdayButton;
    private javax.swing.JLabel saturdayLabel;
    private javax.swing.JLabel startDateLabel;
    private javax.swing.JLabel startTimeLabel;
    private javax.swing.JRadioButton sundayButton;
    private javax.swing.JLabel sundayLabel;
    private javax.swing.JRadioButton thursdayButton;
    private javax.swing.JLabel thursdayLabel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JTextField titleTextBox;
    private javax.swing.JRadioButton tuesdayButton;
    private javax.swing.JLabel tuesdayLabel;
    private javax.swing.JRadioButton wednesdayButton;
    private javax.swing.JLabel wednesdayLabel;
    // End of variables declaration//GEN-END:variables
}