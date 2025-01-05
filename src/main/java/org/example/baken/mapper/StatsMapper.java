package org.example.baken.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.baken.entity.SeckillStats;

import java.sql.Date;
import java.util.List;

public interface StatsMapper {
    List<SeckillStats> getSeckillStats();
    List<SeckillStats> getSeckillStatsByDateRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}