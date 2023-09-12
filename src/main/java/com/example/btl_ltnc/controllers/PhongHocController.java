package com.example.btl_ltnc.controllers;

import com.example.btl_ltnc.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import com.example.btl_ltnc.models.PhongHoc;
import com.example.btl_ltnc.database.DBConnection;
import javafx.stage.Stage;

import java.sql.*;
import java.util.Optional;

public class PhongHocController {
    @FXML
    private TableView<PhongHoc> tableView;
    @FXML
    private TableColumn<PhongHoc, Integer> msphColumn;
    @FXML
    private TableColumn<PhongHoc, String> tenPhongHocColumn;
    @FXML
    private TableColumn<PhongHoc, String> chucNangColumn;
    @FXML
    private TableColumn<PhongHoc, Integer> sucChuaColumn;
    @FXML
    private TextField msphField;
    @FXML
    private TextField tenPhongHocField;
    @FXML
    private TextField chucNangField;
    @FXML
    private TextField sucChuaField;
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

    @FXML
    private void initialize() {
        try {
            connection = DBConnection.getConnection();
            initTableView();
            loadData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initTableView() {
        msphColumn.setCellValueFactory(new PropertyValueFactory<>("msph"));
        tenPhongHocColumn.setCellValueFactory(new PropertyValueFactory<>("tenPhongHoc"));
        chucNangColumn.setCellValueFactory(new PropertyValueFactory<>("chucNang"));
        sucChuaColumn.setCellValueFactory(new PropertyValueFactory<>("sucChua"));

        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                msphField.setText(Integer.toString(newValue.getMsph()));
                tenPhongHocField.setText(newValue.getTenPhongHoc());
                chucNangField.setText(newValue.getChucNang());
                sucChuaField.setText(Integer.toString(newValue.getSucChua()));
            }
        });
    }

    private void loadData() {
        try {
            tableView.getItems().clear();
            String query = "SELECT * FROM PhongHoc";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int msph = resultSet.getInt("MSPH");
                String tenPhongHoc = resultSet.getString("TenPhongHoc");
                String chucNang = resultSet.getString("ChucNang");
                int sucChua = resultSet.getInt("SucChua");

                PhongHoc phongHoc = new PhongHoc(msph, tenPhongHoc, chucNang, sucChua);
                tableView.getItems().add(phongHoc);
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAddButton() {
        int msph = Integer.parseInt(msphField.getText());
        String tenPhongHoc = tenPhongHocField.getText();
        String chucNang = chucNangField.getText();
        int sucChua = Integer.parseInt(sucChuaField.getText());

        insertPhongHoc(msph,tenPhongHoc, chucNang, sucChua);

        showAlert(Alert.AlertType.INFORMATION, "Thông báo", "Thêm mới thành công",
                "Đã thêm mới Phòng học vào cơ sở dữ liệu.");

        clearFields();
        loadData();
    }

    @FXML
    private void handleUpdateButton() {
        int msph = tableView.getSelectionModel().getSelectedItem().getMsph();
        String tenPhongHoc = tenPhongHocField.getText();
        String chucNang = chucNangField.getText();
        int sucChua = Integer.parseInt(sucChuaField.getText());

        updatePhongHoc(msph, tenPhongHoc, chucNang, sucChua);

        showAlert(Alert.AlertType.INFORMATION, "Thông báo", "Cập nhật thành công",
                "Đã cập nhật thông tin Phòng học trong cơ sở dữ liệu.");

        clearFields();
        loadData();
    }

    @FXML
    private void handleDeleteButton() {
        int msph = tableView.getSelectionModel().getSelectedItem().getMsph();

        Optional<ButtonType> result = showAlert(Alert.AlertType.CONFIRMATION, "Xác nhận xóa", "Bạn có chắc chắn muốn xóa?",
                "Dữ liệu về Phòng học sẽ bị xóa khỏi cơ sở dữ liệu.");

        if (result.isPresent() && result.get() == ButtonType.OK) {
            deletePhongHoc(msph);

            showAlert(Alert.AlertType.INFORMATION, "Thông báo", "Xóa thành công",
                    "Đã xóa Phòng học khỏi cơ sở dữ liệu.");

            clearFields();
            loadData();
        }
    }

    @FXML
    private void handleSearchButton() {
        String keyword = searchField.getText();
        searchPhongHoc(keyword);
    }

    @FXML
    private void handleLoadAllButton() {
        loadData();
    }

    private void insertPhongHoc(int msph,String tenPhongHoc, String chucNang, int sucChua) {
        try {
            String query = "INSERT INTO PhongHoc (MSPH,TenPhongHoc, ChucNang, SucChua) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, msph);
            preparedStatement.setString(2, tenPhongHoc);
            preparedStatement.setString(3, chucNang);
            preparedStatement.setInt(4, sucChua);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updatePhongHoc(int msph, String tenPhongHoc, String chucNang, int sucChua) {
        try {
            String query = "UPDATE PhongHoc SET TenPhongHoc=?, ChucNang=?, SucChua=? WHERE MSPH=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, tenPhongHoc);
            preparedStatement.setString(2, chucNang);
            preparedStatement.setInt(3, sucChua);
            preparedStatement.setInt(4, msph);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deletePhongHoc(int msph) {
        try {
            String query = "DELETE FROM PhongHoc WHERE MSPH=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, msph);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void searchPhongHoc(String keyword) {
        try {
            tableView.getItems().clear();
            String query = "SELECT * FROM PhongHoc WHERE TenPhongHoc LIKE ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + keyword + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int msph = resultSet.getInt("MSPH");
                String tenPhongHoc = resultSet.getString("TenPhongHoc");
                String chucNang = resultSet.getString("ChucNang");
                int sucChua = resultSet.getInt("SucChua");

                PhongHoc phongHoc = new PhongHoc(msph, tenPhongHoc, chucNang, sucChua);
                tableView.getItems().add(phongHoc);
            }

            preparedStatement.close();
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
        tenPhongHocField.clear();
        chucNangField.clear();
        sucChuaField.clear();
    }

    private Optional<ButtonType> showAlert(Alert.AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        return alert.showAndWait();
    }
}
