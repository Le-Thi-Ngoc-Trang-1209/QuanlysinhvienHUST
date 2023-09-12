package com.example.btl_ltnc.models;

public class DoAn {
    private int maHocPhan;
    private int soTinChi;
    private String tenHocPhan;
    private String dangKiDeTai;
    private String nguyenVongHuongDan;
    private String trangThaiDangKy;

    public DoAn(int maHocPhan, int soTinChi, String tenHocPhan, String dangKiDeTai, String nguyenVongHuongDan, String trangThaiDangKy) {
        this.maHocPhan = maHocPhan;
        this.soTinChi = soTinChi;
        this.tenHocPhan = tenHocPhan;
        this.dangKiDeTai = dangKiDeTai;
        this.nguyenVongHuongDan = nguyenVongHuongDan;
        this.trangThaiDangKy = trangThaiDangKy;
    }

    public int getMaHocPhan() {
        return maHocPhan;
    }

    public void setMaHocPhan(int maHocPhan) {
        this.maHocPhan = maHocPhan;
    }

    public int getSoTinChi() {
        return soTinChi;
    }

    public void setSoTinChi(int soTinChi) {
        this.soTinChi = soTinChi;
    }

    public String getTenHocPhan() {
        return tenHocPhan;
    }

    public void setTenHocPhan(String tenHocPhan) {
        this.tenHocPhan = tenHocPhan;
    }

    public String getDangKiDeTai() {
        return dangKiDeTai;
    }

    public void setDangKiDeTai(String dangKiDeTai) {
        this.dangKiDeTai = dangKiDeTai;
    }

    public String getNguyenVongHuongDan() {
        return nguyenVongHuongDan;
    }

    public void setNguyenVongHuongDan(String nguyenVongHuongDan) {
        this.nguyenVongHuongDan = nguyenVongHuongDan;
    }

    public String getTrangThaiDangKy() {
        return trangThaiDangKy;
    }

    public void setTrangThaiDangKy(String trangThaiDangKy) {
        this.trangThaiDangKy = trangThaiDangKy;
    }
    // Getter methods...
}
