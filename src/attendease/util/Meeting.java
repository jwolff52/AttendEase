package attendease.util;
/*@author timothy.chandler*/
public class Meeting {
    private String name;
    private String date;
    private String startTime;
    private String endTime;
    private int attendance;
    private int reocurringDays;
    private int gPoints;
    private int rPoints;
    private int lPoints;
    private boolean meatHeld;
    
    public Meeting(String d, String sTime, String eTime, int a, String rD, int g, int r, int l, boolean m){
        name=d+" @ "+sTime;
        date=d;
        startTime=sTime;
        endTime=eTime;
        attendance=a;
        reocurringDays=new Integer(rD);
        gPoints=g;
        rPoints=r;
        lPoints=l;
        meatHeld=m;
    }
    
    public Meeting(String n, String d, String sTime, String eTime, int a, String rD, int g, int r, int l, boolean m){
        name=n;
        date=d;
        startTime=sTime;
        endTime=eTime;
        attendance=a;
        reocurringDays=new Integer(rD);
        gPoints=g;
        rPoints=r;
        lPoints=l;
        meatHeld=m;
    }
    
    public Meeting(String[] v){
        name=v[0];
        date=v[1];
        startTime=v[2];
        endTime=v[3];
        attendance=new Integer(v[4]);
        reocurringDays=new Integer(v[5]);
        gPoints=new Integer(v[6]);
        rPoints=new Integer(v[7]);
        lPoints=new Integer(v[8]);
        meatHeld=Boolean.valueOf(v[9]);
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

    public int getReocurringDays() {
        return reocurringDays;
    }

    public void setReocurringDays(int reocurringDays) {
        this.reocurringDays = reocurringDays;
    }

    public boolean isMeatHeld() {
        return meatHeld;
    }

    public void setMeatHeld(boolean meatHeld) {
        this.meatHeld = meatHeld;
    }

    public String[] getVaules() {
        return new String[]{name,date,startTime,endTime,reocurringDays+"",gPoints+"",rPoints+"",lPoints+"",meatHeld+""};
    }

    public int getAttendance() {
        return attendance;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }
}
