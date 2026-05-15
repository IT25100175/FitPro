package com.fitpro.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity @Table(name="trainers") @DiscriminatorValue("TRAINER")
public class Trainer extends User {
    private String gender;
    private String specialization;
    private Double salary;
    private String availableTime;
    @OneToMany(mappedBy="trainer",cascade=CascadeType.ALL,fetch=FetchType.LAZY) @JsonIgnore
    private List<ClassSession> classSessions=new ArrayList<>();


    public Trainer(){}
    public Trainer(String name,String email,String phoneNo,String userName,String password,
                   String gender,String spec,Double salary,String time){
        super(name,email,phoneNo,userName,password);
        this.gender=gender;this.specialization=spec;this.salary=salary;this.availableTime=time;
    }
    public String getGender()        {return gender;}         public void setGender(String v)        {gender=v;}
    public String getSpecialization(){return specialization;} public void setSpecialization(String v){specialization=v;}
    public Double getSalary()        {return salary;}         public void setSalary(Double v)        {salary=v;}
    public String getAvailableTime() {return availableTime;}  public void setAvailableTime(String v) {availableTime=v;}
    public List<ClassSession> getClassSessions(){return classSessions;} public void setClassSessions(List<ClassSession> v){classSessions=v;}

}
