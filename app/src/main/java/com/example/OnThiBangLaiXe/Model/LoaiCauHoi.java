package com.example.OnThiBangLaiXe.Model;

public class LoaiCauHoi {
    private String hinh;
    private int MaLoaiCH;
    private String TenLoaiCauHoi;


    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public int getMaLoaiCH() {
        return MaLoaiCH;
    }

    public void setMaLoaiCH(int maLoaiCH) {
        MaLoaiCH = maLoaiCH;
    }

    public String getTenLoaiCauHoi() {
        return TenLoaiCauHoi;
    }

    public void setTenLoaiCauHoi(String tenLoaiCauHoi) {
        this.TenLoaiCauHoi = tenLoaiCauHoi;
    }

    public LoaiCauHoi() {
    }

    public LoaiCauHoi(int maLoaiCH, String tenLoaiCauHoi) {
        MaLoaiCH = maLoaiCH;
        TenLoaiCauHoi = tenLoaiCauHoi;
    }

    public LoaiCauHoi(int maLoaiCH, String hinh, String TenLoaiCauHoi) {
        this.MaLoaiCH = maLoaiCH;
        this.hinh = hinh;
        this.TenLoaiCauHoi = TenLoaiCauHoi;
    }
}
