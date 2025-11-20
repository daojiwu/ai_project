package com.example.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.entity.VehicleTest;
import com.example.service.PageResult;
import com.example.service.VehicleTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/vehicle")
public class VehicleTestController {

    @Autowired
    private VehicleTestService vehicleTestService;

    /**
     * 跳转到车辆管理页面
     */
    @GetMapping("/list")
    public String list() {
        return "vehicle";
    }
    
    /**
     * 根路径重定向到车辆管理页面
     */
    @GetMapping("/")
    public String index() {
        return "redirect:/vehicle/list";
    }

    /**
     * 分页查询接口
     */
    @ResponseBody
    @GetMapping("/page")
    public Object page(
            @RequestParam(defaultValue = "") String clmc,
            @RequestParam(defaultValue = "") String cph,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        PageResult<VehicleTest> result = vehicleTestService.selectByCondition(clmc, cph, page, size);
        
        // 创建一个兼容的对象结构
        JSONObject response = new JSONObject();
        response.put("data", result.getData());
        response.put("total", result.getTotal());
        response.put("page", result.getPage());
        response.put("size", result.getSize());
        response.put("totalPages", result.getTotalPages());
        response.put("rows", result.getData()); // 兼容某些前端框架
        response.put("totalCount", result.getTotal()); // 兼容某些前端框架
        response.put("currentPage", result.getPage()); // 兼容某些前端框架
        
        return response;
    }

    /**
     * 测试数据接口，用于验证后端是否能正确返回数据
     */
    @ResponseBody
    @GetMapping("/testData")
    public PageResult<VehicleTest> testData() {
        return vehicleTestService.selectByCondition("", "", 1, 10);
    }

    /**
     * 添加单个车辆信息
     */
    @ResponseBody
    @PostMapping("/add")
    public JSONObject add(@RequestBody VehicleTest vehicle) {
        JSONObject result = new JSONObject();
        try {
            boolean success = vehicleTestService.insert(vehicle);
            result.put("success", success);
            result.put("message", success ? "添加成功" : "添加失败");
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "添加失败：" + e.getMessage());
        }
        return result;
    }

    /**
     * 修改车辆信息
     */
    @ResponseBody
    @PostMapping("/update")
    public JSONObject update(@RequestBody VehicleTest vehicle) {
        JSONObject result = new JSONObject();
        try {
            boolean success = vehicleTestService.update(vehicle);
            result.put("success", success);
            result.put("message", success ? "修改成功" : "修改失败");
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "修改失败：" + e.getMessage());
        }
        return result;
    }

    /**
     * 删除车辆信息
     */
    @ResponseBody
    @PostMapping("/delete/{id}")
    public JSONObject delete(@PathVariable Integer id) {
        JSONObject result = new JSONObject();
        try {
            boolean success = vehicleTestService.deleteById(id);
            result.put("success", success);
            result.put("message", success ? "删除成功" : "删除失败");
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "删除失败：" + e.getMessage());
        }
        return result;
    }

    /**
     * 批量添加车辆信息
     */
    @ResponseBody
    @PostMapping("/batchAdd")
    public JSONObject batchAdd(@RequestBody List<VehicleTest> vehicles) {
        JSONObject result = new JSONObject();
        try {
            boolean success = vehicleTestService.insertBatch(vehicles);
            result.put("success", success);
            result.put("message", success ? "批量添加成功" : "批量添加失败");
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "批量添加失败：" + e.getMessage());
        }
        return result;
    }

    /**
     * 批量修改车辆信息
     */
    @ResponseBody
    @PostMapping("/batchUpdate")
    public JSONObject batchUpdate(@RequestBody List<VehicleTest> vehicles) {
        JSONObject result = new JSONObject();
        try {
            boolean success = vehicleTestService.updateBatch(vehicles);
            result.put("success", success);
            result.put("message", success ? "批量修改成功" : "批量修改失败");
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "批量修改失败：" + e.getMessage());
        }
        return result;
    }

    /**
     * 批量删除车辆信息
     */
    @ResponseBody
    @PostMapping("/batchDelete")
    public JSONObject batchDelete(@RequestBody List<Integer> ids) {
        JSONObject result = new JSONObject();
        try {
            boolean success = vehicleTestService.deleteBatch(ids);
            result.put("success", success);
            result.put("message", success ? "批量删除成功" : "批量删除失败");
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "批量删除失败：" + e.getMessage());
        }
        return result;
    }

    /**
     * 导出数据
     */
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=vehicles.json");

        try (PrintWriter writer = response.getWriter()) {
            // 获取所有数据（实际应用中可能需要分页处理）
            PageResult<VehicleTest> pageResult = vehicleTestService.selectByCondition("", "", 1, 1000);
            writer.write(JSON.toJSONString(pageResult.getData()));
            writer.flush();
        } catch (IOException e) {
            // 记录日志或进行适当处理
            throw e;
        }
    }

    /**
     * 导入数据
     */
    @ResponseBody
    @PostMapping("/import")
    public JSONObject importData(@RequestBody String jsonData) {
        JSONObject result = new JSONObject();
        try {
            JSONArray array = JSON.parseArray(jsonData);
            List<VehicleTest> vehicles = new ArrayList<>();

            for (int i = 0; i < array.size(); i++) {
                JSONObject obj = array.getJSONObject(i);
                VehicleTest vehicle = new VehicleTest();
                vehicle.setFy(obj.getDouble("fy"));
                vehicle.setClmc(obj.getString("clmc"));
                vehicle.setCph(obj.getString("cph"));
                vehicles.add(vehicle);
            }

            boolean success = vehicleTestService.insertBatch(vehicles);
            result.put("success", success);
            result.put("message", success ? "导入成功" : "导入失败");
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "导入失败：" + e.getMessage());
        }
        return result;
    }
}