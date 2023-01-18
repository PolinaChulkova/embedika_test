package com.example.embedika_test.service.impl;

import com.example.embedika_test.dao.dto.DbStatistics;
import com.example.embedika_test.repository.DbStatisticsRepository;
import com.example.embedika_test.service.DbStatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DbStatisticsServiceImpl implements DbStatisticsService {

    private  final DbStatisticsRepository dbStatisticsRepository;

    @Override
    public DbStatistics getDbStatistics() {
        return new DbStatistics(
                dbStatisticsRepository.getCountOfRecords(),
                dbStatisticsRepository.getDataFirstRecord(),
                dbStatisticsRepository.getDataLastRecord()
        );
    }
}
