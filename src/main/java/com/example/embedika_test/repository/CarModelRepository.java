package com.example.embedika_test.repository;

import com.example.embedika_test.dao.model.CarModel;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface CarModelRepository extends PagingAndSortingRepository<CarModel, Integer> {

    @Cacheable(value = "models", key = "#name")
    Optional<CarModel> findByName(String name);

    @Transactional
    @Query(value = "select cm from CarModel cm where cm.carMark.markId = ?1" +
            " order by cm.name asc")
    List<CarModel> findAllByCarMarkId(Integer carMarkId);

    @Cacheable(value = "models", key = "#name")
    boolean existsByName(String name);

    @Override
    @CachePut(value = "models")
    <S extends CarModel> S save(S model);

    @Override
    @Cacheable(value = "models")
    Optional<CarModel> findById(Integer modelId);

    @Override
    @Cacheable(value = "models")
    boolean existsById(Integer modelId);

    @Override
    @CacheEvict(value = "models")
    void deleteById(Integer modelId);
}
