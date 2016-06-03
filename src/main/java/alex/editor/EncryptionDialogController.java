package alex.editor;

import alex.cipher.CipherSettings;
import alex.file.Document;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

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

    protected void onEcrypt() {
        //encryption process starten
    }

    public void setDocument(Document document) {
        this.document = document;
    }
}
