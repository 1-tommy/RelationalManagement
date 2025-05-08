package com.example.service;

import com.example.exception.CarNotFoundException;
import com.example.mapper.CarMapper;
import com.example.model.dto.CarDto;
import com.example.model.entity.Car;
import com.example.repository.CarRepository;
import com.example.service.impl.CarServiceimpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CarServiceimplTest {

    @Mock
    private CarRepository carRepository;

    @Mock
    private CarMapper carMapper;

    @InjectMocks
    private CarServiceimpl carService;

    private Car car;
    private CarDto carDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        car = new Car();
        car.setId(1L);

        carDto = new CarDto();
        carDto.setId(1L);
    }

    @Test
    void createCar_shouldReturnSavedDto() {
        when(carMapper.toEntity(car)).thenReturn(car);
        when(carRepository.save(car)).thenReturn(car);
        when(carMapper.toDto(car)).thenReturn(car);

        Car result = carService.createCar(car);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void getById_shouldReturnDto() {
        when(carRepository.findById(1L)).thenReturn(Optional.of(car));
        when(carMapper.toDto(car)).thenReturn(car);

        Car result = carService.getById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void getById_shouldThrowException_whenNotFound() {
        when(carRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(CarNotFoundException.class, () -> carService.getById(2L));
    }

    @Test
    void getAll_shouldReturnListOfDtos() {
        when(carRepository.findAll()).thenReturn(List.of(car));
        when(carMapper.toDto(car)).thenReturn(car);

        List<Car> result = carService.getAll();

        assertEquals(1, result.size());
        assertEquals(car.getId(), result.get(0).getId());
    }

    @Test
    void updateCar_shouldReturnConfirmationMessage() {
        when(carRepository.findById(1L)).thenReturn(Optional.of(car));
        when(carRepository.save(car)).thenReturn(car);

        String result = carService.updateCar(carDto);

        assertEquals("car updated with id: 1", result);
    }

    @Test
    void deleteCar_shouldReturnConfirmationMessage() {
        doNothing().when(carRepository).deleteById(1L);

        String result = carService.deleteCar(1L);

        assertEquals("car deleted with id: 1", result);
    }
}
