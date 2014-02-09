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
    
    public static boolean isValidTime(String time, boolean isStart, boolean is24Hour){
        if(time==null||time.equals("")){
            if(isStart){
                return false;
            }else{
                return true;
            }
        }else if(time.length()!=0&&(time.length()<3||time.length()>7)){
            return false;
        }
        try{
            int i=time.indexOf(":");
            String h=time.substring(0, i);
            String m=time.substring(i+1, i+3);
            String p=time.substring(time.length()-2, time.length());
            int x=new Integer(h);
            if(p.equalsIgnoreCase("am")||p.equalsIgnoreCase("pm")){
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
        }catch(StringIndexOutOfBoundsException e){
            int i=0;
            if(time.length()==3){
                i=2;
            }else if(time.length()==4){
                i=3;
            }else{
                return false;
            }
            String h=time.substring(0, i);
            String m=time.substring(i);
            String p=time.substring(time.length()-2, time.length());
            int x=-1;
            try{
                x=new Integer(h);
            }catch(NumberFormatException f){
                if(h.substring(h.length()-1).equals(":")){
                    h=h.substring(0,h.length()-1);
                    x=new Integer(h);
                }else{
                    Start.createLog(e, h);
                }
            }
            if(!is24Hour){
                if(x>12||x<=0){
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
        }
        return true;
    }
    private String colonRemover(String time){
        return null;
    }
}
