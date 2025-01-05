package org.example.baken.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class PageResult<T> {
    private int pageNum;    // 当前页码
    private int pageSize;   // 每页大小
    private long total;     // 总记录数
    private List<T> data;   // 当前页数据

    // 无参构造方法
    public PageResult() {
    }

    // 全参构造方法
    public PageResult(int pageNum, int pageSize, long total, List<T> data) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = total;
        this.data = data;
    }

    // getter 和 setter 方法
    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    // 重写 toString 方法，便于打印调试信息
    @Override
    public String toString() {
        return "PageResult{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", total=" + total +
                ", data=" + data +
                '}';
    }
}