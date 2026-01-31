package com.student.assignmenttracker.controller;

import com.student.assignmenttracker.entity.Assignment;
import com.student.assignmenttracker.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"}) // React frontend
@RequestMapping("/api/assignments")
public class AssignmentController {

    @Autowired
    private AssignmentService service;

    @PostMapping
    public Assignment create(@RequestBody Assignment a) {
        return service.createAssignment(a);
    }
    @GetMapping
    public List<Assignment> getAll() {
        return service.getAllAssignments();
    }

    @PutMapping("/{id}")
    public Assignment update(@PathVariable Long id, @RequestBody Assignment a) {
        return service.updateAssignment(id, a);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteAssignment(id);
    }
}
