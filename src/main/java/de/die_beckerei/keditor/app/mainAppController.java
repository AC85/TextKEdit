package de.die_beckerei.keditor.app;

import de.die_beckerei.keditor.app.editor.EditorController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Flo on 14.05.16.
 */
public class MainAppController {

    @FXML
    private ResourceBundle resources;

    @FXML
    Pane editorArea;

    @FXML
    private void initialize() {
        this.initEdtior();
    }

    public void onOpen() {

    }

    public void onSave() {

    }

    public void onClose() {

    }

    public void onSaveAs() {

    }

    private void initEdtior() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("editor/editor.fxml"));

        try {
            this.editorArea.getChildren().add(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
