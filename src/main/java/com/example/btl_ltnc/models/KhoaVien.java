package com.example.btl_ltnc.models;

public class KhoaVien {
    private int mskv;
    private String tenKhoaVien;
    private String tenNhanVat;
    private String chucVu;
    private String msc;
    private String diaChi;
    private String sdt;

    public KhoaVien(int mskv, String tenKhoaVien, String tenNhanVat, String chucVu, String msc, String diaChi, String sdt) {
        this.mskv = mskv;
        this.tenKhoaVien = tenKhoaVien;
        this.tenNhanVat = tenNhanVat;
        this.chucVu = chucVu;
        this.msc = msc;
        this.diaChi = diaChi;
        this.sdt = sdt;
    }

    public int getMskv() {
        return mskv;
    }

    public void setMskv(int mskv) {
        this.mskv = mskv;
    }

    public String getTenKhoaVien() {
        return tenKhoaVien;
    }

    public void setTenKhoaVien(String tenKhoaVien) {
        this.tenKhoaVien = tenKhoaVien;
    }

    public String getTenNhanVat() {
        return tenNhanVat;
    }

    public void setTenNhanVat(String tenNhanVat) {
        this.tenNhanVat = tenNhanVat;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public String getMsc() {
        return msc;
    }

    public void setMsc(String msc) {
        this.msc = msc;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
}
