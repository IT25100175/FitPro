package com.fitpro.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity @Table(name="reviews")
public class Review {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long reviewID;
    @Column(name="member_ref_id") private Long memberId;
    private String memberName;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="member_id",insertable=false,updatable=false) @JsonIgnore private Member member;
    @Column(nullable=false) private String targetType;
    private Long targetID;
    @Column(nullable=false) private Integer rating;
    @Column(length=1000) private String comment;
    private LocalDateTime reviewTime;
    @Column(nullable=false) private String status="ACTIVE";
    @Column(nullable=false) private String reviewType="PUBLIC";
    public Review(){}
    public Review(Member m,String tt,Long tid,Integer r,String c,String rt){
        setMember(m);targetType=tt;targetID=tid;rating=r;comment=c;reviewType=rt;reviewTime=LocalDateTime.now();
    }
    public void submitReview(){reviewTime=LocalDateTime.now();status="ACTIVE";}
    public void editReview(Integer r,String c){rating=r;comment=c;reviewTime=LocalDateTime.now();}
    public void deleteReview(){status="REMOVED";}
    public Long          getReviewID()   {return reviewID;}   public void setReviewID(Long v)          {reviewID=v;}
    public Long          getMemberId()   {return memberId;}   public void setMemberId(Long v)          {memberId=v;}
    public String        getMemberName() {return memberName;} public void setMemberName(String v)      {memberName=v;}
    public Member        getMember()     {return member;}
    public void          setMember(Member m){member=m;memberId=(m!=null)?m.getUserID():null;memberName=(m!=null)?m.getName():null;}
    public String        getTargetType() {return targetType;} public void setTargetType(String v)      {targetType=v;}
    public Long          getTargetID()   {return targetID;}   public void setTargetID(Long v)          {targetID=v;}
    public Integer       getRating()     {return rating;}     public void setRating(Integer v)         {rating=v;}
    public String        getComment()    {return comment;}    public void setComment(String v)         {comment=v;}
    public LocalDateTime getReviewTime() {return reviewTime;} public void setReviewTime(LocalDateTime v){reviewTime=v;}
    public String        getStatus()     {return status;}     public void setStatus(String v)          {status=v;}
    public String        getReviewType() {return reviewType;} public void setReviewType(String v)      {reviewType=v;}
}
