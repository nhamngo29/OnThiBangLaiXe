package com.example.OnThiBangLaiXe.Model;

import java.util.ArrayList;
import java.util.List;

public class DanhSach {
    private static List<LoaiBienBao> dsLoaiBienBao = new ArrayList<>();

    public static List<LoaiBienBao> getDsLoaiBienBao() {
        return dsLoaiBienBao;
    }

    public static void setDsLoaiBienBao(List<LoaiBienBao> dsLoaiBienBao) {
        DanhSach.dsLoaiBienBao = dsLoaiBienBao;
    }
}
