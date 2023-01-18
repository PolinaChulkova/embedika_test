package com.example.embedika_test.controller;

import com.example.embedika_test.service.DbStatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/db/statistics")
@RequiredArgsConstructor
public class DBController {

    private final DbStatisticsService dbStatisticsService;

    @GetMapping
    public ResponseEntity<?> getDbStatistics() {
        return ResponseEntity.ok().body(dbStatisticsService.getDbStatistics());
    }
}
