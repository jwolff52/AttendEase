package attendease.gui;

/*
 * SplashDemo.java
 *
 */

import attendease.util.Start;
import java.awt.*;
import java.awt.event.*;

public class Splash extends Frame implements ActionListener {
    
    private static String loading;
    private static boolean doneLoading;
    private static SplashScreen splash;
    private static Graphics2D g;
    
    private static void renderSplashFrame(Graphics2D g) {
        g.setComposite(AlphaComposite.Clear);
        g.fillRect(112,360,500,100);
        g.setPaintMode();
        g.setColor(Color.RED);
        g.drawString(loading+"...", 112, 400);
    }
    @SuppressWarnings({"SleepWhileInLoop", "LeakingThisInConstructor"})
    public Splash(String l) {
        super("SplashScreen demo");
        loading=l;
        doneLoading=false;
        splash = SplashScreen.getSplashScreen();
        if (splash == null) {
            System.out.println("SplashScreen.getSplashScreen() returned null");
            return;
        }
        g = splash.createGraphics();
        if (g == null) {
            System.out.println("g is null");
        }
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        System.exit(0);
    }
    
    public void startSplash(){
        while(!doneLoading) {
            renderSplashFrame(g);
            splash.update();
            try {
                Thread.sleep(90);
            }
            catch(InterruptedException e) {
                Start.preLogError(e, "Error sleeping");
            }
        }
        splash.close();
    }
    
    public void updateString(String s){
        loading=s;
    }
    
    public void setDoneLoading(boolean dL){
        doneLoading=dL;
    }
}
