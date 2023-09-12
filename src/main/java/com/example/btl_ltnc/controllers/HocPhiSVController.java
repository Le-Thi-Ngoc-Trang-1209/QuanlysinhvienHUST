package com.example.btl_ltnc.controllers;

import com.example.btl_ltnc.MainApp;
import com.example.btl_ltnc.controllers.LoginController;
import com.example.btl_ltnc.database.DBConnection;
import com.example.btl_ltnc.models.NopHocPhi;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.*;
import java.time.LocalDate;
import java.util.Optional;

public class HocPhiSVController {

    private LoginController log;

    private int a;

    @FXML
    private Button btnHPBackToMenu;
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
    private TextField tongTienFieldDN;

    //    @FXML
//    private TextField maHocPhiField;
//    @FXML
//    private TextField maSinhVienField;
//    @FXML
//    private TextField mskhField;
//    @FXML
//    private TextField tongTienField;
//    @FXML
//    private TextField ngayNopField;
//    @FXML
//    private TextField daNopChuaField;
    @FXML
    private Button updateTT;
    @FXML
    private Button searchButton;
    @FXML
    private Button loadAllButton;
    @FXML
    private TextField searchField;
    private MainApp mainApp;
    private int sv;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    private Connection connection;

    public void initialize() {
        try {
            connection = DBConnection.getConnection();
            System.out.println(sv);
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

//        // Bắt sự kiện khi người dùng chọn một hàng trong TableView để hiển thị dữ liệu trong các trường nhập
//        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
//            if (newValue != null) {
//                maHocPhiField.setText(Integer.toString(newValue.getMaHocPhi()));
//                maSinhVienField.setText(Integer.toString(newValue.getMaSinhVien()));
//                mskhField.setText(Integer.toString(newValue.getMskh()));
//                tongTienField.setText(Integer.toString(newValue.getTongTien()));
//                ngayNopField.setText(newValue.getNgayNop().toString());
//                daNopChuaField.setText(newValue.getDaNopChua());
//            }
//        });
    }

    private void loadData() {
        try {
            tableView.getItems().clear();
            String query = "SELECT * FROM NopHocPhi";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
//            String query = "SELECT * FROM NopHocPhi WHERE MaSinhVien = ?";
//            PreparedStatement statement = connection.prepareStatement(query);
//            statement.setInt(1, sv);
//            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int maSinhVien = resultSet.getInt("MaSinhVien");
                //System.out.println(maSinhVien);
                //System.out.println(sv);
                if (maSinhVien == 20101) {
                    int maHocPhi = resultSet.getInt("MaHocPhi");
                    int mskh = resultSet.getInt("MSKH");
                    int tongTien = resultSet.getInt("TongTien");
                    LocalDate ngayNop = resultSet.getDate("NgayNop").toLocalDate();
                    String daNopChua = resultSet.getString("DaNopChua");
                    NopHocPhi nopHocPhi = new NopHocPhi(maHocPhi, maSinhVien, mskh, tongTien, ngayNop, daNopChua);
                    tableView.getItems().add(nopHocPhi);
                } else {
                    System.out.println("Chưa có dữ liệu");
                }
            }
            tongTienFieldDN.setText(Integer.toString(0));
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
                int maSinhVien = resultSet.getInt("MaSinhVien");
                System.out.println(maSinhVien);
                System.out.println(sv);
                if (maSinhVien == 20101) {
                    int maHocPhi = resultSet.getInt("MaHocPhi");
                    int mskh = resultSet.getInt("MSKH");
                    int tongTien = resultSet.getInt("TongTien");
                    a += tongTien;
                    LocalDate ngayNop = resultSet.getDate("NgayNop").toLocalDate();
                    String daNopChua = resultSet.getString("DaNopChua");

                    NopHocPhi nopHocPhi = new NopHocPhi(maHocPhi, maSinhVien, mskh, tongTien, ngayNop, daNopChua);
                    tableView.getItems().add(nopHocPhi);
                }
            }
            tongTienFieldDN.setText(Integer.toString(a));
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
    private void backToMenu() throws SQLException {
        MainApp mainApp = new MainApp();
        mainApp.start4(new Stage());
        Stage stage = (Stage) btnHPBackToMenu.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleUpdateButton() {
        // Xác nhận thanh toán
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xác nhận thanh toán");
        alert.setHeaderText(null);
        alert.setContentText("Thanh toán tất cả học phí?");
        Optional<ButtonType> result = alert.showAndWait();
        try {
            tableView.getItems().clear();
            String query = "SELECT * FROM NopHocPhi";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int maSinhVien = resultSet.getInt("MaSinhVien");
                if (maSinhVien == 20101) {
                    int maHocPhi = resultSet.getInt("MaHocPhi");
                    int mskh = resultSet.getInt("MSKH");
                    int tongTien = resultSet.getInt("TongTien");
                    LocalDate ngayNop = resultSet.getDate("NgayNop").toLocalDate();
                    String daNopChua = "Đã nộp";
                    NopHocPhi nopHocPhi = new NopHocPhi(maHocPhi, maSinhVien, mskh, tongTien, ngayNop, daNopChua);
                    // Cập nhật vào cơ sở dữ liệu
                    updateHocPhiSV(nopHocPhi);
                    tableView.getItems().add(nopHocPhi);
                } else {
                    System.out.println("Chưa có dữ liệu");
                }
            }
            tongTienFieldDN.setText(Integer.toString(0));
            statement.close();
            showAlert(Alert.AlertType.INFORMATION, "Thông báo", "Thanh toán thành công", "Bạn đã thanh toán hết học phí.");
            } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Dữ liệu không hợp lệ", "Không kết nối được csdl.");
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể thanh toán", "Đã xảy ra lỗi khi thanh toán.");
        }
    }

    private void updateHocPhiSV(NopHocPhi nopHocPhi) throws SQLException {
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

    private void showAlert(Alert.AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
