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

import attendease.util.AFrame;
import attendease.util.FrameController;
import attendease.util.Start;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author james.wolff
 */
public class MainFrame extends AFrame {

    public MainFrame() {
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
        } catch (ClassNotFoundException ex) {
            Start.createLog(ex, "Unable to set proper look and feel");
        } catch (InstantiationException ex) {
            Start.createLog(ex, "Unable to set proper look and feel");
        } catch (IllegalAccessException ex) {
            Start.createLog(ex, "Unable to set proper look and feel");
        } catch (UnsupportedLookAndFeelException ex) {
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

        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        homeMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        helpMenuItem = new javax.swing.JMenuItem();
        readmeMenuItem = new javax.swing.JMenuItem();
        liscenseMenuItem = new javax.swing.JMenuItem();
        separator1 = new javax.swing.JPopupMenu.Separator();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("AttendEase");
        setName("mf"); // NOI18N
        setResizable(false);

        fileMenu.setText("File");

        homeMenuItem.setText("Home");
        homeMenuItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                homeMenuItemMouseReleased(evt);
            }
        });
        homeMenuItem.addMenuKeyListener(new javax.swing.event.MenuKeyListener() {
            public void menuKeyPressed(javax.swing.event.MenuKeyEvent evt) {
            }
            public void menuKeyReleased(javax.swing.event.MenuKeyEvent evt) {
            }
            public void menuKeyTyped(javax.swing.event.MenuKeyEvent evt) {
                homeMenuItemMenuKeyTyped(evt);
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

        helpMenuItem.setText("Show Help");
        helpMenu.add(helpMenuItem);

        readmeMenuItem.setText("Show README");
        helpMenu.add(readmeMenuItem);

        liscenseMenuItem.setText("Show LISCENSE");
        helpMenu.add(liscenseMenuItem);
        helpMenu.add(separator1);

        aboutMenuItem.setText("About AttendEase");
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 579, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMenuItemMouseReleased
        FrameController.dispose();
    }//GEN-LAST:event_exitMenuItemMouseReleased

    private void homeMenuItemMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeMenuItemMouseReleased
        FrameController.clearAll();
        FrameController.setCurrentFrame("mf");
        FrameController.getSmgp().setState("Group");
        FrameController.setCurrentPanel("smgp");
    }//GEN-LAST:event_homeMenuItemMouseReleased

    private void homeMenuItemMenuKeyTyped(javax.swing.event.MenuKeyEvent evt) {//GEN-FIRST:event_homeMenuItemMenuKeyTyped
        FrameController.getSmgp().setState("Group");
        FrameController.setCurrentPanel("smgp");
    }//GEN-LAST:event_homeMenuItemMenuKeyTyped
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuItem helpMenuItem;
    private javax.swing.JMenuItem homeMenuItem;
    private javax.swing.JMenuItem liscenseMenuItem;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem readmeMenuItem;
    private javax.swing.JPopupMenu.Separator separator1;
    // End of variables declaration//GEN-END:variables
}
