package com.example.StudentManagementSystem.controller;

import com.example.StudentManagementSystem.dto.StudentRequestDTO;
import com.example.StudentManagementSystem.dto.StudentResponseDTO;
import com.example.StudentManagementSystem.studentservice.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class StudentViewController {

    private final StudentService studentService;

    @GetMapping({"/","","/index"})
    public String display(Model model) {

        List<StudentResponseDTO> students = studentService.getAllStudents();
        model.addAttribute("students",students);
        return "index";
    }
    @GetMapping({"/delete/{id}"})
    public String update(Model model, @PathVariable Long id) {

        studentService.delete(id);
        return "redirect:/index";
    }
    @GetMapping("/update/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {

        StudentResponseDTO response = studentService.getStudent(id);

        // MANUAL MAPPING Response → Request DTO
        StudentRequestDTO formDto = new StudentRequestDTO();
        formDto.setId(response.getId());
        formDto.setName(response.getName());
        formDto.setEmail(response.getEmail());
        formDto.setAge(response.getAge());

        model.addAttribute("student", formDto);

        return "form";
    }


    @GetMapping({"/","","/form"})
    public String displayForm(Model model) {
        model.addAttribute("student",new StudentResponseDTO());
//        List<StudentResponseDTO> students = studentService.getAllStudents();
//        model.addAttribute("students",students);
        return "form";
    }
    @PostMapping("/register")
    public String saveStudent(
            @ModelAttribute("student") StudentRequestDTO dto) {

        System.out.println("FORM ID = " + dto.getId());

        if (dto.getId() == null) {
            System.out.println(">>> CREATE FLOW");
            studentService.saveStudent(dto);
        } else {
            System.out.println(">>> UPDATE FLOW");
            studentService.updateStudent(dto.getId(), dto);
        }

        return "redirect:/index";
    }


}
