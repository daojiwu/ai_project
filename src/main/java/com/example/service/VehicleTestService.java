package com.example.service;

import com.example.entity.VehicleTest;

import java.util.List;

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
}