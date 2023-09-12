package com.example.btl_ltnc.controllers;

import com.example.btl_ltnc.MainApp;
import com.example.btl_ltnc.database.DBConnection;
import com.example.btl_ltnc.models.NopHocPhi;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.*;
import java.time.LocalDate;
import java.util.Optional;

public class NopHocPhiController {
    @FXML
    private TableView<NopHocPhi> tableView;
    @FXML
    private TableColumn<NopHocPhi, Integer> maHocPhiColumn;
    @FXML
    private TableColumn<NopHocPhi, Integer> maSinhVienColumn;
    @FXML
    private TableColumn<NopHocPhi, Integer> mskhColumn;
    @FXML
    private TableColumn<NopHocPhi, Integer> tongTienColumn;
    @FXML
    private TableColumn<NopHocPhi, LocalDate> ngayNopColumn;
    @FXML
    private TableColumn<NopHocPhi, String> daNopChuaColumn;

    @FXML
    private TextField maHocPhiField;
    @FXML
    private TextField maSinhVienField;
    @FXML
    private TextField mskhField;
    @FXML
    private TextField tongTienField;
    @FXML
    private TextField ngayNopField;
    @FXML
    private TextField daNopChuaField;
    @FXML
    private TextField searchField;
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
        maHocPhiColumn.setCellValueFactory(new PropertyValueFactory<>("maHocPhi"));
        maSinhVienColumn.setCellValueFactory(new PropertyValueFactory<>("maSinhVien"));
        mskhColumn.setCellValueFactory(new PropertyValueFactory<>("mskh"));
        tongTienColumn.setCellValueFactory(new PropertyValueFactory<>("tongTien"));
        ngayNopColumn.setCellValueFactory(new PropertyValueFactory<>("ngayNop"));
        daNopChuaColumn.setCellValueFactory(new PropertyValueFactory<>("daNopChua"));

