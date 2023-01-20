package com.example.embedika_test.service.impl;

import com.example.embedika_test.dao.dto.CarMarkDto;
import com.example.embedika_test.dao.dto.CarModelsDto;
import com.example.embedika_test.dao.dto.InfoForCarsDto;
import com.example.embedika_test.dao.model.CarBodyType;
import com.example.embedika_test.dao.model.CarMark;
import com.example.embedika_test.dao.model.CarModel;
import com.example.embedika_test.dao.model.Region;
import com.example.embedika_test.repository.CarMarkRepository;
import com.example.embedika_test.repository.CarModelRepository;
import com.example.embedika_test.repository.RegionRepository;
import com.example.embedika_test.service.InfoForCars;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class InfoForCarsImpl implements InfoForCars {

    private final CarModelRepository carModelRepository;
    private final RegionRepository carRegionRepository;
    private final CarMarkRepository carMarkRepository;

    @Override
    public InfoForCarsDto getAllInfoForCars() {
        log.info("Получение всех доступных марок и регионов автомобилей");
        return new InfoForCarsDto(
                carMarkRepository.findAll(),
                carRegionRepository.findAll(),
                CarBodyType.values()
        );
    }

    @Override
    public List<Region> addRegions(String[] regionsNumbers) {
        log.info("Добавление регионов в справочнил {}", regionsNumbers);
        return Arrays.stream(regionsNumbers)
                .map(Region::new)
                .map(carRegionRepository::save)
                .collect(Collectors.toList());
    }

    @Override
    public CarMark addCarMark(CarMarkDto carMarkDto) {
        log.info("Добавление марки автомобиля: {}", carMarkDto);
        try {
            CarMark carMark = carMarkRepository.save(new CarMark(carMarkDto.getName()));
            carMark.setModels(addCarModels(new CarModelsDto(
                    carMark.getMarkId(),
                    carMarkDto.getCarModelsNames())));
            return carMarkRepository.save(carMark);

        } catch (Exception e) {
            throw new RuntimeException(String.format("Не удалось добавить модель автомобиля \"%s\"! Error: %s)",
                    carMarkDto.getName(), e));
        }
    }

    @Override
    public Set<CarModel> addCarModels(CarModelsDto carModelsDto) {
        log.info("Добавление моделей автомобиля: {}", carModelsDto);
        try {
            return Arrays.stream(carModelsDto.getCarModelsNames())
                    .filter(cm -> !carModelRepository.existsByName(cm))
                    .map(cm -> new CarModel(cm, findMarkById(carModelsDto.getCarMarkId())))
                    .map(carModelRepository::save)
                    .collect(Collectors.toSet());

        } catch (Exception e) {
            throw new RuntimeException(String.format("Не удалось добавить модели автомобилей %s! Error: %s",
                    Arrays.toString(carModelsDto.getCarModelsNames()), e));
        }
    }

    @Override
    public List<CarModel> findCarModelsByMarkId(Integer carMarkId) {
        log.info("Получение моделей автомобилей по carMarkId = {}", carMarkId);
        return carModelRepository.findAllByCarMarkId(carMarkId);
    }

    @Override
    public CarMark findMarkById(Integer carMarkId) {
        log.info("Получение марки автомобиля по её id = {}", carMarkId);
        return carMarkRepository.findById(carMarkId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Марка автомобиля не найдена!"));
    }

    @Override
    public Region findRegionByRegionNumber(String regionNumber) {
        log.info("Получение региона по его номеру: {}" + regionNumber);
        return carRegionRepository.findByRegionNumber(regionNumber)
                .orElseThrow(() ->
                        new EntityNotFoundException("Регион " + regionNumber + " не найден!"));
    }

    @Override
    public CarMark findMarkByName(String name) {
        log.info("Получение марки автомобиля по ее имени: {}", name);
        return carMarkRepository.findByName(name)
                .orElseThrow(() ->
                        new EntityNotFoundException("Марка автомобиля не найдена!"));
    }

    @Override
    public CarModel findModelByName(String name) {
        log.info("Получение модели автомобиля по её имени: {}", name);
        return carModelRepository.findByName(name)
                .orElseThrow(() ->
                        new EntityNotFoundException("Модель автомобиля \"" + name + "\" не найдена!"));
    }

    @Override
    public void deleteMarkById(Integer carMarkId) {
        log.info("Удаление марки автомобиля по её id: {}", carMarkId);
        try {
            if (!carMarkRepository.existsById(carMarkId))
                throw new EntityNotFoundException("Марка автомобиля с id = " + carMarkId + " не найдена!");

            carMarkRepository.deleteById(carMarkId);

        } catch (RuntimeException e) {
            throw new RuntimeException("Не удалось удалить марку автомобилей с id = " + carMarkId + "!");
        }
    }

    @Override
    public void deleteModelById(Integer carModelId) {
        log.info("Удаление модели автомобиля по её id: {}", carModelId);
        try {
            if (!carModelRepository.existsById(carModelId))
                throw new EntityNotFoundException("Модель автомобиля с id = " + carModelId + " не найдена!");

            carModelRepository.deleteById(carModelId);

        } catch (RuntimeException e) {
            throw new RuntimeException("Не удалось удалить модель автомобилей с id = " + carModelId + "!");
        }
    }

    @Override
    public void deleteRegionById(Integer regionId) {
        log.info("Удаление региона по его id: {}", regionId);
        try {
            if (!carRegionRepository.existsById(regionId))
                throw new EntityNotFoundException("Регион с id = " + regionId + " не найден!");

            carRegionRepository.deleteById(regionId);

        } catch (RuntimeException e) {
            throw new RuntimeException("Не удалось удалить регион с id = " + regionId + "!");
        }
    }
}
