package com.example.embedika_test.repository;

import com.example.embedika_test.dao.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @Override
    List<Car> findAll();

    @Override
    <S extends Car> S save(S entity);

    @Override
    Optional<Car> findById(Long aLong);

    @Override
    boolean existsById(Long aLong);

    @Override
    void deleteById(Long aLong);
}
