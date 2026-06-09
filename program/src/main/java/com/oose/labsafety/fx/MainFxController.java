package com.oose.labsafety.fx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public final class MainFxController {

    @FXML
    private StackPane contentPane;

    @FXML
    private Label statusLabel;

    @FXML
    private void initialize() {
        showHome();
    }

    @FXML
    private void showHome() {
        Label label = new Label("왼쪽 메뉴에서 안전교육 관리를 선택하세요.");
        label.getStyleClass().add("empty-message");
        contentPane.getChildren().setAll(label);
        statusLabel.setText("대기 중");
    }

    @FXML
    private void showEducation() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/oose/labsafety/fx/education.fxml"));
            Parent view = loader.load();
            contentPane.getChildren().setAll(view);
            statusLabel.setText("안전교육 관리");
        } catch (IOException exception) {
            statusLabel.setText("안전교육 화면을 불러오지 못했습니다.");
        }
    }
}
