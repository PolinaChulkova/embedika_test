package com.example.embedika_test.dao.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CarModelsDto {

    private final int carMarkId;
    private final String[] carModelsNames;
}
