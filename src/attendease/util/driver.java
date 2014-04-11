package attendease.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author James
 * @date Feb 9, 2014
 */
public class driver {
    public static void main(String[] args){
        String s=Integer.toString(27, 36);
        String s1=Integer.toString(400, 36);
        System.out.println(s);
        System.out.println(Integer.valueOf(s,36));
        System.out.println(s1);
        System.out.println(Integer.valueOf(s1,36));
        try {
            System.out.println(URLDecoder.decode(Start.class.getProtectionDomain().getCodeSource().getLocation().getPath(), "UTF-8").substring(1));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(driver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
