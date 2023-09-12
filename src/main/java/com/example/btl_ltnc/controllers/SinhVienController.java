package com.example.btl_ltnc.controllers;

import com.example.btl_ltnc.MainApp;
import com.example.btl_ltnc.database.DBConnection;
import com.example.btl_ltnc.models.SinhVien;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Optional;

public class SinhVienController {
    @FXML
    private TableView<SinhVien> tableView;
    @FXML
    private TableColumn<SinhVien, Integer> mssvColumn;
    @FXML
    private TableColumn<SinhVien, String> hoTenColumn;
    @FXML
    private TableColumn<SinhVien, String> queQuanColumn;
    @FXML
    private TableColumn<SinhVien, LocalDate> nttnsColumn;
    @FXML
    private TableColumn<SinhVien, String> cccdColumn;
    @FXML
    private TableColumn<SinhVien, LocalDate> ngayTotNghiepColumn;
    @FXML
    private TableColumn<SinhVien, LocalDate> ngayNhapHocColumn;
    @FXML
    private TableColumn<SinhVien, String> ctdtColumn;
    @FXML
    private TableColumn<SinhVien, String> tknhColumn;
    @FXML
    private TableColumn<SinhVien, String> mucCanhCaoColumn;
    @FXML
    private TableColumn<SinhVien, String> mslColumn;
    @FXML
    private TableColumn<SinhVien, String> mstkColumn;

    @FXML
    private TextField mssvField;
    @FXML
    private TextField hoTenField;
    @FXML
    private TextField queQuanField;
    @FXML
    private TextField nttnsField;
    @FXML
    private TextField cccdField;
    @FXML
    private TextField ngayTotNghiepField;
    @FXML
    private TextField ngayNhapHocField;
    @FXML
    private TextField ctdtField;
    @FXML
    private TextField tknhField;
    @FXML
    private TextField mucCanhCaoField;
    @FXML
    private TextField mslField;
    @FXML
    private TextField mstkField;

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
        mssvColumn.setCellValueFactory(new PropertyValueFactory<>("MSSV"));
        hoTenColumn.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
        queQuanColumn.setCellValueFactory(new PropertyValueFactory<>("queQuan"));
        nttnsColumn.setCellValueFactory(new PropertyValueFactory<>("NTNS"));
        cccdColumn.setCellValueFactory(new PropertyValueFactory<>("CCCD"));
        ngayTotNghiepColumn.setCellValueFactory(new PropertyValueFactory<>("ngayTotNghiepCap3"));
        ngayNhapHocColumn.setCellValueFactory(new PropertyValueFactory<>("ngayNhapHoc"));
        ctdtColumn.setCellValueFactory(new PropertyValueFactory<>("chuongTrinhDaoTao"));
        tknhColumn.setCellValueFactory(new PropertyValueFactory<>("taiKhoanNganHang"));
        mucCanhCaoColumn.setCellValueFactory(new PropertyValueFactory<>("mucCanhCao"));
        mslColumn.setCellValueFactory(new PropertyValueFactory<>("MSL"));
        mstkColumn.setCellValueFactory(new PropertyValueFactory<>("MSTK"));

