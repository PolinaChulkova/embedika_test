package com.example.embedika_test.dao.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class CarDto {

    private final String carNumber;
    private final String color;
    private final String year;
    private final Byte amountOfOwners;
    private final String regionNumber;
    private final String carModelName;
}
