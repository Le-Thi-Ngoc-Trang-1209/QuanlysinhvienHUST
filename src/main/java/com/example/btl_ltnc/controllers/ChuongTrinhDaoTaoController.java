package com.example.btl_ltnc.controllers;

import com.example.btl_ltnc.MainApp;
import com.example.btl_ltnc.database.DBConnection;
import com.example.btl_ltnc.models.ChuongTrinhDaoTao;
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

    public class ChuongTrinhDaoTaoController {
        @FXML
        private TableView<ChuongTrinhDaoTao> tableView;
        @FXML
        private TableColumn<ChuongTrinhDaoTao, Integer> msctdtColumn;
        @FXML
        private TableColumn<ChuongTrinhDaoTao, String> tenChuongTrinhColumn;
        @FXML
        private TableColumn<ChuongTrinhDaoTao, Integer> mshpColumn;
        @FXML
        private TableColumn<ChuongTrinhDaoTao, String> tenHocPhanColumn;

        @FXML
        private TextField msctdtField;
        @FXML
        private TextField tenChuongTrinhField;
        @FXML
        private TextField mshpField;
        @FXML
        private TextField tenHocPhanField;
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
            msctdtColumn.setCellValueFactory(new PropertyValueFactory<>("msctdt"));
            tenChuongTrinhColumn.setCellValueFactory(new PropertyValueFactory<>("tenChuongTrinh"));
            mshpColumn.setCellValueFactory(new PropertyValueFactory<>("mshp"));
            tenHocPhanColumn.setCellValueFactory(new PropertyValueFactory<>("tenHocPhan"));

            tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    msctdtField.setText(Integer.toString(newValue.getMsctdt()));
                    tenChuongTrinhField.setText(newValue.getTenChuongTrinh());
                    mshpField.setText(Integer.toString(newValue.getMshp()));
                    tenHocPhanField.setText(newValue.getTenHocPhan());
                }
            });
        }

        private void loadData() {
            try {
                tableView.getItems().clear();
                String query = "SELECT * FROM ChuongTrinhDaoTao";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    int msctdt = resultSet.getInt("MSCTDT");
                    String tenChuongTrinh = resultSet.getString("TenChuongTrinh");
                    int mshp = resultSet.getInt("MSHP");
                    String tenHocPhan = resultSet.getString("TenHocPhan");
                    ChuongTrinhDaoTao ct = new ChuongTrinhDaoTao(msctdt, tenChuongTrinh, mshp, tenHocPhan);
                    tableView.getItems().add(ct);
                }

                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @FXML
        private void handleAddButton() {
            int msctdt = Integer.parseInt(msctdtField.getText());
            String tenChuongTrinh = tenChuongTrinhField.getText();
            int mshp = Integer.parseInt(mshpField.getText());
            String tenHocPhan = tenHocPhanField.getText();
            insertChuongTrinhDaoTao(msctdt, tenChuongTrinh, mshp, tenHocPhan);
            loadData();
            clearFields();
        }

        @FXML
        private void handleUpdateButton() {
            int msctdt = Integer.parseInt(msctdtField.getText());
            String tenChuongTrinh = tenChuongTrinhField.getText();
            int mshp = Integer.parseInt(mshpField.getText());
            String tenHocPhan = tenHocPhanField.getText();
            updateChuongTrinhDaoTao(msctdt, tenChuongTrinh, mshp, tenHocPhan);
            loadData();
            clearFields();
        }

        @FXML
        private void handleDeleteButton() {
            int msctdt = Integer.parseInt(msctdtField.getText());
            deleteChuongTrinhDaoTao(msctdt);
            loadData();
            clearFields();
        }

        @FXML
        private void handleSearchButton() {
            String keyword = searchField.getText();
            searchChuongTrinhDaoTao(keyword);
        }

        @FXML
        private void handleLoadAllButton() {
            loadData();
            clearFields();
        }

        private void insertChuongTrinhDaoTao(int msctdt, String tenChuongTrinh, int mshp, String tenHocPhan) {
            try {
                String query = "INSERT INTO ChuongTrinhDaoTao (MSCTDT, TenChuongTrinh, MSHP, TenHocPhan) VALUES (?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, msctdt);
                preparedStatement.setString(2, tenChuongTrinh);
                preparedStatement.setInt(3, mshp);
                preparedStatement.setString(4, tenHocPhan);
                preparedStatement.executeUpdate();
                preparedStatement.close();
                showAlert(Alert.AlertType.INFORMATION, "Thêm thành công", "Thêm dữ liệu thành công!");
            } catch (SQLException e) {
                showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể thêm dữ liệu: " + e.getMessage());
            }
        }

        private void updateChuongTrinhDaoTao(int msctdt, String tenChuongTrinh, int mshp, String tenHocPhan) {
            try {
                String query = "UPDATE ChuongTrinhDaoTao SET TenChuongTrinh = ?, MSHP = ?, TenHocPhan = ? WHERE MSCTDT = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, tenChuongTrinh);
                preparedStatement.setInt(2, mshp);
                preparedStatement.setString(3, tenHocPhan);
                preparedStatement.setInt(4, msctdt);
                preparedStatement.executeUpdate();
                preparedStatement.close();
                showAlert(Alert.AlertType.INFORMATION, "Cập nhật thành công", "Cập nhật dữ liệu thành công!");
            } catch (SQLException e) {
                showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể cập nhật dữ liệu: " + e.getMessage());
            }
        }

        private void deleteChuongTrinhDaoTao(int msctdt) {
            try {
                String query = "DELETE FROM ChuongTrinhDaoTao WHERE MSCTDT = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, msctdt);
                preparedStatement.executeUpdate();
                preparedStatement.close();
                showAlert(Alert.AlertType.INFORMATION, "Xóa thành công", "Xóa dữ liệu thành công!");
            } catch (SQLException e) {
                showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể xóa dữ liệu: " + e.getMessage());
            }
        }

        private void searchChuongTrinhDaoTao(String keyword) {
            try {
                tableView.getItems().clear();
                String query = "SELECT * FROM ChuongTrinhDaoTao WHERE TenChuongTrinh LIKE ? OR TenHocPhan LIKE ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, "%" + keyword + "%");
                preparedStatement.setString(2, "%" + keyword + "%");
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    int msctdt = resultSet.getInt("MSCTDT");
                    String tenChuongTrinh = resultSet.getString("TenChuongTrinh");
                    int mshp = resultSet.getInt("MSHP");
                    String tenHocPhan = resultSet.getString("TenHocPhan");
                    ChuongTrinhDaoTao ct = new ChuongTrinhDaoTao(msctdt, tenChuongTrinh, mshp, tenHocPhan);
                    tableView.getItems().add(ct);
                }

                preparedStatement.close();
            } catch (SQLException e) {
                showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể tìm kiếm dữ liệu: " + e.getMessage());
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
            msctdtField.clear();
           tenChuongTrinhField.clear();
           mshpField.clear();
           tenChuongTrinhField.clear();
        }

        private void showAlert(Alert.AlertType type, String title, String content) {
            Alert alert = new Alert(type);
            alert.setTitle(title);
            alert.setContentText(content);
            alert.showAndWait();
        }
    }

