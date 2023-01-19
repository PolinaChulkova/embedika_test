package com.example.embedika_test.service;

import com.example.embedika_test.dao.dto.InfoForCarsDto;
import com.example.embedika_test.dao.dto.CarMarkDto;
import com.example.embedika_test.dao.dto.CarModelsDto;
import com.example.embedika_test.dao.model.CarMark;
import com.example.embedika_test.dao.model.CarModel;
import com.example.embedika_test.dao.model.Region;

import java.util.List;
import java.util.Set;

public interface InfoForCars {

    InfoForCarsDto getAllInfoForCars();

    List<Region> addRegions(String[] regionsNumbers);

    CarMark addCarMark(CarMarkDto carMarkDto);

    Set<CarModel> addCarModels(CarModelsDto carModelsDto);

    List<CarModel> findCarModelsByMarkId(Integer carMarkId);

    CarMark findMarkById(Integer carMarkId);

    Region findRegionByRegionNumber(String regionNumber);

    CarMark findMarkByName(String name);

    CarModel findModelByName(String name);

    void deleteMarkById(Integer carMarkId);

    void deleteModelById(Integer carModelId);

    void deleteRegionById(Short regionId);
}
