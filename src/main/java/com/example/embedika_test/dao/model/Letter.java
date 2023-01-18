package com.example.embedika_test.dao.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "letters")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Letter {
    @Id
    @GeneratedValue
    @Column(name = "letter_id")
    private Short letterId;
    @Column(name = "letter")
    private Character letter;
}
