package com.example.embedika_test.service.impl;

import com.example.embedika_test.dao.dto.CarDto;
import com.example.embedika_test.dao.model.Car;
import com.example.embedika_test.dao.model.CarBodyType;
import com.example.embedika_test.repository.CarRepository;
import com.example.embedika_test.service.CarService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.example.embedika_test.dao.model.CarBodyType.OFF_ROAD;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class CarServiceImplTest {

    private final CarService carService;

    public CarServiceImplTest(CarService carService) {
        this.carService = carService;
    }

    @BeforeEach
    void setup() {
        Car car = carService.addCar(new CarDto(
                "АН899Х",
                "синий",
                "2012",
                3,
                60000,
                CarBodyType.SEDAN,
                "177",
                "BMW",
                "5 серия"
        ));

        Car car2 = carService.addCar(new CarDto(
                "АН899Х",
                "красный",
                "2013",
                3,
                60000,
                CarBodyType.SEDAN,
                "99",
                "BMW",
                "5 серия"
        ));

        findAndPrintCar(car.getCarId());
        findAndPrintCar(car2.getCarId());
    }

    private void findAndPrintCar(Long carId) {
        log.info("Получен автомобиль {}", carService.findByCarId(carId));
    }

    @Test
    void getAllCars() {
    }

    @Test
    void findByCarId() {
    }

    @Test
    void searchByText() {
    }

    @Test
    void searchByCarNumberAndRegionNumber() {
    }

    @Test
    void addCar() {
    }

    @Test
    void updateCar() {
    }

    @Test
    void deleteByCarId() {
    }
}