/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package attendease.util;

/**
 *
 * @author james.wolff
 */
public class Validator {
    public static boolean isValidName(String name) {
        if(!name.equals("")){
            char[] c=name.toCharArray();
            name="";
            for (int i=0;i<c.length;i++) {
                if(c[i]!=' '){
                    name+=c[i];
                }
            }
            for(int x=0;x<name.length();x++){
                if(name.substring(x, x+1).toUpperCase().equals(name.substring(x, x+1).toLowerCase())){
                    try{
                        int y=new Integer(name.substring(x, x+1));
                    }catch(NumberFormatException e){
                        return false;
                    }
                }
            }
            return true;
        }else{
            return false;
        }
    }
    
    public static boolean isValidPoints(String points){
        if(points==null||points.equals("")){
            return false;
        }
        try{
            new Integer(points);
        }catch(NumberFormatException e){
            return false;
        }
        return true;
    }
    
    public static boolean isValidTime(String h, String m, boolean isStart, boolean is24Hour){
        if(h+m==null||(h+m).equals("")){
            return !isStart;
        }else if(h.length()<1||m.length()<2){
            return false;
        }
        int x=new Integer(h);
        if(!is24Hour){
            if(x>12||x<0){
                return false;
            }else{
                x=new Integer(m);
                if(x>=60||x<0){
                    return false;
                }
            }
        }else{
            if(x>=24||x<0){
                return false;
            }else{
                x=new Integer(m);
                if(x>=60||x<0){
                    return false;
                }
            }
        }
        return true;
    }
    public static String replaceColons(String time){
        char[] c=time.toCharArray();
        time="";
        for (int i=0;i<c.length;i++){
            if(c[i]==':'){
                time+=' ';
            }else{
                time+=c[i];
            }
        }
        return time;
    }
    public static String replaceSpaces(String time){
        char[] c=time.toCharArray();
        time="";
        int x=0;
        while(x<c.length){
            if(c[x]==' '){
                time+=':';
                x++;
                break;
            }else{
                time+=c[x];
            }
        }
        for (;x<c.length;x++){
            time+=c[x];
        }
        return time;
    }
}
