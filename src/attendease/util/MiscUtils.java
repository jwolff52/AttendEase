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

package attendease.util;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 *
 * @author james.wolff
 */
public class MiscUtils {
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
    
    public static boolean isLate(int s, int c, String startDate, String currentDate){
        int i=compareDates(startDate, currentDate);
        if(i==0){
            return s<c;
        }else if(i>0){
            return false;
        }else{
            return true;
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
            x++;
        }
        while(x<c.length){
            time+=c[x];
            x++;
        }
        return time;
    }
    
    public static int timeToInt(String time){
        char[] c=time.toCharArray();
        time="";
        String period="";
        int x=0;
        while(x<c.length){
            if(c[x]==' '||c[x]==':'){
                x++;
                break;
            }else{
                time+=c[x];
            }
            x++;
        }
        while(x<c.length){
            if(c[x]==' '||c[x]==':'){
                x++;
                break;
            }
            time+=c[x];
            x++;
        }
        while(x<c.length){
            period+=c[x];
            x++;
        }
        x=Integer.valueOf(time);
        if(period.equalsIgnoreCase("pm")){
            return (x+1200)%2400;
        }
        return x;
    }
    
    public static String intToTime(int t){
        t%=2400;
        String hour=t/100+":";
        String minutes=""+t%100;
        if(minutes.length()==1&&minutes.charAt(minutes.length()-1)=='0'){
            minutes+="0";
        }else if(minutes.length()==1){
            minutes="0"+minutes;
        }
        return hour+minutes;
    }

    private static int compareDates(String start, String current) {
        String testStart=start.substring(start.lastIndexOf("/")+1);
        String testCurrent=current.substring(current.lastIndexOf("/")+1);
        if(Integer.valueOf(testStart)>Integer.valueOf(testCurrent)){
            return 1;
        }else if(Integer.valueOf(testStart)<Integer.valueOf(testCurrent)){
            return -1;
        }else{
            testStart=start.substring(0,start.indexOf("/"));
            testCurrent=current.substring(0,current.indexOf("/"));
            int monthStart=getMonthNumber(testStart);
            int monthCurrent=getMonthNumber(testCurrent);
            if(monthStart>monthCurrent){
                return 1;
            }else if(monthStart<monthCurrent){
                return -1;
            }else{
                testStart=start.substring(start.indexOf("/")+1,start.lastIndexOf("/"));
                testCurrent=current.substring(current.indexOf("/")+1,current.lastIndexOf("/"));
                if(Integer.valueOf(testStart)>Integer.valueOf(testCurrent)){
                    return 1;
                }else if(Integer.valueOf(testStart)<Integer.valueOf(testCurrent)){
                    return -1;
                }else{
                    return 0;
                }
            }
        }
    }
    
    public static int getMonthNumber(String month){
        switch(month.toLowerCase()){
            case "january":
                return 1;
            case "february":
                return 2;
            case "march":
                return 3;
            case "april":
                return 4;
            case "may":
                return 5;
            case "june":
                return 6;
            case "july":
                return 7;
            case "august":
                return 8;
            case "september":
                return 9;
            case "october":
                return 10;
            case "november":
                return 11;
            case "december":
                return 12;
            default:
                return 13;
        }
    }
    
    public static String getMonthName(int month){
        switch(month){
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "December";
            default:
                return "Undecimber";
        }
    }
    
    public static int getMaxDays(int year, String month){
        switch(getMonthNumber(month)){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 2:
                if(isLeapYear(year)){
                    return 29;
                }else{
                    return 28;
                }
            default:
                return 30;
        }
    }
    
    public static String getNextIdentifier(){
        int i=0;
        int limit=(int)Math.pow(37, 3)-1;
        while(FrameController.getInv().identifierExisits(tenTo36(i))&&i<limit){
            i++;
        }
        return tenTo36(i);
    }
    
    public static int thirtySixTo10(String b36){
        return Integer.valueOf(new BigInteger(b36, 36).toString());
    }
    
    public static String tenTo36(int i){
        return Integer.toString(i, 36);
    }
    
    private static boolean isLeapYear(int y){
        if(y%4==0){
            return y%100 != 0 || y%400 == 0;
        }
        return false;
    }
    
    public static int[] getDelineatorIndicies(String string, char delineator){
        ArrayList<Integer> ia=new ArrayList<>();
        char[] ca=string.toCharArray();
        int i=0;
        for (char c : ca) {
            if(c==delineator){
                ia.add(i);
            }
            i++;
        }
        int[] indicies=new int[ia.size()];
        for(int j=0; j < 10; j++) {
            try{
                indicies[j]=ia.get(j);
            }catch(IndexOutOfBoundsException e){}
        }
        return indicies;
    }
    
    public static String getArrivalTime(String startTime, String timeDiff){
        return intToTime(timeToInt(startTime)+Integer.valueOf(timeDiff));
    }
    
    public static String getTimeDiff(String startTime, String arrivalTime){
        int temp=timeToInt(startTime)-timeToInt(arrivalTime);
        int timeDiff=(temp/100)*60+temp%100;
        if(timeDiff<0){
            return "+"+Math.abs(timeDiff);
        }
        return "-"+timeDiff;
    }
}
