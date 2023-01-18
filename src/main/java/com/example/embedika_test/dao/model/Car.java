package com.example.embedika_test.dao.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "cars")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Car {
    @Id
    @GeneratedValue
    @Column(name = "car_id")
    private Long carId;
    @Column(name = "car_number")
    private String carNumber;
    @Column(name = "car_model")
    private String carModel;
    @Column(name = "color")
    private String color;
    @Column(name = "year")
    private String year;
    @Column(name = "date_added")
    private LocalDateTime dateAdded;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(carId, car.carId)
                && Objects.equals(carNumber, car.carNumber)
                && Objects.equals(carModel, car.carModel)
                && Objects.equals(color, car.color)
                && Objects.equals(year, car.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carId, carNumber, carModel, color, year);
    }
}
