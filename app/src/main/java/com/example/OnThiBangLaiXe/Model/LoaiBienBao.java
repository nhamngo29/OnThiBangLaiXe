package com.example.OnThiBangLaiXe.Model;

import java.util.ArrayList;
import java.util.List;

public class LoaiBienBao {
    private int MaLoaiBB;
    private String TenLoaiBB;

    private List<BienBao> dsBienBao;

    public int getMaLoaiBB() {
        return MaLoaiBB;
    }

    public void setMaLoaiBB(int maLoaiBB) {
        MaLoaiBB = maLoaiBB;
    }

    public String getTenLoaiBB() {
        return TenLoaiBB;
    }

    public void setTenLoaiBB(String tenLoaiBB) {
        TenLoaiBB = tenLoaiBB;
    }

    public List<BienBao> getDsBienBao() {
        return dsBienBao;
    }

    public void setDsBienBao(List<BienBao> dsBienBao) {
        this.dsBienBao = dsBienBao;
    }

    public LoaiBienBao() {this.dsBienBao = new ArrayList<>();}

    public LoaiBienBao(int maLoaiBB, String tenLoaiBB) {
        this.MaLoaiBB = maLoaiBB;
        this.TenLoaiBB = tenLoaiBB;
        this.dsBienBao = new ArrayList<>();
    }
}
