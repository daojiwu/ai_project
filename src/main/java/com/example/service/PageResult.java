package com.example.service;

import java.util.List;

public class PageResult<T> {
    private List<T> data;
    private long total;
    private int page;
    private int size;
    private int totalPages;

    public PageResult() {
    }

    public PageResult(List<T> data, long total, int page, int size) {
        this.data = data;
        this.total = total;
        this.page = page;
        this.size = size;
        this.totalPages = (int) Math.ceil((double) total / size);
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
    
    // 添加获取数据的方法，确保可以正确序列化
    public List<T> getRows() {
        return data;
    }
    
    // 添加额外的getter方法，确保兼容性
    public long getTotalCount() {
        return total;
    }
    
    public int getCurrentPage() {
        return page;
    }
    
    public int getPageSize() {
        return size;
    }
}