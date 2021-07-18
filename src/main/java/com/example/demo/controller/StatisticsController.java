package com.example.demo.controller;

import com.example.demo.dto.HttpResponse;
import com.example.demo.dto.StatisticDTO;
import com.example.demo.service.StatisticService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/stats")
@RestController
@CrossOrigin
public class StatisticsController {

    private final StatisticService statisticService;

    public StatisticsController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @GetMapping()
    public ResponseEntity<HttpResponse<StatisticDTO>> getListedPatients() {
        StatisticDTO statisticDTO = statisticService.getDatabaseStatistic();
        return ResponseEntity.ok(new HttpResponse<>(HttpResponse.HttpResponseMessage.SUCCESS.getMessage(), statisticDTO));
    }

}
