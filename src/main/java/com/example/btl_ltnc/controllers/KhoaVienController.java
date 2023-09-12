package com.example.btl_ltnc.controllers;

import com.example.btl_ltnc.MainApp;
import com.example.btl_ltnc.database.DBConnection;
import com.example.btl_ltnc.models.KhoaVien;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class KhoaVienController {
    @FXML
    private TableView<KhoaVien> tableView;
    @FXML
    private TableColumn<KhoaVien, Integer> mskvColumn;
    @FXML
    private TableColumn<KhoaVien, String> tenKhoaVienColumn;
    @FXML
    private TableColumn<KhoaVien, String> tenNhanVatColumn;
    @FXML
    private TableColumn<KhoaVien, String> chucVuColumn;
    @FXML
    private TableColumn<KhoaVien, String> mscColumn;
    @FXML
    private TableColumn<KhoaVien, String> diaChiColumn;
    @FXML
    private TableColumn<KhoaVien, String> sdtColumn;

    @FXML
    private TextField mskvField;
    @FXML
    private TextField tenKhoaVienField;
    @FXML
    private TextField tenNhanVatField;
    @FXML
    private TextField chucVuField;
    @FXML
    private TextField mscField;
    @FXML
    private TextField diaChiField;
    @FXML
    private TextField sdtField;
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
        mskvColumn.setCellValueFactory(new PropertyValueFactory<>("mskv"));
        tenKhoaVienColumn.setCellValueFactory(new PropertyValueFactory<>("tenKhoaVien"));
        tenNhanVatColumn.setCellValueFactory(new PropertyValueFactory<>("tenNhanVat"));
        chucVuColumn.setCellValueFactory(new PropertyValueFactory<>("chucVu"));
        mscColumn.setCellValueFactory(new PropertyValueFactory<>("msc"));
        diaChiColumn.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
        sdtColumn.setCellValueFactory(new PropertyValueFactory<>("sdt"));

        // Bắt sự kiện khi người dùng chọn một hàng trong TableView để hiển thị dữ liệu trong các trường nhập
        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                mskvField.setText(Integer.toString(newValue.getMskv()));
                tenKhoaVienField.setText(newValue.getTenKhoaVien());
                tenNhanVatField.setText(newValue.getTenNhanVat());
                chucVuField.setText(newValue.getChucVu());
                mscField.setText(newValue.getMsc());
                diaChiField.setText(newValue.getDiaChi());
                sdtField.setText(newValue.getSdt());
            }
        });
    }

    private void loadData() {
        try {
            tableView.getItems().clear();
            String query = "SELECT * FROM KhoaVien";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int mskv = resultSet.getInt("MSKV");
                String tenKhoaVien = resultSet.getString("TenKhoaVien");
                String tenNhanVat = resultSet.getString("TenNhanVat");
                String chucVu = resultSet.getString("ChucVu");
                String msc = resultSet.getString("MSC");
                String diaChi = resultSet.getString("DiaChi");
                String sdt = resultSet.getString("SDT");

                KhoaVien khoaVien = new KhoaVien(mskv, tenKhoaVien, tenNhanVat, chucVu, msc, diaChi, sdt);
                tableView.getItems().add(khoaVien);
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAddButton() {
        try {
            int mskv = Integer.parseInt(mskvField.getText());
            String tenKhoaVien = tenKhoaVienField.getText();
            String tenNhanVat = tenNhanVatField.getText();
            String chucVu = chucVuField.getText();
            String msc = mscField.getText();
            String diaChi = diaChiField.getText();
            String sdt = sdtField.getText();

            insertKhoaVien(mskv, tenKhoaVien, tenNhanVat, chucVu, msc, diaChi, sdt);

            // Hiển thị thông báo thành công
            showAlert(Alert.AlertType.INFORMATION, "Thêm Khoa Viện", "Thêm thành công",
                    "Đã thêm thông tin Khoa Viện thành công.");

            // Cập nhật lại TableView
            loadData();

            // Xóa dữ liệu trong các trường nhập
            clearFields();
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Lỗi nhập liệu", "Lỗi",
                    "Vui lòng kiểm tra lại các trường số nguyên.");
        }
    }

    @FXML
    private void handleUpdateButton() {
        try {
            int mskv = Integer.parseInt(mskvField.getText());
            String tenKhoaVien = tenKhoaVienField.getText();
            String tenNhanVat = tenNhanVatField.getText();
            String chucVu = chucVuField.getText();
            String msc = mscField.getText();
            String diaChi = diaChiField.getText();
            String sdt = sdtField.getText();

            updateKhoaVien(mskv, tenKhoaVien, tenNhanVat, chucVu, msc, diaChi, sdt);

            // Hiển thị thông báo thành công
            showAlert(Alert.AlertType.INFORMATION, "Cập nhật Khoa Viện", "Cập nhật thành công",
                    "Đã cập nhật thông tin Khoa Viện thành công.");

            // Cập nhật lại TableView
            loadData();

            // Xóa dữ liệu trong các trường nhập
            clearFields();
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Lỗi nhập liệu", "Lỗi",
                    "Vui lòng kiểm tra lại các trường số nguyên.");
        }
    }

    @FXML
    private void handleDeleteButton() {
        try {
            int mskv = Integer.parseInt(mskvField.getText());
            deleteKhoaVien(mskv);

            // Hiển thị thông báo thành công
            showAlert(Alert.AlertType.INFORMATION, "Xóa Khoa Viện", "Xóa thành công",
                    "Đã xóa thông tin Khoa Viện thành công.");

            // Cập nhật lại TableView
            loadData();

            // Xóa dữ liệu trong các trường nhập
            clearFields();
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Lỗi nhập liệu", "Lỗi",
                    "Vui lòng kiểm tra lại giá trị MSKV.");
        }
    }

    @FXML
    private void handleSearchButton() {
        String keyword = searchField.getText();
        searchKhoaVien(keyword);
    }

    @FXML
    private void handleLoadAllButton() {
        loadData();
    }

    private void insertKhoaVien(int mskv, String tenKhoaVien, String tenNhanVat, String chucVu,
                                String msc, String diaChi, String sdt) {
        try {
            String query = "INSERT INTO KhoaVien (MSKV, TenKhoaVien, TenNhanVat, ChucVu, MSC, DiaChi, SDT) " +
                    "VALUES (" + mskv + ", N'" + tenKhoaVien + "', N'" + tenNhanVat + "', N'" +
                    chucVu + "', N'" + msc + "', N'" + diaChi + "', N'" + sdt + "')";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateKhoaVien(int mskv, String tenKhoaVien, String tenNhanVat, String chucVu,
                                String msc, String diaChi, String sdt) {
        try {
            String query = "UPDATE KhoaVien SET TenKhoaVien = N'" + tenKhoaVien + "', TenNhanVat = N'" + tenNhanVat +
                    "', ChucVu = N'" + chucVu + "', MSC = N'" + msc + "', DiaChi = N'" + diaChi +
                    "', SDT = N'" + sdt + "' WHERE MSKV = " + mskv;
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteKhoaVien(int mskv) {
        try {
            String query = "DELETE FROM KhoaVien WHERE MSKV = " + mskv;
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void searchKhoaVien(String keyword) {
        try {
            tableView.getItems().clear();
            String query = "SELECT * FROM KhoaVien WHERE TenKhoaVien LIKE N'%" + keyword + "%' OR TenNhanVat LIKE N'%" + keyword + "%'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int mskv = resultSet.getInt("MSKV");
                String tenKhoaVien = resultSet.getString("TenKhoaVien");
                String tenNhanVat = resultSet.getString("TenNhanVat");
                String chucVu = resultSet.getString("ChucVu");
                String msc = resultSet.getString("MSC");
                String diaChi = resultSet.getString("DiaChi");
                String sdt = resultSet.getString("SDT");

                KhoaVien khoaVien = new KhoaVien(mskv, tenKhoaVien, tenNhanVat, chucVu, msc, diaChi, sdt);
                tableView.getItems().add(khoaVien);
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void backToMenu() {
        MainApp mainApp = new MainApp();
        mainApp.start2(new Stage());
        Stage stage = (Stage) btnBackToMenu.getScene().getWindow();
        stage.close();
    }

    private void clearFields() {
        mskvField.clear();
        tenKhoaVienField.clear();
        tenNhanVatField.clear();
        chucVuField.clear();
        mscField.clear();
        diaChiField.clear();
        sdtField.clear();
    }

    private void showAlert(Alert.AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
