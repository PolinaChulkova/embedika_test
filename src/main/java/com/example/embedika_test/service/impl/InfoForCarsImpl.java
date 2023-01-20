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
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InfoForCarsImpl implements InfoForCars {

    private final CarModelRepository carModelRepository;
    private final RegionRepository carRegionRepository;
    private final CarMarkRepository carMarkRepository;

    @Override
    public InfoForCarsDto getAllInfoForCars() {
        return new InfoForCarsDto(
                carMarkRepository.findAll(),
                carRegionRepository.findAll(),
                CarBodyType.values()
        );
    }

    @Override
    public List<Region> addRegions(String[] regionsNumbers) {
        return Arrays.stream(regionsNumbers)
                .map(Region::new)
                .map(carRegionRepository::save)
                .collect(Collectors.toList());
    }

    @Override
    public CarMark addCarMark(CarMarkDto carMarkDto) {
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
        return carModelRepository.findAllByCarMarkId(carMarkId);
    }

    @Override
    public CarMark findMarkById(Integer carMarkId) {
        return carMarkRepository.findById(carMarkId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Марка автомобиля не найдена!"));
    }

    @Override
    public Region findRegionByRegionNumber(String regionNumber) {
        return carRegionRepository.findByRegionNumber(regionNumber)
                .orElseThrow(() ->
                        new EntityNotFoundException("Регион " + regionNumber + " не найден!"));
    }

    @Override
    public CarMark findMarkByName(String name) {
        return carMarkRepository.findByName(name)
                .orElseThrow(() ->
                        new EntityNotFoundException("Марка автомобиля не найдена!"));
    }

    @Override
    public CarModel findModelByName(String name) {
        return carModelRepository.findByName(name)
                .orElseThrow(() ->
                        new EntityNotFoundException("Модель автомобиля \"" + name + "\" не найдена!"));
    }

    @Override
    public void deleteMarkById(Integer carMarkId) {
        try {
            if (!carModelRepository.existsById(carMarkId))
                throw new EntityNotFoundException("Марка автомобиля с id = " + carMarkId + " не найдена!");

            carModelRepository.deleteById(carMarkId);

        } catch (RuntimeException e) {
            throw new RuntimeException("Не удалось удалить марку автомобилей с id = " + carMarkId + "!");
        }
    }

    @Override
    public void deleteModelById(Integer carModelId) {
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
        try {
            if (!carRegionRepository.existsById(regionId))
                throw new EntityNotFoundException("Регион с id = " + regionId + " не найден!");

            carRegionRepository.deleteById(regionId);

        } catch (RuntimeException e) {
            throw new RuntimeException("Не удалось удалить регион с id = " + regionId + "!");
        }
    }
}
