package com.example.service;

import com.example.model.dto.PersonDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonService {
    PersonDto createPerson(PersonDto personDto);
    PersonDto getById(Long id);
    List<PersonDto> getAll();
    String updatePerson(PersonDto personDto);
    String deletePerson(Long id);
}
