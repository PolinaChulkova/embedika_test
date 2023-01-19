package com.example.embedika_test.dao.dto;

import com.example.embedika_test.dao.model.CarBodyType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CarDto {

    private final String carNumber;
    private final String color;
    private final String year;
    private final Byte amountOfOwners;
    private Integer mileage;
    private CarBodyType bodyType;
    private final String regionNumber;
    private final String carModelName;

}
