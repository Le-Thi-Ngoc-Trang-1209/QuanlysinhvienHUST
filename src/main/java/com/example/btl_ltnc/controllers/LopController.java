package com.example.btl_ltnc.controllers;

import com.example.btl_ltnc.MainApp;
import com.example.btl_ltnc.database.DBConnection;
import com.example.btl_ltnc.models.Lop;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class LopController {
    @FXML
    private TableView<Lop> tableView;
    @FXML
    private TableColumn<Lop, Integer> mslColumn;
    @FXML
    private TableColumn<Lop, String> tenLopColumn;
    @FXML
    private TableColumn<Lop, Integer> siSoColumn;
    @FXML
    private TableColumn<Lop, Integer> msgvColumn;

    @FXML
    private TextField mslField;
    @FXML
    private TextField tenLopField;
    @FXML
    private TextField siSoField;
    @FXML
    private TextField msgvField;
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
        mslColumn.setCellValueFactory(new PropertyValueFactory<>("MSL"));
        tenLopColumn.setCellValueFactory(new PropertyValueFactory<>("TenLop"));
        siSoColumn.setCellValueFactory(new PropertyValueFactory<>("SiSo"));
        msgvColumn.setCellValueFactory(new PropertyValueFactory<>("MSGV"));

        // Bắt sự kiện khi người dùng chọn một hàng trong TableView để hiển thị dữ liệu trong các trường nhập
        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                mslField.setText(Integer.toString(newValue.getMSL()));
                tenLopField.setText(newValue.getTenLop());
                siSoField.setText(Integer.toString(newValue.getSiSo()));
                msgvField.setText(Integer.toString(newValue.getMSGV()));
            }
        });
    }

    private void loadData() {
        try {
            tableView.getItems().clear();
            String query = "SELECT * FROM Lop";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int msl = resultSet.getInt("MSL");
                String tenLop = resultSet.getString("TenLop");
                int siSo = resultSet.getInt("SiSo");
                int msgv = resultSet.getInt("MSGV");

                Lop lop = new Lop(msl, tenLop, siSo, msgv);
                tableView.getItems().add(lop);
            }

            statement.close();
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Lỗi khi tải dữ liệu", e.getMessage());
        }
    }

    @FXML
    private void handleAddButton() {
        try {
            int msl = Integer.parseInt(mslField.getText());
            String tenLop = tenLopField.getText();
            int siSo = Integer.parseInt(siSoField.getText());
            int msgv = Integer.parseInt(msgvField.getText());

            insertLop(msl, tenLop, siSo, msgv);

            showAlert(Alert.AlertType.INFORMATION, "Thông báo", "Thêm lớp học thành công", null);

            clearFields();
            loadData();
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Lỗi định dạng dữ liệu", "Vui lòng kiểm tra lại các trường nhập.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Lỗi khi thêm lớp học", e.getMessage());
        }
    }

    @FXML
    private void handleUpdateButton() {
        try {
            int msl = Integer.parseInt(mslField.getText());
            String tenLop = tenLopField.getText();
            int siSo = Integer.parseInt(siSoField.getText());
            int msgv = Integer.parseInt(msgvField.getText());

            updateLop(msl, tenLop, siSo, msgv);

            showAlert(Alert.AlertType.INFORMATION, "Thông báo", "Cập nhật lớp học thành công", null);

            clearFields();
            loadData();
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Lỗi định dạng dữ liệu", "Vui lòng kiểm tra lại các trường nhập.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Lỗi khi cập nhật lớp học", e.getMessage());
        }
    }

    @FXML
    private void handleDeleteButton() {
        try {
            int msl = Integer.parseInt(mslField.getText());

            deleteLop(msl);

            showAlert(Alert.AlertType.INFORMATION, "Thông báo", "Xóa lớp học thành công", null);

            clearFields();
            loadData();
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Lỗi định dạng dữ liệu", "Vui lòng kiểm tra lại trường nhập.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Lỗi khi xóa lớp học", e.getMessage());
        }
    }

    @FXML
    private void handleSearchButton() {
        try {
            String keyword = searchField.getText();

            searchLop(keyword);

            clearFields();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Lỗi khi tìm kiếm lớp học", e.getMessage());
        }
    }

    @FXML
    private void handleLoadAllButton() {
        try {
            loadData();
            clearFields();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Lỗi khi tải dữ liệu", e.getMessage());
        }
    }

    private void insertLop(int msl, String tenLop, int siSo, int msgv) {
        try {
            String query = String.format("INSERT INTO Lop (MSL, TenLop, SiSo, MSGV) VALUES (%d, '%s', %d, %d)",
                    msl, tenLop, siSo, msgv);

            Statement statement = connection.createStatement();
            statement.executeUpdate(query);

            statement.close();
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Lỗi khi thêm lớp học", e.getMessage());
        }
    }

    private void updateLop(int msl, String tenLop, int siSo, int msgv) {
        try {
            String query = String.format("UPDATE Lop SET TenLop = '%s', SiSo = %d, MSGV = %d WHERE MSL = %d",
                    tenLop, siSo, msgv, msl);

            Statement statement = connection.createStatement();
            statement.executeUpdate(query);

            statement.close();
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Lỗi khi cập nhật lớp học", e.getMessage());
        }
    }

    private void deleteLop(int msl) {
        try {
            String query = String.format("DELETE FROM Lop WHERE MSL = %d", msl);

            Statement statement = connection.createStatement();
            statement.executeUpdate(query);

            statement.close();
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Lỗi khi xóa lớp học", e.getMessage());
        }
    }

    private void searchLop(String keyword) {
        try {
            tableView.getItems().clear();

            String query = String.format("SELECT * FROM Lop WHERE TenLop LIKE '%%%s%%'", keyword);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int msl = resultSet.getInt("MSL");
                String tenLop = resultSet.getString("TenLop");
                int siSo = resultSet.getInt("SiSo");
                int msgv = resultSet.getInt("MSGV");

                Lop lop = new Lop(msl, tenLop, siSo, msgv);
                tableView.getItems().add(lop);
            }

            statement.close();
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Lỗi khi tìm kiếm lớp học", e.getMessage());
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
        mslField.clear();
        tenLopField.clear();
        siSoField.clear();
        msgvField.clear();
    }
    private Optional<ButtonType> showAlert(Alert.AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        return alert.showAndWait();
    }
}
