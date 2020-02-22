package io.tylerbutler.app;

import io.tylerbutler.test.TargetSettingsTest;
import io.tylerbutler.tools.TerminalNavigation;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


public class Controller {

    @FXML
    void blank(MouseEvent event) {
        TerminalNavigation tn = new TerminalNavigation();
        tn.getLs();
    }

    @FXML
    private Button setTargetBtn;

    @FXML
    private TextArea cmdQueTArea;

    @FXML
    void setTarget(MouseEvent event) {
        cmdQueTArea.setText(TargetSettingsTest.targetSettingsTest());
    }

    // TODO remove display method with working method
    @FXML
    void clickLS(MouseEvent event) {
        TerminalNavigation tn = new TerminalNavigation();
        String test = tn.getLs();
        Platform.runLater(() -> {
//            display.setText(display.getText() + "log n°2 ...");
        });
    }
}
