package com.example.OnThiBangLaiXe.Model;

public class CauHoi {
    int MaCH,MaLoaiCH,MaLoaiBang;
    String NoiDung,HinhAnh,DapAnA,DapAnB,DapAnC,DapAnD,DapAnDung,GiaiThich;
    Boolean Luu,HaySai;

    public int getMaCH() {
        return MaCH;
    }

    public void setMaCH(int maCH) {
        MaCH = maCH;
    }

    public int getMaLoaiCH() {
        return MaLoaiCH;
    }

    public void setMaLoaiCH(int maLoaiCH) {
        MaLoaiCH = maLoaiCH;
    }

    public int getMaLoaiBang() {
        return MaLoaiBang;
    }

    public void setMaLoaiBang(int maLoaiBang) {
        MaLoaiBang = maLoaiBang;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String noiDung) {
        NoiDung = noiDung;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public String getDapAnA() {
        return DapAnA;
    }

    public void setDapAnA(String dapAnA) {
        DapAnA = dapAnA;
    }
    public String getDapAnB() {
        return DapAnB;
    }

    public void setDapAnB(String dapAnB) {
        DapAnB = dapAnB;
    }

    public String getDapAnC() {
        return DapAnC;
    }

    public void setDapAnC(String dapAnC) {
        DapAnC = dapAnC;
    }

    public String getDapAnD() {
        return DapAnD;
    }

    public void setDapAnD(String dapAnD) {
        DapAnD = dapAnD;
    }

    public String getDapAnDung() {
        return DapAnDung;
    }

    public void setDapAnDung(String dapAnDung) {
        DapAnDung = dapAnDung;
    }

    public String getGiaiThich() {
        return GiaiThich;
    }

    public void setGiaiThich(String giaiThich) {
        GiaiThich = giaiThich;
    }

    public Boolean getLuu() {
        return Luu;
    }

    public void setLuu(Boolean luu) {
        Luu = luu;
    }

    public Boolean getHaySai() {
        return HaySai;
    }

    public void setHaySai(Boolean haySai) {
        HaySai = haySai;
    }

    public CauHoi(int maCH, int maLoaiCH, int maLoaiBang, String noiDung, String hinhAnh, String dapAnA, String dapAnB, String dapAnC, String dapAnD, String dapAnDung, String giaiThich, Boolean haySai) {
        MaCH = maCH;
        MaLoaiCH = maLoaiCH;
        MaLoaiBang = maLoaiBang;
        NoiDung = noiDung;
        HinhAnh = hinhAnh;
        DapAnA = dapAnA;
        DapAnB = dapAnB;
        DapAnC = dapAnC;
        DapAnD = dapAnD;
        DapAnDung = dapAnDung;
        GiaiThich = giaiThich;
        HaySai = haySai;
    }

    public CauHoi() {
    }

    public CauHoi(int maCH, int maLoaiCH, int maLoaiBang, String noiDung, String hinhAnh, String dapAnA, String dapAnB, String dapAnC, String dapAnD, String dapAnDung, String giaiThich, Boolean luu, Boolean haySai) {
        MaCH = maCH;
        MaLoaiCH = maLoaiCH;
        MaLoaiBang = maLoaiBang;
        NoiDung = noiDung;
        HinhAnh = hinhAnh;
        DapAnA = dapAnA;
        DapAnB = dapAnB;
        DapAnC = dapAnC;
        DapAnD = dapAnD;
        DapAnDung = dapAnDung;
        GiaiThich = giaiThich;
        Luu = luu;
        HaySai = haySai;
    }
}
