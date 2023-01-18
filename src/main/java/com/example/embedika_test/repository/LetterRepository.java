package com.example.embedika_test.repository;

import com.example.embedika_test.dao.model.Letter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LetterRepository extends JpaRepository<Letter, Short> {

    @Override
    List<Letter> findAll();

    boolean existByLetter(Character letter);
}
