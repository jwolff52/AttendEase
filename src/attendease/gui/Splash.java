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

import attendease.util.Start;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.SplashScreen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Splash extends Frame implements ActionListener {
    
    private static final String VERSION=Start.getVersion();;
    private static String loading;
    private static boolean doneLoading;
    private static SplashScreen splash;
    private static Graphics2D g;
    
    private static void renderSplashFrame(Graphics2D g)throws NullPointerException {
            g.setComposite(AlphaComposite.Clear);
            g.fillRect(75, 360, 500, 300);
            g.setPaintMode();
            g.setColor(Color.RED);
            g.setFont(new Font("Tahoma", Font.BOLD, 16));
            g.drawString(VERSION+"     "+loading+"...", 75, 400);
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
        renderSplashFrame(g);
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
