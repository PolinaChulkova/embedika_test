package com.example.embedika_test.controller;

import com.example.embedika_test.service.DbStatisticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Api(description = "API для получения статистики БД")
@RestController
@RequestMapping("/db/statistics")
@RequiredArgsConstructor
public class DbStatisticsController {

    private final DbStatisticsService dbStatisticsService;

    /**
     * Получение статистики БД с количеством добавленныз записей за период
     * @param data1 - с какого числа (default: data2 - 1 год)
     * @param data2 - по какое число (default настоящая дата)
     * @return JSON c объектом DbStatistics, в котором содержится информация об общем количестве записей в базе
     * и о количестве записей, добавленных за указанный период, дата первой и последней записи
     */
    @ApiOperation("Получение статистики БД")
    @GetMapping
    public ResponseEntity<?> getDbStatistics(@RequestParam(value = "data1", required = false) LocalDateTime data1,
                                             @RequestParam(value = "data2", required = false) LocalDateTime data2) {
        return ResponseEntity.ok().body(dbStatisticsService.getDbStatistics(data1, data2));
    }


}
