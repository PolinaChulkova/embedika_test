package com.example.embedika_test.dao.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "regions")
@NoArgsConstructor
@Getter
@Setter
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_id")
    private Integer regionId;
    @Column(name = "region_number", unique = true)
    private String regionNumber;

    public Region(String regionNumber) {
        this.regionNumber = regionNumber;
    }

    @JsonBackReference
    @OneToMany(mappedBy = "carId", cascade = CascadeType.MERGE)
    private Set<Car> cars;

}
