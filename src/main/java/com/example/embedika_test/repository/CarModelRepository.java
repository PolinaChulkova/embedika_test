package com.example.embedika_test.repository;

import com.example.embedika_test.dao.model.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarModelRepository extends JpaRepository<CarModel, Long> {
    @Override
    List<CarModel> findAll();

    Optional<CarModel> findByName(String name);
}
