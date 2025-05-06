package com.example.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;
    private String surname;
    private int age;


    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Car> cars = new ArrayList<>();



}
