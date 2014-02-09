/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package attendease.gui;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author james.wolff
 */
public class ContentPane extends JPanel{
    public ContentPane(){
        setOpaque(false);
    }
    
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2D =(Graphics2D)g.create();
        g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        g2D.setColor(getBackground());
        g2D.fill(getBounds());
        
        g2D.dispose();
    }
}
