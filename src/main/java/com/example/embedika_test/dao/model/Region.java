package com.example.embedika_test.dao.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "regions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Region {
    @Id
    @GeneratedValue
    @Column(name = "region_id")
    private Short regionId;
    @Column(name = "region_number")
    private String regionNumber;

    @OneToMany(cascade = CascadeType.MERGE)
    private Set<Car> cars;

}
