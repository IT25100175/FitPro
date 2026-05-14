package com.fitpro.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity @Table(name="membership_plans")
public class MembershipPlan {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long planID;
    @Column(nullable=false) private String planName;
    @Column(nullable=false) private Integer durationMonths;
    @Column(nullable=false) private Double price;
    @Column(length=500) private String description;
    private Integer maxClassesPerWeek;
    @Column(nullable=false) private String status="ACTIVE";
    @Column(nullable=false) private String planType="BASIC";
    @OneToMany(mappedBy="plan",fetch=FetchType.LAZY) @JsonIgnore private List<Member> enrolledMembers=new ArrayList<>();
    public MembershipPlan(){}
    public MembershipPlan(String planName,Integer dur,Double price,String desc,Integer max,String type){
        this.planName=planName;this.durationMonths=dur;this.price=price;
        this.description=desc;this.maxClassesPerWeek=max;this.planType=type;
    }
    public void createPlan() {status="ACTIVE";}
    public void deletePlan() {status="INACTIVE";}
    public void updatePlan(String n,Double p,Integer d){planName=n;price=p;durationMonths=d;}
    public Long    getPlanID()           {return planID;}          public void setPlanID(Long v)          {planID=v;}
    public String  getPlanName()         {return planName;}        public void setPlanName(String v)       {planName=v;}
    public Integer getDurationMonths()   {return durationMonths;}  public void setDurationMonths(Integer v){durationMonths=v;}
    public Double  getPrice()            {return price;}           public void setPrice(Double v)          {price=v;}
    public String  getDescription()      {return description;}     public void setDescription(String v)    {description=v;}
    public Integer getMaxClassesPerWeek(){return maxClassesPerWeek;} public void setMaxClassesPerWeek(Integer v){maxClassesPerWeek=v;}
    public String  getStatus()           {return status;}          public void setStatus(String v)         {status=v;}
    public String  getPlanType()         {return planType;}        public void setPlanType(String v)       {planType=v;}
    public List<Member> getEnrolledMembers(){return enrolledMembers;} public void setEnrolledMembers(List<Member> v){enrolledMembers=v;}
}
