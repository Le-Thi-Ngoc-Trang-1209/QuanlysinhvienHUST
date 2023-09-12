package com.example.btl_ltnc.models;

public class ChuongTrinhDaoTao {
    private int msctdt;
    private String tenChuongTrinh;
    private int mshp;
    private String tenHocPhan;

    public ChuongTrinhDaoTao(int msctdt, String tenChuongTrinh, int mshp, String tenHocPhan) {
        this.msctdt = msctdt;
        this.tenChuongTrinh = tenChuongTrinh;
        this.mshp = mshp;
        this.tenHocPhan = tenHocPhan;
    }

    public int getMsctdt() {
        return msctdt;
    }

    public void setMsctdt(int msctdt) {
        this.msctdt = msctdt;
    }

    public String getTenChuongTrinh() {
        return tenChuongTrinh;
    }

    public void setTenChuongTrinh(String tenChuongTrinh) {
        this.tenChuongTrinh = tenChuongTrinh;
    }

    public int getMshp() {
        return mshp;
    }

    public void setMshp(int mshp) {
        this.mshp = mshp;
    }

    public String getTenHocPhan() {
        return tenHocPhan;
    }

    public void setTenHocPhan(String tenHocPhan) {
        this.tenHocPhan = tenHocPhan;
    }
}
