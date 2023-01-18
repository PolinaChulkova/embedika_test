package com.example.embedika_test.repository;

import com.example.embedika_test.dao.model.Car;
import com.example.embedika_test.dao.model.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    Page<Car> findAll(Pageable pageable);

    @Override
    <S extends Car> S save(S entity);

    @Override
    Optional<Car> findById(Long aLong);

    Optional<Car> findByCarNumberAndRegion(String carNumber, Region region);

    @Override
    boolean existsById(Long aLong);

    @Override
    void deleteById(Long aLong);
}
