package alex.editor;

import alex.cipher.CipherSettings;
import alex.file.Document;
import alex.file.DocumentService;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;

import java.io.File;

/**
 * Created by alexanderchristoph on 03.06.16.
 */
public class EncryptionDialogController {

    @FXML
    ListView cipherList;

    @FXML
    ListView modeList;

    @FXML
    ListView paddingList;

    private Document document;

    public void initialize() {
        //für später

        cipherList.getItems().setAll(CipherSettings.CIPHER.values());
        modeList.getItems().setAll(CipherSettings.MODE.values());
        paddingList.getItems().setAll(CipherSettings.PADDING.values());
    }

    @FXML
    protected void onEcrypt() throws Exception {
        //set ciphersettings
        CipherSettings settings = new CipherSettings();
        settings.setCipher((CipherSettings.CIPHER) cipherList.getSelectionModel().getSelectedItem());
        settings.setMode((CipherSettings.MODE) modeList.getSelectionModel().getSelectedItem());
        settings.setPadding((CipherSettings.PADDING) paddingList.getSelectionModel().getSelectedItem());

        this.document.setCipherSettings(settings);

        Document encryptedDoc = DocumentService.encrypt(this.document);


        //Pfad zum abspeichern holen
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Datei speichern unter  ...");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("*.xml", "*.xml"));

        File file = fileChooser.showSaveDialog(this.modeList.getScene().getWindow());

        System.out.println(encryptedDoc.getCipherSettings());
        System.out.println(file);

        if(file != null) {
            encryptedDoc.setFile(file);
        }

        //Datei abspeichern
        DocumentService.saveAsXml(encryptedDoc);
    }

    public void setDocument(Document document) {
        this.document = document;
    }
}
