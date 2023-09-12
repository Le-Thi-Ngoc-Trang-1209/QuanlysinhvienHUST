package com.example.btl_ltnc.controllers;

import com.example.btl_ltnc.MainApp;
import com.example.btl_ltnc.database.DBConnection;
import com.example.btl_ltnc.models.HocPhan;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.*;
import java.util.Optional;

public class HocPhanController {
    @FXML
    private TableView<HocPhan> tableView;
    @FXML
    private TableColumn<HocPhan, Integer> maHocPhanColumn;
    @FXML
    private TableColumn<HocPhan, Integer> soTinChiColumn;
    @FXML
    private TableColumn<HocPhan, String> tenHocPhanColumn;
    @FXML
    private TableColumn<HocPhan, Integer> dinhMucHocPhiColumn;

    @FXML
    private TextField maHocPhanField;
    @FXML
    private TextField soTinChiField;
    @FXML
    private TextField tenHocPhanField;
    @FXML
    private TextField dinhMucHocPhiField;
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
        maHocPhanColumn.setCellValueFactory(new PropertyValueFactory<>("maHocPhan"));
        soTinChiColumn.setCellValueFactory(new PropertyValueFactory<>("soTinChi"));
        tenHocPhanColumn.setCellValueFactory(new PropertyValueFactory<>("tenHocPhan"));
        dinhMucHocPhiColumn.setCellValueFactory(new PropertyValueFactory<>("dinhMucHocPhi"));

