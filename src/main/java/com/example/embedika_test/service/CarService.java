package com.example.embedika_test.service;

import com.example.embedika_test.dao.model.Car;

import java.util.List;

public interface CarService {

    List<Car> getAllCars();

    Car findByCarId(Long carId);

    Car addCar(Car car);

    void deleteByCarId(Long carId);
}
