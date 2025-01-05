package org.example.baken.controller;


import org.example.baken.entity.SeckillStats;
import org.example.baken.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/stats")
public class AdminStatsController {

    @Autowired
    private StatsService statsService;

    @GetMapping("/seckill")
    public List<SeckillStats> getSeckillStats() {
        return statsService.getSeckillStats();
    }
}
