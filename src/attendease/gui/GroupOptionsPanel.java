
package attendease.gui;

import attendease.util.FrameController;
import attendease.util.Start;


public class GroupOptionsPanel extends javax.swing.JPanel {

    /**
     * Creates new form GroupOptions
     */
    public GroupOptionsPanel() {
        preInit();
        initComponents();
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
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        groupLabel = new javax.swing.JLabel();
        viewMeetingsButton = new javax.swing.JButton();
        viewMembersButton = new javax.swing.JButton();
        updateMembersButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(800, 600));

        groupLabel.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        groupLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        groupLabel.setText("Group Name");
        groupLabel.setToolTipText("");
        groupLabel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        viewMeetingsButton.setText("View Meetings");
        viewMeetingsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                viewMeetingsButtonMouseReleased(evt);
            }
        });

        viewMembersButton.setText("View Attendence");
        viewMembersButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                viewMembersButtonMouseReleased(evt);
            }
        });

        updateMembersButton.setText("Update Members");
        updateMembersButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                updateMembersButtonMouseReleased(evt);
            }
        });

        backButton.setText("Back");
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                backButtonMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(331, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(viewMembersButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(updateMembersButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(viewMeetingsButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(groupLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(331, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(groupLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(viewMeetingsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(viewMembersButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(updateMembersButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(260, 260, 260)
                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void viewMeetingsButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewMeetingsButtonMouseReleased
        FrameController.getSmgp().setState("Meeting");
        FrameController.setCurrentPanel("smgp");
    }//GEN-LAST:event_viewMeetingsButtonMouseReleased

    private void viewMembersButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewMembersButtonMouseReleased
        FrameController.setCurrentPanel("sp");
    }//GEN-LAST:event_viewMembersButtonMouseReleased

    private void updateMembersButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateMembersButtonMouseReleased
        FrameController.changeFrameState("umg");
    }//GEN-LAST:event_updateMembersButtonMouseReleased

    private void backButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backButtonMouseReleased
        FrameController.getSmgp().setState("Group");
        FrameController.setCurrentPanel("smgp");
    }//GEN-LAST:event_backButtonMouseReleased

    public void setCurrentGroup(String cg){
        groupLabel.setText(cg);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JLabel groupLabel;
    private javax.swing.JButton updateMembersButton;
    private javax.swing.JButton viewMeetingsButton;
    private javax.swing.JButton viewMembersButton;
    // End of variables declaration//GEN-END:variables
}
