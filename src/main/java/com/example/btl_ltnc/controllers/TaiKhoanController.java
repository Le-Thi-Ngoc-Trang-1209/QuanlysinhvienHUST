package com.example.btl_ltnc.controllers;

import com.example.btl_ltnc.MainApp;
import com.example.btl_ltnc.database.DBConnection;
import com.example.btl_ltnc.models.TaiKhoan;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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

public class TaiKhoanController {
    @FXML
    private TableView<TaiKhoan> tableView;
    @FXML
    private TableColumn<TaiKhoan, String> mstkColumn;
    @FXML
    private TableColumn<TaiKhoan, String> usernameColumn;
    @FXML
    private TableColumn<TaiKhoan, String> passwordColumn;
    @FXML
    private TableColumn<TaiKhoan, String> phanQuyenColumn;

    @FXML
    private TextField mstkField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField phanQuyenField;
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
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Lỗi kết nối cơ sở dữ liệu", e.getMessage());
        }
    }

    private Optional<ButtonType> showAlert(Alert.AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        return alert.showAndWait();
    }

    private void initTableView() {
        mstkColumn.setCellValueFactory(new PropertyValueFactory<>("mstk"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        phanQuyenColumn.setCellValueFactory(new PropertyValueFactory<>("phanQuyen"));

        // Bắt sự kiện khi người dùng chọn một hàng trong TableView để hiển thị dữ liệu trong các trường nhập
        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                mstkField.setText(newValue.getMstk());
                usernameField.setText(newValue.getUsername());
                passwordField.setText(newValue.getPassword());
                phanQuyenField.setText(newValue.getPhanQuyen());
            }
        });
    }

    private void loadData() {
        try {
            tableView.getItems().clear();
            String query = "SELECT * FROM TaiKhoan";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String mstk = resultSet.getString("MSTK");
                String username = resultSet.getString("Username");
                String password = resultSet.getString("Password");
                String phanQuyen = resultSet.getString("PhanQuyen");

                TaiKhoan taiKhoan = new TaiKhoan(mstk, username, password, phanQuyen);
                tableView.getItems().add(taiKhoan);
            }

            statement.close();
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Lỗi khi tải dữ liệu", e.getMessage());
        }
    }

    @FXML
    private void handleAddButton() {
        try {
            String mstk = mstkField.getText();
            String username = usernameField.getText();
            String password = passwordField.getText();
            String phanQuyen = phanQuyenField.getText();

            insertTaiKhoan(mstk, username, password, phanQuyen);

            showAlert(Alert.AlertType.INFORMATION, "Thông báo", "Thêm tài khoản thành công", null);

            clearFields();
            loadData();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Lỗi khi thêm tài khoản", e.getMessage());
        }
    }

    @FXML
    private void handleUpdateButton() {
        try {
            String mstk = mstkField.getText();
            String username = usernameField.getText();
            String password = passwordField.getText();
            String phanQuyen = phanQuyenField.getText();

            updateTaiKhoan(mstk, username, password, phanQuyen);

            showAlert(Alert.AlertType.INFORMATION, "Thông báo", "Cập nhật tài khoản thành công", null);

            clearFields();
            loadData();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Lỗi khi cập nhật tài khoản", e.getMessage());
        }
    }

    @FXML
    private void handleDeleteButton() {
        try {
            String mstk = mstkField.getText();

            deleteTaiKhoan(mstk);

            showAlert(Alert.AlertType.INFORMATION, "Thông báo", "Xóa tài khoản thành công", null);

            clearFields();
            loadData();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Lỗi khi xóa tài khoản", e.getMessage());
        }
    }

    @FXML
    private void handleSearchButton() {
        try {
            String keyword = searchField.getText();

            searchTaiKhoan(keyword);
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Lỗi khi tìm kiếm tài khoản", e.getMessage());
        }
    }

    @FXML
    private void handleLoadAllButton() {
        loadData();
    }

    private void insertTaiKhoan(String mstk, String username, String password, String phanQuyen) {
        try {
            String query = String.format("INSERT INTO TaiKhoan (MSTK, Username, Password, PhanQuyen) VALUES ('%s', '%s', '%s', '%s')",
                    mstk, username, password, phanQuyen);

            Statement statement = connection.createStatement();
            statement.executeUpdate(query);

            statement.close();
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Lỗi khi thêm tài khoản", e.getMessage());
        }
    }

    private void updateTaiKhoan(String mstk, String username, String password, String phanQuyen) {
        try {
            String query = String.format("UPDATE TaiKhoan SET Username = '%s', Password = '%s', PhanQuyen = '%s' WHERE MSTK = '%s'",
                    username, password, phanQuyen, mstk);

            Statement statement = connection.createStatement();
            statement.executeUpdate(query);

            statement.close();
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Lỗi khi cập nhật tài khoản", e.getMessage());
        }
    }

    private void deleteTaiKhoan(String mstk) {
        try {
            String query = String.format("DELETE FROM TaiKhoan WHERE MSTK = '%s'", mstk);

            Statement statement = connection.createStatement();
            statement.executeUpdate(query);

            statement.close();
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Lỗi khi xóa tài khoản", e.getMessage());
        }
    }

    private void searchTaiKhoan(String keyword) {
        try {
            tableView.getItems().clear();

            String query = String.format("SELECT * FROM TaiKhoan WHERE MSTK LIKE '%%%s%%' OR Username LIKE '%%%s%%'", keyword, keyword);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String mstk = resultSet.getString("MSTK");
                String username = resultSet.getString("Username");
                String password = resultSet.getString("Password");
                String phanQuyen = resultSet.getString("PhanQuyen");

                TaiKhoan taiKhoan = new TaiKhoan(mstk, username, password, phanQuyen);
                tableView.getItems().add(taiKhoan);
            }

            statement.close();
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Lỗi khi tìm kiếm tài khoản", e.getMessage());
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
        mstkField.clear();
        usernameField.clear();
        passwordField.clear();
        phanQuyenField.clear();
    }
}
