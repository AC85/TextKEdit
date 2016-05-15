package de.die_beckerei.keditor.app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Created by Flo on 14.05.16.
 */
public class MainAppController {

    @FXML
    private ResourceBundle resources;

    @FXML
    AnchorPane editorArea;

    @FXML
    private void initialize() {
        this.initEditor();
    }

    public void onOpen() {

    }

    public void onSave() {

    }

    public void onClose() {

    }

    public void onSaveAs() {

    }

    private void initEditor() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("editor/editor.fxml"));

        try {
            this.editorArea.getChildren().add(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
