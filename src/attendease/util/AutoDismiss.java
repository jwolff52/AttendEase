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
    
    public static void showMessageDialog(Component parent, Object message) {
        final JOptionPane optionPane = new JOptionPane(message);
        String title = UIManager.getString("OptionPane.messageDialogTitle");
        final JDialog dialog = optionPane.createDialog(parent, title);
        Timer timer = new Timer(3000, new AutoDismiss(dialog));
        timer.setRepeats(false);
        timer.start();
        if (dialog.isDisplayable()){
            dialog.setVisible(true);
        }
    }
}
