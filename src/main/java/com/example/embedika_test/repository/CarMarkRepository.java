package com.example.embedika_test.repository;

import com.example.embedika_test.dao.model.CarMark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarMarkRepository extends JpaRepository<CarMark, Integer> {


}
