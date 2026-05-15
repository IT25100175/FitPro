package com.fitpro.controller;
import com.fitpro.model.Member;
import com.fitpro.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
@RestController @RequestMapping("/api/members") @CrossOrigin(origins="*")
public class MemberController {
    @Autowired private MemberService svc;
    @PostMapping   public ResponseEntity<?> register(@RequestBody Member m){try{return ResponseEntity.ok(svc.registerMember(m));}catch(RuntimeException e){return ResponseEntity.badRequest().body(Map.of("error",e.getMessage()));}}
    @GetMapping    public ResponseEntity<List<Member>> getAll()             {return ResponseEntity.ok(svc.getAllMembers());}
    @GetMapping("/active") public ResponseEntity<List<Member>> getActive()  {return ResponseEntity.ok(svc.getActiveMembers());}
    @GetMapping("/search") public ResponseEntity<List<Member>> search(@RequestParam String name){return ResponseEntity.ok(svc.searchByName(name));}
    @GetMapping("/{id}")   public ResponseEntity<?> getById(@PathVariable Long id){return svc.getMemberById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());}
    @PutMapping("/{id}")   public ResponseEntity<?> update(@PathVariable Long id,@RequestBody Member m){try{return ResponseEntity.ok(svc.updateMember(id,m));}catch(RuntimeException e){return ResponseEntity.badRequest().body(Map.of("error",e.getMessage()));}}
    @PutMapping("/{id}/profile") public ResponseEntity<?> updateProfile(@PathVariable Long id,@RequestBody Map<String,String> b){try{return ResponseEntity.ok(svc.updateProfile(id,b.get("name"),b.get("email"),b.get("phoneNo"),b.get("password")));}catch(RuntimeException e){return ResponseEntity.badRequest().body(Map.of("error",e.getMessage()));}}
    @PutMapping("/{id}/plan/{pid}") public ResponseEntity<?> assignPlan(@PathVariable Long id,@PathVariable Long pid){try{return ResponseEntity.ok(svc.assignPlan(id,pid));}catch(RuntimeException e){return ResponseEntity.badRequest().body(Map.of("error",e.getMessage()));}}
    @DeleteMapping("/{id}")           public ResponseEntity<?> deactivate(@PathVariable Long id){svc.deactivateMember(id);return ResponseEntity.ok(Map.of("message","Deactivated"));}
    @DeleteMapping("/{id}/permanent") public ResponseEntity<?> delete(@PathVariable Long id)   {svc.deleteMember(id);    return ResponseEntity.ok(Map.of("message","Deleted"));}
}
