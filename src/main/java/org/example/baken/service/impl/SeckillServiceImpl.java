package org.example.baken.service.impl;

import org.example.baken.entity.SeckillActivity;
import org.example.baken.entity.SeckillOrder;
import org.example.baken.exception.SeckillException;
import org.example.baken.mapper.SeckillActivityMapper;
import org.example.baken.mapper.SeckillOrderMapper;
import org.example.baken.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;

@Service
public class SeckillServiceImpl implements SeckillService {

    @Autowired
    private SeckillActivityMapper seckillActivityMapper;

    @Autowired
    private SeckillOrderMapper seckillOrderMapper;

    @Override
    @Transactional
    public boolean participateSeckill(Long userId, Long seckillActivityId, BigDecimal totalPrice) {
        // 1. 检查秒杀活动是否存在
        SeckillActivity activity = seckillActivityMapper.selectSeckillActivityById(seckillActivityId);
        if (activity == null) {
            throw new SeckillException("秒杀活动不存在");
        }

        // 2. 检查活动是否正在进行
        Date now = new Date();
        if (now.before(activity.getStartTime()) || now.after(activity.getEndTime())) {
            throw new SeckillException("秒杀活动未开始或已结束");
        }

        // 3. 检查库存是否充足
        if (activity.getStock() <= 0) {
            throw new SeckillException("库存不足");
        }

        // 4. 检查用户是否已经参与过该活动
        SeckillOrder order = seckillOrderMapper.selectSeckillOrderByUserAndActivity(userId, seckillActivityId);
        if (order != null) {
            throw new SeckillException("您已经参与过该活动");
        }

        // 5. 扣减库存
        activity.setStock(activity.getStock() - 1);
        seckillActivityMapper.updateSeckillActivity(activity);


        // 6. 生成订单
        SeckillOrder newOrder = new SeckillOrder();
        newOrder.setUserId(userId);
        newOrder.setSeckillActivityId(seckillActivityId);
        newOrder.setCreateTime(now);
        newOrder.setTotalPrice(totalPrice);
        newOrder.setStatus(0);
        seckillOrderMapper.insertSeckillOrder(newOrder);

        return true;
    }
}
