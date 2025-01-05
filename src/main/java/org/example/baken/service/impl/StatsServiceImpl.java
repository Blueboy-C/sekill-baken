package org.example.baken.service.impl;

import com.alibaba.excel.EasyExcel;
import org.example.baken.entity.SeckillStats;
import org.example.baken.mapper.StatsMapper;
import org.example.baken.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@Service
public class StatsServiceImpl implements StatsService {
    @Autowired
    private StatsMapper statsMapper;

    @Override
    public List<SeckillStats> getSeckillStats() {
        return statsMapper.getSeckillStats();
    }

    @Override
    public List<SeckillStats> getSeckillStatsByDateRange(Date startDate, Date endDate) {
        return statsMapper.getSeckillStatsByDateRange(startDate, endDate);
    }

    @Override
    public void exportSeckillStats(HttpServletResponse response) throws IOException {
        List<SeckillStats> stats = statsMapper.getSeckillStats();
        if (stats.isEmpty()) {
            throw new RuntimeException("没有可导出的数据");
        }

        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename=seckill_stats.xlsx");

        EasyExcel.write(response.getOutputStream(), SeckillStats.class)
                .sheet("秒杀活动统计")
                .doWrite(stats);
    }
}
