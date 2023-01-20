package com.example.embedika_test.repository;

import com.example.embedika_test.dao.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {

    Optional<Region> findByRegionNumber(String regionNumber);
}
