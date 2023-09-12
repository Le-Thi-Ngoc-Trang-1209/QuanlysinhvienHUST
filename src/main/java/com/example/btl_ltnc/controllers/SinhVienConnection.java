package com.example.btl_ltnc.controllers;

import com.example.btl_ltnc.database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SinhVienConnection {
    private int mssv;
    public int getMSSV(){
        return this.mssv;
    }

    private String MSTK;

    public SinhVienConnection(String newMSTK){ this.MSTK = newMSTK;}

    public int SinhvienConnection() {
        try {
            Connection connection1 = DBConnection.getConnection();
            String query1 = "SELECT * FROM SinhVien WHERE MSTK = ?";
            PreparedStatement statement = connection1.prepareStatement(query1);
            statement.setString(1, MSTK);
            ResultSet resultset = statement.executeQuery();
            if (resultset.next()) {
                Integer MSSV = resultset.getInt("MSSV");
                //System.out.println(MSSV);
                this.mssv = MSSV;
            }
            connection1.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
            System.out.println("Loi");
        }
        return this.getMSSV();
    }

}
