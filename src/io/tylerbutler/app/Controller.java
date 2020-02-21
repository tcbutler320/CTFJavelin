package io.tylerbutler.app;

import io.tylerbutler.tools.TerminalNavigation;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


public class Controller {

    @FXML
    void blank(MouseEvent event) {
        TerminalNavigation tn = new TerminalNavigation();
        tn.getLs();
    }

    @FXML
    private TextField display;

    @FXML
    void clickLS(MouseEvent event) {
        TerminalNavigation tn = new TerminalNavigation();
        String test = tn.getLs();
        Platform.runLater(() -> {
            display.setText(display.getText() + "log n°2 ...");
        });
    }
}
