package com.example.service;

import com.example.model.dto.CarDto;
import com.example.model.entity.Car;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarService {
    Car createCar(Car car); // можно также использовать CarDto если хочешь DTO на вход
    Car getById(Long id);
    List<Car> getAll();
    String updateCar(CarDto carDto);
    String deleteCar(Long id);
}
