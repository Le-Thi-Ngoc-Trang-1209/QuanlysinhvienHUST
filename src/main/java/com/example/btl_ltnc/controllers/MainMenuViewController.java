package com.example.btl_ltnc.controllers;

import com.example.btl_ltnc.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainMenuViewController {

    private MainApp mainApp;
    private Button btnBackToMenu;


    @FXML
    private AnchorPane rootLayout;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void handleHocPhanView(ActionEvent event) {
        mainApp.openHocPhanView();
    }

    @FXML
    private void handleNopHocPhiView(ActionEvent event) {
        mainApp.openNopHocPhiView();
    }

    @FXML
    private void handleGiangVienView(ActionEvent event) {
        mainApp.openGiangVienView();
    }

    @FXML
    private void handleSinhVienView(ActionEvent event) {
        mainApp.openSinhVienView();
    }

    @FXML
    private void handleKyHocView(ActionEvent event) {
        mainApp.openKyHocView();
    }

    @FXML
    private void handleChuongTrinhDaoTaoView(ActionEvent event) {
        mainApp.openChuongTrinhDaoTaoView();
    }

    @FXML
    private void handleLopView(ActionEvent event) {
        mainApp.openLopView();
    }

    @FXML
    private void handleLopHocPhanView(ActionEvent event) {
        mainApp.openLopHocPhanView();
    }

    @FXML
    private void handleTaiKhoanView(ActionEvent event) {
        mainApp.openTaiKhoanView();
    }

    @FXML
    private void handleKhoaVienView(ActionEvent event) {
        mainApp.openKhoaVienView();
    }

    @FXML
    private void handlePhongHocView(ActionEvent event) {
        mainApp.openPhongHocView();
    }
    @FXML
    private void backToMenu() {
        mainApp.showLoginView();
    }
}
