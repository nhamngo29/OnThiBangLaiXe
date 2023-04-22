package com.example.OnThiBangLaiXe.Model;

public class DeThi {
    private int maDeThi;
    private String tenDeThi;

    public int getMaDeThi() {
        return maDeThi;
    }

    public void setMaDeThi(int maDeThi) {
        this.maDeThi = maDeThi;
    }

    public String getTenDeThi() {
        return tenDeThi;
    }

    public void setTenDeThi(String tenDeThi) {
        this.tenDeThi = tenDeThi;
    }

    public DeThi() {
    }

    public DeThi(int maDeThi, String tenDeThi) {
        this.maDeThi = maDeThi;
        this.tenDeThi = tenDeThi;
    }
}
