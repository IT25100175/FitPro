package com.fitpro.model;
import jakarta.persistence.*;
@Entity @Table(name="users")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="user_type",discriminatorType=DiscriminatorType.STRING)
public abstract class User {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long userID;
    @Column(nullable=false) private String name;
    @Column(nullable=false,unique=true) private String email;
    private String phoneNo;
    @Column(nullable=false,unique=true) private String userName;
    @Column(nullable=false) private String password;
    @Column(nullable=false) private String status="ACTIVE";
    protected User(){}
    protected User(String name,String email,String phoneNo,String userName,String password){
        this.name=name;this.email=email;this.phoneNo=phoneNo;this.userName=userName;this.password=password;
    }
    public boolean login(String u,String p){return this.userName.equals(u)&&this.password.equals(p);}
    public void changePassword(String p){this.password=p;}
    public void updateProfile(String n,String e,String ph){this.name=n;this.email=e;this.phoneNo=ph;}
    public Long   getUserID()  {return userID;}  public void setUserID(Long v)   {userID=v;}
    public String getName()    {return name;}    public void setName(String v)    {name=v;}
    public String getEmail()   {return email;}   public void setEmail(String v)   {email=v;}
    public String getPhoneNo() {return phoneNo;} public void setPhoneNo(String v) {phoneNo=v;}
    public String getUserName(){return userName;} public void setUserName(String v){userName=v;}
    public String getPassword(){return password;} public void setPassword(String v){password=v;}
    public String getStatus()  {return status;}  public void setStatus(String v)  {status=v;}
}
