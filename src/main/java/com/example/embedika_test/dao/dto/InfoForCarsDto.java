package com.example.embedika_test.dao.dto;

import com.example.embedika_test.dao.model.CarBodyType;
import com.example.embedika_test.dao.model.CarMark;
import com.example.embedika_test.dao.model.Region;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class InfoForCarsDto {

    private final List<CarMark> carMarks;
    private final List<Region> regions;
    private final CarBodyType[] carBodyType;
}
