package com.example.btl_ltnc.models;

public class TaiKhoan {
    private String mstk;
    private String username;
    private String password;
    private String phanQuyen;

    public TaiKhoan(String mstk, String username, String password, String phanQuyen) {
        this.mstk = mstk;
        this.username = username;
        this.password = password;
        this.phanQuyen = phanQuyen;
    }

    public String getMstk() {
        return mstk;
    }

    public void setMstk(String mstk) {
        this.mstk = mstk;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhanQuyen() {
        return phanQuyen;
    }

    public void setPhanQuyen(String phanQuyen) {
        this.phanQuyen = phanQuyen;
    }
}
