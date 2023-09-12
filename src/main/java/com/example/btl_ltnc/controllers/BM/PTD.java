package com.example.btl_ltnc.controllers.BM;

import com.example.btl_ltnc.MainApp;
import com.example.btl_ltnc.database.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class PTD {

    @FXML
    private Button btnBMBackToMenu;

    @FXML
    private Button btnsave0;

    @FXML
    private TextField htCCCD;

    @FXML
    private TextField htCTDT;

    @FXML
    private TextField htMSSV;

    @FXML
    private TextField htNS;

    @FXML
    private TextField htQQ;

    @FXML
    private TextField htTSV;


    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {this.mainApp = mainApp;}
    @FXML
    private void backToMenu() throws SQLException {
        mainApp.openBieuMauView();
    }

    @FXML
    private void hienthi() {
        try {
            int MSSV = 20101;
            Connection connection1 = DBConnection.getConnection();
            String query1 = "SELECT * FROM SinhVien WHERE MSSV = ?";
            PreparedStatement statement = connection1.prepareStatement(query1);
            statement.setInt(1, MSSV);
            ResultSet resultset = statement.executeQuery();
            if (resultset.next()) {
                String TenSV = resultset.getString("HoTen");
                String ms = String.valueOf(resultset.getInt("MSSV"));
                String chuongTrinhDaoTao = resultset.getString("ChuongTrinhDaoTao");
                String ngaysinh = String.valueOf(resultset.getDate("NTNS"));
                String ngaynhaphoc = String.valueOf(resultset.getDate("NgayNhapHoc"));
                String muccanhcao = resultset.getString("MucCanhCao");
                String quequan = resultset.getString("QueQuan");
                String cccd = resultset.getString("CCCD");
                htTSV.setText(TenSV);
                htMSSV.setText(ms);
                htCTDT.setText(chuongTrinhDaoTao);
                htNS.setText(ngaysinh);
                // htNNH.setText(ngaynhaphoc);
                // htMCC.setText(muccanhcao);
                htQQ.setText(quequan);
                htCCCD.setText(cccd);
            } else {
                System.out.println("Loi truy nhap");
            }
            statement.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
            System.out.println("Loi");
        }
    }


    @FXML
    private void getSave0(ActionEvent event) {
        Optional<ButtonType> result = showAlert(Alert.AlertType.CONFIRMATION, "Xác nhận gửi", "Bạn có chắc chắn muốn gửi?",
                "Xem xét thông tin trước khi gửi.");
        if (result.isPresent() && result.get() == ButtonType.OK) {
            showAlert(Alert.AlertType.INFORMATION, "Thông báo", "Xóa lớp thành công", "Đã gửi thành công. Hãy chờ thông báo.");
        } else {
            showAlert(Alert.AlertType.INFORMATION, "CANCEL", "Bản nháp bị huỷ.", "Đã huỷ thành công.");
        }
    }

    private Optional<ButtonType> showAlert(Alert.AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        return alert.showAndWait();
    }


}
