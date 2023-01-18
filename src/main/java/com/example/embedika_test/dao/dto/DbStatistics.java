package com.example.embedika_test.dao.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class DbStatistics {
    private final Long countOfRecords;
    private final LocalDateTime dataFirstRecord;
    private final LocalDateTime dataLastRecord;

}
