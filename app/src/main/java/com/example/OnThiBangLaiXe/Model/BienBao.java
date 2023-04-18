package com.example.OnThiBangLaiXe.Model;

public class BienBao{
    private String MaBB;
    private String TieuDe;
    private String Noidung;
    private String HinhAnh;
    private int MaLoaiBB;
    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.HinhAnh = hinhAnh;
    }

    public String getMaBB() {
        return MaBB;
    }

    public void setMaBB(String maBB) {
        this.MaBB = maBB;
    }

    public String getTieuDe() {
        return TieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.TieuDe = tieuDe;
    }

    public String getNoidung() {
        return Noidung;
    }

    public void setNoidung(String noidung) {
        Noidung = noidung;
    }

    public int getMaLoaiBB() {
        return MaLoaiBB;
    }

    public void setMaLoaiBB(int maLoaiBB) {
        MaLoaiBB = maLoaiBB;
    }

    public BienBao() {
    }

    public BienBao( String MaBB, int maLoaiBB,String TieuDe, String noidung, String HinhAnh) {
        this.HinhAnh = HinhAnh;
        this.MaBB = MaBB;
        this.TieuDe = TieuDe;
        this.Noidung = noidung;
        this.MaLoaiBB = maLoaiBB;
    }
}
