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
public class ClassSessionService {
    @Autowired private ClassSessionRepository classRepo;
    @Autowired private TrainerRepository      trainerRepo;
    @Autowired private MemberRepository       memberRepo;
    public ClassSession createClass(ClassSession cs,Long trainerId){
        var t=trainerRepo.findById(trainerId).orElseThrow(()->new RuntimeException("Trainer not found"));
        cs.setTrainer(t);cs.scheduleClass();return classRepo.save(cs);
    }
    public List<ClassSession>     getAllClasses()        {return classRepo.findAll();}
    public Optional<ClassSession> getClassById(Long id) {return classRepo.findById(id);}
    public List<ClassSession>     getUpcoming()         {return classRepo.findByStatusAndScheduleDateGreaterThanEqual("SCHEDULED",LocalDate.now());}
    public ClassSession updateClass(Long id,ClassSession u){
        return classRepo.findById(id).map(c->{
            c.setClassName(u.getClassName());c.setScheduleDate(u.getScheduleDate());c.setScheduleTime(u.getScheduleTime());
            c.setDuration(u.getDuration());c.setCapacity(u.getCapacity());c.setLocation(u.getLocation());c.setClassType(u.getClassType());
            if(u.getTrainerId()!=null) trainerRepo.findById(u.getTrainerId()).ifPresent(c::setTrainer);
            return classRepo.save(c);
        }).orElseThrow(()->new RuntimeException("Class not found"));
    }
    public boolean enrollMember(Long cid,Long mid){
        var cs=classRepo.findById(cid).orElseThrow(()->new RuntimeException("Class not found"));
        var m=memberRepo.findById(mid).orElseThrow(()->new RuntimeException("Member not found"));
        boolean ok=cs.enrollMember(m);classRepo.save(cs);return ok;
    }
    public void cancelClass(Long id){classRepo.findById(id).ifPresent(c->{c.cancelClass();classRepo.save(c);});}
    public void deleteClass(Long id){classRepo.deleteById(id);}
}
