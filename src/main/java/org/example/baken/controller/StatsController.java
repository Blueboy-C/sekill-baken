package org.example.baken.controller;

import org.example.baken.entity.ApiResponse;
import org.example.baken.entity.SeckillStats;
import org.example.baken.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/stats")
public class StatsController {
    @Autowired
    private StatsService statsService;

    @GetMapping("/seckill")
    public ApiResponse<List<SeckillStats>> getSeckillStats() {
        List<SeckillStats> stats = statsService.getSeckillStats();
        return ApiResponse.success(stats);
    }

    @GetMapping("/seckill/date-range")
    public ApiResponse<List<SeckillStats>> getSeckillStatsByDateRange(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        List<SeckillStats> stats = statsService.getSeckillStatsByDateRange(startDate, endDate);
        return ApiResponse.success(stats);
    }

    @GetMapping("/seckill/export")
    public void exportSeckillStats(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename=seckill_stats.xlsx");
        statsService.exportSeckillStats(response);
    }
}
