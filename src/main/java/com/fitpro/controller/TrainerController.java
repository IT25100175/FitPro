package com.fitpro.controller;
import com.fitpro.model.Trainer;
import com.fitpro.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
@RestController @RequestMapping("/api/trainers") @CrossOrigin(origins="*")
public class TrainerController {
    @Autowired private TrainerService svc;
    @PostMapping   public ResponseEntity<?> add(@RequestBody Trainer t){try{return ResponseEntity.ok(svc.addTrainer(t));}catch(RuntimeException e){return ResponseEntity.badRequest().body(Map.of("error",e.getMessage()));}}
    @GetMapping    public ResponseEntity<List<Trainer>> getAll()       {return ResponseEntity.ok(svc.getAllTrainers());}
    @GetMapping("/{id}") public ResponseEntity<?> getById(@PathVariable Long id){return svc.getTrainerById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());}
    @PutMapping("/{id}") public ResponseEntity<?> update(@PathVariable Long id,@RequestBody Trainer t){try{return ResponseEntity.ok(svc.updateTrainer(id,t));}catch(RuntimeException e){return ResponseEntity.badRequest().body(Map.of("error",e.getMessage()));}}
    @DeleteMapping("/{id}")           public ResponseEntity<?> deactivate(@PathVariable Long id){svc.deactivateTrainer(id);return ResponseEntity.ok(Map.of("message","Deactivated"));}
    @DeleteMapping("/{id}/permanent") public ResponseEntity<?> delete(@PathVariable Long id)   {svc.deleteTrainer(id);    return ResponseEntity.ok(Map.of("message","Deleted"));}
}
