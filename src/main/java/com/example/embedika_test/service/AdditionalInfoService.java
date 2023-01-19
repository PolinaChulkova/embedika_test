package com.example.embedika_test.service;

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

import javax.persistence.EntityExistsException;
import java.util.Arrays;
import java.util.List;
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

    public List<CarMark> addCarMark(CarMarkDto carMarkDto) {
        CarMark carMark = carMarkRepository.save(new CarMark(carMarkDto.getName()));
        Arrays.stream(carMarkDto.getCarModelsNames())
                .map(carModel -> new CarModel(carModel, carMark))
                .map(carModelRepository::save).collect(Collectors.toList());
        return carMarkRepository.findAll();
    }

    public List<CarModel> addCarModels(CarModelsDto carModelsDto) {
        return Arrays.stream(carModelsDto.getCarModelsNames())
                .filter(cm -> !carModelRepository.existsByName(cm))
                .map(cm -> new CarModel(cm, findMarkById(carModelsDto.getCarMarkId())))
                .collect(Collectors.toList());
    }

    public List<CarModel> findCarModelsByMarkId(Integer carMarkId) {
        return carModelRepository.findAllByCarMarkId(carMarkId);
    }

    public CarMark findMarkById(Integer carMarkId) {
        return carMarkRepository.findById(carMarkId)
                .orElseThrow(() ->
                        new EntityExistsException("Марка автомобиля не найдена!"));
    }

    public CarModel findModelByName(String name) {
        return carModelRepository.findByName(name)
                .orElseThrow(() ->
                        new EntityExistsException("Модель автомобиля \"" + name + "\" не найдена!"));
    }
}
