package com.example.embedika_test.controller;

import com.example.embedika_test.dao.model.Car;
import com.example.embedika_test.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllCars() {
        return ResponseEntity.ok().body(carService.getAllCars());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCar(@RequestBody Car car) {
        return ResponseEntity.ok().body(carService.addCar(car));
    }

    @DeleteMapping("/delete/{carId}")
    public ResponseEntity<?> deleteCarById(@PathVariable("carId") Long carId) {
        carService.deleteByCarId(carId);
        return ResponseEntity.ok().body("Удален");
    }
}
