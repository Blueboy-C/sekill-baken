package org.example.baken.entity;

import java.util.Date;

public class SeckillActivity {
    private Long id;
    private String name;
    private Long productId;
    private Date startTime;
    private Date endTime;
    private Integer stock;
    private Integer status; // 0:未开始, 1:进行中, 2:已结束

    private Product product; // 新增的Product属性

    // 其他getter和setter方法

    // product的getter和setter
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    // id的getter和setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // name的getter和setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // productId的getter和setter
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    // startTime的getter和setter
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    // endTime的getter和setter
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    // stock的getter和setter
    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    // status的getter和setter
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
