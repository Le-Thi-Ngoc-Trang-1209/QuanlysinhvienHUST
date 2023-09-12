package com.example.btl_ltnc.controllers;

import com.example.btl_ltnc.MainApp;
import com.example.btl_ltnc.database.DBConnection;
import com.example.btl_ltnc.models.HocPhan;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.*;
import java.util.Optional;

public class QuanLyHPSV {
    @FXML
    private TableView<HocPhan> tableView;
    @FXML
    private TableColumn<HocPhan, Integer> maHocPhanColumn;
    @FXML
    private TableColumn<HocPhan, Integer> soTinChiColumn;
    @FXML
    private TableColumn<HocPhan, String> tenHocPhanColumn;
    @FXML
    private TableColumn<HocPhan, Integer> dinhMucHocPhiColumn;
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
        dinhMucHocPhiColumn.setCellValueFactory(new PropertyValueFactory<>("dinhMucHocPhi"));

    }

    private void loadData() throws SQLException {
        tableView.getItems().clear();
        String query = "SELECT * FROM HocPhan";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            int maHocPhan = resultSet.getInt("MaHocPhan");
            int soTinChi = resultSet.getInt("SoTinChi");
            String tenHocPhan = resultSet.getString("TenHocPhan");
            int dinhMucHocPhi = resultSet.getInt("DinhMucHocPhi");

            HocPhan hocPhan = new HocPhan(maHocPhan, soTinChi, tenHocPhan, dinhMucHocPhi);
            tableView.getItems().add(hocPhan);
        }

        statement.close();
    }

    @FXML
    private void handleSearchButton() {
        // Lấy từ khóa tìm kiếm từ trường nhập
        String keyword = searchField.getText().trim();

        // Xóa dữ liệu hiện tại trong TableView
        tableView.getItems().clear();

        try {
            String query = "SELECT * FROM HocPhan WHERE TenHocPhan LIKE ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + keyword + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int maHocPhan = resultSet.getInt("MaHocPhan");
                int soTinChi = resultSet.getInt("SoTinChi");
                String tenHocPhan = resultSet.getString("TenHocPhan");
                int dinhMucHocPhi = resultSet.getInt("DinhMucHocPhi");

                HocPhan hocPhan = new HocPhan(maHocPhan, soTinChi, tenHocPhan, dinhMucHocPhi);
                tableView.getItems().add(hocPhan);
            }

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleLoadAllButton() throws SQLException {
        // Tải lại tất cả HocPhan từ cơ sở dữ liệu và cập nhật TableView
        loadData();
    }
    @FXML
    private void backToMenu() throws SQLException {
        MainApp mainApp = new MainApp();
        mainApp.start4(new Stage());
        Stage stage = (Stage) btnBackToMenu.getScene().getWindow();
        stage.close();
    }


    private void showAlert(Alert.AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
