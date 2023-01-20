package com.example.embedika_test.dao.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "car_models")
@NoArgsConstructor
@Getter
@Setter
public class CarModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "model_id")
    private Integer modelId;
    @Column(name = "model_name")
    private String name;

    @JsonManagedReference
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "car_mark_id", referencedColumnName = "mark_id")
    private CarMark carMark;

    @OneToMany(mappedBy = "carModel", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<Car> cars = new HashSet<>();

    public CarModel(String name) {
        this.name = name;
    }

    public CarModel(String name, CarMark carMark) {
        this.name = name;
        this.carMark = carMark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarModel carModel = (CarModel) o;
        return Objects.equals(modelId, carModel.modelId) && Objects.equals(name, carModel.name) && Objects.equals(carMark, carModel.carMark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(modelId, name, carMark);
    }
}
