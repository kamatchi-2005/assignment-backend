package com.student.assignmenttracker.service;

import com.student.assignmenttracker.entity.Assignment;
import com.student.assignmenttracker.repository.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentRepository repo;

    public Assignment createAssignment(Assignment a) {
        a.setStatus("Assigned"); // default status
        return repo.save(a);
    }

    public List<Assignment> getAllAssignments() {
        return repo.findAll();
    }

    public Assignment updateAssignment(Long id, Assignment updated) {
        Assignment a = repo.findById(id).orElseThrow();
        a.setStudentName(updated.getStudentName());
        a.setProjectName(updated.getProjectName());
        a.setStatus(updated.getStatus());
        return repo.save(a);
    }

    public void deleteAssignment(Long id) {
        repo.deleteById(id);
    }
}
