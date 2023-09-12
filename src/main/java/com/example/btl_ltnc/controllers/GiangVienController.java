package com.example.btl_ltnc.controllers;

import com.example.btl_ltnc.MainApp;
import com.example.btl_ltnc.database.DBConnection;
import com.example.btl_ltnc.models.GiangVien;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.*;
import java.time.LocalDate;
import java.util.Optional;

public class GiangVienController {
    @FXML
    private TableView<GiangVien> tableView;
    @FXML
    private TableColumn<GiangVien, Integer> MSGVColumn;
    @FXML
    private TableColumn<GiangVien, String> hoTenColumn;
    @FXML
    private TableColumn<GiangVien, String> queQuanColumn;
    @FXML
    private TableColumn<GiangVien, LocalDate> NTNSColumn;
    @FXML
    private TableColumn<GiangVien, String> CCCDColumn;
    @FXML
    private TableColumn<GiangVien, String> khoaGiangDayColumn;
    @FXML
    private TableColumn<GiangVien, LocalDate> ngayVaoTruongColumn;
    @FXML
    private TableColumn<GiangVien, String> chucVuColumn;
    @FXML
    private TableColumn<GiangVien, String> chucDanhColumn;
    @FXML
    private TableColumn<GiangVien, String> maLopHocColumn;
    @FXML
    private TableColumn<GiangVien, String> MSTKColumn;

    @FXML
    private TextField MSGVField;
    @FXML
    private TextField hoTenField;
    @FXML
    private TextField queQuanField;
    @FXML
    private TextField NTNSField;
    @FXML
    private TextField CCCDField;
    @FXML
    private TextField khoaGiangDayField;
    @FXML
    private TextField ngayVaoTruongField;
    @FXML
    private TextField chucVuField;
    @FXML
    private TextField chucDanhField;
    @FXML
    private TextField maLopHocField;
    @FXML
    private TextField MSTKField;
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
    private TextField searchField;
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
        MSGVColumn.setCellValueFactory(new PropertyValueFactory<>("MSGV"));
        hoTenColumn.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
        queQuanColumn.setCellValueFactory(new PropertyValueFactory<>("queQuan"));
        NTNSColumn.setCellValueFactory(new PropertyValueFactory<>("NTNS"));
        CCCDColumn.setCellValueFactory(new PropertyValueFactory<>("CCCD"));
        khoaGiangDayColumn.setCellValueFactory(new PropertyValueFactory<>("khoaGiangDay"));
        ngayVaoTruongColumn.setCellValueFactory(new PropertyValueFactory<>("ngayVaoTruong"));
        chucVuColumn.setCellValueFactory(new PropertyValueFactory<>("chucVu"));
        chucDanhColumn.setCellValueFactory(new PropertyValueFactory<>("chucDanh"));
        maLopHocColumn.setCellValueFactory(new PropertyValueFactory<>("maLopHoc"));
        MSTKColumn.setCellValueFactory(new PropertyValueFactory<>("MSTK"));

