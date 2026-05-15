package com.fitpro.service;
import com.fitpro.model.*;
import com.fitpro.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
@Service @Transactional
public class ReviewService {
    @Autowired private ReviewRepository reviewRepo;
    @Autowired private MemberRepository memberRepo;
    public Review submitReview(Long memberId,Review review){
        var m=memberRepo.findById(memberId).orElseThrow(()->new RuntimeException("Member not found"));
        review.setMember(m);review.submitReview();return reviewRepo.save(review);
    }
    public List<Review>     getAllReviews()                     {return reviewRepo.findAll();}
    public Optional<Review> getById(Long id)                   {return reviewRepo.findById(id);}
    public List<Review>     getByMember(Long mid)              {return reviewRepo.findByMemberId(mid);}
    public List<Review>     getByTarget(String type,Long tid)  {return reviewRepo.findByTargetTypeAndTargetID(type,tid);}
    public List<Review>     getActiveReviews()                 {return reviewRepo.findByStatus("ACTIVE");}
    public Review editReview(Long id,Integer rating,String comment){
        return reviewRepo.findById(id).map(r->{r.editReview(rating,comment);return reviewRepo.save(r);})
               .orElseThrow(()->new RuntimeException("Review not found"));
    }
    public void removeReview(Long id){reviewRepo.findById(id).ifPresent(r->{r.deleteReview();reviewRepo.save(r);});}
    public void deleteReview(Long id){reviewRepo.deleteById(id);}
}
