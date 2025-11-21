package com.example.service.impl;

import com.example.entity.VehicleTest;
import com.example.mapper.VehicleTestMapper;
import com.example.service.PageResult;
import com.example.service.VehicleTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class VehicleTestServiceImpl implements VehicleTestService {

    @Autowired
    private VehicleTestMapper vehicleTestMapper;

    @Override
    public PageResult<VehicleTest> selectByCondition(String clmc, String cph, int page, int size) {
        int offset = (page - 1) * size;
        List<VehicleTest> data = vehicleTestMapper.selectByCondition(clmc, cph, offset, size);
        int total = vehicleTestMapper.countByCondition(clmc, cph);
        return new PageResult<>(data, total, page, size);
    }

    @Override
    @Async("taskExecutor")
    public CompletableFuture<Boolean> insertBatchAsync(List<VehicleTest> vehicles) {
        try {
            boolean result = vehicleTestMapper.insertBatch(vehicles) > 0;
            return CompletableFuture.completedFuture(result);
        } catch (Exception e) {
            e.printStackTrace();
            return CompletableFuture.completedFuture(false);
        }
    }

    @Override
    @Async("taskExecutor")
    public CompletableFuture<Boolean> updateBatchAsync(List<VehicleTest> vehicles) {
        try {
            boolean result = vehicleTestMapper.updateBatch(vehicles) > 0;
            return CompletableFuture.completedFuture(result);
        } catch (Exception e) {
            e.printStackTrace();
            return CompletableFuture.completedFuture(false);
        }
    }

    @Override
    @Async("taskExecutor")
    public CompletableFuture<Boolean> deleteBatchAsync(List<Integer> ids) {
        try {
            boolean result = vehicleTestMapper.deleteBatch(ids) > 0;
            return CompletableFuture.completedFuture(result);
        } catch (Exception e) {
            e.printStackTrace();
            return CompletableFuture.completedFuture(false);
        }
    }

    @Override
    @Async("taskExecutor")
    public CompletableFuture<Boolean> insertAsync(VehicleTest vehicle) {
        try {
            boolean result = vehicleTestMapper.insert(vehicle) > 0;
            return CompletableFuture.completedFuture(result);
        } catch (Exception e) {
            e.printStackTrace();
            return CompletableFuture.completedFuture(false);
        }
    }

    @Override
    @Async("taskExecutor")
    public CompletableFuture<Boolean> updateAsync(VehicleTest vehicle) {
        try {
            boolean result = vehicleTestMapper.update(vehicle) > 0;
            return CompletableFuture.completedFuture(result);
        } catch (Exception e) {
            e.printStackTrace();
            return CompletableFuture.completedFuture(false);
        }
    }

    @Override
    @Async("taskExecutor")
    public CompletableFuture<Boolean> deleteByIdAsync(Integer id) {
        try {
            boolean result = vehicleTestMapper.deleteById(id) > 0;
            return CompletableFuture.completedFuture(result);
        } catch (Exception e) {
            e.printStackTrace();
            return CompletableFuture.completedFuture(false);
        }
    }

    // 保持原有的同步方法
    @Override
    public boolean insertBatch(List<VehicleTest> vehicles) {
        try {
            return vehicleTestMapper.insertBatch(vehicles) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateBatch(List<VehicleTest> vehicles) {
        try {
            return vehicleTestMapper.updateBatch(vehicles) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteBatch(List<Integer> ids) {
        try {
            return vehicleTestMapper.deleteBatch(ids) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean insert(VehicleTest vehicle) {
        try {
            return vehicleTestMapper.insert(vehicle) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(VehicleTest vehicle) {
        try {
            return vehicleTestMapper.update(vehicle) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteById(Integer id) {
        try {
            return vehicleTestMapper.deleteById(id) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public VehicleTest selectById(Integer id) {
        return vehicleTestMapper.selectById(id);
    }
}