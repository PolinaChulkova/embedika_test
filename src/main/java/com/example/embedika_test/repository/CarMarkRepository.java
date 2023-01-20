package com.example.embedika_test.repository;

import com.example.embedika_test.dao.model.CarMark;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarMarkRepository extends JpaRepository<CarMark, Integer> {

    @Cacheable(value = "marks", key = "#name")
    Optional<CarMark> findByName(String name);

    @Override
    @CachePut(value = "marks")
    <S extends CarMark> S save(S mark);

    @Override
    @Cacheable(value = "marks")
    Optional<CarMark> findById(Integer carId);

    @Override
    @CacheEvict(value = "marks")
    void deleteById(Integer carId);
}
