package com.example.embedika_test.service.impl;


import com.example.embedika_test.dao.dto.CarDto;
import com.example.embedika_test.dao.model.Car;
import com.example.embedika_test.dao.model.CarMark;
import com.example.embedika_test.dao.model.CarModel;
import com.example.embedika_test.repository.CarRepository;
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
    private final AdditionalInfoService additionalInfoService;

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
        try {
            if (carRepository.existsByCarNumberAndRegionNumber(carDto.getCarNumber(), carDto.getRegionNumber())) {
                throw new EntityExistsException("Автомобиль с регистрационным знаком \""
                        + carDto.getCarNumber() + carDto.getRegionNumber() + "\" уже существует!");
            }
            return carRepository.save(new Car(
                    carDto.getCarNumber(),
                    carDto.getColor(),
                    carDto.getYear(),
                    carDto.getAmountOfOwners(),
                    carDto.getMileage(),
                    carDto.getBodyType(),
                    additionalInfoService.findRegionByRegionNumber(carDto.getRegionNumber()),
                    additionalInfoService.findMarkByName(carDto.getCarMark()),
                    additionalInfoService.findModelByName(carDto.getCarModelName())

            ));
        } catch (RuntimeException e) {
            throw new RuntimeException(String.format("Автомобиль \"%s\" не добавлен в справочник! Error: %s",
                    carDto.getCarNumber(), e));
        }
    }

    @Override
    public Car updateCar(Long carId, CarDto carDto) {
        try {
            Car car = findByCarId(carId);
            CarMark carMark = additionalInfoService.findMarkByName(carDto.getCarMark());
            CarModel carModel = additionalInfoService.findModelByName(carDto.getCarModelName());

            car.setCarNumber(car.getCarNumber());
            car.setColor(car.getColor());
            car.setYear(carDto.getYear());
            car.setAmountOfOwners(car.getAmountOfOwners());
            car.setMileage(carDto.getMileage());
            car.setBodyType(carDto.getBodyType());
            car.setRegion(additionalInfoService.findRegionByRegionNumber(carDto.getRegionNumber()));
            car.setCarMark(carMark);
            car.setCarModel(carMark.getModels().contains(carModel) ? carModel : null);

            return carRepository.save(car);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Ну удалось обновить автомобиль с id = %d! Error: %s", carId, e));
        }
    }

    @Override
    public void deleteByCarId(Long carId) {
        try {
            if (!carRepository.existsById(carId))
                throw new EntityNotFoundException("Автомобиль не найден!");

            carRepository.deleteById(carId);

        } catch (RuntimeException e) {
            throw new RuntimeException(String.format("Не удалось удалить автомобиль с id = %d! Error: %s", carId, e));
        }
    }
}
