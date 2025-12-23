package com.example.StudentManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequestDTO {
    private Long id;
    private String name;
//    private long contactNO;
    private String email;
    private Integer age;

    private AddressRequestDTO address;
}
