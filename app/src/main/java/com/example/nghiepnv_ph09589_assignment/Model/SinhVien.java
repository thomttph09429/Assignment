package com.example.nghiepnv_ph09589_assignment.Model;

public class SinhVien {
    private String MaLop;
    private String TenSV;
    private String Sn;

    public SinhVien(String maLop, String tenSV, String sn) {
        MaLop = maLop;
        TenSV = tenSV;
        Sn = sn;
    }
    public SinhVien(){

    }
    public String getMaLop() {
        return MaLop;
    }

    public void setMaLop(String maLop) {
        MaLop = maLop;
    }

    public String getTenSV() {
        return TenSV;
    }

    public void setTenSV(String tenSV) {
        TenSV = tenSV;
    }

    public String getSn() {
        return Sn;
    }

    public void setSn(String sn) {
        Sn = sn;
    }
}
