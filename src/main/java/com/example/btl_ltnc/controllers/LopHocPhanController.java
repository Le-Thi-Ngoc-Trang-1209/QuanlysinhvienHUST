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
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class LopHocPhanController {
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
    private TextField maLopGiangDayField;
    @FXML
    private TextField thoiGianBatDauField;
    @FXML
    private TextField thoiGianKetThucField;
    @FXML
    private TextField phongHocField;
    @FXML
    private TextField tenMonHocField;
    @FXML
    private TextField maHocPhanField;
    @FXML
    private TextField soLuongSinhVienField;
    @FXML
    private TextField msgvField;
    @FXML
    private TextField tenGiangVienField;
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
        maLopGiangDayColumn.setCellValueFactory(new PropertyValueFactory<>("maLopGiangDay"));
        thoiGianBatDauColumn.setCellValueFactory(new PropertyValueFactory<>("thoiGianBatDau"));
        thoiGianKetThucColumn.setCellValueFactory(new PropertyValueFactory<>("thoiGianKetThuc"));
        phongHocColumn.setCellValueFactory(new PropertyValueFactory<>("phongHoc"));
        tenMonHocColumn.setCellValueFactory(new PropertyValueFactory<>("tenMonHoc"));
        maHocPhanColumn.setCellValueFactory(new PropertyValueFactory<>("maHocPhan"));
        soLuongSinhVienColumn.setCellValueFactory(new PropertyValueFactory<>("soLuongSinhVien"));
        msgvColumn.setCellValueFactory(new PropertyValueFactory<>("msgv"));
        tenGiangVienColumn.setCellValueFactory(new PropertyValueFactory<>("tenGiangVien"));

        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                maLopGiangDayField.setText(Integer.toString(newValue.getMaLopGiangDay()));
                thoiGianBatDauField.setText(newValue.getThoiGianBatDau().toString());
                thoiGianKetThucField.setText(newValue.getThoiGianKetThuc().toString());
                phongHocField.setText(newValue.getPhongHoc());
                tenMonHocField.setText(newValue.getTenMonHoc());
                maHocPhanField.setText(Integer.toString(newValue.getMaHocPhan()));
                soLuongSinhVienField.setText(Integer.toString(newValue.getSoLuongSinhVien()));
                msgvField.setText(Integer.toString(newValue.getMsgv()));
                tenGiangVienField.setText(newValue.getTenGiangVien());
            }
        });
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
    private void handleAddButton() {
        // Triển khai mã thêm mới dựa trên các trường nhập
        int maLopGiangDay = Integer.parseInt(maLopGiangDayField.getText());
        LocalDateTime thoiGianBatDau = LocalDateTime.parse(thoiGianBatDauField.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
        LocalDateTime thoiGianKetThuc = LocalDateTime.parse(thoiGianKetThucField.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
        String phongHoc = phongHocField.getText();
        String tenMonHoc = tenMonHocField.getText();
        int maHocPhan = Integer.parseInt(maHocPhanField.getText());
        int soLuongSinhVien = Integer.parseInt(soLuongSinhVienField.getText());
        int maGiangVien = Integer.parseInt(msgvField.getText());
        String tenGiangVien = tenGiangVienField.getText();

        insertLopHocPhan(maLopGiangDay, thoiGianBatDau, thoiGianKetThuc, phongHoc, tenMonHoc, maHocPhan, soLuongSinhVien, maGiangVien, tenGiangVien);

        // Hiển thị thông báo sau khi thêm
        showAlert(Alert.AlertType.INFORMATION, "Thông báo", "Thêm mới thành công", "Đã thêm mới Lớp học phần vào cơ sở dữ liệu.");

        // Xóa nội dung các trường nhập
        clearFields();

        // Cập nhật lại dữ liệu trong TableView
        loadData();
    }

    @FXML
    private void handleUpdateButton() {
        // Triển khai mã cập nhật dựa trên các trường nhập
        int maLopGiangDay = Integer.parseInt(maLopGiangDayField.getText());
        LocalDateTime thoiGianBatDau = LocalDateTime.parse(thoiGianBatDauField.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
        LocalDateTime thoiGianKetThuc = LocalDateTime.parse(thoiGianKetThucField.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
        String phongHoc = phongHocField.getText();
        String tenMonHoc = tenMonHocField.getText();
        int maHocPhan = Integer.parseInt(maHocPhanField.getText());
        int soLuongSinhVien = Integer.parseInt(soLuongSinhVienField.getText());
        int maGiangVien = Integer.parseInt(msgvField.getText());
        String tenGiangVien = tenGiangVienField.getText();

        updateLopHocPhan(maLopGiangDay, thoiGianBatDau, thoiGianKetThuc, phongHoc, tenMonHoc, maHocPhan, soLuongSinhVien, maGiangVien, tenGiangVien);

        // Hiển thị thông báo sau khi cập nhật
        showAlert(Alert.AlertType.INFORMATION, "Thông báo", "Cập nhật thành công", "Đã cập nhật thông tin Lớp học phần trong cơ sở dữ liệu.");

        // Xóa nội dung các trường nhập
        clearFields();

        // Cập nhật lại dữ liệu trong TableView
        loadData();
    }

    @FXML
    private void handleDeleteButton() {
        // Lấy mã lớp giảng dạy từ trường nhập
        int maLopGiangDay = Integer.parseInt(maLopGiangDayField.getText());

        // Hiển thị hộp thoại xác nhận xóa
        Optional<ButtonType> result = showAlert(Alert.AlertType.CONFIRMATION, "Xác nhận xóa", "Bạn có chắc chắn muốn xóa?",
                "Dữ liệu về Lớp học phần sẽ bị xóa khỏi cơ sở dữ liệu.");

        // Nếu người dùng xác nhận xóa
        if (result.isPresent() && result.get() == ButtonType.OK) {
            deleteLopHocPhan(maLopGiangDay);

            // Hiển thị thông báo sau khi xóa
            showAlert(Alert.AlertType.INFORMATION, "Thông báo", "Xóa thành công", "Đã xóa Lớp học phần khỏi cơ sở dữ liệu.");

            // Xóa nội dung các trường nhập
            clearFields();

            // Cập nhật lại dữ liệu trong TableView
            loadData();
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

    private void insertLopHocPhan(int maLopGiangDay, LocalDateTime thoiGianBatDau, LocalDateTime thoiGianKetThuc,
                                  String phongHoc, String tenMonHoc, int maHocPhan, int soLuongSinhVien,
                                  int msgv, String tenGiangVien) {
        try {
            String query = "INSERT INTO LopHocPhan (MaLopGiangDay, ThoiGianBatDau, ThoiGianKetThuc, PhongHoc, TenMonHoc, MaHocPhan, SoLuongSinhVien, MSGV, TenGiangVien) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, maLopGiangDay);
            preparedStatement.setTimestamp(2, Timestamp.valueOf(thoiGianBatDau));
            preparedStatement.setTimestamp(3, Timestamp.valueOf(thoiGianKetThuc));
            preparedStatement.setString(4, phongHoc);
            preparedStatement.setString(5, tenMonHoc);
            preparedStatement.setInt(6, maHocPhan);
            preparedStatement.setInt(7, soLuongSinhVien);
            preparedStatement.setInt(8, msgv);
            preparedStatement.setString(9, tenGiangVien);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateLopHocPhan(int maLopGiangDay, LocalDateTime thoiGianBatDau, LocalDateTime thoiGianKetThuc,
                                  String phongHoc, String tenMonHoc, int maHocPhan, int soLuongSinhVien,
                                  int msgv, String tenGiangVien) {
        try {
            String query = "UPDATE LopHocPhan SET ThoiGianBatDau=?, ThoiGianKetThuc=?, PhongHoc=?, TenMonHoc=?, MaHocPhan=?, SoLuongSinhVien=?, MSGV=?, TenGiangVien=? WHERE MaLopGiangDay=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setTimestamp(1, Timestamp.valueOf(thoiGianBatDau));
            preparedStatement.setTimestamp(2, Timestamp.valueOf(thoiGianKetThuc));
            preparedStatement.setString(3, phongHoc);
            preparedStatement.setString(4, tenMonHoc);
            preparedStatement.setInt(5, maHocPhan);
            preparedStatement.setInt(6, soLuongSinhVien);
            preparedStatement.setInt(7, msgv);
            preparedStatement.setString(8, tenGiangVien);
            preparedStatement.setInt(9, maLopGiangDay);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteLopHocPhan(int maLopGiangDay) {
        try {
            String query = "DELETE FROM LopHocPhan WHERE MaLopGiangDay=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, maLopGiangDay);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void searchLopHocPhan(String keyword) {
        try {
            tableView.getItems().clear();
            String query = "SELECT * FROM LopHocPhan WHERE TenMonHoc LIKE ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + keyword + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

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
    private void backToMenu() {
        MainApp mainApp = new MainApp();
        mainApp.start2(new Stage());
        Stage stage = (Stage) btnBackToMenu.getScene().getWindow();
        stage.close();
    }

    private void clearFields() {
        maLopGiangDayField.clear();
        thoiGianBatDauField.clear();
        thoiGianKetThucField.clear();
        phongHocField.clear();
        tenMonHocField.clear();
        maHocPhanField.clear();
        soLuongSinhVienField.clear();
        msgvField.clear();
        tenGiangVienField.clear();
    }
    private Optional<ButtonType> showAlert(Alert.AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        return alert.showAndWait();
    }
}
