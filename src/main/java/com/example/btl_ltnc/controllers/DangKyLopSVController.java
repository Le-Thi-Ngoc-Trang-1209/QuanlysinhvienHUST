package com.example.btl_ltnc.controllers;

import com.example.btl_ltnc.MainApp;
import com.example.btl_ltnc.database.DBConnection;
import com.example.btl_ltnc.models.DoAn;
import com.example.btl_ltnc.models.LopHocPhan;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class DangKyLopSVController {
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
    private TextField addField;
    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;
    @FXML
    private TextField searchField;
    @FXML
    private Button searchButton;
    @FXML
    private Button loadAllButton;
    @FXML
    private Button btnBackToMenu;

    private Connection connection;

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
            String query = "SELECT * FROM LopHocPhan";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int maLopGiangDay = resultSet.getInt("MaLopGiangDay");
                LocalDateTime thoiGianBatDau = resultSet.getTimestamp("ThoiGianBatDau").toLocalDateTime();
                LocalDateTime thoiGianKetThuc = resultSet.getTimestamp("ThoiGianKetThuc").toLocalDateTime();
                String phongHoc = resultSet.getString("PhongHoc");
                String tenMonHoc = resultSet.getString("TenMonHoc");
                int maHocPhan = resultSet.getInt("MaHocPhan");
                int soLuongSinhVien = resultSet.getInt("SoLuongSinhVien");
                int msgv = resultSet.getInt("MSGV");
                String tenGiangVien = resultSet.getString("TenGiangVien");

                LopHocPhan lopHocPhan = new LopHocPhan(maLopGiangDay, thoiGianBatDau, thoiGianKetThuc, phongHoc,
                        tenMonHoc, maHocPhan, soLuongSinhVien, msgv, tenGiangVien);
                tableView.getItems().add(lopHocPhan);
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAddButton() throws SQLException {

        int malopgiangday = Integer.parseInt(addField.getText());
        String query = "SELECT * FROM LopHocPhan WHERE MaLopGiangDay LIKE ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, malopgiangday);
        ResultSet resultSet = statement.executeQuery();
        //System.out.println(malopgiangday);
        if (resultSet.next()) {
            String tenmonhoc = resultSet.getString("TenMonHoc");
            Optional<ButtonType> result = showAlert(Alert.AlertType.CONFIRMATION, "Xác nhận đăng ký", "Bạn có chắc chắn muốn đăng ký lớp?",
                    "Lớp sẽ được thêm vào lịch của bạn.");
            if (result.isPresent() && result.get() == ButtonType.OK) {
                int MSSV = 20101;
                String query1 = "SELECT * FROM DangKy WHERE TenMonHoc =? AND MSSV = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query1);
                preparedStatement.setString(1, tenmonhoc);
                preparedStatement.setInt(2, MSSV);
                ResultSet resultset = preparedStatement.executeQuery();
                if (!resultset.next()) {
                    int mssv = 20101;
                    int maLopGiangDay = resultSet.getInt("MaLopGiangDay");
                    String tenMonHoc = resultSet.getString("TenMonHoc");
                    int mskh = 2;
                    insertDangKyLop(mssv, maLopGiangDay, tenMonHoc, mskh);

                    // Hiển thị thông báo sau khi thêm
                    showAlert(Alert.AlertType.INFORMATION, "Thông báo", "Thêm mới thành công", "Đã đăng ký lớp mới thành công.");
                } else{
                    showAlert(Alert.AlertType.INFORMATION, "Thông báo", "Không thể thêm", "Lớp đã đăng ký.");
                }
                preparedStatement.close();
            }
        }
        statement.close();
    }
    @FXML
    private void handleDeleteButton() throws SQLException {
        // Lấy mã lớp giảng dạy từ trường nhập
        int maLopGiangDay = Integer.parseInt(addField.getText());
        int MSSV = 20101;
        Optional<ButtonType> result = showAlert(Alert.AlertType.CONFIRMATION, "Xác nhận xóa", "Bạn có chắc chắn muốn xóa?",
                "Dữ liệu về lớp đăng ký sẽ bị xóa khỏi cơ sở dữ liệu.");

        // Nếu người dùng xác nhận xóa
        if (result.isPresent() && result.get() == ButtonType.OK) {
            String query = "SELECT * FROM DangKy WHERE  MaLopGiangDay=? AND MSSV=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, maLopGiangDay);
            preparedStatement.setInt(2, MSSV);
            ResultSet resultset = preparedStatement.executeQuery();
            if (resultset.next()) {
                //int MSKHDK = 2;
                deleteDangKyLop(MSSV, maLopGiangDay);
                // Hiển thị thông báo sau khi xóa
                showAlert(Alert.AlertType.INFORMATION, "Thông báo", "Xóa lớp thành công", "Đã xóa lớp đã đăng ký khỏi cơ sở dữ liệu.");
                } else{
                showAlert(Alert.AlertType.INFORMATION, "Thông báo", "Không thể xoá lớp", "Lớp chưa đăng ký.");
            }
            preparedStatement.close();
        }
    }

    private void insertDangKyLop(int MSSV, int maLopGiangDay, String tenMonHoc, int MSKHDK) {
        try {
            String query = "INSERT INTO DangKy (MSSV, MaLopGiangDay, TenMonHoc, MSKHDK) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            //preparedStatement.setInt(1, MSDK);
            preparedStatement.setInt(1, MSSV);
            preparedStatement.setInt(2, maLopGiangDay);
            preparedStatement.setString(3, tenMonHoc);
            preparedStatement.setInt(4, MSKHDK);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    private void deleteDangKyLop(int MSSV, int maLopGiangDay) {
        try {
            String query = "DELETE FROM DangKy WHERE MaLopGiangDay=? AND MSSV=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, maLopGiangDay);
            preparedStatement.setInt(2, MSSV);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSearchButton() {
        String keyword = searchField.getText();
        searchLopHocPhan(keyword);
    }

    @FXML
    private void handleLoadAllButton() {
        loadData();
    }


    private void searchLopHocPhan(String keyword) {
        try {
            tableView.getItems().clear();
            String query = "SELECT * FROM LopHocPhan WHERE TenMonHoc LIKE ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + keyword + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

//            String query1 = "SELECT * FROM LopHocPhan WHERE MaHocPhan LIKE ?";
//            PreparedStatement statement = connection.prepareStatement(query);
//            statement.setString(1, "%" + keyword + "%");
//            ResultSet resultset = statement.executeQuery();

            while (resultSet.next()) {
                int maLopGiangDay = resultSet.getInt("MaLopGiangDay");
                LocalDateTime thoiGianBatDau = resultSet.getTimestamp("ThoiGianBatDau").toLocalDateTime();
                LocalDateTime thoiGianKetThuc = resultSet.getTimestamp("ThoiGianKetThuc").toLocalDateTime();
                String phongHoc = resultSet.getString("PhongHoc");
                String tenMonHoc = resultSet.getString("TenMonHoc");
                int maHocPhan = resultSet.getInt("MaHocPhan");
                int soLuongSinhVien = resultSet.getInt("SoLuongSinhVien");
                int msgv = resultSet.getInt("MSGV");
                String tenGiangVien = resultSet.getString("TenGiangVien");

                LopHocPhan lopHocPhan = new LopHocPhan(maLopGiangDay, thoiGianBatDau, thoiGianKetThuc, phongHoc,
                        tenMonHoc, maHocPhan, soLuongSinhVien, msgv, tenGiangVien);
                tableView.getItems().add(lopHocPhan);
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

    private Optional<ButtonType> showAlert(Alert.AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        return alert.showAndWait();
    }
}