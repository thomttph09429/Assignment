package com.example.thomttph09429.Model;

public class Lop {
    private String MaLop, TenLop;

    public Lop(String maLop, String tenLop) {
        MaLop = maLop;
        TenLop = tenLop;
    }
    public Lop(){

    }
    public String getMaLop() {
        return MaLop;
    }

    public void setMaLop(String maLop) {
        MaLop = maLop;
    }

    public String getTenLop() {
        return TenLop;
    }

    public void setTenLop(String tenLop) {
        TenLop = tenLop;
    }

    @Override
    public String toString() {
        return MaLop+" - "+TenLop;
    }
}
