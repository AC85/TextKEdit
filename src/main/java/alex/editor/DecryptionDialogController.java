package alex.editor;

import alex.file.Document;
import alex.file.DocumentService;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

/**
 * Created by alexanderchristoph on 22.06.16.
 */
public class DecryptionDialogController {

    @FXML
    private PasswordField passwordField;
    private Document document;
    private EditorController parentController;
    private Stage stage;

    /**
     * Entschlüsselt das aktuelle Dokument
     */
    public void onDecrypt() throws Exception {
        //TODO passwort auslesen
        Document decryptedDoc = DocumentService.decrypt(this.document);

        parentController.setDocument(decryptedDoc);

        this.stage.close();
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Document getDocument() {
        return document;
    }

    public void setParentController(EditorController parentController) {
        this.parentController = parentController;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
