package com.fitpro.service;
import com.fitpro.model.Trainer;
import com.fitpro.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service @Transactional
public class TrainerService {
    @Autowired private TrainerRepository trainerRepo;
    public Trainer addTrainer(Trainer t){
        if(t.getUserName()==null||t.getUserName().isBlank()){
            String base=t.getEmail()!=null?t.getEmail().split("@")[0]:"trainer";
            String candidate=base; int suffix=1;
            while(trainerRepo.findByUserName(candidate).isPresent()) candidate=base+suffix++;
            t.setUserName(candidate);
        } else if(trainerRepo.findByUserName(t.getUserName()).isPresent()){
            throw new RuntimeException("Username already exists");
        }
        if(trainerRepo.findByEmail(t.getEmail()).isPresent()) throw new RuntimeException("Email already registered");
        if(t.getPassword()==null||t.getPassword().isBlank()) t.setPassword(UUID.randomUUID().toString().substring(0,12));
        return trainerRepo.save(t);
    }
    public List<Trainer>     getAllTrainers()         {return trainerRepo.findAll();}
    public Optional<Trainer> getTrainerById(Long id) {return trainerRepo.findById(id);}
    public Trainer updateTrainer(Long id,Trainer u){
        return trainerRepo.findById(id).map(t->{
            t.setName(u.getName());t.setEmail(u.getEmail());t.setPhoneNo(u.getPhoneNo());
            t.setGender(u.getGender());t.setSpecialization(u.getSpecialization());
            t.setSalary(u.getSalary());t.setAvailableTime(u.getAvailableTime());
            if(u.getPassword()!=null&&!u.getPassword().isBlank()) t.setPassword(u.getPassword());
            return trainerRepo.save(t);
        }).orElseThrow(()->new RuntimeException("Trainer not found"));
    }
    public void deactivateTrainer(Long id){trainerRepo.findById(id).ifPresent(t->{t.setStatus("INACTIVE");trainerRepo.save(t);});}
    public void deleteTrainer(Long id)    {trainerRepo.deleteById(id);}
}
