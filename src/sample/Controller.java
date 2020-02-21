package sample;

import io.tylerbutler.tools.TerminalNavigation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;


public class Controller {

    @FXML
    void clickLS(MouseEvent event) {
        TerminalNavigation tn = new TerminalNavigation();
        tn.getLs();
    }
}
