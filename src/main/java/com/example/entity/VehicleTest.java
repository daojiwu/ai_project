package com.example.entity;
//测试测试车辆
public class VehicleTest {
    private Integer id;
    private Double fy;
    private String clmc;
    private String cph;

    // Constructors
    public VehicleTest() {}

    public VehicleTest(Integer id, Double fy, String clmc, String cph) {
        this.id = id;
        this.fy = fy;
        this.clmc = clmc;
        this.cph = cph;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getFy() {
        return fy;
    }

    public void setFy(Double fy) {
        this.fy = fy;
    }

    public String getClmc() {
        return clmc;
    }

    public void setClmc(String clmc) {
        this.clmc = clmc;
    }

    public String getCph() {
        return cph;
    }

    public void setCph(String cph) {
        this.cph = cph;
    }

    @Override
    public String toString() {
        return "VehicleTest{" +
                "id=" + id +
                ", fy=" + fy +
                ", clmc='" + clmc + '\'' +
                ", cph='" + cph + '\'' +
                '}';
    }
}