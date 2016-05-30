package de.die_beckerei.keditor.app;

import de.die_beckerei.keditor.app.editor.EditorController;
import de.die_beckerei.keditor.app.file.Document;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ResourceBundle;

/**
 * Created by Flo on 14.05.16.
 */
public class MainAppController {

    @FXML
    AnchorPane editorArea;

    private EditorController editorController;

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
                this.showAlert("File Error", "File could not be opened", e);
            }
        }
    }

    public void onSave() {
        try {
            this.editorController.saveCurrentDocument();
        } catch (IOException e) {
            this.showAlert("File Error", "File could not be saved.", e);
        }
    }

    public void onClose() {
        this.editorController.closeCurrentTab();
    }

    public void onSaveAs() {
        try {
            this.editorController.saveAs();
        } catch (IOException e) {
            this.showAlert("File Error", "File could not be saved.", e);
        }
    }

    public void onEncrypt()  {
        try {
            this.editorController.openEncryptionDialog();
        } catch (Exception e) {
            this.showAlert("File Error", "File could not be encrypted or saved.", e);
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

    public void onDecrypt() {
        try {
            this.editorController.openDecryptionDialog();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * creates alert dialog
     * @param title Dialog Title
     * @param subheader Subheader, explains error in one sentence
     * @param e exception, stacktrace is printed in the dialog
     */
    private void showAlert(String title, String subheader, Exception e) {
        e.printStackTrace();

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(subheader);
        alert.setContentText(e.getMessage());

        // Create expandable Exception.
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String exceptionText = sw.toString();

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(textArea, 0, 1);

        // Set expandable Exception into the dialog pane.
        alert.getDialogPane().setExpandableContent(expContent);


        alert.showAndWait();
    }
}
