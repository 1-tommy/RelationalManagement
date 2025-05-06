package com.example.model.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PersonDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank(message = "name")
    private String name;
    @NotBlank(message = "surname")
    private String surname;
    @NotBlank(message = "age")
    private int age;


}
