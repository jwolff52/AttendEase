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

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.UIManager;

/**
 * @author James
 * @date Feb 10, 2014
 */
public class AutoDismiss implements ActionListener{
    private JDialog dialog;

    public AutoDismiss(JDialog dialog)
    {
        this.dialog = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        dialog.dispose();
    }
    
    public static void showMessageDialog(int time, Component parent, Object message) {
        final JOptionPane optionPane = new JOptionPane(message);
        String title = UIManager.getString("OptionPane.messageDialogTitle");
        final JDialog dialog = optionPane.createDialog(parent, title);
        Timer timer = new Timer(time, new AutoDismiss(dialog));
        timer.setRepeats(false);
        timer.start();
        if (dialog.isDisplayable()){
            dialog.setVisible(true);
        }
    }
}
