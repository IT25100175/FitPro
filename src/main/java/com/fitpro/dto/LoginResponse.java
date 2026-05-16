package com.fitpro.dto;
public class LoginResponse {
    private Long userID; private String name; private String email;
    private String userName; private String role; private String status;
    public LoginResponse() {}
    public LoginResponse(Long id,String name,String email,String userName,String role,String status){
        this.userID=id;this.name=name;this.email=email;this.userName=userName;this.role=role;this.status=status;
    }
    public Long   getUserID()   { return userID; }   public void setUserID(Long v)    { userID=v; }
    public String getName()     { return name; }     public void setName(String v)    { name=v; }
    public String getEmail()    { return email; }    public void setEmail(String v)   { email=v; }
    public String getUserName() { return userName; } public void setUserName(String v){ userName=v; }
    public String getRole()     { return role; }     public void setRole(String v)    { role=v; }
    public String getStatus()   { return status; }   public void setStatus(String v)  { status=v; }
}
