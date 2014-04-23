package attendease.util;

import java.util.ArrayList;

/**
 * @author James
 * @date Apr 21, 2014
 */
public class AttendedMeeting extends Meeting{
    private final String arrivalTime;
    private String attendedStudentIdentifiers;
    
    public AttendedMeeting(String i, String d, String sTime, String eTime, int a, int g, int r, int l, boolean m, String t){
        super(i,d,sTime,eTime,a,g,r,l,m);
        arrivalTime=MiscUtils.getArrivalTime(sTime, t);
    }
    
    public AttendedMeeting(Meeting m, String t){
        super(m);
        arrivalTime=MiscUtils.getArrivalTime(m.getStartTime(), t);
    }
    
    public AttendedMeeting(String[] v){
        super(v);
        arrivalTime=MiscUtils.getArrivalTime(v[2], v[v.length-1]);
    }
    
    public String getArrivalTime(){
        return arrivalTime;
    }
    
    public ArrayList<Student> getAttendedStudents(String group){
        ArrayList<Student> groupStews=FrameController.getInv().getGroup(group).getStudents();
        ArrayList<Student> attendedStews=new ArrayList<>();
        for (Student student : groupStews) {
            for (AttendedMeeting m: student.getMeetingsAttended()) {
                if(m.getIdentifier().equals(this.getIdentifier())){
                    attendedStews.add(student);
                    break;
                }
            }
        }
        return attendedStews;
    }
}
