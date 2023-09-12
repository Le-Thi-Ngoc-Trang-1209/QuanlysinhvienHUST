package com.example.btl_ltnc.models;

public class KyHoc {
    private int mskh;
    private String tenKyHoc;

    public KyHoc(int mskh, String tenKyHoc) {
        this.mskh = mskh;
        this.tenKyHoc = tenKyHoc;
    }

    public int getMskh() {
        return mskh;
    }

    public String getTenKyHoc() {
        return tenKyHoc;
    }
}
