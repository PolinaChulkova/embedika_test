package com.example.embedika_test.dao.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "car_models")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CarModel {
    @Id
    @GeneratedValue
    @Column(name = "model_id")
    private Integer modelId;
    @Column(name = "model_name")
    private String name;
}
