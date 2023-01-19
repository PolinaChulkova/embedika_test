package com.example.embedika_test.controller;

import com.example.embedika_test.dao.dto.CarMarkDto;
import com.example.embedika_test.dao.dto.CarModelsDto;
import com.example.embedika_test.service.impl.AdditionalInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class AdditionalInfoController {

    private final AdditionalInfoService additionalInfoService;

    @GetMapping("/all-info-for-cars")
    public ResponseEntity<?> getAllInfoForCars() {
        return ResponseEntity.ok().body(additionalInfoService.getAllInfoForCars());
    }

    @GetMapping("/car-models/{carMarkId}")
    public ResponseEntity<?> getCarModelsByMark(@PathVariable Integer carMarkId) {
        return ResponseEntity.ok().body(additionalInfoService.findCarModelsByMarkId(carMarkId));
    }

    @PostMapping("/add/regions")
    public ResponseEntity<?> addRegions(@RequestBody String[] regionsNumbers) {
        return ResponseEntity.ok().body(additionalInfoService.addRegions(regionsNumbers));
    }

    @PostMapping("/add/car-mark")
    public ResponseEntity<?> addCarMark(@RequestBody CarMarkDto markDto) {
        return ResponseEntity.ok().body(additionalInfoService.addCarMark(markDto));
    }

    @PostMapping("/add/car-models")
    public ResponseEntity<?> addCarModels(@RequestBody CarModelsDto modelsDto) {
        return ResponseEntity.ok().body(additionalInfoService.addCarModels(modelsDto));
    }

    @DeleteMapping("/region/{regionId}")
    public ResponseEntity<?> deleteRegion(@PathVariable Short regionId) {
        additionalInfoService.deleteRegionById(regionId);
        return ResponseEntity.ok().body(HttpStatus.OK.toString());
    }

    @DeleteMapping("/car-mark/{markId}")
    public ResponseEntity<?> deleteCarMark(@PathVariable Integer markId) {
        additionalInfoService.deleteMarkById(markId);
        return ResponseEntity.ok().body(HttpStatus.OK.toString());
    }

    @DeleteMapping("/car-model/{modelId}")
    public ResponseEntity<?> deleteCarModel(@PathVariable Integer modelId) {
        additionalInfoService.deleteModelById(modelId);
        return ResponseEntity.ok().body(HttpStatus.OK.toString());
    }
}
