
package com.student.assignmenttracker.controller;

import com.student.assignmenttracker.entity.Assignment;
import com.student.assignmenttracker.service.AssignmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(
        origins = {
                "http://localhost:3000",
                "http://localhost:3001",
                "https://assignment-frontend-ram9.vercel.app"
        }
)
@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {

    private final AssignmentService service;

    public AssignmentController(AssignmentService service) {
        this.service = service;
    }

    // =============================
    // 1️⃣ Create Assignment
    // =============================
    @PostMapping("/create")
    public ResponseEntity<Assignment> createAssignment(@RequestBody Map<String, Object> payload) {

        String studentName = (String) payload.get("studentName");
        String assignmentName = (String) payload.get("assignmentName");
        String subject = (String) payload.get("subject");
        String dueDate = (String) payload.get("dueDate");
        String status = (String) payload.get("status");

        Assignment assignment = new Assignment();
        assignment.setStudentName(studentName);
        assignment.setAssignmentName(assignmentName);
        assignment.setSubject(subject);
        assignment.setDueDate(dueDate);
        assignment.setStatus(status);

        return ResponseEntity.ok(service.createAssignment(assignment));
    }

    // =============================
    // 2️⃣ Get All Assignments
    // =============================
    @GetMapping("/all")
    public ResponseEntity<List<Assignment>> getAllAssignments() {
        return ResponseEntity.ok(service.getAllAssignments());
    }

    // =============================
    // 3️⃣ Get Assignment by ID
    // =============================
    @GetMapping("/{id}")
    public ResponseEntity<Assignment> getById(@PathVariable Long id) {
        return service.getAssignmentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // =============================
    // 4️⃣ Update Assignment
    // =============================
    @PutMapping("/{id}")
    public ResponseEntity<Assignment> updateAssignment(
            @PathVariable Long id,
            @RequestBody Map<String, Object> payload
    ) {
        String studentName = (String) payload.get("studentName");
        String assignmentName = (String) payload.get("assignmentName");
        String subject = (String) payload.get("subject");
        String dueDate = (String) payload.get("dueDate");
        String status = (String) payload.get("status");

        Assignment updated = service.updateAssignment(id, studentName, assignmentName, subject, dueDate, status);
        return ResponseEntity.ok(updated);
    }

    // =============================
    // 5️⃣ Delete Assignment
    // =============================
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAssignment(@PathVariable Long id) {
        service.deleteAssignment(id);
        return ResponseEntity.ok("Assignment deleted successfully");
    }

    // =============================
    // 6️⃣ Health Check
    // =============================
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Assignment API + DB working ✅");
    }
}
