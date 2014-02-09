package attendease.util;
/*@author timothy.chandler*/
public class Meeting {
    private String name;
    private String date;
    private String startTime;
    private String endTime;
    private int reocurringDays;
    private int gPoints;
    private int rPoints;
    private int lPoints;
    
    public Meeting(String d, String sTime, String eTime, String rD, int g, int r, int l){
        name=d+" @ "+sTime;
        date=d;
        startTime=sTime;
        endTime=eTime;
        reocurringDays=new Integer(rD);
        gPoints=g;
        rPoints=r;
        lPoints=l;
    }
    
    public Meeting(String n, String d, String sTime, String eTime, String rD, int g, int r, int l){
        name=n;
        date=d;
        startTime=sTime;
        endTime=eTime;
        reocurringDays=new Integer(rD);
        gPoints=g;
        rPoints=r;
        lPoints=l;
    }
    
    public Meeting(String[] v){
        for(String s:v){
            System.out.println(s);
        }
        name=v[0];
        date=v[1];
        startTime=v[2];
        endTime=v[3];
        reocurringDays=new Integer(v[4]);
        gPoints=new Integer(v[5]);
        rPoints=new Integer(v[6]);
        lPoints=new Integer(v[7]);
    }

    public String getName() {
        return name;
    }

    public void setName(String n) {
        name = n;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String d) {
        date = d;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String sTime) {
        startTime = sTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String eTime) {
        endTime = eTime;
    }

    public int getgPoints() {
        return gPoints;
    }

    public void setgPoints(int gPoints) {
        this.gPoints = gPoints;
    }

    public int getrPoints() {
        return rPoints;
    }

    public void setrPoints(int rPoints) {
        this.rPoints = rPoints;
    }

    public int getlPoints() {
        return lPoints;
    }

    public void setlPoints(int lPoints) {
        this.lPoints = lPoints;
    }
}
