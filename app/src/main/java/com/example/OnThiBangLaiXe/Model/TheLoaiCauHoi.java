package com.example.OnThiBangLaiXe.Model;

public class TheLoaiCauHoi {
    private String hinh;
    private String ten;
    private int soCauHoi;
    private int soCauHoiDaTraLoi;
    private int soCauDung;

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getSoCauHoi() {
        return soCauHoi;
    }

    public void setSoCauHoi(int soCauHoi) {
        this.soCauHoi = soCauHoi;
    }

    public int getSoCauHoiDaTraLoi() {
        return soCauHoiDaTraLoi;
    }

    public void setSoCauHoiDaTraLoi(int soCauHoiDaTraLoi) {
        this.soCauHoiDaTraLoi = soCauHoiDaTraLoi;
    }

    public int getSoCauDung() {
        return soCauDung;
    }

    public void setSoCauDung(int soCauDung) {
        this.soCauDung = soCauDung;
    }

    public TheLoaiCauHoi(String hinh, String ten) {
        this.hinh = hinh;
        this.ten = ten;
    }
}
