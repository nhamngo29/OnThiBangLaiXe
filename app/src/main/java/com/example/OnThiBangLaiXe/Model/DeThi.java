package com.example.OnThiBangLaiXe.Model;

public class DeThi {
    private int MaDeThi;
    private String TenDeThi;

    public int getMaDeThi() {
        return MaDeThi;
    }

    public void setMaDeThi(int maDeThi) {
        MaDeThi = maDeThi;
    }

    public String getTenDeThi() {
        return TenDeThi;
    }

    public void setTenDeThi(String tenDeThi) {
        TenDeThi = tenDeThi;
    }

    public DeThi() {
    }

    public DeThi(int maDeThi, String tenDeThi) {
        MaDeThi = maDeThi;
        TenDeThi = tenDeThi;
    }
}
