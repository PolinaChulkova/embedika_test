package com.example.embedika_test.dao.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "car_mark")
@NoArgsConstructor
@Getter
@Setter
public class CarMark {
    @Id
    @GeneratedValue
    @Column(name = "mark_id")
    private Integer markId;
    @Column(name = "mark_name")
    private String name;

    @OneToMany(mappedBy = "carMark", cascade = CascadeType.MERGE)
    private Set<Car> cars = new HashSet<>();

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<CarModel> models = new HashSet<>();

    public CarMark(String name) {
        this.name = name;
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
