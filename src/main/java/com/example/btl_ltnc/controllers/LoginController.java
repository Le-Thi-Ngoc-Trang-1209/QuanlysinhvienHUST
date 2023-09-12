package com.example.btl_ltnc.controllers;

import com.example.btl_ltnc.MainApp;
import com.example.btl_ltnc.database.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Establish database connection
        try {
            Connection connection = DBConnection.getConnection();
            String query = "SELECT * FROM TaiKhoan WHERE Username = ? AND Password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String phanQuyen = resultSet.getString("PhanQuyen");
                // Check user's role and open appropriate view
                if ("SV".equals(phanQuyen)) {
                    //mainApp.SinhVienView();
                    String MSTK = resultSet.getString("MSTK");
                    //System.out.println(MSTK);
                    SinhVienConnection svc = new SinhVienConnection(MSTK);
                    int a = svc.SinhvienConnection();
                    //System.out.println(a);
                    mainApp.showSVView();
//                    try{
//                        Connection connection1 = DBConnection.getConnection();
//                        String query1 = "SELECT * FROM SinhVien WHERE MSTK = ?";
//                        PreparedStatement statement = connection1.prepareStatement(query1);
//                        statement.setString(1, MSTK);
//                        ResultSet resultset = statement.executeQuery();
//                        if (resultset.next()) {
//                            Integer MSSV = resultset.getInt("MSSV");
//                            System.out.println(MSSV);
//                            mssv = MSSV;
//                            mainApp.showSVView();
//                        }
//                        connection1.close();
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                        showAlert("Lỗi", "Không thể kết nối đến cơ sở dữ liệu.");}
                } else if ("Admin".equals(phanQuyen)) {
                    mainApp.AdminView();
                } else if ("GV".equals(phanQuyen)) {
                    //mainApp.GiangVienView();
                    mainApp.showGVView();
                }
            } else {
                // Show an error message for invalid login
                showAlert("Đăng nhập không thành công", "Tên đăng nhập hoặc mật khẩu không đúng.");
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Lỗi", "Không thể kết nối đến cơ sở dữ liệu.");
        }
    }


    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
