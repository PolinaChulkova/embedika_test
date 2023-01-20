package com.example.embedika_test.repository;

import com.example.embedika_test.dao.model.Region;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {

    @Cacheable(value = "regions", key = "#regionNumber")
    Optional<Region> findByRegionNumber(String regionNumber);

    @Override
    @CachePut(value = "regions")
    <S extends Region> S save(S region);

    @Override
    @Cacheable(value = "regions")
    Optional<Region> findById(Integer regionId);

    @Override
    @Cacheable(value = "regions")
    boolean existsById(Integer regionId);

    @Override
    @CacheEvict(value = "regions")
    void deleteById(Integer regionId);
}
