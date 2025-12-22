package com.example.StudentManagementSystem.studentservice;

import com.example.StudentManagementSystem.model.Address;
import com.example.StudentManagementSystem.model.Student;
import com.example.StudentManagementSystem.repository.StudentRepository;
import dto.AddressResponseDTO;
import dto.StudentRequestDTO;
import dto.StudentResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    // CREATE
    @Override
    public StudentResponseDTO saveStudent(StudentRequestDTO dto) {

        // DTO → Entity
        Student student = mapToEntity(dto);

        // Save entity
        Student savedStudent = studentRepository.save(student);

        // Entity → Response DTO
        return mapToResponseDTO(savedStudent);
    }

    // READ ALL
    @Override
    public List<StudentResponseDTO> getAllStudents() {

        return studentRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    // READ ONE
    @Override
    public StudentResponseDTO getStudent(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Student not found with id: " + id));

        return mapToResponseDTO(student);
    }

    // UPDATE
    @Override
    public StudentResponseDTO updateStudent(Long id, StudentRequestDTO dto) {

        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Student not found with id: " + id));

        // Update fields
        existingStudent.setName(dto.getName());
        existingStudent.setEmail(dto.getEmail());
//        existingStudent.setCourse(dto.getCourse());
        existingStudent.setAge(dto.getAge());

        Student updatedStudent = studentRepository.save(existingStudent);

        return mapToResponseDTO(updatedStudent);
    }

    // DELETE
    @Override
    public void delete(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Student not found with id: " + id));

        studentRepository.delete(student);
    }

    // ---------- MAPPING METHODS ----------

    private Student mapToEntity(StudentRequestDTO dto) {

        Student student = new Student();
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
//        student.setCourse(dto.getCourse());
        student.setAge(dto.getAge());

        // ✅ CHECK BEFORE SETTING
        if (dto.getAddress() != null) {
            Address address = new Address();
            address.setStreet(dto.getAddress().getStreet());
            address.setCity(dto.getAddress().getCity());
            address.setState(dto.getAddress().getState());
            address.setZipCode(dto.getAddress().getZipCode());
            student.setAddress(address);
        }

        return student;
    }

    private StudentResponseDTO mapToResponseDTO(Student student) {

        StudentResponseDTO dto = new StudentResponseDTO();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setEmail(student.getEmail());
//        dto.setCourse(student.getCourse());
        dto.setAge(student.getAge());

        // ✅ SAFE NULL CHECK
        if (student.getAddress() != null) {
            AddressResponseDTO addressDTO = new AddressResponseDTO();
            addressDTO.setStreet(student.getAddress().getStreet());
            addressDTO.setCity(student.getAddress().getCity());
            addressDTO.setState(student.getAddress().getState());
            addressDTO.setZipCode(student.getAddress().getZipCode());
            dto.setAddress(addressDTO);
        }

        return dto;
    }

}

