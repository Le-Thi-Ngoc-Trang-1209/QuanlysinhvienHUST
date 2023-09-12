package com.example.btl_ltnc.controllers;

import com.example.btl_ltnc.MainApp;
import com.example.btl_ltnc.database.DBConnection;
import com.example.btl_ltnc.models.DoAn;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DoAnController {

    @FXML
    private TableView<DoAn> tableView;

    @FXML
    private TableColumn<DoAn, Integer> maHocPhanColumn;

    @FXML
    private TableColumn<DoAn, Integer> soTinChiColumn;

    @FXML
    private TableColumn<DoAn, String> tenHocPhanColumn;

    @FXML
    private TableColumn<DoAn, String> dangKiDeTaiColumn;

    @FXML
    private TableColumn<DoAn, String> nguyenVongHuongDanColumn;

    @FXML
    private TableColumn<DoAn, String> trangThaiDangKyColumn;
    @FXML
    private TextField maHocPhanField;
    @FXML
    private TextField soTinChiField;
    @FXML
    private TextField tenHocPhanField;
    @FXML
    private TextField dangKiDeTaiField;
    @FXML
    private TextField nguyenVongHuongDanField;
    @FXML
    private TextField trangThaiDangKyField;
    @FXML
    private Button addButton;
    @FXML
    private Button updateButton;
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
        maHocPhanColumn.setCellValueFactory(new PropertyValueFactory<>("maHocPhan"));
        soTinChiColumn.setCellValueFactory(new PropertyValueFactory<>("soTinChi"));
        tenHocPhanColumn.setCellValueFactory(new PropertyValueFactory<>("tenHocPhan"));
        dangKiDeTaiColumn.setCellValueFactory(new PropertyValueFactory<>("dangKiDeTai"));
        nguyenVongHuongDanColumn.setCellValueFactory(new PropertyValueFactory<>("nguyenVongHuongDan"));
        trangThaiDangKyColumn.setCellValueFactory(new PropertyValueFactory<>("trangThaiDangKy"));

        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                maHocPhanField.setText(Integer.toString(newValue.getMaHocPhan()));
                soTinChiField.setText(Integer.toString(newValue.getSoTinChi()));
                tenHocPhanField.setText(newValue.getTenHocPhan());
                dangKiDeTaiField.setText(newValue.getDangKiDeTai());
                nguyenVongHuongDanField.setText(newValue.getNguyenVongHuongDan());
                trangThaiDangKyField.setText(newValue.getTrangThaiDangKy());
            }
        });
    }

    private void loadData() {
        try {
            tableView.getItems().clear();
            String query = "SELECT * FROM DoAn";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int maHocPhan = resultSet.getInt("MaHocPhan");
                int soTinChi = resultSet.getInt("SoTinChi");
                String tenHocPhan = resultSet.getString("TenHocPhan");
                String dangKiDeTai = resultSet.getString("DangKiDeTai");
                String nguyenVongHuongDan = resultSet.getString("NguyenVongHuongDan");
                String trangThaiDangKy = resultSet.getString("TrangThaiDangKy");
                DoAn doAn = new DoAn(maHocPhan, soTinChi, tenHocPhan, dangKiDeTai, nguyenVongHuongDan, trangThaiDangKy);
                tableView.getItems().add(doAn);
            }

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleAddButton() {
        int maHocPhan = Integer.parseInt(maHocPhanField.getText());
        int soTinChi = Integer.parseInt(soTinChiField.getText());
        String tenHocPhan = tenHocPhanField.getText();
        String dangKiDeTai = dangKiDeTaiField.getText();
        String nguyenVongHuongDan = nguyenVongHuongDanField.getText();
        String trangThaiDangKy = trangThaiDangKyField.getText();
        insertDoAn(maHocPhan, soTinChi, tenHocPhan, dangKiDeTai, nguyenVongHuongDan, trangThaiDangKy);
        loadData();
        clearFields();
    }

    @FXML
    private void handleUpdateButton() {
        int maHocPhan = Integer.parseInt(maHocPhanField.getText());
        int soTinChi = Integer.parseInt(soTinChiField.getText());
        String tenHocPhan = tenHocPhanField.getText();
        String dangKiDeTai = dangKiDeTaiField.getText();
        String nguyenVongHuongDan = nguyenVongHuongDanField.getText();
        String trangThaiDangKy = trangThaiDangKyField.getText();
        updateDoAn(maHocPhan, soTinChi, tenHocPhan, dangKiDeTai, nguyenVongHuongDan, trangThaiDangKy);
        loadData();
        clearFields();
    }

    @FXML
    private void handleDeleteButton() {
        int maHocPhan = Integer.parseInt(maHocPhanField.getText());
        deleteDoAn(maHocPhan);
        loadData();
        clearFields();
    }

    // ... Các phương thức xử lý tìm kiếm và hiển thị dữ liệu

    private void insertDoAn(int maHocPhan, int soTinChi, String tenHocPhan, String dangKiDeTai, String nguyenVongHuongDan, String trangThaiDangKy) {
        try {
            String query = "INSERT INTO DoAn (MaHocPhan, SoTinChi, TenHocPhan, DangKiDeTai, NguyenVongHuongDan, TrangThaiDangKy) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, maHocPhan);
            preparedStatement.setInt(2, soTinChi);
            preparedStatement.setString(3, tenHocPhan);
            preparedStatement.setString(4, dangKiDeTai);
            preparedStatement.setString(5, nguyenVongHuongDan);
            preparedStatement.setString(6, trangThaiDangKy);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            showAlert(Alert.AlertType.INFORMATION, "Thêm thành công", "Thêm dữ liệu thành công!");
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể thêm dữ liệu: " + e.getMessage());
        }
    }

    private void updateDoAn(int maHocPhan, int soTinChi, String tenHocPhan, String dangKiDeTai, String nguyenVongHuongDan, String trangThaiDangKy) {
        try {
            String query = "UPDATE DoAn SET SoTinChi = ?, TenHocPhan = ?, DangKiDeTai = ?, NguyenVongHuongDan = ?, TrangThaiDangKy = ? WHERE MaHocPhan = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, soTinChi);
            preparedStatement.setString(2, tenHocPhan);
            preparedStatement.setString(3, dangKiDeTai);
            preparedStatement.setString(4, nguyenVongHuongDan);
            preparedStatement.setString(5, trangThaiDangKy);
            preparedStatement.setInt(6, maHocPhan);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            showAlert(Alert.AlertType.INFORMATION, "Cập nhật thành công", "Cập nhật dữ liệu thành công!");
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể cập nhật dữ liệu: " + e.getMessage());
        }
    }

    private void deleteDoAn(int maHocPhan) {
        try {
            String query = "DELETE FROM DoAn WHERE MaHocPhan = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, maHocPhan);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            showAlert(Alert.AlertType.INFORMATION, "Xóa thành công", "Xóa dữ liệu thành công!");
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể xóa dữ liệu: " + e.getMessage());
        }
    }
    @FXML
    private void handleSearchButton() {
        String keyword = searchField.getText();
        searchDoAn(keyword);
    }

    @FXML
    private void handleLoadAllButton() {
        loadData();
        clearFields();
    }

    private void searchDoAn(String keyword) {
        try {
            tableView.getItems().clear();
            String query = "SELECT * FROM DoAn WHERE TenHocPhan LIKE ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + keyword + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int maHocPhan = resultSet.getInt("MaHocPhan");
                int soTinChi = resultSet.getInt("SoTinChi");
                String tenHocPhan = resultSet.getString("TenHocPhan");
                String dangKiDeTai = resultSet.getString("DangKiDeTai");
                String nguyenVongHuongDan = resultSet.getString("NguyenVongHuongDan");
                String trangThaiDangKy = resultSet.getString("TrangThaiDangKy");
                DoAn doAn = new DoAn(maHocPhan, soTinChi, tenHocPhan, dangKiDeTai, nguyenVongHuongDan, trangThaiDangKy);
                tableView.getItems().add(doAn);
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
    private void clearFields() {
        maHocPhanField.clear();
        soTinChiField.clear();
        tenHocPhanField.clear();
        dangKiDeTaiField.clear();
        nguyenVongHuongDanField.clear();
        trangThaiDangKyField.clear();
    }
    private Optional<ButtonType> showAlert(Alert.AlertType type, String title, String header) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
//        alert.setContentText(content);
        return alert.showAndWait();
    }


    // Các phương thức xử lý thêm, sửa, xóa...
}