        // Bắt sự kiện khi người dùng chọn một hàng trong TableView để hiển thị dữ liệu trong các trường nhập
        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                maHocPhiField.setText(Integer.toString(newValue.getMaHocPhi()));
                maSinhVienField.setText(Integer.toString(newValue.getMaSinhVien()));
                mskhField.setText(Integer.toString(newValue.getMskh()));
                tongTienField.setText(Integer.toString(newValue.getTongTien()));
                ngayNopField.setText(newValue.getNgayNop().toString());
                daNopChuaField.setText(newValue.getDaNopChua());
            }
        });
    }

    private void loadData() {
        try {
            tableView.getItems().clear();
            String query = "SELECT * FROM NopHocPhi";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int maHocPhi = resultSet.getInt("MaHocPhi");
                int maSinhVien = resultSet.getInt("MaSinhVien");
                int mskh = resultSet.getInt("MSKH");
                int tongTien = resultSet.getInt("TongTien");
                LocalDate ngayNop = resultSet.getDate("NgayNop").toLocalDate();
                String daNopChua = resultSet.getString("DaNopChua");

                NopHocPhi nopHocPhi = new NopHocPhi(maHocPhi, maSinhVien, mskh, tongTien, ngayNop, daNopChua);
                tableView.getItems().add(nopHocPhi);
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAddButton() {
        String maHocPhiStr = maHocPhiField.getText();
        String maSinhVienStr = maSinhVienField.getText();
        String mskhStr = mskhField.getText();
        String tongTienStr = tongTienField.getText();
        String ngayNopStr = ngayNopField.getText();
        String daNopChua = daNopChuaField.getText();

        try {
            int maHocPhi = Integer.parseInt(maHocPhiStr);
            int maSinhVien = Integer.parseInt(maSinhVienStr);
            int mskh = Integer.parseInt(mskhStr);
            int tongTien = Integer.parseInt(tongTienStr);
            LocalDate ngayNop = LocalDate.parse(ngayNopStr);

            NopHocPhi nopHocPhi = new NopHocPhi(maHocPhi, maSinhVien, mskh, tongTien, ngayNop, daNopChua);

            // Thêm vào cơ sở dữ liệu
            insertNopHocPhi(nopHocPhi);

            // Thêm vào TableView
            tableView.getItems().add(nopHocPhi);

            // Xóa dữ liệu trong các trường nhập
            clearFields();
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Dữ liệu không hợp lệ", "Vui lòng nhập số vào các trường Mã Học Phí, Mã Sinh Viên, MSKH, và Tổng Tiền.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Dữ liệu không hợp lệ", "Vui lòng kiểm tra định dạng ngày (yyyy-MM-dd).");
        }
    }

    private void insertNopHocPhi(NopHocPhi nopHocPhi) throws SQLException {
        String query = "INSERT INTO NopHocPhi (MaHocPhi, MaSinhVien, MSKH, TongTien, NgayNop, DaNopChua) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, nopHocPhi.getMaHocPhi());
        preparedStatement.setInt(2, nopHocPhi.getMaSinhVien());
        preparedStatement.setInt(3, nopHocPhi.getMskh());
        preparedStatement.setInt(4, nopHocPhi.getTongTien());
        preparedStatement.setDate(5, Date.valueOf(nopHocPhi.getNgayNop()));
        preparedStatement.setString(6, nopHocPhi.getDaNopChua());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @FXML
    private void handleUpdateButton() {
        String maHocPhiStr = maHocPhiField.getText();
        String maSinhVienStr = maSinhVienField.getText();
        String mskhStr = mskhField.getText();
        String tongTienStr = tongTienField.getText();
        String ngayNopStr = ngayNopField.getText();
        String daNopChua = daNopChuaField.getText();

        try {
            int maHocPhi = Integer.parseInt(maHocPhiStr);
            int maSinhVien = Integer.parseInt(maSinhVienStr);
            int mskh = Integer.parseInt(mskhStr);
            int tongTien = Integer.parseInt(tongTienStr);
            LocalDate ngayNop = LocalDate.parse(ngayNopStr);

            NopHocPhi nopHocPhi = new NopHocPhi(maHocPhi, maSinhVien, mskh, tongTien, ngayNop, daNopChua);

            // Cập nhật vào cơ sở dữ liệu
            updateNopHocPhi(nopHocPhi);

            // Sau khi cập nhật thành công, gọi lại loadData() để cập nhật TableView
            loadData();

            // Xóa dữ liệu trong các trường nhập
            clearFields();
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Dữ liệu không hợp lệ", "Vui lòng nhập số vào các trường Mã Học Phí, Mã Sinh Viên, MSKH, và Tổng Tiền.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Dữ liệu không hợp lệ", "Vui lòng kiểm tra định dạng ngày (yyyy-MM-dd).");
        }
    }

    private void updateNopHocPhi(NopHocPhi nopHocPhi) throws SQLException {
        String query = "UPDATE NopHocPhi SET MaSinhVien=?, MSKH=?, TongTien=?, NgayNop=?, DaNopChua=? WHERE MaHocPhi=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, nopHocPhi.getMaSinhVien());
        preparedStatement.setInt(2, nopHocPhi.getMskh());
        preparedStatement.setInt(3, nopHocPhi.getTongTien());
        preparedStatement.setDate(4, Date.valueOf(nopHocPhi.getNgayNop()));
        preparedStatement.setString(5, nopHocPhi.getDaNopChua());
        preparedStatement.setInt(6, nopHocPhi.getMaHocPhi());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @FXML
    private void handleDeleteButton() {
        String maHocPhiStr = maHocPhiField.getText();

        try {
            int maHocPhi = Integer.parseInt(maHocPhiStr);

            // Xác nhận xóa
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Xác nhận xóa");
            alert.setHeaderText(null);
            alert.setContentText("Bạn có chắc muốn xóa Học Phí này?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Xóa khỏi cơ sở dữ liệu
                deleteNopHocPhi(maHocPhi);

                // Sau khi xóa thành công, gọi lại loadData() để cập nhật TableView
                loadData();

                // Xóa dữ liệu trong các trường nhập
                clearFields();
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Dữ liệu không hợp lệ", "Vui lòng nhập số vào trường Mã Học Phí.");
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể xóa dữ liệu", "Đã xảy ra lỗi khi xóa Học Phí.");
        }
    }

    private void deleteNopHocPhi(int maHocPhi) throws SQLException {
        String query = "DELETE FROM NopHocPhi WHERE MaHocPhi=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, maHocPhi);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @FXML
    private void handleSearchButton() {
        String keyword = searchField.getText().trim();

        tableView.getItems().clear();

        try {
            String query = "SELECT * FROM NopHocPhi WHERE DaNopChua LIKE ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + keyword + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int maHocPhi = resultSet.getInt("MaHocPhi");
                int maSinhVien = resultSet.getInt("MaSinhVien");
                int mskh = resultSet.getInt("MSKH");
                int tongTien = resultSet.getInt("TongTien");
                LocalDate ngayNop = resultSet.getDate("NgayNop").toLocalDate();
                String daNopChua = resultSet.getString("DaNopChua");

                NopHocPhi nopHocPhi = new NopHocPhi(maHocPhi, maSinhVien, mskh, tongTien, ngayNop, daNopChua);
                tableView.getItems().add(nopHocPhi);
            }

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
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
        maHocPhiField.clear();
        maSinhVienField.clear();
        mskhField.clear();
        tongTienField.clear();
        ngayNopField.clear();
        daNopChuaField.clear();
    }

    private void showAlert(Alert.AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
