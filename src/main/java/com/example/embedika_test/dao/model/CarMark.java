package com.example.embedika_test.dao.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "car_marks")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "markId")
@NoArgsConstructor
@Getter
@Setter
public class CarMark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mark_id")
    private Integer markId;
    @Column(name = "mark_name")
    private String name;

    @JsonBackReference
    @OneToMany(mappedBy = "carMark", cascade = {CascadeType.MERGE})
    private Set<Car> cars = new HashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "modelId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<CarModel> models = new HashSet<>();

    public CarMark(String name) {
        this.name = name;
    }

    public CarMark(String name, Set<CarModel> models) {
        this.name = name;
        this.models = models;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarMark carMark = (CarMark) o;
        return Objects.equals(markId, carMark.markId) && Objects.equals(name, carMark.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(markId, name);
    }
}
