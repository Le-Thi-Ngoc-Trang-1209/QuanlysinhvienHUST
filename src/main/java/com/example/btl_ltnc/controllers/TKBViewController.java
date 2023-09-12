package com.example.btl_ltnc.controllers;

import com.example.btl_ltnc.MainApp;
import com.example.btl_ltnc.database.DBConnection;
import com.example.btl_ltnc.models.LopHocPhan;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Optional;

public class TKBViewController {
    private int MSSV;
    @FXML
    private TableView<LopHocPhan> tableView;
    @FXML
    private TableColumn<LopHocPhan, Integer> maLopGiangDayColumn;
    @FXML
    private TableColumn<LopHocPhan, String> thoiGianBatDauColumn;
    @FXML
    private TableColumn<LopHocPhan, String> thoiGianKetThucColumn;
    @FXML
    private TableColumn<LopHocPhan, String> phongHocColumn;
    @FXML
    private TableColumn<LopHocPhan, String> tenMonHocColumn;
    @FXML
    private TableColumn<LopHocPhan, Integer> maHocPhanColumn;
    @FXML
    private TableColumn<LopHocPhan, Integer> soLuongSinhVienColumn;
    @FXML
    private TableColumn<LopHocPhan, Integer> msgvColumn;
    @FXML
    private TableColumn<LopHocPhan, String> tenGiangVienColumn;


    @FXML
    private TextField searchField;
    @FXML
    private Button searchButton;
    @FXML
    private Button loadAllButton;
    @FXML
    private Button btnBackToMenu;

    private Connection connection;
    private Connection conn;

    public void initialize() {
        try {
            connection = DBConnection.getConnection();
            initTableView();
            loadData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initTableView() {
        maLopGiangDayColumn.setCellValueFactory(new PropertyValueFactory<>("maLopGiangDay"));
        thoiGianBatDauColumn.setCellValueFactory(new PropertyValueFactory<>("thoiGianBatDau"));
        thoiGianKetThucColumn.setCellValueFactory(new PropertyValueFactory<>("thoiGianKetThuc"));
        phongHocColumn.setCellValueFactory(new PropertyValueFactory<>("phongHoc"));
        tenMonHocColumn.setCellValueFactory(new PropertyValueFactory<>("tenMonHoc"));
        maHocPhanColumn.setCellValueFactory(new PropertyValueFactory<>("maHocPhan"));
        soLuongSinhVienColumn.setCellValueFactory(new PropertyValueFactory<>("soLuongSinhVien"));
        msgvColumn.setCellValueFactory(new PropertyValueFactory<>("msgv"));
        tenGiangVienColumn.setCellValueFactory(new PropertyValueFactory<>("tenGiangVien"));

    }

    private void loadData() {
        try {
            tableView.getItems().clear();
            int MSSV = 20101;
            String query1 = "SELECT * FROM DangKy WHERE MSSV = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query1);
            preparedStatement.setInt(1, MSSV);
            ResultSet resultset = preparedStatement.executeQuery();
            while (resultset.next()){
                int maLopGiangDay = resultset.getInt("MaLopGiangDay");
                //System.out.println(maLopGiangDay);
                try {
                    String query = "SELECT * FROM LopHocPhan WHERE MaLopGiangDay LIKE ?";
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setInt(1, maLopGiangDay);
                    ResultSet resultSet = statement.executeQuery();

                    while (resultSet.next()) {
                        int malop = resultSet.getInt("MaLopGiangDay");
                        LocalDateTime thoiGianBatDau = resultSet.getTimestamp("ThoiGianBatDau").toLocalDateTime();
                        LocalDateTime thoiGianKetThuc = resultSet.getTimestamp("ThoiGianKetThuc").toLocalDateTime();
                        String phongHoc = resultSet.getString("PhongHoc");
                        String tenMonHoc = resultSet.getString("TenMonHoc");
                        int maHocPhan = resultSet.getInt("MaHocPhan");
                        int soLuongSinhVien = resultSet.getInt("SoLuongSinhVien");
                        int msgv = resultSet.getInt("MSGV");
                        String tenGiangVien = resultSet.getString("TenGiangVien");
//                        String thoigianbatdau = String.valueOf(thoiGianBatDau);
//                        String thoigianketthuc = String.valueOf(thoiGianKetThuc);
                        LopHocPhan lopHocPhan = new LopHocPhan(malop, thoiGianBatDau, thoiGianKetThuc, phongHoc,
                                tenMonHoc, maHocPhan, soLuongSinhVien, msgv, tenGiangVien);
                        tableView.getItems().add(lopHocPhan);
                    }
                    statement.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void handleSearchButton() {
        int keyword = Integer.parseInt(searchField.getText());
        searchLopHocPhan(keyword);
    }

    @FXML
    private void handleLoadAllButton() {
        loadData();
    }


    private void searchLopHocPhan(int keyword) {
        try {
            tableView.getItems().clear();
            int MSSV = 20101;
            String query1 = "SELECT * FROM DangKy WHERE MSSV = ? AND MSKHDK = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query1);
            preparedStatement.setInt(1, MSSV);
            preparedStatement.setInt(2, keyword);
            ResultSet resultset = preparedStatement.executeQuery();
            while (resultset.next()){
                int maLopGiangDay = resultset.getInt("MaLopGiangDay");
                try {
                    String query = "SELECT * FROM LopHocPhan WHERE MaLopGiangDay = ?";
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setInt(1, maLopGiangDay);
                    ResultSet resultSet = statement.executeQuery();
                    //System.out.println(maLopGiangDay);
                    while (resultSet.next()) {
                        int maLop = resultSet.getInt("MaLopGiangDay");
                        LocalDateTime thoiGianBatDau = resultSet.getTimestamp("ThoiGianBatDau").toLocalDateTime();
                        LocalDateTime thoiGianKetThuc = resultSet.getTimestamp("ThoiGianKetThuc").toLocalDateTime();
                        String phongHoc = resultSet.getString("PhongHoc");
                        String tenMonHoc = resultSet.getString("TenMonHoc");
                        int maHocPhan = resultSet.getInt("MaHocPhan");
                        int soLuongSinhVien = resultSet.getInt("SoLuongSinhVien");
                        int msgv = resultSet.getInt("MSGV");
                        String tenGiangVien = resultSet.getString("TenGiangVien");

                        LopHocPhan lopHocPhan = new LopHocPhan(maLop, thoiGianBatDau, thoiGianKetThuc, phongHoc,
                                tenMonHoc, maHocPhan, soLuongSinhVien, msgv, tenGiangVien);
                        tableView.getItems().add(lopHocPhan);
                    }
                    statement.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void backToMenu() throws SQLException {
        MainApp mainApp = new MainApp();
        mainApp.start4(new Stage());
        Stage stage = (Stage) btnBackToMenu.getScene().getWindow();
        stage.close();
    }


}
