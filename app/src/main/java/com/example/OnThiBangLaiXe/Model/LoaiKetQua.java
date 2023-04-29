package com.example.OnThiBangLaiXe.Model;

public class LoaiKetQua {
    String ten;
    String img;

    public LoaiKetQua() {
    }

    public LoaiKetQua(String ten, String img) {
        this.ten = ten;
        this.img = img;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
