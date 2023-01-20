package com.example.embedika_test.controller;

import com.example.embedika_test.dao.dto.CarMarkDto;
import com.example.embedika_test.dao.dto.CarModelsDto;
import com.example.embedika_test.service.InfoForCars;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(description = "PI для работы с дополнительными данными по автомобилям (регионы, марки и модели автомобилей)")
@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class InfoForCarsController {

    private final InfoForCars infoForCars;

    /**
     * Получение всех, добавленных в справочник марок автомобилей и регионов
     * @return JSON с массивами марок и регионов
     */
    @ApiOperation("Получение всех, добавленных в справочник марок автомобилей и регионов")
    @GetMapping("/all-info-for-cars")
    public ResponseEntity<?> getAllInfoForCars() {
        return ResponseEntity.ok().body(infoForCars.getAllInfoForCars());
    }

    /**
     * Получение всех моделей автомобилей, относящихся к указанной марке
     * @return JSON с массивом моделей
     */
    @ApiOperation("Получение всех моделей автомобилей, относящихся к указанной марке")
    @GetMapping("/car-models/{carMarkId}")
    public ResponseEntity<?> getCarModelsByMark(@PathVariable Integer carMarkId) {
        return ResponseEntity.ok().body(infoForCars.findCarModelsByMarkId(carMarkId));
    }

    @ApiOperation("Добавление списка регионов")
    @PostMapping("/add/regions")
    public ResponseEntity<?> addRegions(@RequestBody String[] regionsNumbers) {
        return ResponseEntity.ok().body(infoForCars.addRegions(regionsNumbers));
    }

    @ApiOperation("Добавление марки автомобиля")
    @PostMapping("/add/car-mark")
    public ResponseEntity<?> addCarMark(@RequestBody CarMarkDto markDto) {
        return ResponseEntity.ok().body(infoForCars.addCarMark(markDto));
    }

    @ApiOperation("Добавление списка моделей автомобилей, относящихся к одной марке")
    @PostMapping("/add/car-models")
    public ResponseEntity<?> addCarModels(@RequestBody CarModelsDto modelsDto) {
        return ResponseEntity.ok().body(infoForCars.addCarModels(modelsDto));
    }

    @ApiOperation("Удаление региона автомобиля по его id")
    @DeleteMapping("/region/{regionId}")
    public ResponseEntity<?> deleteRegion(@PathVariable Integer regionId) {
        infoForCars.deleteRegionById(regionId);
        return ResponseEntity.ok().body(HttpStatus.OK.toString());
    }

    @ApiOperation("Удаление марки автомобиля по её id")
    @DeleteMapping("/car-mark/{markId}")
    public ResponseEntity<?> deleteCarMark(@PathVariable Integer markId) {
        infoForCars.deleteMarkById(markId);
        return ResponseEntity.ok().body(HttpStatus.OK.toString());
    }

    @ApiOperation("Удаление модели автомобиля по её id")
    @DeleteMapping("/car-model/{modelId}")
    public ResponseEntity<?> deleteCarModel(@PathVariable Integer modelId) {
        infoForCars.deleteModelById(modelId);
        return ResponseEntity.ok().body(HttpStatus.OK.toString());
    }
}
