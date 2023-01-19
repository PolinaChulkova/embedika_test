package com.example.embedika_test.repository;

import com.example.embedika_test.dao.model.CarMark;
import com.example.embedika_test.dao.model.CarModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface CarModelRepository extends PagingAndSortingRepository<CarModel, Integer> {

    Optional<CarModel> findByName(String name);

    @Transactional
    @Query(value = "select cm from CarModel cm where cm.carMark.markId = ?1" +
            " order by cm.name asc")
    List<CarModel> findAllByCarMarkId(Integer carMarkId);

    boolean existsByName(String name);

}
