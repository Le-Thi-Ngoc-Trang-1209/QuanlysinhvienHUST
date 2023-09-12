module com.example.btl_ltnc {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;
    requires java.naming;
//
//    requires org.controlsfx.controls;
//    requires com.dlsc.formsfx;
//    requires org.kordamp.bootstrapfx.core;

    exports com.example.btl_ltnc.controllers;
    opens com.example.btl_ltnc.controllers to javafx.fxml;
    exports com.example.btl_ltnc.controllers.BM;
    opens com.example.btl_ltnc.controllers.BM to javafx.fxml;
    opens com.example.btl_ltnc to javafx.fxml;
    exports com.example.btl_ltnc;


    // Mở gói com.example.btl_ltnc.models cho module javafx.base
    opens com.example.btl_ltnc.models to javafx.base;

}