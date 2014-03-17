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
    
    private static void renderSplashFrame(Graphics2D g)throws NullPointerException {
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
            try{
                splash = SplashScreen.getSplashScreen();
                g = splash.createGraphics();
            }catch(NullPointerException e){
            }
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        System.exit(0);
    }
    
    public void startSplash()throws NullPointerException{
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
