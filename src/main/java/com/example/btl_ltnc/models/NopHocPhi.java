package com.example.btl_ltnc.models;

import java.time.LocalDate;

public class NopHocPhi {
    private int maHocPhi;
    private int maSinhVien;
    private int mskh;
    private int tongTien;
    private LocalDate ngayNop;
    private String daNopChua;

    public NopHocPhi(int maHocPhi, int maSinhVien, int mskh, int tongTien, LocalDate ngayNop, String daNopChua) {
        this.maHocPhi = maHocPhi;
        this.maSinhVien = maSinhVien;
        this.mskh = mskh;
        this.tongTien = tongTien;
        this.ngayNop = ngayNop;
        this.daNopChua = daNopChua;
    }

    public int getMaHocPhi() {
        return maHocPhi;
    }

    public void setMaHocPhi(int maHocPhi) {
        this.maHocPhi = maHocPhi;
    }

    public int getMaSinhVien() {
        return maSinhVien;
    }

    public void setMaSinhVien(int maSinhVien) {
        this.maSinhVien = maSinhVien;
    }

    public int getMskh() {
        return mskh;
    }

    public void setMskh(int mskh) {
        this.mskh = mskh;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public LocalDate getNgayNop() {
        return ngayNop;
    }

    public void setNgayNop(LocalDate ngayNop) {
        this.ngayNop = ngayNop;
    }

    public String getDaNopChua() {
        return daNopChua;
    }

    public void setDaNopChua(String daNopChua) {
        this.daNopChua = daNopChua;
    }
}
