package com.example.mapper;

import com.example.entity.VehicleTest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VehicleTestMapper {
    // 分页查询
    List<VehicleTest> selectByCondition(@Param("clmc") String clmc, 
                                       @Param("cph") String cph, 
                                       @Param("offset") int offset, 
                                       @Param("limit") int limit);
    
    int countByCondition(@Param("clmc") String clmc, @Param("cph") String cph);
    
    // 批量操作
    int insertBatch(@Param("vehicles") List<VehicleTest> vehicles);
    int updateBatch(@Param("vehicles") List<VehicleTest> vehicles);
    int deleteBatch(@Param("ids") List<Integer> ids);
    
    // 单个操作
    int insert(VehicleTest vehicle);
    int update(VehicleTest vehicle);
    int deleteById(Integer id);
    VehicleTest selectById(Integer id);
}