        // Bắt sự kiện khi người dùng chọn một hàng trong TableView để hiển thị dữ liệu trong các trường nhập
        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                maHocPhanField.setText(Integer.toString(newValue.getMaHocPhan()));
                soTinChiField.setText(Integer.toString(newValue.getSoTinChi()));
                tenHocPhanField.setText(newValue.getTenHocPhan());
                dinhMucHocPhiField.setText(Integer.toString(newValue.getDinhMucHocPhi()));
            }
        });
    }

    private void loadData() throws SQLException {
        tableView.getItems().clear();
        String query = "SELECT * FROM HocPhan";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            int maHocPhan = resultSet.getInt("MaHocPhan");
            int soTinChi = resultSet.getInt("SoTinChi");
            String tenHocPhan = resultSet.getString("TenHocPhan");
            int dinhMucHocPhi = resultSet.getInt("DinhMucHocPhi");

            HocPhan hocPhan = new HocPhan(maHocPhan, soTinChi, tenHocPhan, dinhMucHocPhi);
            tableView.getItems().add(hocPhan);
        }

        statement.close();
    }

    @FXML
    private void handleAddButton() {
        String maHocPhanStr = maHocPhanField.getText();
        String soTinChiStr = soTinChiField.getText();
        String tenHocPhan = tenHocPhanField.getText();
        String dinhMucHocPhiStr = dinhMucHocPhiField.getText();

        try {
            int maHocPhan = Integer.parseInt(maHocPhanStr);
            int soTinChi = Integer.parseInt(soTinChiStr);
            int dinhMucHocPhi = Integer.parseInt(dinhMucHocPhiStr);

            HocPhan hocPhan = new HocPhan(maHocPhan, soTinChi, tenHocPhan, dinhMucHocPhi);

            // Thêm vào cơ sở dữ liệu
            insertHocPhan(hocPhan);

            // Thêm vào TableView
            tableView.getItems().add(hocPhan);

            // Xóa dữ liệu trong các trường nhập
            clearFields();
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Dữ liệu không hợp lệ", "Vui lòng nhập số vào các trường Mã Học Phần, Số Tín Chỉ, và Định Mức Học Phí.");
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể thêm dữ liệu", "Đã xảy ra lỗi khi thêm Học Phần mới.");
        }
    }

    private void insertHocPhan(HocPhan hocPhan) throws SQLException {
        String query = "INSERT INTO HocPhan (MaHocPhan, SoTinChi, TenHocPhan, DinhMucHocPhi) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, hocPhan.getMaHocPhan());
        preparedStatement.setInt(2, hocPhan.getSoTinChi());
        preparedStatement.setString(3, hocPhan.getTenHocPhan());
        preparedStatement.setInt(4, hocPhan.getDinhMucHocPhi());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    @FXML
    private void handleUpdateButton() {
        // Lấy dữ liệu từ các trường nhập
        String maHocPhanStr = maHocPhanField.getText();
        String soTinChiStr = soTinChiField.getText();
        String tenHocPhan = tenHocPhanField.getText();
        String dinhMucHocPhiStr = dinhMucHocPhiField.getText();

        try {
            int maHocPhan = Integer.parseInt(maHocPhanStr);
            int soTinChi = Integer.parseInt(soTinChiStr);
            int dinhMucHocPhi = Integer.parseInt(dinhMucHocPhiStr);

            HocPhan hocPhan = new HocPhan(maHocPhan, soTinChi, tenHocPhan, dinhMucHocPhi);

            // Cập nhật vào cơ sở dữ liệu
            updateHocPhan(hocPhan);

            // Sau khi cập nhật thành công, gọi lại loadData() để cập nhật TableView
            loadData();

            // Xóa dữ liệu trong các trường nhập
            clearFields();
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Dữ liệu không hợp lệ", "Vui lòng nhập số vào các trường Mã Học Phần, Số Tín Chỉ, và Định Mức Học Phí.");
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể cập nhật dữ liệu", "Đã xảy ra lỗi khi cập nhật Học Phần.");
        }
    }

    private void updateHocPhan(HocPhan hocPhan) throws SQLException {
        String query = "UPDATE HocPhan SET SoTinChi=?, TenHocPhan=?, DinhMucHocPhi=? WHERE MaHocPhan=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, hocPhan.getSoTinChi());
        preparedStatement.setString(2, hocPhan.getTenHocPhan());
        preparedStatement.setInt(3, hocPhan.getDinhMucHocPhi());
        preparedStatement.setInt(4, hocPhan.getMaHocPhan());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @FXML
    private void handleDeleteButton() {
        // Lấy dữ liệu từ trường nhập Mã Học Phần
        String maHocPhanStr = maHocPhanField.getText();

        try {
            int maHocPhan = Integer.parseInt(maHocPhanStr);

            // Xác nhận xóa
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Xác nhận xóa");
            alert.setHeaderText(null);
            alert.setContentText("Bạn có chắc muốn xóa Học Phần này?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Xóa khỏi cơ sở dữ liệu
                deleteHocPhan(maHocPhan);

                // Sau khi xóa thành công, gọi lại loadData() để cập nhật TableView
                loadData();

                // Xóa dữ liệu trong các trường nhập
                clearFields();
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Dữ liệu không hợp lệ", "Vui lòng nhập số vào trường Mã Học Phần.");
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể xóa dữ liệu", "Đã xảy ra lỗi khi xóa Học Phần.");
        }
    }
    @FXML
    private void handleSearchButton() {
        // Lấy từ khóa tìm kiếm từ trường nhập
        String keyword = searchField.getText().trim();

        // Xóa dữ liệu hiện tại trong TableView
        tableView.getItems().clear();

        try {
            String query = "SELECT * FROM HocPhan WHERE MaHocPhan LIKE ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + keyword + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int maHocPhan = resultSet.getInt("MaHocPhan");
                int soTinChi = resultSet.getInt("SoTinChi");
                String tenHocPhan = resultSet.getString("TenHocPhan");
                int dinhMucHocPhi = resultSet.getInt("DinhMucHocPhi");

                HocPhan hocPhan = new HocPhan(maHocPhan, soTinChi, tenHocPhan, dinhMucHocPhi);
                tableView.getItems().add(hocPhan);
            }

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteHocPhan(int maHocPhan) throws SQLException {
        String query = "DELETE FROM HocPhan WHERE MaHocPhan=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, maHocPhan);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    @FXML
    private void handleLoadAllButton() throws SQLException {
        // Tải lại tất cả HocPhan từ cơ sở dữ liệu và cập nhật TableView
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
        maHocPhanField.clear();
        soTinChiField.clear();
        tenHocPhanField.clear();
        dinhMucHocPhiField.clear();
    }

    private void showAlert(Alert.AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
