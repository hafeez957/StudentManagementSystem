package com.example.StudentManagementSystem.dto;

import lombok.Data;

@Data
public class AddressResponseDTO {
    private String street;
    private String city;
    private String state;
    private String zipCode;
}
