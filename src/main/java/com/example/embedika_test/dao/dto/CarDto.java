package com.example.embedika_test.dao.dto;

import com.example.embedika_test.dao.model.AutoBodyType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CarDto {

    private final String carNumber;
    private final String color;
    private final String year;
    private final Byte amountOfOwners;
    private Integer mileage;
    private AutoBodyType bodyType;
    private final String regionNumber;
    private final String carModelName;

}
