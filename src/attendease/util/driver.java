package attendease.util;

/**
 * @author James
 * @date Feb 9, 2014
 */
public class driver {
    public static void main(String[] args){
        Meeting m=new Meeting("a", "test", "April 6, 2013", "13:30", "14:30", 0, 0 ,0, 0, false);
        System.out.println(MiscUtils.getArrivalTime(m.getStartTime(), "+0"));
        System.out.println(MiscUtils.getTimeDiff("12:00", "13:30"));
    }
        
}
