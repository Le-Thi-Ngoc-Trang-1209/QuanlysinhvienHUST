package com.example.btl_ltnc.models;

import java.time.LocalDateTime;

public class LopHocPhan {
    private int maLopGiangDay;
    private LocalDateTime thoiGianBatDau;
    private LocalDateTime thoiGianKetThuc;
    private String phongHoc;
    private String tenMonHoc;
    private int maHocPhan;
    private int soLuongSinhVien;
    private int msgv;
    private String tenGiangVien;

    public LopHocPhan(int maLopGiangDay, LocalDateTime thoiGianBatDau, LocalDateTime thoiGianKetThuc,
                      String phongHoc, String tenMonHoc, int maHocPhan, int soLuongSinhVien,
                      int msgv, String tenGiangVien) {
        this.maLopGiangDay = maLopGiangDay;
        this.thoiGianBatDau = thoiGianBatDau;
        this.thoiGianKetThuc = thoiGianKetThuc;
        this.phongHoc = phongHoc;
        this.tenMonHoc = tenMonHoc;
        this.maHocPhan = maHocPhan;
        this.soLuongSinhVien = soLuongSinhVien;
        this.msgv = msgv;
        this.tenGiangVien = tenGiangVien;
    }

    public int getMaLopGiangDay() {
        return maLopGiangDay;
    }

    public void setMaLopGiangDay(int maLopGiangDay) {
        this.maLopGiangDay = maLopGiangDay;
    }

    public LocalDateTime getThoiGianBatDau() {
        return thoiGianBatDau;
    }

    public void setThoiGianBatDau(LocalDateTime thoiGianBatDau) {
        this.thoiGianBatDau = thoiGianBatDau;
    }

    public LocalDateTime getThoiGianKetThuc() {
        return thoiGianKetThuc;
    }

    public void setThoiGianKetThuc(LocalDateTime thoiGianKetThuc) {
        this.thoiGianKetThuc = thoiGianKetThuc;
    }

    public String getPhongHoc() {
        return phongHoc;
    }

    public void setPhongHoc(String phongHoc) {
        this.phongHoc = phongHoc;
    }

    public String getTenMonHoc() {
        return tenMonHoc;
    }

    public void setTenMonHoc(String tenMonHoc) {
        this.tenMonHoc = tenMonHoc;
    }

    public int getMaHocPhan() {
        return maHocPhan;
    }

    public void setMaHocPhan(int maHocPhan) {
        this.maHocPhan = maHocPhan;
    }

    public int getSoLuongSinhVien() {
        return soLuongSinhVien;
    }

    public void setSoLuongSinhVien(int soLuongSinhVien) {
        this.soLuongSinhVien = soLuongSinhVien;
    }

    public int getMsgv() {
        return msgv;
    }

    public void setMsgv(int msgv) {
        this.msgv = msgv;
    }

    public String getTenGiangVien() {
        return tenGiangVien;
    }

    public void setTenGiangVien(String tenGiangVien) {
        this.tenGiangVien = tenGiangVien;
    }
}
