package com.example.btl_ltnc.controllers;

import com.example.btl_ltnc.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class BieuMauViewController {
    private MainApp mainapp;
    @FXML
    private Button btnBD;
    Stage stage;

    @FXML
    private Button btnCNSV;

    @FXML
    private Button btnDKVL;

    @FXML
    private Button btnGGT;

    @FXML
    private Button btnML;

    @FXML
    private Button btnPTD;

    @FXML
    private Button btnBMBackToMenu;


    public void setMainApp(MainApp mainapp) {
        this.mainapp = mainapp;
    }

    private Stage primaryStage;

    @FXML
    private void getCNSV(ActionEvent event) throws IOException {mainapp.openBMCNSV();
    }

    @FXML
    private void getML(ActionEvent event) {
        mainapp.openBMxinmolop();
    }
    @FXML
    private void getGGT(ActionEvent event) {
        mainapp.openBMgioithieu();
    }
    @FXML
    private void getDKVL(ActionEvent event) {
        mainapp.openBMdangkyvao();
    }
    @FXML
    private void getBangDiem(ActionEvent event) {
        mainapp.openBMinbangdiem();
    }
    @FXML
    private void getPTD(ActionEvent event) {
        mainapp.openBMphuctra();
    }
    @FXML
    private void backToMenu() throws SQLException {
        MainApp mainApp = new MainApp();
        mainApp.start4(new Stage());
        Stage stage = (Stage) btnBMBackToMenu.getScene().getWindow();
        stage.close();
    }

}
