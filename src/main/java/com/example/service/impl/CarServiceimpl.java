package com.example.service.impl;

import com.example.exception.CarNotFoundException;
import com.example.mapper.CarMapper;
import com.example.model.dto.CarDto;
import com.example.model.entity.Car;
import com.example.repository.CarRepository;
import com.example.service.CarService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceimpl implements CarService {
    private static final String CAR_CACHE = "car";
    private final CarRepository carRepository;
    private final CarMapper carMapper;

    public CarServiceimpl(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }

//    public Car createCar(Car car) {
//        return carRepository.save(car);
//    }



    @Cacheable(value = CAR_CACHE , key = "#result.id()")
    public Car createCar(Car carDto) {
        Car entity = carMapper.toEntity(carDto);
        carRepository.save(entity);
        return carMapper.toDto(entity);
    }

    @Cacheable(value = CAR_CACHE, key ="#id")
    public Car getById(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException("product not doun " + id));

        return carMapper.toDto(car);
    }



    @Cacheable(value = CAR_CACHE, key = "'all'")
    public List<Car> getAll() {
        List<Car> all = carRepository.findAll();
        return all.stream()
                .map(carMapper::toDto)
                .toList();
    }

    @Cacheable(value = CAR_CACHE, key ="#result.getId()")
    public String updateCar (CarDto carDto) {
        Long id = carDto.getId();

        Car car = carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException("car not exist " + id));

        System.out.println("hello");

        carMapper.mapDtoToEntity(carDto, car);
        carMapper.toDto(carRepository.save(car));


        return "car updated with id: " + id;
    }


    @CacheEvict(value = CAR_CACHE, key = "#id")
    public String deleteCar (Long id) {
        carRepository.deleteById(id);
        return "car deleted with id: " + id;
    }
}
