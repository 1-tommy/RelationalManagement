package com.example.service.impl;

import com.example.exception.CarNotFoundException;
import com.example.exception.PersonNotFoundException;
import com.example.mapper.PersonMapper;
import com.example.model.dto.CarDto;
import com.example.model.dto.PersonDto;
import com.example.model.entity.Car;
import com.example.model.entity.Person;
import com.example.repository.PersonRepository;
import com.example.service.PersonService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceimpl implements PersonService {

    private static final String PERSON_CACHE = "person";
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;



    public PersonServiceimpl(PersonRepository personRepositoryRepository, PersonMapper personMapperMapper, PersonRepository personRepository, PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }

    public Person createPerson(Person person) {
        return personRepository.save(person);
    }



    @Cacheable(value = PERSON_CACHE , key = "#result.id()")
    public PersonDto createPerson(PersonDto personDto) {
        Person entity = personMapper.toEntity(personDto);
        personRepository.save(entity);
        return personMapper.toDto(entity);
    }

    @Cacheable(value = PERSON_CACHE, key ="#id")
    public PersonDto getById(Long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException("product not doun " + id));

        return personMapper.toDto(person);
    }



    @Cacheable(value = PERSON_CACHE, key ="'all'")
    public List<PersonDto> getAll() {
        List<Person> all = personRepository.findAll();
        Person person = new Person();
        return (List<PersonDto>) personMapper.toDto(person);
    }

    @Cacheable(value = PERSON_CACHE, key ="#result.getId()")
    public String updatePerson(PersonDto personDto) {
        Long id = personDto.getId();

        Person person = personRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException("person not exist " + id));


        personMapper.mapDtoToEntity(personDto, person);
        personMapper.toDto(personRepository.save(person));


        return "car updated with id: " + id;
    }


    @CacheEvict(value = PERSON_CACHE, key = "#id")
    public String deletePerson (Long id) {
        personRepository.deleteById(id);
        return "car deleted with id: " + id;
    }
}
