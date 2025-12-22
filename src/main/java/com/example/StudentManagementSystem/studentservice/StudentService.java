package com.example.StudentManagementSystem.studentservice;

import com.example.StudentManagementSystem.dto.StudentRequestDTO;
import com.example.StudentManagementSystem.dto.StudentResponseDTO;

import java.util.List;

public interface StudentService {

    public StudentResponseDTO saveStudent(StudentRequestDTO student);

    public List<StudentResponseDTO> getAllStudents();

    public StudentResponseDTO getStudent(Long id);

    public StudentResponseDTO updateStudent(Long id,StudentRequestDTO student);

    public void delete(Long id);
}
