package de.die_beckerei.keditor.app.editor.dialog;


import de.die_beckerei.keditor.app.editor.UiHelper;
import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 *
 */
public class AbstractDialog {

    protected UiHelper uiHelper;
    /**
     * make sure to convert this in your child class
     * when you need it
     */
    protected Object parenController;
    protected Stage currentStage;

    public void initialize() {
        this.uiHelper = new UiHelper();
    }

    @FXML
    public void cancelDialog() {
        this.currentStage.close();
    }

    public void setParentController(Object controller) {
        this.parenController = controller;
    }

    public void setStage(Stage stage) {
        this.currentStage = stage;
    }
}
