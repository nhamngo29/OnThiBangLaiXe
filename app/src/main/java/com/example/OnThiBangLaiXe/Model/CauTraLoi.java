package com.example.OnThiBangLaiXe.Model;

public class CauTraLoi {
    private int MaDeThi;
    private int MaCH;
    private String DapAnChon;

    public CauTraLoi(int maDeThi, int maCH) {
        MaDeThi = maDeThi;
        MaCH = maCH;
    }

    public int getMaDeThi() {
        return MaDeThi;
    }

    public void setMaDeThi(int maDeThi) {
        MaDeThi = maDeThi;
    }

    public int getMaCH() {
        return MaCH;
    }

    public void setMaCH(int maCH) {
        MaCH = maCH;
    }

    public String getDapAnChon() {
        return DapAnChon;
    }

    public void setDapAnChon(String dapAnChon) {
        DapAnChon = dapAnChon;
    }

    public CauTraLoi() {
    }

    public CauTraLoi(int maDeThi, int maCH, String dapAnChon) {
        MaDeThi = maDeThi;
        MaCH = maCH;
        DapAnChon = dapAnChon;
    }
}
