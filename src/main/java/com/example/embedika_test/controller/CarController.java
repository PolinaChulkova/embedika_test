package com.example.embedika_test.controller;

import com.example.embedika_test.dao.dto.CarDto;
import com.example.embedika_test.dao.dto.OtherInfo;
import com.example.embedika_test.repository.CarModelRepository;
import com.example.embedika_test.repository.RegionRepository;
import com.example.embedika_test.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;
    private final CarModelRepository carModelRepository;
    private final RegionRepository regionRepository;

    @GetMapping("/all")
    public ResponseEntity<?> getAllCars(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                        @RequestParam(value = "size", required = false, defaultValue = "2") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("dateAdded").descending());
        return ResponseEntity.ok().body(carService.getAllCars(pageable).getContent());
    }

    @GetMapping("/other-info")
    public ResponseEntity<?> getOtherInfo() {
        return ResponseEntity.ok().body(
                new OtherInfo(
                carModelRepository.findAll().stream()
                        .sorted((m1, m2) -> m1.getName().compareToIgnoreCase(m2.getName()))
                        .collect(Collectors.toList()),
                regionRepository.findAll().stream()
                        .sorted((r1, r2) -> r1.getRegionNumber().compareToIgnoreCase(r2.getRegionNumber()))
                        .collect(Collectors.toList())
        ));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCar(@RequestBody CarDto carDto) {
        return ResponseEntity.ok().body(carService.addCar(carDto));
    }

    @DeleteMapping("/delete/{carId}")
    public ResponseEntity<?> deleteCarById(@PathVariable("carId") Long carId) {
        carService.deleteByCarId(carId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
