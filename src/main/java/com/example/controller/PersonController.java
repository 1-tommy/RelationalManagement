package com.example.controller;

import com.example.model.dto.PersonDto;
import com.example.model.entity.Person;
import com.example.service.impl.PersonServiceimpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {
    private PersonServiceimpl personService;

    public PersonController(PersonServiceimpl personService) {
        this.personService = personService;
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    public Person createPerson(@RequestBody Person person) {
        return personService.createPerson(person);
    }
    @GetMapping()
    public List<PersonDto> getAllPersons() {
        return personService.getAll();
    }
    @GetMapping
    public PersonDto getPersonById(@RequestParam("id") Long id) {
        return personService.getById(id);
    }
    @DeleteMapping()
    public void deletePersonById(@RequestParam("id") Long id) {

    }
}
