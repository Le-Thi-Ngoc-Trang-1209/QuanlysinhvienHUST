package com.example.btl_ltnc;

import com.example.btl_ltnc.controllers.*;
import com.example.btl_ltnc.controllers.BM.*;
import com.example.btl_ltnc.database.DBConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainApp extends Application {
    private Stage primaryStage;
    private int mssv;
    public int getmssv(){
        return this.mssv;
    }
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        showLoginView();
        //AdminView();
//        showGVView();

    }
    public void start2(Stage primaryStage) {
        this.primaryStage = primaryStage;
        AdminView();
    }
    public void start3(Stage primaryStage) {
        this.primaryStage = primaryStage;
        GVView();
    }

    public void showLoginView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginView.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Đăng nhập");
            primaryStage.show();

            // Gán controller cho LoginView.fxml
            LoginController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showMainMenuView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainApp.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Quản lý đào tạo HUST");
            primaryStage.show();

            // Gán controller cho MainMenuView.fxml
            MainMenuViewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void showGVView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GVView.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Quản lý Chức Năng");
            primaryStage.show();

            // Gán controller cho MainMenuView.fxml
            GVViewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    public void showSVView(String MSTK) throws SQLException {
//        String mstk = MSTK;
//        //System.out.println(mstk);
//        try {
//            Connection connection = DBConnection.getConnection();
//            String query = "SELECT * FROM SinhVien WHERE MSTK = ?";
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setString(1, mstk);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            //System.out.println("1");
//            if (resultSet.next()) {
//                Integer MSSV = resultSet.getInt("MSSV");
//                System.out.println(MSSV);
//                mssv = MSSV;
//                SVView(mssv);
//            } else{
//                System.out.println("Không có dữ liệu???");
//            }
//            connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public void showSVView() throws SQLException {
        try {
            Connection connection = DBConnection.getConnection();
            SVView();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void SVView() throws SQLException{
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SVView.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Quản lý Chức Năng của Sinh viên");
            primaryStage.show();

            // Gán controller cho MainMenuView.fxml
            SVViewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();}
    }
//    public void SVView() throws SQLException{
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("SVView.fxml"));
//            Parent root = loader.load();
//            Scene scene = new Scene(root);
//            primaryStage.setScene(scene);
//            primaryStage.setTitle("Quản lý Chức Năng của Sinh viên");
//            primaryStage.show();
//
//            // Gán controller cho MainMenuView.fxml
//            SVViewController controller = loader.getController();
//            controller.setMainApp(this);
//        } catch (IOException e) {
//            e.printStackTrace();}
//    }

    public void openBieuMauView(){try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BieuMauView.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Quản lý Chức Năng của Sinh viên");
        primaryStage.show();

        // Gán controller cho MainMenuView.fxml
        BieuMauViewController controller = loader.getController();
        controller.setMainApp(this);
    } catch (IOException e) {
        e.printStackTrace();}}


    public void openBMCNSV(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("BM_chungnhansinhvien.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Quản lý Chức Năng của Sinh viên");
            primaryStage.show();

            // Gán controller cho MainMenuView.fxml
            CNSV controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();}
    }

    public void openBMdangkyvao(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("BM_dangkyvao.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Quản lý Chức Năng của Sinh viên");
            primaryStage.show();

            // Gán controller cho MainMenuView.fxml
            DKVL controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();}}
    public void openBMgioithieu(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("BM_gioithieu.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Quản lý Chức Năng của Sinh viên");
            primaryStage.show();

            // Gán controller cho MainMenuView.fxml
            GGT controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();}}
    public void openBMinbangdiem(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("BM_inbangdiem.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Quản lý Chức Năng của Sinh viên");
            primaryStage.show();

            // Gán controller cho MainMenuView.fxml
            BD controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();}}
    public void openBMphuctra(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("BM_phuctra.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Quản lý Chức Năng của Sinh viên");
            primaryStage.show();

            // Gán controller cho MainMenuView.fxml
            PTD controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();}}
    public void openBMxinmolop(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("BM_xinmolop.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Quản lý Chức Năng của Sinh viên");
            primaryStage.show();

            // Gán controller cho MainMenuView.fxml
            ML controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();}}
    public void start4(Stage primaryStage) throws SQLException {
        this.primaryStage = primaryStage;
        SVView();
    }
    public  void AdminView() {
        showMainMenuView();
    }
    public  void GVView() {
        showGVView();
    }

    // Các phương thức mở giao diện chức năng tương ứng
    public void openHocPhanView() {
        loadView("HocPhanView.fxml");
    }

    public void openNopHocPhiView() {
        loadView("NopHocPhiView.fxml");
    }

    public void openGiangVienView() {
        loadView("GiangVienView.fxml");
    }
    public void openSinhVienView() {
        loadView("SinhVienView.fxml");
    }
    public void openKyHocView() {
        loadView("KyHocView.fxml");
    }
    public void openChuongTrinhDaoTaoView() {
        loadView("ChuongTrinhDaoTaoView.fxml");
    }
    public void openLopView() {
        loadView("LopView.fxml");
    }
    public void openLopHocPhanView() {
        loadView("LopHocPhanView.fxml");
    }
    public void openTaiKhoanView() {
        loadView("TaiKhoanView.fxml");
    }
    public void openKhoaVienView() {
        loadView("KhoaVienView.fxml");
    }
    public void openPhongHocView() {
        loadView("PhongHocView.fxml");
    }

    // Giao diện sinh viên
    public void openDoAnView() {
        loadView("DoAnView.fxml");
    }

    public void openDKLSV(){loadView("DangKyLopView.fxml");}
    public void openHPSV(){loadView("HocPhiSVView.fxml");}
    public void openQuanlyHPSV(){loadView("QuanLyHPSVView.fxml");}

    public void openTKB(){loadView("TKBView.fxml");}


    private void loadView(String viewFileName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(viewFileName));
            Parent view = loader.load();
            Scene scene = new Scene(view);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
