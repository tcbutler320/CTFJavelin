package io.tylerbutler.app;

import io.tylerbutler.test.TargetSettingsTest;
import io.tylerbutler.tools.TerminalNavigation;
import io.tylerbutler.utils.CTFDocumentation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;


public class Controller {

    @FXML
    void blank(MouseEvent event) {
        TerminalNavigation tn = new TerminalNavigation();
        tn.getLs();
    }

    @FXML
    private MenuItem mBarNew;

    @FXML
    private TextArea targetTextField;

    @FXML
    private Button setTargetBtn;

    @FXML
    private TextArea cmdQueTArea;

    @FXML
    void createProject(ActionEvent event) {
        CTFDocumentation.newProject();
    }


    @FXML
    void setTarget(MouseEvent event) {
        cmdQueTArea.appendText("\n"+TargetSettingsTest.targetSettingsTest(targetTextField.getText()));
    }

}
