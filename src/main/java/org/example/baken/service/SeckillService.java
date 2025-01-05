package org.example.baken.service;

import java.math.BigDecimal;

public interface SeckillService {
    boolean participateSeckill(Long userId, Long seckillActivityId, BigDecimal totalPrice);
}