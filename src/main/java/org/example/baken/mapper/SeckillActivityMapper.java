package org.example.baken.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.baken.entity.SeckillActivity;

import java.util.List;

@Mapper
public interface SeckillActivityMapper {
    SeckillActivity selectSeckillActivityById(Long id);
    List<SeckillActivity> selectAllSeckillActivities();
    List<SeckillActivity> selectSeckillActivitiesByPage(@Param("offset") int offset, @Param("pageSize") int pageSize);
    long countSeckillActivities();
    List<SeckillActivity> searchSeckillActivities(@Param("name") String name);
    void insertSeckillActivity(SeckillActivity seckillActivity);
    void updateSeckillActivity(SeckillActivity seckillActivity);
    void deleteSeckillActivityById(Long id);
    // 分页查询当前正在进行的秒杀活动
    List<SeckillActivity> selectCurrentSeckillActivitiesByPage(@Param("offset") int offset, @Param("pageSize") int pageSize);
    // 计算当前正在进行的秒杀活动总数
    long countCurrentSeckillActivities();
}
