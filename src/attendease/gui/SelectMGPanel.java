/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SelectPanel.java
 *
 * Created on Oct 9, 2013, 10:44:38 AM
 */

package attendease.gui;

import attendease.util.FrameController;
import attendease.util.Start;
import java.awt.Font;
import java.util.List;
import javax.swing.DefaultListModel;

/**
 *
 * @author sterling.long
 */
public class SelectMGPanel extends javax.swing.JPanel {

    /** Creates new form SelectPanel */
    public SelectMGPanel() {
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
        isGroup=true;
        buttonsEnabled=false;
        setCurrentGroup("");
        setCurrentMeeting("");
        groupList=new DefaultListModel();
        meetingList=new DefaultListModel();
        fillList("Clubs");
    }
    
    private void postInit(){
        toggleButtons(false);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        qeButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        addButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        selectButton = new javax.swing.JButton();
        gmListScrollPane = new javax.swing.JScrollPane();
        gmList = new javax.swing.JList();
        selectAddLabel = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(800, 600));
        setRequestFocusEnabled(false);

        mainPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        mainPanel.setRequestFocusEnabled(false);
        mainPanel.setLayout(new java.awt.GridLayout(1, 0, 20, 0));

        qeButton.setText("Quit");
        qeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                qeButtonMouseReleased(evt);
            }
        });
        mainPanel.add(qeButton);

        editButton.setText("Edit Group");
        editButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                editButtonMouseReleased(evt);
            }
        });
        mainPanel.add(editButton);

        addButton.setText("Add Group");
        addButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                addButtonMouseReleased(evt);
            }
        });
        mainPanel.add(addButton);

        deleteButton.setText("Delete Group");
        deleteButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                deleteButtonMouseReleased(evt);
            }
        });
        mainPanel.add(deleteButton);

        selectButton.setText("Select Group");
        selectButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                selectButtonMouseReleased(evt);
            }
        });
        mainPanel.add(selectButton);

        gmList.setModel(groupList);
        gmList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                gmListValueChanged(evt);
            }
        });
        gmListScrollPane.setViewportView(gmList);

        selectAddLabel.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        selectAddLabel.setText("Select or Add Group");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(gmListScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 784, Short.MAX_VALUE)
                    .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
                    .addComponent(selectAddLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 784, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(selectAddLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(gmListScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void qeButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_qeButtonMouseReleased
        if(isGroup){
            FrameController.dispose();
        }else{
            FrameController.setCurrentPanel("gop");
        }
    }//GEN-LAST:event_qeButtonMouseReleased

    private void editButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editButtonMouseReleased
        if(optionSelected()){
            if(isGroup){
                FrameController.getGg().putData(FrameController.getGroup(gmList.getSelectedIndex()));
                FrameController.getGg().toggleMeetingTab();
                FrameController.changeFrameState("gg");
            }else{
                FrameController.getMep().putData(FrameController.getGroup(getCurrentGroup()).getMeeting(gmList.getSelectedIndex()));
                FrameController.getMg().setButtonText(FrameController.getMg().getFinishButton());
                FrameController.changeFrameState("mg");
            }
        }
    }//GEN-LAST:event_editButtonMouseReleased

    private void addButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addButtonMouseReleased
        if(isGroup){
            FrameController.changeFrameState("gg");
        }else{
            FrameController.getMg().setButtonText(FrameController.getMg().getCreateButton());
            FrameController.changeFrameState("mg");
        }
    }//GEN-LAST:event_addButtonMouseReleased

    private void selectButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectButtonMouseReleased
        if(optionSelected()){
            if(isGroup){
                FrameController.setCurrentPanel("gop");
            }else{
                FrameController.setCurrentPanel("mp");
            }
            gmList.clearSelection();
        }
    }//GEN-LAST:event_selectButtonMouseReleased

    private void deleteButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteButtonMouseReleased
        if(optionSelected()){
            if(!isGroup){
                List m=gmList.getSelectedValuesList();
                String meetings="";
                for(int i=0;i<m.size();i++){
                    meetings+=m.get(i)+"\n";
                }
                if(javax.swing.JOptionPane.showConfirmDialog(FrameController.getMf(), "Are you sure you wish to delete the following Meeting(s) and all repeated events?\n"+meetings, "Delete Group(s)?", javax.swing.JOptionPane.YES_NO_OPTION)==javax.swing.JOptionPane.YES_OPTION){
                    if(multipleSelected()){
                        int[] nums=gmList.getSelectedIndices();
                        for(int i=m.size();i>=0;i--){
                            Start.d.deleteMeeting(getCurrentGroup(),(String)m.get(i));
                            meetingList.remove(nums[i]);
                        }
                    }else{
                        Start.d.deleteMeeting(getCurrentGroup(),(String)gmList.getSelectedValue());
                        meetingList.remove(gmList.getSelectedIndex());
                    }
                }
            }else{
                List g=gmList.getSelectedValuesList();
                String groups="";
                for(int i=0;i<gmList.getSelectedIndices().length;i++) {
                    groups+=g.get(i)+"\n";
                }
                if(javax.swing.JOptionPane.showConfirmDialog(FrameController.getMf(), "Are you sure you wish to delete the following Group(s)?\n"+groups, "Delete Group(s)?", javax.swing.JOptionPane.YES_NO_OPTION)==javax.swing.JOptionPane.YES_OPTION){
                    if(multipleSelected()){
                        int[] nums=gmList.getSelectedIndices();
                        for(int i=g.size()-1;i>=0;i--){
                            Start.d.deleteClub((String)g.get(i));
                            groupList.remove(nums[i]);
                        }
                    }else{
                        Start.d.deleteClub((String)gmList.getSelectedValue());
                        groupList.remove(gmList.getSelectedIndex());
                    }
                    gmList.repaint();
                    gmList.clearSelection();
                    toggleButtons(false);
                }
            }
        }
    }//GEN-LAST:event_deleteButtonMouseReleased

    private void gmListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_gmListValueChanged
        if(gmList.getSelectedIndex()>-1){
            if(isGroup){
                setCurrentGroup(gmList.getSelectedValue().toString());
            }
            if(multipleSelected()){
                changeButtons(true);
            }else{
                changeButtons(false);
            }
        }
    }//GEN-LAST:event_gmListValueChanged
    
    public void setState(String state){
        state=state.toLowerCase();
        gmList.clearSelection();
        toggleButtons(false);
        switch(state){
            case "group":
                selectAddLabel.setText(GROUP_TITLE);
                qeButton.setText("Quit");
                editButton.setText("Edit Group");
                addButton.setText("Add Group");
                deleteButton.setText("Delete Group");
                selectButton.setText("Select Group");
                isGroup=true;
                fillList("Clubs");
                reInitGList();
                gmList.repaint();
                break;
            case "meeting":
                selectAddLabel.setText(MEETING_TITLE);
                qeButton.setText("Back");
                editButton.setText("Edit Meeting");
                addButton.setText("Add Meeting");
                deleteButton.setText("Delete Meeting");
                selectButton.setText("Start Meeting");
                isGroup=false;
                fillList(getCurrentGroup());
                reInitMList();
                gmList.repaint();
        }
    }
    
    public void setTitle(String title){
        selectAddLabel.setText(title);
    }
    
    private void reInitGList(){
        Font f=gmList.getFont();
        gmList=new javax.swing.JList();
        gmList.setModel(groupList);
        gmList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            @Override
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                gmListValueChanged(evt);
            }
        });
        gmListScrollPane.setViewportView(gmList);
        gmList.setFont(f);
    }
    
    private void reInitMList(){
        Font f=gmList.getFont();
        gmList=new javax.swing.JList();
        gmList.setModel(meetingList);
        gmList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            @Override
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                gmListValueChanged(evt);
            }
        });
        gmListScrollPane.setViewportView(gmList);
        gmList.setFont(f);
    }
    
    private void toggleButtons(boolean state){
        editButton.setEnabled(state);
        deleteButton.setEnabled(state);
        selectButton.setEnabled(state);
        buttonsEnabled=!buttonsEnabled;
    }
    
    private void changeButtons(boolean multiple){
        editButton.setEnabled(!multiple);
        deleteButton.setEnabled(true);
        selectButton.setEnabled(!multiple);
    }
    
    private boolean optionSelected(){
        return gmList.getSelectedIndex()!=-1;
    }
    
    private boolean multipleSelected(){
        return gmList.getSelectedIndices().length>1;
    }
    
    public String getCurrentGroup() {
        return currentGroup;
    }

    public void setCurrentGroup(String cg) {
        if(cg!=null){
            currentGroup = cg;
        }
    }
    
    public String getCurrentMeeting() {
        return currentMeeting;
    }

    public void setCurrentMeeting(String cm) {
        if(cm!=null){
            currentMeeting = cm;
        }
    }
    
    private void fillList(String clubName){
        if(isGroup){
            groupList.clear();
            for(int i=0;i<FrameController.getInv().getGroups().size();i++) {
                groupList.addElement(FrameController.getInv().getGroup(i).getName());
            }
        }else{
            meetingList.clear();
            for(int i=0;i<FrameController.getInv().getGroup(clubName).getMeetings().size(); i++) {
                meetingList.addElement(FrameController.getInv().getGroup(clubName).getMeeting(i).getName());
            }
        }
    }
    
    private boolean isGroup;
    private boolean buttonsEnabled;
    
    private final String GROUP_TITLE="Select or Add Group";
    private final String MEETING_TITLE="Select or Add Meeting";
    private String currentGroup;
    private String currentMeeting;
    
    private DefaultListModel groupList;
    private DefaultListModel meetingList;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton editButton;
    private javax.swing.JList gmList;
    private javax.swing.JScrollPane gmListScrollPane;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton qeButton;
    private javax.swing.JLabel selectAddLabel;
    private javax.swing.JButton selectButton;
    // End of variables declaration//GEN-END:variables
}
