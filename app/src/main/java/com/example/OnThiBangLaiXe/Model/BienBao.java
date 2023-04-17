package com.example.OnThiBangLaiXe.Model;

public class BienBao{
    private String MaBB;
    private String TieuDe;
    private String NoiDung;
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

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String noiDung) {
        NoiDung = noiDung;
    }

    public int getMaLoaiBB() {
        return MaLoaiBB;
    }

    public void setMaLoaiBB(int maLoaiBB) {
        MaLoaiBB = maLoaiBB;
    }

    public BienBao() {
    }
    public BienBao(String HinhAnh, String MaBB, String TieuDe, String noiDung, int maLoaiBB) {
        this.HinhAnh = HinhAnh;
        this.MaBB = MaBB;
        this.TieuDe = TieuDe;
        this.NoiDung = noiDung;
        this.MaLoaiBB = maLoaiBB;
    }
}
