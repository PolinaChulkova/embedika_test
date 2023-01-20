package com.example.embedika_test.dao.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "cars", uniqueConstraints = {@UniqueConstraint(columnNames = {"car_number", "region_id"})})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "carId")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private Long carId;
    @Column(name = "car_number")
    private String carNumber;
    @Column(name = "color")
    private String color;
    @Column(name = "year")
    private String year;
    @Column(name = "amountOfOwners")
    private Integer amountOfOwners;
    @Column(name = "mileage")
    private Integer mileage;
    @Column(name = "body_type")
    @Enumerated(EnumType.STRING)
    private CarBodyType bodyType;
    @Column(name = "date_added")
    private LocalDateTime dateAdded = LocalDateTime.now();

    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "region_id", referencedColumnName = "region_id")
    private Region region;

    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "car_mark_id", referencedColumnName = "mark_id")
    private CarMark carMark;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "car_model_id", referencedColumnName = "model_id")
    private CarModel carModel;

    public Car(String carNumber, String color, String year, Integer amountOfOwners,
               Integer mileage, CarBodyType bodyType, Region region, CarMark carMark, CarModel carModel) {
        this.carNumber = carNumber;
        this.color = color;
        this.year = year;
        this.amountOfOwners = amountOfOwners;
        this.mileage = mileage;
        this.bodyType = bodyType;
        this.region = region;
        this.carMark = carMark;
        this.carModel = carModel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(carId, car.carId) && Objects.equals(carNumber, car.carNumber) &&
                Objects.equals(color, car.color) && Objects.equals(year, car.year) &&
                Objects.equals(amountOfOwners, car.amountOfOwners) &&
                Objects.equals(mileage, car.mileage) && bodyType == car.bodyType &&
                Objects.equals(dateAdded, car.dateAdded);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carId, carNumber, color, year, amountOfOwners, mileage, bodyType, dateAdded);
    }
}
