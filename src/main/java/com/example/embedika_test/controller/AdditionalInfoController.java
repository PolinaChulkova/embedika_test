package com.example.embedika_test.controller;

import com.example.embedika_test.dao.dto.CarMarkDto;
import com.example.embedika_test.dao.dto.CarModelsDto;
import com.example.embedika_test.service.AdditionalInfoService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<?> getAllInfoForCars(@PathVariable Integer carMarkId) {
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
}
