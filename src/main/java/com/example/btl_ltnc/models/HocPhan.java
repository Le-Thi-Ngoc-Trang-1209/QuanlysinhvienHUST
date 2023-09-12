package com.example.btl_ltnc.models;

public class HocPhan {
    private int maHocPhan;
    private int soTinChi;
    private String tenHocPhan;
    private int dinhMucHocPhi;

    public HocPhan(int maHocPhan, int soTinChi, String tenHocPhan, int dinhMucHocPhi) {
        this.maHocPhan = maHocPhan;
        this.soTinChi = soTinChi;
        this.tenHocPhan = tenHocPhan;
        this.dinhMucHocPhi = dinhMucHocPhi;
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

    public int getDinhMucHocPhi() {
        return dinhMucHocPhi;
    }

    public void setDinhMucHocPhi(int dinhMucHocPhi) {
        this.dinhMucHocPhi = dinhMucHocPhi;
    }
    // Constructor, getters, and setters
    // ...

    @Override
    public String toString() {
        return "HocPhan{" +
                "maHocPhan=" + maHocPhan +
                ", soTinChi=" + soTinChi +
                ", tenHocPhan='" + tenHocPhan + '\'' +
                ", dinhMucHocPhi=" + dinhMucHocPhi +
                '}';
    }
}

