package com.example.btl_ltnc.controllers;

import com.example.btl_ltnc.MainApp;
import com.example.btl_ltnc.database.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.w3c.dom.events.MouseEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class SVViewController {
    @FXML
    private Button hienthi;
    private Button btnBackToMenu;
    @FXML
    private TextField htCTDT;

    @FXML
    private TextField htMCC;

    @FXML
    private TextField htMSSV;

    @FXML
    private TextField htTSV;


    @FXML
    private TextField htQQ;
    @FXML
    private TextField htCCCD;

    @FXML
    private TextField htCPA;

    @FXML
    private TextField htGPA;

    @FXML
    private TextField htNNH;

    @FXML
    private TextField htNS;

    @FXML
    private AnchorPane rootLayout;

    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private Button button4;

    @FXML
    private Button button5;
    @FXML
    private Button button6;
    private MainApp mainApp;


    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void hienthi(){
        try {
            int MSSV = 20101;
            Connection connection1 = DBConnection.getConnection();
            String query1 = "SELECT * FROM SinhVien WHERE MSSV = ?";
            PreparedStatement statement = connection1.prepareStatement(query1);
            statement.setInt(1, MSSV);
            ResultSet resultset = statement.executeQuery();
            if (resultset.next()) {
                String TenSV = resultset.getString("HoTen");
                String ms = String.valueOf(resultset.getInt("MSSV"));
                String chuongTrinhDaoTao = resultset.getString("ChuongTrinhDaoTao");
                String ngaysinh = String.valueOf(resultset.getDate("NTNS"));
                String ngaynhaphoc = String.valueOf(resultset.getDate("NgayNhapHoc"));
                String muccanhcao = resultset.getString("MucCanhCao");
                String quequan = resultset.getString("QueQuan");
                String cccd = resultset.getString("CCCD");
                htTSV.setText(TenSV);
                htMSSV.setText(ms);
                htCTDT.setText(chuongTrinhDaoTao);
                htNS.setText(ngaysinh);
                htNNH.setText(ngaynhaphoc);
                htMCC.setText(muccanhcao);
                htQQ.setText(quequan);
                htCCCD.setText(cccd);
            } else {
                System.out.println("Loi truy nhap");
            }
            statement.close();
            String query = "SELECT * FROM TongKetDiem WHERE MSSV = ?";
            PreparedStatement state = connection1.prepareStatement(query);
            state.setInt(1, MSSV);
            ResultSet result = state.executeQuery();
            if (result.next()) {
                String cpa = String.valueOf(result.getFloat("CPA"));
                String gpa = String.valueOf(result.getFloat("GPA"));
                htCPA.setText(cpa);
                htGPA.setText(gpa);
            }else {
                System.out.println("Loi truy nhap");
            }
            state.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
            System.out.println("Loi");
        }
    }


    @FXML
    private void handleDoAnView(ActionEvent event) {
        mainApp.openDoAnView();
    }
    @FXML
    private void backToMenu() {
        mainApp.showLoginView();
    }
    @FXML
    private void getBM(ActionEvent event) {mainApp.openBieuMauView();}
    @FXML
    private void getDKLSV(ActionEvent event){mainApp.openDKLSV();}
    @FXML
    private void getHP(ActionEvent event){mainApp.openHPSV();}
    @FXML
    private void getQLHP(ActionEvent event){mainApp.openQuanlyHPSV();}
    @FXML
    private void getTKB(ActionEvent event) {mainApp.openTKB();}
}
