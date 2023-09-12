package com.example.btl_ltnc.models;

import java.time.LocalDate;

public class SinhVien {
    private int MSSV;
    private String hoTen;
    private String queQuan;
    private LocalDate NTNS;
    private String CCCD;
    private LocalDate ngayTotNghiepCap3;
    private LocalDate ngayNhapHoc;
    private String chuongTrinhDaoTao;
    private String taiKhoanNganHang;
    private String mucCanhCao;
    private String MSL;
    private String MSTK;

    public SinhVien(int MSSV, String hoTen, String queQuan, LocalDate NTNS, String CCCD, LocalDate ngayTotNghiepCap3, LocalDate ngayNhapHoc, String chuongTrinhDaoTao, String taiKhoanNganHang, String mucCanhCao, String MSL, String MSTK) {
        this.MSSV = MSSV;
        this.hoTen = hoTen;
        this.queQuan = queQuan;
        this.NTNS = NTNS;
        this.CCCD = CCCD;
        this.ngayTotNghiepCap3 = ngayTotNghiepCap3;
        this.ngayNhapHoc = ngayNhapHoc;
        this.chuongTrinhDaoTao = chuongTrinhDaoTao;
        this.taiKhoanNganHang = taiKhoanNganHang;
        this.mucCanhCao = mucCanhCao;
        this.MSL = MSL;
        this.MSTK = MSTK;
    }

    public int getMSSV() {
        return MSSV;
    }

    public void setMSSV(int MSSV) {
        this.MSSV = MSSV;
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

    public LocalDate getNgayTotNghiepCap3() {
        return ngayTotNghiepCap3;
    }

    public void setNgayTotNghiepCap3(LocalDate ngayTotNghiepCap3) {
        this.ngayTotNghiepCap3 = ngayTotNghiepCap3;
    }

    public LocalDate getNgayNhapHoc() {
        return ngayNhapHoc;
    }

    public void setNgayNhapHoc(LocalDate ngayNhapHoc) {
        this.ngayNhapHoc = ngayNhapHoc;
    }

    public String getChuongTrinhDaoTao() {
        return chuongTrinhDaoTao;
    }

    public void setChuongTrinhDaoTao(String chuongTrinhDaoTao) {
        this.chuongTrinhDaoTao = chuongTrinhDaoTao;
    }

    public String getTaiKhoanNganHang() {
        return taiKhoanNganHang;
    }

    public void setTaiKhoanNganHang(String taiKhoanNganHang) {
        this.taiKhoanNganHang = taiKhoanNganHang;
    }

    public String getMucCanhCao() {
        return mucCanhCao;
    }

    public void setMucCanhCao(String mucCanhCao) {
        this.mucCanhCao = mucCanhCao;
    }

    public String getMSL() {
        return MSL;
    }

    public void setMSL(String MSL) {
        this.MSL = MSL;
    }

    public String getMSTK() {
        return MSTK;
    }

    public void setMSTK(String MSTK) {
        this.MSTK = MSTK;
    }

    @Override
    public String toString() {
        return "SinhVien{" +
                "MSSV=" + MSSV +
                ", hoTen='" + hoTen + '\'' +
                ", queQuan='" + queQuan + '\'' +
                ", NTNS=" + NTNS +
                ", CCCD='" + CCCD + '\'' +
                ", ngayTotNghiepCap3=" + ngayTotNghiepCap3 +
                ", ngayNhapHoc=" + ngayNhapHoc +
                ", chuongTrinhDaoTao='" + chuongTrinhDaoTao + '\'' +
                ", taiKhoanNganHang='" + taiKhoanNganHang + '\'' +
                ", mucCanhCao='" + mucCanhCao + '\'' +
                ", MSL='" + MSL + '\'' +
                ", MSTK='" + MSTK + '\'' +
                '}';
    }
}
