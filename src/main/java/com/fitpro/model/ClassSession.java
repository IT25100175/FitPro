package com.fitpro.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
@Entity @Table(name="class_sessions")
public class ClassSession {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long classID;
    @Column(nullable=false) private String className;
    @Column(name="trainer_ref_id") private Long trainerId;
    private String trainerName;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="trainer_id",insertable=false,updatable=false) @JsonIgnore private Trainer trainer;
    private LocalDate scheduleDate;
    private LocalTime scheduleTime;
    private Integer duration;
    private Integer capacity;
    private Integer enrolledCount=0;
    private String  location;
    @Column(nullable=false) private String status="SCHEDULED";
    @Column(nullable=false) private String classType="GROUP";
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="class_enrollments",joinColumns=@JoinColumn(name="class_id"),inverseJoinColumns=@JoinColumn(name="member_id"))
    @JsonIgnore private List<Member> enrolledMembers=new ArrayList<>();
    @OneToMany(mappedBy="classSession",cascade=CascadeType.ALL,fetch=FetchType.LAZY) @JsonIgnore private List<AttendanceRecord> attendanceRecords=new ArrayList<>();
    public ClassSession(){}
    public ClassSession(String cn,Trainer t,LocalDate d,LocalTime ti,Integer dur,Integer cap,String loc,String type){
        this.className=cn;this.scheduleDate=d;this.scheduleTime=ti;
        this.duration=dur;this.capacity=cap;this.location=loc;this.classType=type;setTrainer(t);
    }
    public boolean canEnroll(){return "SCHEDULED".equals(status)&&enrolledCount<capacity;}
    public void scheduleClass(){status="SCHEDULED";}
    public void cancelClass()  {status="CANCELLED";}
    public boolean enrollMember(Member m){if(canEnroll()&&!enrolledMembers.contains(m)){enrolledMembers.add(m);enrolledCount++;return true;}return false;}
    public Long    getClassID()       {return classID;}      public void setClassID(Long v)       {classID=v;}
    public String  getClassName()     {return className;}    public void setClassName(String v)    {className=v;}
    public Long    getTrainerId()     {return trainerId;}    public void setTrainerId(Long v)      {trainerId=v;}
    public String  getTrainerName()   {return trainerName;}  public void setTrainerName(String v)  {trainerName=v;}
    public Trainer getTrainer()       {return trainer;}
    public void    setTrainer(Trainer t){trainer=t;trainerId=(t!=null)?t.getUserID():null;trainerName=(t!=null)?t.getName():null;}
    public LocalDate getScheduleDate(){return scheduleDate;} public void setScheduleDate(LocalDate v){scheduleDate=v;}
    public LocalTime getScheduleTime(){return scheduleTime;} public void setScheduleTime(LocalTime v){scheduleTime=v;}
    public Integer getDuration()      {return duration;}     public void setDuration(Integer v)    {duration=v;}
    public Integer getCapacity()      {return capacity;}     public void setCapacity(Integer v)    {capacity=v;}
    public Integer getEnrolledCount() {return enrolledCount;}public void setEnrolledCount(Integer v){enrolledCount=v;}
    public String  getLocation()      {return location;}     public void setLocation(String v)     {location=v;}
    public String  getStatus()        {return status;}       public void setStatus(String v)       {status=v;}
    public String  getClassType()     {return classType;}    public void setClassType(String v)    {classType=v;}
    public List<Member> getEnrolledMembers(){return enrolledMembers;} public void setEnrolledMembers(List<Member> v){enrolledMembers=v;}
    public List<AttendanceRecord> getAttendanceRecords(){return attendanceRecords;} public void setAttendanceRecords(List<AttendanceRecord> v){attendanceRecords=v;}
}
