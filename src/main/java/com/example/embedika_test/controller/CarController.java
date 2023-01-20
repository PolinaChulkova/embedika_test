package com.example.embedika_test.controller;

import com.example.embedika_test.dao.dto.CarDto;
import com.example.embedika_test.service.CarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(description = "API для работы с автомобилями")
@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    /**
     * Получение всех автомобилей из справочника с учётом опциональных параметров
     * @param sort - атрибут, по которому сортируется список
     * @param order - порядок элементов
     * @param page - номер страницы с 0
     * @param size - количество элементов на странице
     * @return JSON со списком в теле ответа
     */
    @ApiOperation("Получение всех автомобилей из справочника с учётом опциональных параметров")
    @GetMapping("/all")
    public ResponseEntity<?> getAllCars(
            @RequestParam(value = "sort", required = false, defaultValue = "dateAdded") String sort,
            @RequestParam(value = "order", required = false, defaultValue = "DESC") String order,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(order), sort));
        return ResponseEntity.ok().body(carService.getAllCars(pageable).getContent());
    }

    /**
     * Поиск автомобилей по их номеру и региону (номер + регион = уникальный регистрационный номер автомобиля)
     * @param carNumber - номер автомобиля (без региона)
     * @param regionNumber - номер региона
     * @param sort - атрибут, по которому сортируется список
     * @param order - порядок элементов
     * @param page - номер страницы с 0
     * @param size - количество элементов на странице
     * @return JSON со списком в теле ответа
     */
    @ApiOperation("Поиск автомобилей по их номеру и региону")
    @GetMapping("/search")
    public ResponseEntity<?> searchCarsByCarNumberAndRegionNumber(
            @RequestParam(value = "carNumber", required = false) String carNumber,
            @RequestParam(value = "regionNumber", required = false) String regionNumber,
            @RequestParam(value = "sort", required = false, defaultValue = "dateAdded") String sort,
            @RequestParam(value = "order", required = false, defaultValue = "DESC") String order,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(order), sort));
        return ResponseEntity.ok().body(carService.searchByCarNumberAndRegionNumber(carNumber, regionNumber, pageable));
    }

    /**
     * Поиск автомобилей по ключевому слову, которое совпадает со значением одного из полей
     * (имя модели, название марки, цвет, тип кузова)
     * @param search - ключевое слово
     * @param sort -
     * @param sort - атрибут, по которому сортируется список
     * @param order - порядок элементов
     * @param page - номер страницы с 0
     * @param size - количество элементов на странице
     * @return JSON со списком в теле ответа
     */
    @ApiOperation("Поиск автомобилей по ключевому слову, которое совпадает со значением одного из полей (имя модели, название марки, цвет, тип кузова)")
    @GetMapping
    public ResponseEntity<?> searchCarsByText(
            @RequestParam(value = "search") String search,
            @RequestParam(value = "sort", required = false, defaultValue = "dateAdded") String sort,
            @RequestParam(value = "order", required = false, defaultValue = "DESC") String order,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(order), sort));
        return ResponseEntity.ok().body(carService.searchByText(search, pageable));
    }

    @ApiOperation("Поиск автомобиля по его id")
    @GetMapping("/{carId}")
    public ResponseEntity<?> findCarByCarId(@PathVariable("carId") Long carId) {
        return ResponseEntity.ok().body(carService.findByCarId(carId));
    }

    /**
     * Добавление нового автомобиля в список
     * @param carDto - объект для создания автомобиля
     * @return JSON с добавленным в справочник объектом Car
     */
    @ApiOperation("Добавление автомобиля в справочник")
    @PostMapping("/add")
    public ResponseEntity<?> addCar(@RequestBody CarDto carDto) {
        return ResponseEntity.ok().body(carService.addCar(carDto));
    }

    @ApiOperation("Удаление автомобиля по его id")
    @DeleteMapping("/{carId}")
    public ResponseEntity<?> deleteCarById(@PathVariable("carId") Long carId) {
        carService.deleteByCarId(carId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
