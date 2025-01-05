package org.example.baken.entity;

import java.math.BigDecimal;

public class SeckillStats {
    private Long seckillActivityId;
    private String seckillActivityName;
    private Integer participantCount; // 参与人数
    private Integer orderCount;       // 成交订单数
    private BigDecimal totalSales;    // 总销售额

    // Getters
    public Long getSeckillActivityId() {
        return seckillActivityId;
    }

    public String getSeckillActivityName() {
        return seckillActivityName;
    }

    public Integer getParticipantCount() {
        return participantCount;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public BigDecimal getTotalSales() {
        return totalSales;
    }

    // Setters
    public void setSeckillActivityId(Long seckillActivityId) {
        this.seckillActivityId = seckillActivityId;
    }

    public void setSeckillActivityName(String seckillActivityName) {
        this.seckillActivityName = seckillActivityName;
    }

    public void setParticipantCount(Integer participantCount) {
        this.participantCount = participantCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public void setTotalSales(BigDecimal totalSales) {
        this.totalSales = totalSales;
    }
}
