package com.example.OnThiBangLaiXe.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DanhSach {
    private static List<LoaiBienBao> dsLoaiBienBao = new ArrayList<>();
    private static List<BienBao> dsBienBao = new ArrayList<>();
    private static List<CauHoi> dsCauHoi = new ArrayList<>();
    private static List<DeThi> dsDeThi = new ArrayList<>();
    private static List<CauTraLoi> dsCauTraLoi = new ArrayList<>();
    private static List<String> dsBang=new ArrayList<>();
    private static int LoaiBang;

    public static int getLoaiBang() {
        return LoaiBang;
    }

    public static void setLoaiBang(int loaiBang) {
        LoaiBang = loaiBang;
    }

    public static List<String> getDsBang() {
        String[] item={"A1","B1"};
        dsBang.clear();
        dsBang.addAll(Arrays.asList(item));
        return dsBang;
    }

    public static void setDsBang(List<String> dsBang) {
        DanhSach.dsBang = dsBang;
    }

    public static List<DeThi> getDsDeThi() {
        return dsDeThi;
    }

    public static void setDsDeThi(List<DeThi> dsDeThi) {
        DanhSach.dsDeThi = dsDeThi;
    }

    public static List<CauTraLoi> getDsCauTraLoi() {
        return dsCauTraLoi;
    }

    public static void setDsCauTraLoi(List<CauTraLoi> dsCauTraLoi) {
        DanhSach.dsCauTraLoi = dsCauTraLoi;
    }
    public static List<LoaiBienBao> getDsLoaiBienBao() {
        return dsLoaiBienBao;
    }

    public static void setDsLoaiBienBao(List<LoaiBienBao> dsLoaiBienBao) {
        DanhSach.dsLoaiBienBao = dsLoaiBienBao;
    }

    public static List<BienBao> getDsBienBao() {
        return dsBienBao;
    }

    public static void setDsBienBao(List<BienBao> dsBienBao) {
        DanhSach.dsBienBao = dsBienBao;
    }

    public static List<CauHoi> getDsCauHoi() {
        return dsCauHoi;
    }

    public static void setDsCauHoi(List<CauHoi> dsCauHoi) {
        DanhSach.dsCauHoi = dsCauHoi;
    }

}
