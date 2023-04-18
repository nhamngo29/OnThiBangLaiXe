package com.example.OnThiBangLaiXe.Model;

public class LoaiCauHoi {
    private String hinh;
    private int MaLoaiCH;
    private String TenLoaiCauHoi;
    private int SoCau;

    private int SoCauHoiDaTraLoi;
    private int SoCauDung;

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

    public int getSoCau() {
        return SoCau;
    }

    public void setSoCau(int soCau) {
        this.SoCau = soCau;
    }

    public int getSoCauHoiDaTraLoi() {
        return SoCauHoiDaTraLoi;
    }

    public void setSoCauHoiDaTraLoi(int soCauHoiDaTraLoi) {
        this.SoCauHoiDaTraLoi = soCauHoiDaTraLoi;
    }

    public int getSoCauDung() {
        return SoCauDung;
    }

    public void setSoCauDung(int soCauDung) {
        this.SoCauDung = soCauDung;
    }

    public LoaiCauHoi() {
    }

    public LoaiCauHoi(int maLoaiCH, String hinh, String TenLoaiCauHoi) {
        this.MaLoaiCH = maLoaiCH;
        this.hinh = hinh;
        this.TenLoaiCauHoi = TenLoaiCauHoi;
        this.SoCau = 100;
        this.SoCauHoiDaTraLoi = 80;
        this.SoCauDung = 45;
    }
}
