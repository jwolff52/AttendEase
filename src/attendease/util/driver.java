package attendease.util;

import java.util.Random;

/**
 * @author James
 * @date Feb 9, 2014
 */
public class driver {
    public static void main(String[] args){
        Random rand=new Random();
        int j;
        for (int i = 0; i < 10; i++) {
            j=rand.nextInt(9999)+1;
            System.out.println(j);
            System.out.println(MiscUtils.thirtySixTo10(MiscUtils.tenTo36(j)));
            System.out.println("");
            System.out.println("\n\n");
        }
        System.out.println(3%3);
        System.out.println(MiscUtils.thirtySixTo10(MiscUtils.tenTo36(10)));
        
    }
}
