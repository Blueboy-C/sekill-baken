package org.example.baken.service;

import org.example.baken.entity.SeckillStats;

import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.List;
import java.io.IOException;

public interface StatsService {
    List<SeckillStats> getSeckillStats();
    List<SeckillStats> getSeckillStatsByDateRange(Date startDate, Date endDate); // 按时间范围查询
    void exportSeckillStats(HttpServletResponse response) throws IOException; // 导出为 Excel
}
