package org.example.baken.service;

import org.example.baken.entity.PageResult;
import org.example.baken.entity.SeckillActivity;

import java.util.List;

public interface SeckillActivityService {
    SeckillActivity getSeckillActivityById(Long id);
    List<SeckillActivity> getAllSeckillActivities();
    PageResult<SeckillActivity> getSeckillActivitiesByPage(int pageNum, int pageSize); // 分页查询

    List<SeckillActivity> searchSeckillActivities(String name); // 搜索功能
    void addSeckillActivity(SeckillActivity seckillActivity);
    void updateSeckillActivity(SeckillActivity seckillActivity);
    void deleteSeckillActivityById(Long id);
    // 分页查询当前正在进行的秒杀活动
    PageResult<SeckillActivity> getCurrentSeckillActivitiesByPage(int pageNum, int pageSize);
}