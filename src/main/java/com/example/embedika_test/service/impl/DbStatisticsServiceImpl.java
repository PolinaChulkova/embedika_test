package com.example.embedika_test.service.impl;

import com.example.embedika_test.dao.dto.DbStatistics;
import com.example.embedika_test.repository.DbStatisticsRepository;
import com.example.embedika_test.service.DbStatisticsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class DbStatisticsServiceImpl implements DbStatisticsService {

    private  final DbStatisticsRepository dbStatisticsRepository;

    @Override
    public DbStatistics getDbStatistics(LocalDateTime data1, LocalDateTime data2) {
        log.info("Получение статистики базы данных");
        if (data2 == null) data2 = LocalDateTime.now();
        if (data1 == null) data1 = data2.minusYears(1);
        return new DbStatistics(
                dbStatisticsRepository.getCountOfRecords(),
                dbStatisticsRepository.getDataFirstRecord(),
                dbStatisticsRepository.getDataLastRecord(),
                dbStatisticsRepository.getCountOfRecordsPeriod(data1, data2)
        );
    }
}
