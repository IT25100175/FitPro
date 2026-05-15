package com.fitpro.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Entity @Table(name="members") @DiscriminatorValue("MEMBER")
public class Member extends User {
    private String gender;
    private LocalDate dateOfBirth;
    @Column(nullable=false) private String membershipType="BASIC";
    private LocalDate membershipStartDate;
    private LocalDate membershipEndDate;
    @Column(name="trainer_ref_id") private Long assignedTrainerId;
    @Column(name="plan_ref_id")    private Long planId;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="assigned_trainer_id",insertable=false,updatable=false) @JsonIgnore private Trainer assignedTrainer;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="plan_id",insertable=false,updatable=false)             @JsonIgnore private MembershipPlan plan;
    @OneToMany(mappedBy="member",cascade=CascadeType.ALL,fetch=FetchType.LAZY) @JsonIgnore private List<AttendanceRecord> attendanceRecords=new ArrayList<>();
    @OneToMany(mappedBy="member",cascade=CascadeType.ALL,fetch=FetchType.LAZY) @JsonIgnore private List<Review> reviews=new ArrayList<>();

    public Member(){}
    public Member(String name,String email,String phoneNo,String userName,String password,
                  String gender,LocalDate dob,String membershipType){
        super(name,email,phoneNo,userName,password);
        this.gender=gender;this.dateOfBirth=dob;this.membershipType=membershipType;
    }
    public void registerMember(){this.setStatus("ACTIVE");this.membershipStartDate=LocalDate.now();}
    public boolean isPremium(){return "PREMIUM".equals(membershipType);}
    public boolean checkIn() {return "ACTIVE".equals(getStatus())&&membershipEndDate!=null&&!LocalDate.now().isAfter(membershipEndDate);}
    public String    getGender()            {return gender;}           public void setGender(String v)            {gender=v;}
    public LocalDate getDateOfBirth()       {return dateOfBirth;}      public void setDateOfBirth(LocalDate v)    {dateOfBirth=v;}
    public String    getMembershipType()    {return membershipType;}   public void setMembershipType(String v)    {membershipType=v;}
    public LocalDate getMembershipStartDate(){return membershipStartDate;} public void setMembershipStartDate(LocalDate v){membershipStartDate=v;}
    public LocalDate getMembershipEndDate() {return membershipEndDate;}    public void setMembershipEndDate(LocalDate v)  {membershipEndDate=v;}
    public Long      getAssignedTrainerId() {return assignedTrainerId;}    public void setAssignedTrainerId(Long v)       {assignedTrainerId=v;}
    public Long      getPlanId()            {return planId;}           public void setPlanId(Long v)              {planId=v;}
    public Trainer   getAssignedTrainer()   {return assignedTrainer;}  public void setAssignedTrainer(Trainer v)  {assignedTrainer=v;}
    public MembershipPlan getPlan()         {return plan;}             public void setPlan(MembershipPlan v)      {plan=v;}
}
