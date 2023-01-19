package com.example.embedika_test.controller;

import com.example.embedika_test.dao.dto.CarMarkDto;
import com.example.embedika_test.dao.dto.CarModelsDto;
import com.example.embedika_test.service.InfoForCars;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class InfoForCarsController {

    private final InfoForCars infoForCars;

    @GetMapping("/all-info-for-cars")
    public ResponseEntity<?> getAllInfoForCars() {
        return ResponseEntity.ok().body(infoForCars.getAllInfoForCars());
    }

    @GetMapping("/car-models/{carMarkId}")
    public ResponseEntity<?> getCarModelsByMark(@PathVariable Integer carMarkId) {
        return ResponseEntity.ok().body(infoForCars.findCarModelsByMarkId(carMarkId));
    }

    @PostMapping("/add/regions")
    public ResponseEntity<?> addRegions(@RequestBody String[] regionsNumbers) {
        return ResponseEntity.ok().body(infoForCars.addRegions(regionsNumbers));
    }

    @PostMapping("/add/car-mark")
    public ResponseEntity<?> addCarMark(@RequestBody CarMarkDto markDto) {
        return ResponseEntity.ok().body(infoForCars.addCarMark(markDto));
    }

    @PostMapping("/add/car-models")
    public ResponseEntity<?> addCarModels(@RequestBody CarModelsDto modelsDto) {
        return ResponseEntity.ok().body(infoForCars.addCarModels(modelsDto));
    }

    @DeleteMapping("/region/{regionId}")
    public ResponseEntity<?> deleteRegion(@PathVariable Short regionId) {
        infoForCars.deleteRegionById(regionId);
        return ResponseEntity.ok().body(HttpStatus.OK.toString());
    }

    @DeleteMapping("/car-mark/{markId}")
    public ResponseEntity<?> deleteCarMark(@PathVariable Integer markId) {
        infoForCars.deleteMarkById(markId);
        return ResponseEntity.ok().body(HttpStatus.OK.toString());
    }

    @DeleteMapping("/car-model/{modelId}")
    public ResponseEntity<?> deleteCarModel(@PathVariable Integer modelId) {
        infoForCars.deleteModelById(modelId);
        return ResponseEntity.ok().body(HttpStatus.OK.toString());
    }
}
