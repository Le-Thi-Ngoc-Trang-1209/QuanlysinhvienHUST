//package com.example.btl_ltnc;
//
//import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.layout.AnchorPane;
//import javafx.stage.Stage;
//
//import java.io.IOException;
//
//public class MainApp extends Application {
//
//    private AnchorPane rootLayout;
//
//    @Override
//    public void start(Stage primaryStage) {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainApp.fxml"));
//            rootLayout = loader.load();
//
//            // Gán controller cho MainApp.fxml
//            MainApp controller = loader.getController();
//            controller.setMainApp(this);
//
//            Scene scene = new Scene(rootLayout);
//            primaryStage.setScene(scene);
//            primaryStage.setTitle("Quản lý Chức Năng");
//            primaryStage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // Các phương thức mở giao diện chức năng tương ứng
//    public void openHocPhanView() {
//        loadView("HocPhanView.fxml");
//    }
//
//    public void openNopHocPhiView() {
//        loadView("NopHocPhiView.fxml");
//    }
//
//    public void openGiangVienView() {
//        loadView("GiangVienView.fxml");
//    }
//    public void openSinhVienView() {
//        loadView("SinhVienView.fxml");
//    }
//    public void openKyHocView() {
//        loadView("KyHocView.fxml");
//    }
//    public void openChuongTrinhDaoTaoView() {
//        loadView("ChuongTrinhDaoTaoView.fxml");
//    }
//    public void openLopView() {
//        loadView("LopView.fxml");
//    }
//    public void openLopHocPhanView() {
//        loadView("LopHocPhanView.fxml");
//    }
//    public void openTaiKhoanView() {
//        loadView("TaiKhoanView.fxml");
//    }
//    public void openKhoaVienView() {
//        loadView("KhoaVienView.fxml");
//    }
//    public void openPhongHocView() {
//        loadView("PhongHocView.fxml");
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//    private void loadView(String viewFileName) {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource(viewFileName));
//            Parent view = loader.load();
//            rootLayout.getChildren().setAll(view);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    public void setMainApp(MainApp mainApp) {
//        this.rootLayout = mainApp.rootLayout;
//    }
//
//}
//
