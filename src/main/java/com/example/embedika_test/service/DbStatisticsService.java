package com.example.embedika_test.service;

import com.example.embedika_test.dao.dto.DbStatistics;

import java.time.LocalDateTime;

public interface DbStatisticsService {
    DbStatistics getDbStatistics(LocalDateTime data1, LocalDateTime data2);
}
