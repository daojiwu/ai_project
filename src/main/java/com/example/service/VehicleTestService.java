package com.example.service;

import com.example.entity.VehicleTest;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface VehicleTestService {
    // 分页查询
    PageResult<VehicleTest> selectByCondition(String clmc, String cph, int page, int size);
    
    // 批量操作
    boolean insertBatch(List<VehicleTest> vehicles);
    boolean updateBatch(List<VehicleTest> vehicles);
    boolean deleteBatch(List<Integer> ids);
    
    // 单个操作
    boolean insert(VehicleTest vehicle);
    boolean update(VehicleTest vehicle);
    boolean deleteById(Integer id);
    VehicleTest selectById(Integer id);
    
    // 异步批量操作
    CompletableFuture<Boolean> insertBatchAsync(List<VehicleTest> vehicles);
    CompletableFuture<Boolean> updateBatchAsync(List<VehicleTest> vehicles);
    CompletableFuture<Boolean> deleteBatchAsync(List<Integer> ids);
    
    // 异步单个操作
    CompletableFuture<Boolean> insertAsync(VehicleTest vehicle);
    CompletableFuture<Boolean> updateAsync(VehicleTest vehicle);
    CompletableFuture<Boolean> deleteByIdAsync(Integer id);
}