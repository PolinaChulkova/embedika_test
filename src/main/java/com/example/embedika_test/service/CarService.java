package com.example.embedika_test.service;

import com.example.embedika_test.dao.dto.CarDto;
import com.example.embedika_test.dao.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CarService {

    Page<Car> getAllCars(Pageable pageable);

    Page<Car> searchByText(String text, Pageable pageable);

    Page<Car> searchByCarNumberAndRegionNumber(String carNumber, String regionNumber, Pageable pageable);

    Car findByCarId(Long carId);

    Car addCar(CarDto carDto);

    void deleteByCarId(Long carId);
}
