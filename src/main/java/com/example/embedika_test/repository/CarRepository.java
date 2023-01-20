package com.example.embedika_test.repository;

import com.example.embedika_test.dao.model.Car;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface CarRepository extends PagingAndSortingRepository<Car, Long> {

    @Transactional
    @Query(value = "select c from Car c where " +
            "lower(c.carModel.name) like ('%'||lower(trim(:text))||'%') or " +
            "lower(c.carMark.name) like ('%'||lower(trim(:text))||'%') or " +
            "c.color like ('%'||lower(trim(:text))||'%') or " +
            "c.bodyType like ('%'||upper(trim(:text))||'%')")
    Page<Car> searchByText(@Param("text") String text, Pageable pageable);

    @Transactional
    @Query(value = "select c from Car c where " +
            "(:carNumber is null or c.carNumber = upper(trim(:carNumber))) and " +
            "(:regionNumber is null or c.region.regionNumber = trim(:regionNumber))")
    Page<Car> searchByCarNumberAndRegionNumber(@Param("carNumber") String carNumber,
                                               @Param("regionNumber") String regionNumber,
                                               Pageable pageable);

    @Transactional
    @Query(value = "select case when count(c) > 0 then true else false end from Car c where " +
            "c.carNumber = upper(trim(?1)) and c.region.regionNumber = trim(?2)")
    boolean existsByCarNumberAndRegionNumber(String carNumber, String regionNumber);

    Page<Car> findAll(Pageable pageable);

    @Override
    @CachePut(value = "cars")
    <S extends Car> S save(S car);

    @Override
    @Cacheable(value = "cars")
    Optional<Car> findById(Long carId);

    @Override
    @Cacheable(value = "cars")
    boolean existsById(Long carId);

    @Override
    @CacheEvict(value = "cars")
    void deleteById(Long carId);
}