        // Bắt sự kiện khi người dùng chọn một hàng trong TableView để hiển thị dữ liệu trong các trường nhập
        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                MSGVField.setText(Integer.toString(newValue.getMSGV()));
                hoTenField.setText(newValue.getHoTen());
                queQuanField.setText(newValue.getQueQuan());
                NTNSField.setText(newValue.getNTNS().toString());
                CCCDField.setText(newValue.getCCCD());
                khoaGiangDayField.setText(newValue.getKhoaGiangDay());
                ngayVaoTruongField.setText(newValue.getNgayVaoTruong().toString());
                chucVuField.setText(newValue.getChucVu());
                chucDanhField.setText(newValue.getChucDanh());
                maLopHocField.setText(newValue.getMaLopHoc());
                MSTKField.setText(newValue.getMSTK());
            }
        });
    }

    private void loadData() {
        try {
            tableView.getItems().clear();
            String query = "SELECT * FROM GiangVien";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int MSGV = resultSet.getInt("MSGV");
                String hoTen = resultSet.getString("HoTen");
                String queQuan = resultSet.getString("QueQuan");
                LocalDate NTNS = resultSet.getDate("NTNS").toLocalDate();
                String CCCD = resultSet.getString("CCCD");
                String khoaGiangDay = resultSet.getString("KhoaGiangDay");
                LocalDate ngayVaoTruong = resultSet.getDate("NgayVaoTruong").toLocalDate();
                String chucVu = resultSet.getString("ChucVu");
                String chucDanh = resultSet.getString("ChucDanh");
                String maLopHoc = resultSet.getString("MaLopHoc");
                String MSTK = resultSet.getString("MSTK");

                GiangVien giangVien = new GiangVien(MSGV, hoTen, queQuan, NTNS, CCCD, khoaGiangDay, ngayVaoTruong, chucVu, chucDanh, maLopHoc, MSTK);
                tableView.getItems().add(giangVien);
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAddButton() {
        String MSGVStr = MSGVField.getText();
        String hoTen = hoTenField.getText();
        String queQuan = queQuanField.getText();
        String NTNSStr = NTNSField.getText();
        String CCCD = CCCDField.getText();
        String khoaGiangDay = khoaGiangDayField.getText();
        String ngayVaoTruongStr = ngayVaoTruongField.getText();
        String chucVu = chucVuField.getText();
        String chucDanh = chucDanhField.getText();
        String maLopHoc = maLopHocField.getText();
        String MSTK = MSTKField.getText();

        try {
            int MSGV = Integer.parseInt(MSGVStr);
            LocalDate NTNS = LocalDate.parse(NTNSStr);
            LocalDate ngayVaoTruong = LocalDate.parse(ngayVaoTruongStr);

            GiangVien giangVien = new GiangVien(MSGV, hoTen, queQuan, NTNS, CCCD, khoaGiangDay, ngayVaoTruong, chucVu, chucDanh, maLopHoc, MSTK);

            // Thêm vào cơ sở dữ liệu
            insertGiangVien(giangVien);

            // Thêm vào TableView
            tableView.getItems().add(giangVien);

            // Xóa dữ liệu trong các trường nhập
            clearFields();
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Dữ liệu không hợp lệ", "Vui lòng nhập số vào trường MSGV.");
        } catch (java.time.format.DateTimeParseException e) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Dữ liệu không hợp lệ", "Vui lòng nhập ngày tháng trong định dạng yyyy-MM-dd.");
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể thêm dữ liệu", "Đã xảy ra lỗi khi thêm Giảng viên mới.");
        }
    }

    private void insertGiangVien(GiangVien giangVien) throws SQLException {
        String query = "INSERT INTO GiangVien (MSGV, HoTen, QueQuan, NTNS, CCCD, KhoaGiangDay, NgayVaoTruong, ChucVu, ChucDanh, MaLopHoc, MSTK) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, giangVien.getMSGV());
        preparedStatement.setString(2, giangVien.getHoTen());
        preparedStatement.setString(3, giangVien.getQueQuan());
        preparedStatement.setDate(4, Date.valueOf(giangVien.getNTNS()));
        preparedStatement.setString(5, giangVien.getCCCD());
        preparedStatement.setString(6, giangVien.getKhoaGiangDay());
        preparedStatement.setDate(7, Date.valueOf(giangVien.getNgayVaoTruong()));
        preparedStatement.setString(8, giangVien.getChucVu());
        preparedStatement.setString(9, giangVien.getChucDanh());
        preparedStatement.setString(10, giangVien.getMaLopHoc());
        preparedStatement.setString(11, giangVien.getMSTK());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    @FXML
    private void handleUpdateButton() {
        String MSGVStr = MSGVField.getText();
        String hoTen = hoTenField.getText();
        String queQuan = queQuanField.getText();
        String NTNSStr = NTNSField.getText();
        String CCCD = CCCDField.getText();
        String khoaGiangDay = khoaGiangDayField.getText();
        String ngayVaoTruongStr = ngayVaoTruongField.getText();
        String chucVu = chucVuField.getText();
        String chucDanh = chucDanhField.getText();
        String maLopHoc = maLopHocField.getText();
        String MSTK = MSTKField.getText();

        try {
            int MSGV = Integer.parseInt(MSGVStr);
            LocalDate NTNS = LocalDate.parse(NTNSStr);
            LocalDate ngayVaoTruong = LocalDate.parse(ngayVaoTruongStr);

            GiangVien giangVien = new GiangVien(MSGV, hoTen, queQuan, NTNS, CCCD, khoaGiangDay, ngayVaoTruong, chucVu, chucDanh, maLopHoc, MSTK);

            // Cập nhật vào cơ sở dữ liệu
            updateGiangVien(giangVien);

            // Sau khi cập nhật thành công, gọi lại loadData() để cập nhật TableView
            loadData();

            // Xóa dữ liệu trong các trường nhập
            clearFields();
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Dữ liệu không hợp lệ", "Vui lòng nhập số vào trường MSGV.");
        } catch (java.time.format.DateTimeParseException e) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Dữ liệu không hợp lệ", "Vui lòng nhập ngày tháng trong định dạng yyyy-MM-dd.");
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể cập nhật dữ liệu", "Đã xảy ra lỗi khi cập nhật Giảng viên.");
        }
    }

    private void updateGiangVien(GiangVien giangVien) throws SQLException {
        String query = "UPDATE GiangVien SET HoTen=?, QueQuan=?, NTNS=?, CCCD=?, KhoaGiangDay=?, NgayVaoTruong=?, ChucVu=?, ChucDanh=?, MaLopHoc=?, MSTK=? WHERE MSGV=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, giangVien.getHoTen());
        preparedStatement.setString(2, giangVien.getQueQuan());
        preparedStatement.setDate(3, Date.valueOf(giangVien.getNTNS()));
        preparedStatement.setString(4, giangVien.getCCCD());
        preparedStatement.setString(5, giangVien.getKhoaGiangDay());
        preparedStatement.setDate(6, Date.valueOf(giangVien.getNgayVaoTruong()));
        preparedStatement.setString(7, giangVien.getChucVu());
        preparedStatement.setString(8, giangVien.getChucDanh());
        preparedStatement.setString(9, giangVien.getMaLopHoc());
        preparedStatement.setString(10, giangVien.getMSTK());
        preparedStatement.setInt(11, giangVien.getMSGV());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @FXML
    private void handleDeleteButton() {
        String MSGVStr = MSGVField.getText();

        try {
            int MSGV = Integer.parseInt(MSGVStr);

            // Xác nhận xóa
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Xác nhận xóa");
            alert.setHeaderText(null);
            alert.setContentText("Bạn có chắc muốn xóa Giảng viên này?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Xóa khỏi cơ sở dữ liệu
                deleteGiangVien(MSGV);

                // Sau khi xóa thành công, gọi lại loadData() để cập nhật TableView
                loadData();

                // Xóa dữ liệu trong các trường nhập
                clearFields();
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Dữ liệu không hợp lệ", "Vui lòng nhập số vào trường MSGV.");
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể xóa dữ liệu", "Đã xảy ra lỗi khi xóa Giảng viên.");
        }
    }

    private void deleteGiangVien(int MSGV) throws SQLException {
        String query = "DELETE FROM GiangVien WHERE MSGV=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, MSGV);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @FXML
    private void handleSearchButton() {
        String keyword = searchField.getText().trim();

        // Xóa dữ liệu hiện tại trong TableView
        tableView.getItems().clear();

        try {
            String query = "SELECT * FROM GiangVien WHERE HoTen LIKE ? OR QueQuan LIKE ? OR MSTK LIKE ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + keyword + "%");
            preparedStatement.setString(2, "%" + keyword + "%");
            preparedStatement.setString(3, "%" + keyword + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int MSGV = resultSet.getInt("MSGV");
                String hoTen = resultSet.getString("HoTen");
                String queQuan = resultSet.getString("QueQuan");
                LocalDate NTNS = resultSet.getDate("NTNS").toLocalDate();
                String CCCD = resultSet.getString("CCCD");
                String khoaGiangDay = resultSet.getString("KhoaGiangDay");
                LocalDate ngayVaoTruong = resultSet.getDate("NgayVaoTruong").toLocalDate();
                String chucVu = resultSet.getString("ChucVu");
                String chucDanh = resultSet.getString("ChucDanh");
                String maLopHoc = resultSet.getString("MaLopHoc");
                String MSTK = resultSet.getString("MSTK");

                GiangVien giangVien = new GiangVien(MSGV, hoTen, queQuan, NTNS, CCCD, khoaGiangDay, ngayVaoTruong, chucVu, chucDanh, maLopHoc, MSTK);
                tableView.getItems().add(giangVien);
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
        MSGVField.clear();
        hoTenField.clear();
        queQuanField.clear();
        NTNSField.clear();
        CCCDField.clear();
        khoaGiangDayField.clear();
        ngayVaoTruongField.clear();
        chucVuField.clear();
        chucDanhField.clear();
        maLopHocField.clear();
        MSTKField.clear();
    }

    private void showAlert(Alert.AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    // Các phương thức tiện ích khác (updateGiangVien, deleteGiangVien, searchGiangVien) triển khai tương tự như trong HocPhanController.

    // ...
}
