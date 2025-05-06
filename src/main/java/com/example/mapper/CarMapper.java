package com.example.mapper;

import com.example.model.dto.CarDto;
import com.example.model.entity.Car;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarMapper {
    Car toEntity(Car carDto);
    Car toDto(Car car);
    List<CarDto> toDtoList (List<Car> carDtoList) ;

    void mapDtoToEntity(CarDto carDto, @MappingTarget Car car) ;

}
