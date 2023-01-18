package com.example.embedika_test.repository;

import com.example.embedika_test.dao.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface DbStatisticsRepository extends JpaRepository<Car, Long> {

    @Query(value = "SELECT COUNT(c.carId) FROM Car c")
    Long getCountOfRecords();

    @Query(value = "SELECT MIN(c.dateAdded) FROM Car c")
    LocalDateTime getDataFirstRecord();

    @Query(value = "SELECT MAX(c.dateAdded) FROM Car c")
    LocalDateTime getDataLastRecord();
}