        // Bắt sự kiện khi người dùng chọn một hàng trong TableView để hiển thị dữ liệu trong các trường nhập
        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                mssvField.setText(Integer.toString(newValue.getMSSV()));
                hoTenField.setText(newValue.getHoTen());
                queQuanField.setText(newValue.getQueQuan());
                nttnsField.setText(newValue.getNTNS().toString());
                cccdField.setText(newValue.getCCCD());
                ngayTotNghiepField.setText(newValue.getNgayTotNghiepCap3().toString());
                ngayNhapHocField.setText(newValue.getNgayNhapHoc().toString());
                ctdtField.setText(newValue.getChuongTrinhDaoTao());
                tknhField.setText(newValue.getTaiKhoanNganHang());
                mucCanhCaoField.setText(newValue.getMucCanhCao());
                mslField.setText(newValue.getMSL());
                mstkField.setText(newValue.getMSTK());
            }
        });
    }

    private void loadData() {
        try {
            tableView.getItems().clear();
            String query = "SELECT * FROM SinhVien";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int mssv = resultSet.getInt("MSSV");
                String hoTen = resultSet.getString("HoTen");
                String queQuan = resultSet.getString("QueQuan");
                LocalDate nttns = resultSet.getDate("NTNS").toLocalDate();
                String cccd = resultSet.getString("CCCD");
                LocalDate ngayTotNghiep = resultSet.getDate("NgayTotNghiepCap3").toLocalDate();
                LocalDate ngayNhapHoc = resultSet.getDate("NgayNhapHoc").toLocalDate();
                String ctdt = resultSet.getString("ChuongTrinhDaoTao");
                String tknh = resultSet.getString("TaiKhoanNganHang");
                String mucCanhCao = resultSet.getString("MucCanhCao");
                String msl = resultSet.getString("MSL");
                String mstk = resultSet.getString("MSTK");

                SinhVien sinhVien = new SinhVien(mssv, hoTen, queQuan, nttns, cccd, ngayTotNghiep, ngayNhapHoc, ctdt, tknh, mucCanhCao, msl, mstk);
                tableView.getItems().add(sinhVien);
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAddButton() {
        String mssvStr = mssvField.getText();
        String hoTen = hoTenField.getText();
        String queQuan = queQuanField.getText();
        String nttnsStr = nttnsField.getText();
        String cccd = cccdField.getText();
        String ngayTotNghiepStr = ngayTotNghiepField.getText();
        String ngayNhapHocStr = ngayNhapHocField.getText();
        String ctdt = ctdtField.getText();
        String tknh = tknhField.getText();
        String mucCanhCao = mucCanhCaoField.getText();
        String msl = mslField.getText();
        String mstk = mstkField.getText();

        try {
            int mssv = Integer.parseInt(mssvStr);
            LocalDate nttns = LocalDate.parse(nttnsStr);
            LocalDate ngayTotNghiep = LocalDate.parse(ngayTotNghiepStr);
            LocalDate ngayNhapHoc = LocalDate.parse(ngayNhapHocStr);

            SinhVien sinhVien = new SinhVien(mssv, hoTen, queQuan, nttns, cccd, ngayTotNghiep, ngayNhapHoc, ctdt, tknh, mucCanhCao, msl, mstk);
            insertSinhVien(sinhVien);
            loadData();
            clearFields();
        } catch (NumberFormatException | DateTimeException e) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Dữ liệu không hợp lệ!", "Vui lòng kiểm tra lại các trường nhập.");
        }
    }

    @FXML
    private void handleUpdateButton() {
        String mssvStr = mssvField.getText();
        String hoTen = hoTenField.getText();
        String queQuan = queQuanField.getText();
        String nttnsStr = nttnsField.getText();
        String cccd = cccdField.getText();
        String ngayTotNghiepStr = ngayTotNghiepField.getText();
        String ngayNhapHocStr = ngayNhapHocField.getText();
        String ctdt = ctdtField.getText();
        String tknh = tknhField.getText();
        String mucCanhCao = mucCanhCaoField.getText();
        String msl = mslField.getText();
        String mstk = mstkField.getText();

        try {
            int mssv = Integer.parseInt(mssvStr);
            LocalDate nttns = LocalDate.parse(nttnsStr);
            LocalDate ngayTotNghiep = LocalDate.parse(ngayTotNghiepStr);
            LocalDate ngayNhapHoc = LocalDate.parse(ngayNhapHocStr);

            SinhVien sinhVien = new SinhVien(mssv, hoTen, queQuan, nttns, cccd, ngayTotNghiep, ngayNhapHoc, ctdt, tknh, mucCanhCao, msl, mstk);
            updateSinhVien(sinhVien);
            loadData();
            clearFields();
        } catch (NumberFormatException | DateTimeException e) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Dữ liệu không hợp lệ!", "Vui lòng kiểm tra lại các trường nhập.");
        }
    }

    @FXML
    private void handleDeleteButton() {
        String mssvStr = mssvField.getText();

        try {
            int mssv = Integer.parseInt(mssvStr);
            Optional<ButtonType> result = showAlert(Alert.AlertType.CONFIRMATION, "Xác nhận xóa", "Bạn có chắc chắn muốn xóa sinh viên này?",
                    "Mã số sinh viên: " + mssv);
            if (result.isPresent() && result.get() == ButtonType.OK) {
                deleteSinhVien(mssv);
                loadData();
                clearFields();
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Dữ liệu không hợp lệ!", "Vui lòng nhập mã số sinh viên là một số nguyên.");
        }
    }

    @FXML
    private void handleSearchButton() {
        String keyword = searchField.getText();
        if (!keyword.isEmpty()) {
            searchSinhVien(keyword);
        } else {
            showAlert(Alert.AlertType.WARNING, "Cảnh báo", "Từ khóa tìm kiếm trống!", "Vui lòng nhập từ khóa tìm kiếm.");
        }
    }

    @FXML
    private void handleLoadAllButton() {
        loadData();
        clearFields();
    }

    private void insertSinhVien(SinhVien sinhVien) {
        try {
            String query = "INSERT INTO SinhVien (MSSV, HoTen, QueQuan, NTNS, CCCD, NgayTotNghiepCap3, NgayNhapHoc, ChuongTrinhDaoTao, TaiKhoanNganHang, MucCanhCao, MSL, MSTK) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, sinhVien.getMSSV());
            preparedStatement.setString(2, sinhVien.getHoTen());
            preparedStatement.setString(3, sinhVien.getQueQuan());
            preparedStatement.setDate(4, Date.valueOf(sinhVien.getNTNS()));
            preparedStatement.setString(5, sinhVien.getCCCD());
            preparedStatement.setDate(6, Date.valueOf(sinhVien.getNgayTotNghiepCap3()));
            preparedStatement.setDate(7, Date.valueOf(sinhVien.getNgayNhapHoc()));
            preparedStatement.setString(8, sinhVien.getChuongTrinhDaoTao());
            preparedStatement.setString(9, sinhVien.getTaiKhoanNganHang());
            preparedStatement.setString(10, sinhVien.getMucCanhCao());
            preparedStatement.setString(11, sinhVien.getMSL());
            preparedStatement.setString(12, sinhVien.getMSTK());

            preparedStatement.executeUpdate();
            preparedStatement.close();

            showAlert(Alert.AlertType.INFORMATION, "Thông báo", "Thêm mới thành công!", "Đã thêm mới thông tin sinh viên.");
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Lỗi khi thêm mới!", "Vui lòng kiểm tra lại thông tin và thử lại.");
            e.printStackTrace();
        }
    }

    private void updateSinhVien(SinhVien sinhVien) {
        try {
            String query = "UPDATE SinhVien SET HoTen=?, QueQuan=?, NTNS=?, CCCD=?, NgayTotNghiepCap3=?, NgayNhapHoc=?, ChuongTrinhDaoTao=?, TaiKhoanNganHang=?, MucCanhCao=?, MSL=?, MSTK=? WHERE MSSV=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, sinhVien.getHoTen());
            preparedStatement.setString(2, sinhVien.getQueQuan());
            preparedStatement.setDate(3, Date.valueOf(sinhVien.getNTNS()));
            preparedStatement.setString(4, sinhVien.getCCCD());
            preparedStatement.setDate(5, Date.valueOf(sinhVien.getNgayTotNghiepCap3()));
            preparedStatement.setDate(6, Date.valueOf(sinhVien.getNgayNhapHoc()));
            preparedStatement.setString(7, sinhVien.getChuongTrinhDaoTao());
            preparedStatement.setString(8, sinhVien.getTaiKhoanNganHang());
            preparedStatement.setString(9, sinhVien.getMucCanhCao());
            preparedStatement.setString(10, sinhVien.getMSL());
            preparedStatement.setString(11, sinhVien.getMSTK());
            preparedStatement.setInt(12, sinhVien.getMSSV());

            preparedStatement.executeUpdate();
            preparedStatement.close();

            showAlert(Alert.AlertType.INFORMATION, "Thông báo", "Cập nhật thành công!", "Đã cập nhật thông tin sinh viên.");
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Lỗi khi cập nhật!", "Vui lòng kiểm tra lại thông tin và thử lại.");
            e.printStackTrace();
        }
    }

    private void deleteSinhVien(int mssv) {
        try {
            String query = "DELETE FROM SinhVien WHERE MSSV=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, mssv);

            preparedStatement.executeUpdate();
            preparedStatement.close();

            showAlert(Alert.AlertType.INFORMATION, "Thông báo", "Xóa thành công!", "Đã xóa thông tin sinh viên.");
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Lỗi khi xóa!", "Vui lòng kiểm tra lại thông tin và thử lại.");
            e.printStackTrace();
        }
    }

    private void searchSinhVien(String keyword) {
        try {
            tableView.getItems().clear();
            String query = "SELECT * FROM SinhVien WHERE MSSV LIKE ? OR HoTen LIKE ? OR QueQuan LIKE ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + keyword + "%");
            preparedStatement.setString(2, "%" + keyword + "%");
            preparedStatement.setString(3, "%" + keyword + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int mssv = resultSet.getInt("MSSV");
                String hoTen = resultSet.getString("HoTen");
                String queQuan = resultSet.getString("QueQuan");
                LocalDate nttns = resultSet.getDate("NTNS").toLocalDate();
                String cccd = resultSet.getString("CCCD");
                LocalDate ngayTotNghiep = resultSet.getDate("NgayTotNghiepCap3").toLocalDate();
                LocalDate ngayNhapHoc = resultSet.getDate("NgayNhapHoc").toLocalDate();
                String ctdt = resultSet.getString("ChuongTrinhDaoTao");
                String tknh = resultSet.getString("TaiKhoanNganHang");
                String mucCanhCao = resultSet.getString("MucCanhCao");
                String msl = resultSet.getString("MSL");
                String mstk = resultSet.getString("MSTK");

                SinhVien sinhVien = new SinhVien(mssv, hoTen, queQuan, nttns, cccd, ngayTotNghiep, ngayNhapHoc, ctdt, tknh, mucCanhCao, msl, mstk);
                tableView.getItems().add(sinhVien);
            }

            preparedStatement.close();
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Lỗi khi tìm kiếm!", "Vui lòng kiểm tra lại thông tin và thử lại.");
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
        mssvField.clear();
        hoTenField.clear();
        queQuanField.clear();
        nttnsField.clear();
        cccdField.clear();
        ngayTotNghiepField.clear();
        ngayNhapHocField.clear();
        ctdtField.clear();
        tknhField.clear();
        mucCanhCaoField.clear();
        mslField.clear();
        mstkField.clear();
    }

    private Optional<ButtonType> showAlert(Alert.AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        return alert.showAndWait();
    }
}
