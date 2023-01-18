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
    @Column(name = "color")
    private String color;
    @Column(name = "year")
    private String year;
    @Column(name = "amountOfOwners")
    private Byte amountOfOwners;
    @Column(name = "date_added")
    private LocalDateTime dateAdded = LocalDateTime.now();

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "region")
    private Region region;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "car_model")
    private CarModel carModel;

    public Car(String carNumber, String color, String year, Region region, CarModel carModel) {
        this.carNumber = carNumber;
        this.color = color;
        this.year = year;
        this.region = region;
        this.carModel = carModel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(carId, car.carId)
                && Objects.equals(carNumber, car.carNumber)
                && Objects.equals(color, car.color)
                && Objects.equals(year, car.year)
                && Objects.equals(dateAdded, car.dateAdded)
                && Objects.equals(region, car.region)
                && Objects.equals(carModel, car.carModel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carId, carNumber, color, year, dateAdded, region, carModel);
    }
}
