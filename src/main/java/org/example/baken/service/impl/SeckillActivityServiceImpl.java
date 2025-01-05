package org.example.baken.service.impl;

import org.example.baken.entity.PageResult;
import org.example.baken.entity.SeckillActivity;
import org.example.baken.mapper.SeckillActivityMapper;
import org.example.baken.service.SeckillActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeckillActivityServiceImpl implements SeckillActivityService {
    @Autowired
    private SeckillActivityMapper seckillActivityMapper;

    @Override
    public SeckillActivity getSeckillActivityById(Long id) {
        return seckillActivityMapper.selectSeckillActivityById(id);
    }

    @Override
    public List<SeckillActivity> getAllSeckillActivities() {
        return seckillActivityMapper.selectAllSeckillActivities();
    }

    @Override
    public PageResult<SeckillActivity> getSeckillActivitiesByPage(int pageNum, int pageSize) {
        int offset = (pageNum - 1) * pageSize;
        List<SeckillActivity> activities = seckillActivityMapper.selectSeckillActivitiesByPage(offset, pageSize);
        long total = seckillActivityMapper.countSeckillActivities();
        return new PageResult<>(pageNum, pageSize, total, activities);
    }

    @Override
    public List<SeckillActivity> searchSeckillActivities(String name) {
        return seckillActivityMapper.searchSeckillActivities(name);
    }

    @Override
    public void addSeckillActivity(SeckillActivity seckillActivity) {
        seckillActivityMapper.insertSeckillActivity(seckillActivity);
    }

    @Override
    public void updateSeckillActivity(SeckillActivity seckillActivity) {
        seckillActivityMapper.updateSeckillActivity(seckillActivity);
    }

    @Override
    public void deleteSeckillActivityById(Long id) {
        seckillActivityMapper.deleteSeckillActivityById(id);
    }

    @Override
    public PageResult<SeckillActivity> getCurrentSeckillActivitiesByPage(int pageNum, int pageSize) {
        int offset = (pageNum - 1) * pageSize;
        List<SeckillActivity> activities = seckillActivityMapper.selectCurrentSeckillActivitiesByPage(offset, pageSize);
        long total = seckillActivityMapper.countCurrentSeckillActivities(); // 假设有一个方法来计算当前正在进行的秒杀活动总数
        return new PageResult<>(pageNum, pageSize, total, activities);
    }


}