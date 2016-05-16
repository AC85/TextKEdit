package de.die_beckerei.keditor.app;

import de.die_beckerei.keditor.app.editor.EditorController;
import de.die_beckerei.keditor.app.file.Document;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ResourceBundle;

/**
 * Created by Flo on 14.05.16.
 */
public class MainAppController {

    @FXML
    private ResourceBundle resources;

    @FXML
    AnchorPane editorArea;

    private EditorController editorController;

    @FXML
    private void initialize() {
        this.initEditor();

    }

    public void onOpen() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open file ...");

        File file = fileChooser.showOpenDialog(null);

        if(file != null) {
            try {
                Document document = Document.load(file.toPath());

                this.editorController.openDocument(document);

            } catch (IOException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("File Error");
                alert.setHeaderText("File could not be opened");
                alert.showAndWait();
            }
        }
    }

    public void onSave() {
        try {
            this.editorController.saveCurrentDocument();
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("File Error");
            alert.setHeaderText("File could not be saved");
            alert.showAndWait();
        }
    }

    public void onClose() {
        this.editorController.closeCurrentTab();
    }

    public void onSaveAs() {
        try {
            this.editorController.saveAs();
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("File Error");
            alert.setHeaderText("File could not be saved");
            alert.showAndWait();
        }
    }

    public void encryptToAES() {
        try {
            this.editorController.encryptToAES();
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("File Error");
            alert.setHeaderText("File could not be saved");
            alert.showAndWait();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
    }

    private void initEditor() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("editor/editor.fxml"));

        try {
            this.editorArea.getChildren().add(loader.load());

            this.editorController = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
