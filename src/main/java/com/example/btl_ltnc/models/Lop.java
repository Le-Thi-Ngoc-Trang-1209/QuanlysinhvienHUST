package com.example.btl_ltnc.models;

public class Lop {
    private int MSL;
    private String TenLop;
    private int SiSo;
    private int MSGV;

    public Lop(int MSL, String TenLop, int SiSo, int MSGV) {
        this.MSL = MSL;
        this.TenLop = TenLop;
        this.SiSo = SiSo;
        this.MSGV = MSGV;
    }

    public int getMSL() {
        return MSL;
    }

    public void setMSL(int MSL) {
        this.MSL = MSL;
    }

    public String getTenLop() {
        return TenLop;
    }

    public void setTenLop(String TenLop) {
        this.TenLop = TenLop;
    }

    public int getSiSo() {
        return SiSo;
    }

    public void setSiSo(int SiSo) {
        this.SiSo = SiSo;
    }

    public int getMSGV() {
        return MSGV;
    }

    public void setMSGV(int MSGV) {
        this.MSGV = MSGV;
    }
}
