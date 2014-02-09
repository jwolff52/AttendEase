/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package attendease.util;

import attendease.database.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author timothy.chandler
 */
public class driver {
    public static void main(String[] args){
        try {
            Database d=new Database();
            ResultSet r=d.readTable("SYSPERMS");
            while(r.next()){
                System.out.println(r.getString(1));
            }
        } catch (InstantiationException | ClassNotFoundException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(driver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
