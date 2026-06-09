package com.oose.labsafety.fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public final class LabSafetyFxApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(LabSafetyFxApplication.class.getResource("/com/oose/labsafety/fx/main.fxml"));
        Scene scene = new Scene(loader.load(), 1100, 720);
        stage.setTitle("연구실 안전관리 시스템");
        stage.setScene(scene);
        stage.setMinWidth(980);
        stage.setMinHeight(640);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
