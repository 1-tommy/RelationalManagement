package com.example.mapper;

import com.example.model.dto.CarDto;
import com.example.model.dto.PersonDto;
import com.example.model.entity.Car;
import com.example.model.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    Person toEntity(PersonDto personDto);
    PersonDto toDto(Person person);
    List<PersonDto> toDtoList (List<Person> personDtoList) ;

    void mapDtoToEntity(PersonDto personDto, @MappingTarget Person person) ;

}
