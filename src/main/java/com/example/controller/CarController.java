package com.example.controller;

import com.example.model.dto.CarDto;
import com.example.model.entity.Car;
import com.example.service.impl.CarServiceimpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarController {
    private final CarServiceimpl carService;

    public CarController(CarServiceimpl carService) {
        this.carService = carService;
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    public String createCar(@RequestBody Car car) {
        return carService.createCar(car).toString();
    }

    @GetMapping
    @Cacheable(value = "#id")
    public List<CarDto> getAllCars() {
        return carService.getAll();
    }

    @GetMapping
    public Car getCarById(@RequestParam Long id){
        return carService.getById(id);
    }

    @DeleteMapping
    public String deleteCarById(@RequestParam Long id) {
        return carService.deleteCar(id);
    }

}
