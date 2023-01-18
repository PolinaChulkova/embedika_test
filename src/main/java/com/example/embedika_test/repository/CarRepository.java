package com.example.embedika_test.repository;

import com.example.embedika_test.dao.model.Car;
import com.example.embedika_test.dao.model.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepository extends PagingAndSortingRepository<Car, Long> {

    @Query(value = "select c from Car c where " +
            "lower(c.carModel) like %lower(trim(:text))% or " +
            "lower(c.carNumber) like %lower(trim(:text))% or " +
            "lower(c.color) like %lower(trim(:text))%")
    Page<Car> searchByText(@Param("text") String text, Pageable pageable);

    @Query(value = "select c from Car c where " +
            "(c.carNumber is null or c.carNumber = upper(trim(:carNumber))) and " +
            "(c.region.regionNumber is null or c.region.regionNumber = trim(:regionNumber))")
    Page<Car> searchByCarNumberAndRegionNumber(@Param("carNumber") String carNumber,
                                               @Param("regionNumber") String regionNumber,
                                               Pageable pageable);

    @Query(value = "select case when count(c) > 0 then true else false end from Car c where " +
            "c.carNumber = upper(trim(?1)) and c.region.regionNumber = trim(?2)")
    boolean existsByCarNumberAndRegionNumber(String carNumber, String regionNumber);

    Page<Car> findAll(Pageable pageable);

    @Override
    Optional<Car> findById(Long aLong);

    @Override
    boolean existsById(Long aLong);

    @Override
    void deleteById(Long aLong);

    @Override
    <S extends Car> S save(S entity);
}
