package com.example.OnThiBangLaiXe.Model;

public class LoaiBang {
    int MaLoaiBang;
    String TenLoaiBang;

    public String getTenLoaiBang() {
        return TenLoaiBang;
    }

    public void setTenLoaiBang(String tenLoaiBang) {
        this.TenLoaiBang = tenLoaiBang;
    }

    public int getMaLoaiBang() {
        return MaLoaiBang;
    }

    public void setMaLoaiBang(int maLoaiBang) {
        this.MaLoaiBang = maLoaiBang;
    }

    public LoaiBang() {
    }

    public LoaiBang(String ten, int ma) {
        this.TenLoaiBang = ten;
        this.MaLoaiBang = ma;
    }
}
