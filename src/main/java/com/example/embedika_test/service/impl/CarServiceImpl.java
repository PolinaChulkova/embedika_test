package com.example.embedika_test.service.impl;


import com.example.embedika_test.dao.dto.CarDto;
import com.example.embedika_test.dao.model.Car;
import com.example.embedika_test.dao.model.Region;
import com.example.embedika_test.repository.CarModelRepository;
import com.example.embedika_test.repository.CarRepository;
import com.example.embedika_test.repository.RegionRepository;
import com.example.embedika_test.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final RegionRepository regionRepository;
    private final CarModelRepository carModelRepository;

    @Override
    public Page<Car> getAllCars(Pageable pageable) {
        return carRepository.findAll(pageable);
    }

    @Override
    public Car findByCarId(Long carId) {
        return carRepository.findById(carId).orElseThrow(() ->
                new EntityNotFoundException("Автомобиль не найден!"));
    }

    @Override
    public Page<Car> searchByText(String text, Pageable pageable) {
        return carRepository.searchByText(text, pageable);
    }

    @Override
    public Page<Car> searchByCarNumberAndRegionNumber(String carNumber, String regionNumber, Pageable pageable) {
        return carRepository.searchByCarNumberAndRegionNumber(carNumber, regionNumber, pageable);
    }

    @Override
    public Car addCar(CarDto carDto) {

        if (carRepository.existsByCarNumberAndRegionNumber(carDto.getCarNumber(), carDto.getRegionNumber())) {
            throw new EntityExistsException("Автомобиль с регистрационным знаком \""
                    + carDto.getCarNumber() + carDto.getRegionNumber() + "\" уже существует!");
        }
        return carRepository.save(new Car(
                carDto.getCarNumber(),
                carDto.getColor(),
                carDto.getYear(),

                regionRepository.findByRegionNumber(carDto.getRegionNumber()).orElseThrow(() ->
                        new EntityNotFoundException("Регион " + carDto.getRegionNumber() + " не найден!")),

                carModelRepository.findByName(carDto.getCarModelName()).orElseThrow(() ->
                        new EntityNotFoundException("Модель автомобиля \"" + carDto.getCarModelName() + "\" не найдена!"))
        ));
    }

    @Override
    public void deleteByCarId(Long carId) {
        carRepository.deleteById(carId);
    }
}
