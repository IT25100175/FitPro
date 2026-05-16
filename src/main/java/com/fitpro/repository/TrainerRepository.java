package com.fitpro.repository;
import com.fitpro.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@Repository
public interface TrainerRepository extends JpaRepository<Trainer,Long> {
    Optional<Trainer> findByUserName(String u);
    Optional<Trainer> findByEmail(String e);
    List<Trainer> findByStatus(String s);
}
