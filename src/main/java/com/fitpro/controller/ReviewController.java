package com.fitpro.controller;
import com.fitpro.model.Review;
import com.fitpro.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
@RestController @RequestMapping("/api/reviews") @CrossOrigin(origins="*")
public class ReviewController {
    @Autowired private ReviewService svc;
    @PostMapping public ResponseEntity<?> submit(@RequestParam Long memberId,@RequestBody Review r){try{return ResponseEntity.ok(svc.submitReview(memberId,r));}catch(RuntimeException e){return ResponseEntity.badRequest().body(Map.of("error",e.getMessage()));}}
    @GetMapping          public ResponseEntity<List<Review>> getAll()   {return ResponseEntity.ok(svc.getAllReviews());}
    @GetMapping("/active") public ResponseEntity<List<Review>> active() {return ResponseEntity.ok(svc.getActiveReviews());}
    @GetMapping("/{id}") public ResponseEntity<?> getById(@PathVariable Long id){return svc.getById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());}
    @GetMapping("/member/{mid}") public ResponseEntity<List<Review>> byMember(@PathVariable Long mid){return ResponseEntity.ok(svc.getByMember(mid));}
    @GetMapping("/target") public ResponseEntity<List<Review>> byTarget(@RequestParam String type,@RequestParam Long id){return ResponseEntity.ok(svc.getByTarget(type,id));}
    @PutMapping("/{id}") public ResponseEntity<?> edit(@PathVariable Long id,@RequestParam Integer rating,@RequestParam String comment){try{return ResponseEntity.ok(svc.editReview(id,rating,comment));}catch(RuntimeException e){return ResponseEntity.badRequest().body(Map.of("error",e.getMessage()));}}
    @DeleteMapping("/{id}")           public ResponseEntity<?> remove(@PathVariable Long id){svc.removeReview(id);return ResponseEntity.ok(Map.of("message","Removed"));}
    @DeleteMapping("/{id}/permanent") public ResponseEntity<?> delete(@PathVariable Long id){svc.deleteReview(id);return ResponseEntity.ok(Map.of("message","Deleted"));}
}
