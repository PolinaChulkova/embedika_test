package com.example.embedika_test.dao.dto;

import com.example.embedika_test.dao.model.CarModel;
import com.example.embedika_test.dao.model.Region;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class OtherInfo {

    private final List<CarModel> carModels;
    private final List<Region> regions;
}
