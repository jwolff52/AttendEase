/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package attendease.gui;

import attendease.util.FrameController;
import attendease.util.Meeting;
import attendease.util.Start;
import attendease.util.Validator;

/**
 *
 * @author timothy.chandler
 */
public class MeetingGUI extends javax.swing.JFrame {

    /**
     * Creates new form MeetingGUI
     */
    public MeetingGUI() {
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
    }
    
    private void postInit(){
        javax.swing.GroupLayout layout=new javax.swing.GroupLayout(getContentPane());
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(mep)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(mep)
        );
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        cancelButton = new javax.swing.JButton();
        createButton = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        homeMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Edit/Add Meeting");
        setResizable(false);

        cancelButton.setText("Cancel");
        cancelButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cancelButtonMouseReleased(evt);
            }
        });

        createButton.setText("Create");
        createButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                createButtonMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cancelButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
                .addComponent(createButton)
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(createButton))
                .addContainerGap())
        );

        fileMenu.setText("File");

        homeMenuItem.setText("Home");
        homeMenuItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                homeMenuItemMouseReleased(evt);
            }
        });
        homeMenuItem.addMenuKeyListener(new javax.swing.event.MenuKeyListener() {
            public void menuKeyTyped(javax.swing.event.MenuKeyEvent evt) {
                homeMenuItemMenuKeyTyped(evt);
            }
            public void menuKeyPressed(javax.swing.event.MenuKeyEvent evt) {
            }
            public void menuKeyReleased(javax.swing.event.MenuKeyEvent evt) {
            }
        });
        fileMenu.add(homeMenuItem);

        exitMenuItem.setText("Quit");
        exitMenuItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                exitMenuItemMouseReleased(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText("Help");
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 288, Short.MAX_VALUE)
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelButtonMouseReleased
        FrameController.changeFrameState("mg");
    }//GEN-LAST:event_cancelButtonMouseReleased

    private void createButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_createButtonMouseReleased
        if(Validator.isValidTime(mep.getStartHour(),mep.getStartMinute(), true, mep.is24Hour())&&Validator.isValidTime(mep.getEndHour(), mep.getEndMinute(), false, mep.is24Hour())){
            String[] values=FrameController.getMep().getValues();
            String[] localValues=values;
            localValues[0]=localValues[0].substring(0, localValues[0].length()-6)+":"+localValues[0].substring(localValues[0].length()-5);
            FrameController.getInv().getGroup(FrameController.getSmgp().getCurrentGroup()).addMeeting(new Meeting(localValues));
            FrameController.getSmgp().setState("meeting");
            dispose();
            Start.d.addMeeting(FrameController.getSmgp().getCurrentGroup(), values);
        }else if(!mep.isStartTimeGiven()){
            javax.swing.JOptionPane.showMessageDialog(this, "There was no start time provided!", "Time Error", javax.swing.JOptionPane.WARNING_MESSAGE);
        }else if(!Validator.isValidTime(mep.getStartHour(),mep.getStartMinute(), true, mep.is24Hour())){
            javax.swing.JOptionPane.showMessageDialog(this, "The start time given is invalid, as indicated by the red box.", "Time Error", javax.swing.JOptionPane.WARNING_MESSAGE);
        }else if(!Validator.isValidTime(mep.getEndHour(),mep.getEndMinute(), false, mep.is24Hour())){
            javax.swing.JOptionPane.showMessageDialog(this, "The end time given is invalid, as indicated by the red box.", "Time Error", javax.swing.JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_createButtonMouseReleased

    private void homeMenuItemMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeMenuItemMouseReleased
        FrameController.getSmgp().setState("Group");
        FrameController.setCurrentPanel("smgp");
    }//GEN-LAST:event_homeMenuItemMouseReleased

    private void homeMenuItemMenuKeyTyped(javax.swing.event.MenuKeyEvent evt) {//GEN-FIRST:event_homeMenuItemMenuKeyTyped
        FrameController.getSmgp().setState("Group");
        FrameController.setCurrentPanel("smgp");
    }//GEN-LAST:event_homeMenuItemMenuKeyTyped

    private void exitMenuItemMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMenuItemMouseReleased
        FrameController.dispose();
    }//GEN-LAST:event_exitMenuItemMouseReleased

//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(MeetingGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new MeetingGUI().setVisible(true);
//            }
//        });
//    }
    
    public String getCreateButton(){
        return CREATE_BUTTON;
    }
    
    public String getFinishButton(){
        return FINISH_BUTTON;
    }
    
    public void setButtonText(String text){
        createButton.setText(text);
    }
    
    private final MeetingEditPanel mep=FrameController.getMep();
    private final String CREATE_BUTTON="Create";
    private final String FINISH_BUTTON="Finish";
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton createButton;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuItem homeMenuItem;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    // End of variables declaration//GEN-END:variables
}
