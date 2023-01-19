package com.example.embedika_test.service.impl;

import com.example.embedika_test.dao.dto.AllInfoForCarsDto;
import com.example.embedika_test.dao.dto.CarMarkDto;
import com.example.embedika_test.dao.dto.CarModelsDto;
import com.example.embedika_test.dao.model.CarBodyType;
import com.example.embedika_test.dao.model.CarMark;
import com.example.embedika_test.dao.model.CarModel;
import com.example.embedika_test.dao.model.Region;
import com.example.embedika_test.repository.CarMarkRepository;
import com.example.embedika_test.repository.CarModelRepository;
import com.example.embedika_test.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdditionalInfoService {

    private final CarModelRepository carModelRepository;
    private final RegionRepository carRegionRepository;
    private final CarMarkRepository carMarkRepository;

    public AllInfoForCarsDto getAllInfoForCars() {
        return new AllInfoForCarsDto(
                carMarkRepository.findAll(),
                carRegionRepository.findAll(),
                CarBodyType.values()
        );
    }

    public List<Region> addRegions(String[] regionsNumbers) {
        return Arrays.stream(regionsNumbers)
                .map(Region::new)
                .map(carRegionRepository::save)
                .collect(Collectors.toList());
    }

    public CarMark addCarMark(CarMarkDto carMarkDto) {
        CarMark carMark = carMarkRepository.save(new CarMark(carMarkDto.getName()));
        carMark.setModels(addCarModels(new CarModelsDto(
                carMark.getMarkId(),
                carMarkDto.getCarModelsNames())));
        return carMarkRepository.save(carMark);
    }

    public Set<CarModel> addCarModels(CarModelsDto carModelsDto) {
        return Arrays.stream(carModelsDto.getCarModelsNames())
                .filter(cm -> !carModelRepository.existsByName(cm))
                .map(cm -> new CarModel(cm, findMarkById(carModelsDto.getCarMarkId())))
                .map(carModelRepository::save)
                .collect(Collectors.toSet());
    }

    public List<CarModel> findCarModelsByMarkId(Integer carMarkId) {
        return carModelRepository.findAllByCarMarkId(carMarkId);
    }

    public CarMark findMarkById(Integer carMarkId) {
        return carMarkRepository.findById(carMarkId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Марка автомобиля не найдена!"));
    }

    public Region findRegionByRegionNumber(String regionNumber) {
        return carRegionRepository.findByRegionNumber(regionNumber)
                .orElseThrow(() ->
                        new EntityNotFoundException("Регион " + regionNumber + " не найден!"));
    }

    public CarMark findMarkByName(String name) {
        return carMarkRepository.findByName(name)
                .orElseThrow(() ->
                        new EntityNotFoundException("Марка автомобиля не найдена!"));
    }

    public CarModel findModelByName(String name) {
        return carModelRepository.findByName(name)
                .orElseThrow(() ->
                        new EntityNotFoundException("Модель автомобиля \"" + name + "\" не найдена!"));
    }

    public void deleteMarkById(Integer carMarkId) {
        try {
            if (!carModelRepository.existsById(carMarkId))
                throw new EntityNotFoundException("Марка автомобиля с id = " + carMarkId + " не найдена!");

            carModelRepository.deleteById(carMarkId);

        } catch (RuntimeException e) {
            throw new RuntimeException("Не удалось удалить марку автомобилей с id = " + carMarkId + "!");
        }
    }

    public void deleteModelById(Integer carModelId) {
        try {
            if (!carModelRepository.existsById(carModelId))
                throw new EntityNotFoundException("Модель автомобиля с id = " + carModelId + " не найдена!");

            carModelRepository.deleteById(carModelId);

        } catch (RuntimeException e) {
            throw new RuntimeException("Не удалось удалить модель автомобилей с id = " + carModelId + "!");
        }
    }

    public void deleteRegionById(Short regionId) {
        try {
            if (!carRegionRepository.existsById(regionId))
                throw new EntityNotFoundException("Регион с id = " + regionId + " не найден!");

            carRegionRepository.deleteById(regionId);

        } catch (RuntimeException e) {
            throw new RuntimeException("Не удалось удалить регион с id = " + regionId + "!");
        }
    }
}
