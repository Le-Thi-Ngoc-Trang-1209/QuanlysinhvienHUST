package com.example.btl_ltnc.controllers;

import com.example.btl_ltnc.MainApp;
import com.example.btl_ltnc.database.DBConnection;
import com.example.btl_ltnc.models.KyHoc;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KyHocController {
    @FXML
    private TableView<KyHoc> tableView;
    @FXML
    private TableColumn<KyHoc, Integer> mskhColumn;
    @FXML
    private TableColumn<KyHoc, String> tenKyHocColumn;
    @FXML
    private TextField mskhField;
    @FXML
    private TextField tenKyHocField;
    @FXML
    private TextField searchField ;
    @FXML
    private Button addButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;
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
        mskhColumn.setCellValueFactory(new PropertyValueFactory<>("mskh"));
        tenKyHocColumn.setCellValueFactory(new PropertyValueFactory<>("tenKyHoc"));

        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                mskhField.setText(Integer.toString(newValue.getMskh()));
                tenKyHocField.setText(newValue.getTenKyHoc());
            }
        });
    }

    private void loadData() {
        try {
            tableView.getItems().clear();
            String query = "SELECT * FROM KyHoc";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int mskh = resultSet.getInt("MSKH");
                String tenKyHoc = resultSet.getString("TenKyHoc");

                KyHoc kyHoc = new KyHoc(mskh, tenKyHoc);
                tableView.getItems().add(kyHoc);
            }

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAddButton() {
        int mskh = Integer.parseInt(mskhField.getText());
        String tenKyHoc = tenKyHocField.getText();

        KyHoc kyHoc = new KyHoc(mskh, tenKyHoc);
        insertKyHoc(kyHoc);

        clearFields();
        loadData();
    }

    private void insertKyHoc(KyHoc kyHoc) {
        try {
            String query = "INSERT INTO KyHoc (MSKH, TenKyHoc) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, kyHoc.getMskh());
            preparedStatement.setString(2, kyHoc.getTenKyHoc());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleUpdateButton() {
        KyHoc selectedKyHoc = tableView.getSelectionModel().getSelectedItem();
        if (selectedKyHoc != null) {
            int mskh = Integer.parseInt(mskhField.getText());
            String tenKyHoc = tenKyHocField.getText();

            KyHoc updatedKyHoc = new KyHoc(mskh, tenKyHoc);
            updateKyHoc(updatedKyHoc);

            clearFields();
            loadData();
        } else {
            showAlert("Vui lòng chọn một dòng để cập nhật.");
        }
    }

    private void updateKyHoc(KyHoc kyHoc) {
        try {
            String query = "UPDATE KyHoc SET TenKyHoc = ? WHERE MSKH = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, kyHoc.getTenKyHoc());
            preparedStatement.setInt(2, kyHoc.getMskh());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleDeleteButton() {
        KyHoc selectedKyHoc = tableView.getSelectionModel().getSelectedItem();
        if (selectedKyHoc != null) {
            deleteKyHoc(selectedKyHoc.getMskh());
            loadData();
        } else {
            showAlert("Vui lòng chọn một dòng để xóa.");
        }
    }

    private void deleteKyHoc(int mskh) {
        try {
            String query = "DELETE FROM KyHoc WHERE MSKH = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, mskh);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleSearchButton() {
        String searchKeyword = searchField.getText().trim();

        if (!searchKeyword.isEmpty()) {
            try {
                tableView.getItems().clear();
                String query = "SELECT * FROM KyHoc WHERE TenKyHoc LIKE ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, "%" + searchKeyword + "%");
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    int mskh = resultSet.getInt("MSKH");
                    String tenKyHoc = resultSet.getString("TenKyHoc");

                    KyHoc kyHoc = new KyHoc(mskh, tenKyHoc);
                    tableView.getItems().add(kyHoc);
                }

                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleLoadAllButton() {
        loadData();
    }
    @FXML
    private void backToMenu() {
        MainApp mainApp = new MainApp();
        mainApp.start2(new Stage());
        Stage stage = (Stage) btnBackToMenu.getScene().getWindow();
        stage.close();
    }


    private void clearFields() {
        mskhField.clear();
        tenKyHocField.clear();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Cảnh báo");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
