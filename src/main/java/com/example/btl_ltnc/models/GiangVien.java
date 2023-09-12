package com.example.btl_ltnc.models;

import java.time.LocalDate;

public class GiangVien {
    private int MSGV;
    private String hoTen;
    private String queQuan;
    private LocalDate NTNS;
    private String CCCD;
    private String khoaGiangDay;
    private LocalDate ngayVaoTruong;
    private String chucVu;
    private String chucDanh;
    private String maLopHoc;
    private String MSTK;

    public GiangVien(int MSGV, String hoTen, String queQuan, LocalDate NTNS, String CCCD, String khoaGiangDay, LocalDate ngayVaoTruong, String chucVu, String chucDanh, String maLopHoc, String MSTK) {
        this.MSGV = MSGV;
        this.hoTen = hoTen;
        this.queQuan = queQuan;
        this.NTNS = NTNS;
        this.CCCD = CCCD;
        this.khoaGiangDay = khoaGiangDay;
        this.ngayVaoTruong = ngayVaoTruong;
        this.chucVu = chucVu;
        this.chucDanh = chucDanh;
        this.maLopHoc = maLopHoc;
        this.MSTK = MSTK;
    }

    public int getMSGV() {
        return MSGV;
    }

    public void setMSGV(int MSGV) {
        this.MSGV = MSGV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getQueQuan() {
        return queQuan;
    }

    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }

    public LocalDate getNTNS() {
        return NTNS;
    }

    public void setNTNS(LocalDate NTNS) {
        this.NTNS = NTNS;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public String getKhoaGiangDay() {
        return khoaGiangDay;
    }

    public void setKhoaGiangDay(String khoaGiangDay) {
        this.khoaGiangDay = khoaGiangDay;
    }

    public LocalDate getNgayVaoTruong() {
        return ngayVaoTruong;
    }

    public void setNgayVaoTruong(LocalDate ngayVaoTruong) {
        this.ngayVaoTruong = ngayVaoTruong;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public String getChucDanh() {
        return chucDanh;
    }

    public void setChucDanh(String chucDanh) {
        this.chucDanh = chucDanh;
    }

    public String getMaLopHoc() {
        return maLopHoc;
    }

    public void setMaLopHoc(String maLopHoc) {
        this.maLopHoc = maLopHoc;
    }

    public String getMSTK() {
        return MSTK;
    }

    public void setMSTK(String MSTK) {
        this.MSTK = MSTK;
    }
    // Getters and setters (thuộc tính của lớp GiangVien)
    // ...

    @Override
    public String toString() {
        return "GiangVien{" +
                "MSGV=" + MSGV +
                ", hoTen='" + hoTen + '\'' +
                ", queQuan='" + queQuan + '\'' +
                ", NTNS=" + NTNS +
                ", CCCD='" + CCCD + '\'' +
                ", khoaGiangDay='" + khoaGiangDay + '\'' +
                ", ngayVaoTruong=" + ngayVaoTruong +
                ", chucVu='" + chucVu + '\'' +
                ", chucDanh='" + chucDanh + '\'' +
                ", maLopHoc='" + maLopHoc + '\'' +
                ", MSTK='" + MSTK + '\'' +
                '}';
    }
}

