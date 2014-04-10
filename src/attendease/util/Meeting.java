package attendease.util;
/*@author timothy.chandler*/
public class Meeting {
    private String identifier;
    private String name;
    private String date;
    private String startTime;
    private String endTime;
    private int attendance;
    private int gPoints;
    private int rPoints;
    private int lPoints;
    private boolean meatHeld;
    
    public Meeting(String i, String d, String sTime, String eTime, int a, int g, int r, int l, boolean m){
        identifier=i;
        name=d+" @ "+sTime;
        date=d;
        startTime=sTime;
        endTime=eTime;
        attendance=a;
        gPoints=g;
        rPoints=r;
        lPoints=l;
        meatHeld=m;
    }
    
    public Meeting(String i, String n, String d, String sTime, String eTime, int a, int g, int r, int l, boolean m){
        identifier=i;
        name=n;
        date=d;
        startTime=sTime;
        endTime=eTime;
        attendance=a;
        gPoints=g;
        rPoints=r;
        lPoints=l;
        meatHeld=m;
    }
    
    public Meeting(String[] v){
        identifier=v[0];
        name=v[1];
        date=v[2];
        startTime=v[3];
        endTime=v[4];
        attendance=new Integer(v[5]);
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

    public boolean isMeatHeld() {
        return meatHeld;
    }

    public void setMeatHeld(boolean meatHeld) {
        this.meatHeld = meatHeld;
    }

    public String[] getVaules() {
        return new String[]{identifier,name,date,startTime,endTime,attendance+"",gPoints+"",rPoints+"",lPoints+"",meatHeld+""};
    }

    public int getAttendance() {
        return attendance;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }
    
    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier=identifier;
    }
}
