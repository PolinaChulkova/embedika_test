package com.example.embedika_test.controller;

import com.example.embedika_test.dao.dto.CarDto;
import com.example.embedika_test.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllCars(@RequestParam(value = "sort", required = false) String sort,
                                        @RequestParam(value = "order", required = false) String order,
                                        @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                        @RequestParam(value = "size", required = false, defaultValue = "2") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.by(order).getDirection(), sort));
        return ResponseEntity.ok().body(carService.getAllCars(pageable).getContent());
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchCarsByCarNumberAndRegionNumber(@RequestParam(value = "carNumber", required = false) String carNumber,
                                                                  @RequestParam(value = "regionNumber", required = false) String regionNumber,
                                                                  @RequestParam(value = "sort", required = false, defaultValue = "dateAdded") String sort,
                                                                  @RequestParam(value = "order", required = false, defaultValue = "DESC") String order,
                                                                  @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                                                  @RequestParam(value = "size", required = false, defaultValue = "2") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(order), sort));
        return ResponseEntity.ok().body(carService.searchByCarNumberAndRegionNumber(carNumber, regionNumber, pageable));
    }

    @GetMapping
    public ResponseEntity<?> searchCarsByText(@RequestParam(value = "search") String search,
                                              @RequestParam(value = "sort", required = false, defaultValue = "dateAdded") String sort,
                                              @RequestParam(value = "order", required = false, defaultValue = "DESC") String order,
                                              @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                              @RequestParam(value = "size", required = false, defaultValue = "2") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(order), sort));
        return ResponseEntity.ok().body(carService.searchByText(search, pageable));
    }

    @GetMapping("/{carId}")
    public ResponseEntity<?> findCarByCarId(@PathVariable("carId") Long carId) {
        return ResponseEntity.ok().body(carService.findByCarId(carId));
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
