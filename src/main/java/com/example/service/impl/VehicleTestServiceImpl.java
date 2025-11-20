package com.example.service.impl;

import com.example.entity.VehicleTest;
import com.example.mapper.VehicleTestMapper;
import com.example.service.PageResult;
import com.example.service.VehicleTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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