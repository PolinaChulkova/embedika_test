package com.example.embedika_test.repository;

import com.example.embedika_test.dao.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region, Short> {

    @Override
    List<Region> findAll();

    Optional<Region> findByRegionNumber(String regionNumber);
}
