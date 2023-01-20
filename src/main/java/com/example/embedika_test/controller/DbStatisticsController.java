package com.example.embedika_test.controller;

import com.example.embedika_test.service.DbStatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/db/statistics")
@RequiredArgsConstructor
public class DbStatisticsController {

    private final DbStatisticsService dbStatisticsService;


    @GetMapping
    public ResponseEntity<?> getDbStatistics(@RequestParam(value = "data1", required = false) LocalDateTime data1,
                                             @RequestParam(value = "data2", required = false) LocalDateTime data2) {
        return ResponseEntity.ok().body(dbStatisticsService.getDbStatistics(data1, data2));
    }


}
