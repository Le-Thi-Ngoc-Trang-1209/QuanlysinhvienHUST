package com.example.btl_ltnc.models;

public class PhongHoc {
    private int msph;
    private String tenPhongHoc;
    private String chucNang;
    private int sucChua;

    public PhongHoc(int msph, String tenPhongHoc, String chucNang, int sucChua) {
        this.msph = msph;
        this.tenPhongHoc = tenPhongHoc;
        this.chucNang = chucNang;
        this.sucChua = sucChua;
    }

    public int getMsph() {
        return msph;
    }

    public String getTenPhongHoc() {
        return tenPhongHoc;
    }

    public String getChucNang() {
        return chucNang;
    }

    public int getSucChua() {
        return sucChua;
    }
}
