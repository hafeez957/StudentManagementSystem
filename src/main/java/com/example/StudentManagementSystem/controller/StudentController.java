package com.example.StudentManagementSystem.controller;

import com.example.StudentManagementSystem.studentservice.StudentService;
import com.example.StudentManagementSystem.dto.StudentRequestDTO;
import com.example.StudentManagementSystem.dto.StudentResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    // CREATE
    @PostMapping
    public ResponseEntity<StudentResponseDTO> saveStudent(@Valid @RequestBody StudentRequestDTO dto) {

        StudentResponseDTO response = studentService.saveStudent(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<StudentResponseDTO>> getAllStudents() {

        List<StudentResponseDTO> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    // READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> getStudent(
            @PathVariable Long id) {

        StudentResponseDTO student = studentService.getStudent(id);
        return ResponseEntity.ok(student);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody StudentRequestDTO dto) {

        StudentResponseDTO updated = studentService.updateStudent(id, dto);
        return ResponseEntity.ok(updated);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {

        studentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
