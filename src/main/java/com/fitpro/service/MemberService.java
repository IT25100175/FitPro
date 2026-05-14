package com.fitpro.service;
import com.fitpro.model.*;
import com.fitpro.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Service @Transactional
public class MemberService {
    @Autowired private MemberRepository         memberRepo;
    @Autowired private MembershipPlanRepository planRepo;
    public Member registerMember(Member m){
        if(memberRepo.findByUserName(m.getUserName()).isPresent()) throw new RuntimeException("Username already exists");
        if(memberRepo.findByEmail(m.getEmail()).isPresent())       throw new RuntimeException("Email already registered");
        m.registerMember(); return memberRepo.save(m);
    }
    public List<Member>     getAllMembers()         {return memberRepo.findAll();}
    public Optional<Member> getMemberById(Long id) {return memberRepo.findById(id);}
    public List<Member>     getActiveMembers()      {return memberRepo.findByStatus("ACTIVE");}
    public List<Member>     searchByName(String n)  {return memberRepo.searchByName(n);}
    public Member updateMember(Long id,Member u){
        return memberRepo.findById(id).map(m->{
            m.setName(u.getName());m.setEmail(u.getEmail());m.setPhoneNo(u.getPhoneNo());
            m.setGender(u.getGender());m.setMembershipType(u.getMembershipType());
            m.setMembershipEndDate(u.getMembershipEndDate());m.setPlanId(u.getPlanId());
            if(u.getStatus()!=null&&!u.getStatus().isBlank()) m.setStatus(u.getStatus());
            return memberRepo.save(m);
        }).orElseThrow(()->new RuntimeException("Member not found"));
    }
    public Member updateProfile(Long id,String name,String email,String phone,String password){
        return memberRepo.findById(id).map(m->{
            m.setName(name);m.setEmail(email);m.setPhoneNo(phone);
            if(password!=null&&!password.isBlank()) m.setPassword(password);
            return memberRepo.save(m);
        }).orElseThrow(()->new RuntimeException("Member not found"));
    }
    public Member assignPlan(Long mid,Long pid){
        var m=memberRepo.findById(mid).orElseThrow(()->new RuntimeException("Member not found"));
        var p=planRepo.findById(pid).orElseThrow(()->new RuntimeException("Plan not found"));
        m.setPlanId(p.getPlanID());m.setMembershipStartDate(LocalDate.now());
        m.setMembershipEndDate(LocalDate.now().plusMonths(p.getDurationMonths()));
        return memberRepo.save(m);
    }
    public void deactivateMember(Long id){memberRepo.findById(id).ifPresent(m->{m.setStatus("INACTIVE");memberRepo.save(m);});}
    public void deleteMember(Long id)    {memberRepo.deleteById(id);}
}
