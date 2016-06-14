package alex.editor;

import alex.file.Document;
import alex.file.DocumentService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Created by alexanderchristoph on 03.06.16.
 */
public class EditorController {

    private Document currentDocument;

    @FXML
    TextArea editorArea;

    @FXML
    private ResourceBundle resourceBundle;

    @FXML
    private void initialize()  {
        //leeres Dokument erstellen
        this.currentDocument = DocumentService.getEmpty();
    }

    public void onOpen()  {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File ...");

        File file = fileChooser.showOpenDialog(null);

        if(file != null) {
            try {
                Document document = DocumentService.load(file);

                this.currentDocument = document;

                this.editorArea.setText(this.currentDocument.getPayloadAsString());


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Speichert ein Document ab,
     * ist im Document keine Pfadangabe wird der Nutzer nach einer gefragt
     */
    public void onSave() {
        this.currentDocument.setPayload( this.editorArea.getText() );

        try {
            //wenn kein Pfad im Document vorhanden ist, einen sezten lassen
            if(this.currentDocument.isTransient()) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Save file ...");

                File file = fileChooser.showSaveDialog(null);

                if(file != null) {
                    this.currentDocument.setFile(file);
                }
            }

            //bereits gespeichertes Document überschreiben
            DocumentService.save(this.currentDocument);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openEncryptionDialog() {

        try {
            //neues Fenster mit dem Encryption Dialog erzeugen
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/CipherDialog.fxml"));
            Parent root = fxmlLoader.load();
            EncryptionDialogController controller = fxmlLoader.getController();

            //Dialog vorbereiten
            controller.setDocument(this.currentDocument);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UTILITY);
            stage.setTitle("Encryption File");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
