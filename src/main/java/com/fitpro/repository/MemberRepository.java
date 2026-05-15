package com.fitpro.repository;
import com.fitpro.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findByUserName(String u);
    Optional<Member> findByEmail(String e);
    List<Member> findByStatus(String s);
    List<Member> findByMembershipType(String t);
    List<Member> findByAssignedTrainerId(Long tid);
    @Query("SELECT m FROM Member m WHERE LOWER(m.name) LIKE LOWER(CONCAT('%',:n,'%'))") List<Member> searchByName(String n);
}
