package org.example.baken.entity;

import java.math.BigDecimal;
import java.util.Date;

public class SeckillOrder {
    private Long id;
    private Long userId;
    private Long seckillActivityId;
    private BigDecimal totalPrice; // 总价格
    private Integer status; // 订单状态：0-待支付，1-已支付，2-已取消
    private Date createTime;

    // 新增关联对象
    private SeckillActivity seckillActivity; // 关联的秒杀活动信息
    private Product product; // 关联的商品信息

    // getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSeckillActivityId() {
        return seckillActivityId;
    }

    public void setSeckillActivityId(Long seckillActivityId) {
        this.seckillActivityId = seckillActivityId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public SeckillActivity getSeckillActivity() {
        return seckillActivity;
    }

    public void setSeckillActivity(SeckillActivity seckillActivity) {
        this.seckillActivity = seckillActivity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


}