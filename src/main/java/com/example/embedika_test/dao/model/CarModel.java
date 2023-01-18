package com.example.embedika_test.dao.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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

    @OneToMany(mappedBy = "carModel", cascade = CascadeType.MERGE)
    private Set<Car> cars = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarModel carModel = (CarModel) o;
        return Objects.equals(modelId, carModel.modelId) && Objects.equals(name, carModel.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(modelId, name);
    }
}
