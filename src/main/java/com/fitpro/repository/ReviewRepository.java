package com.fitpro.repository;
import com.fitpro.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findByMemberId(Long mid);
    List<Review> findByTargetTypeAndTargetID(String t, Long id);
    List<Review> findByStatus(String s);
}
