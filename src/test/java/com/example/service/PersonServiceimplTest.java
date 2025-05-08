package com.example.service;

import com.example.exception.PersonNotFoundException;
import com.example.mapper.PersonMapper;
import com.example.model.dto.PersonDto;
import com.example.model.entity.Person;
import com.example.repository.PersonRepository;
import com.example.service.impl.PersonServiceimpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PersonServiceimplTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private PersonMapper personMapper;

    @InjectMocks
    private PersonServiceimpl personService;

    private Person person;
    private PersonDto personDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        person = new Person();
        person.setId(1L);
        person.setName("John");

        personDto = new PersonDto();
        personDto.setId(1L);
        personDto.setName("John");
    }

    @Test
    void createPerson_shouldReturnSavedDto() {
        when(personMapper.toEntity(personDto)).thenReturn(person);
        when(personRepository.save(person)).thenReturn(person);
        when(personMapper.toDto(person)).thenReturn(personDto);

        PersonDto result = personService.createPerson(personDto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void getById_shouldReturnDto() {
        when(personRepository.findById(1L)).thenReturn(Optional.of(person));
        when(personMapper.toDto(person)).thenReturn(personDto);

        PersonDto result = personService.getById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void getById_shouldThrowException_whenNotFound() {
        when(personRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(PersonNotFoundException.class, () -> personService.getById(2L));
    }

    @Test
    void getAll_shouldReturnListOfDtos() {
        when(personRepository.findAll()).thenReturn(List.of(person));
        when(personMapper.toDto(person)).thenReturn(personDto);

        List<PersonDto> result = personService.getAll();

        assertEquals(1, result.size());
        assertEquals(personDto.getId(), result.get(0).getId());
    }

    @Test
    void updatePerson_shouldReturnConfirmationMessage() {
        when(personRepository.findById(1L)).thenReturn(Optional.of(person));
        when(personRepository.save(person)).thenReturn(person);

        String result = personService.updatePerson(personDto);

        assertEquals("car updated with id: 1", result);
    }

    @Test
    void deletePerson_shouldReturnConfirmationMessage() {
        doNothing().when(personRepository).deleteById(1L);

        String result = personService.deletePerson(1L);

        assertEquals("car deleted with id: 1", result);
    }
}

