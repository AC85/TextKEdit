package de.die_beckerei.keditor.app.editor;

import de.die_beckerei.keditor.app.crypto.CipherFactory;
import de.die_beckerei.keditor.app.crypto.CipherSettings;
import de.die_beckerei.keditor.app.crypto.cipher.Cipher;
import de.die_beckerei.keditor.app.file.Document;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.print.Doc;
import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by Flo on 22.05.16.
 */
public class AESEncryptionDialogController {

    @FXML
    ComboBox AESComboPadding;

    @FXML
    ComboBox AESComboBlockMode;

    @FXML
    TextField AESPassword;

    private Document document;
    private EditorController editorController;
    private UiHelper uiHelper;

    public void initialize() {

        this.uiHelper = new UiHelper();

        //set available Paddings
        AESComboPadding.getItems().setAll(CipherSettings.PADDING.values());
        AESComboPadding.getSelectionModel().selectFirst();

        //set available BlockModes
        AESComboBlockMode.getItems().setAll(CipherSettings.BLOCK.values());
        AESComboBlockMode.getSelectionModel().selectFirst();

    }

    /**
     * Encrypt and save the current docuemnt
     * in order to work you need to set the Document first, otherwise this will throw a exception
     * @throws Exception
     */
    @FXML
    public void encrypt() throws Exception {
        if(this.document == null)
            throw new Exception("No Document set for encryption.");

        //gather encryption info
        CipherSettings.PADDING padding = (CipherSettings.PADDING) AESComboPadding.getSelectionModel().getSelectedItem();
        CipherSettings.BLOCK blockMode = (CipherSettings.BLOCK) AESComboBlockMode.getSelectionModel().getSelectedItem();
        String key = AESPassword.getText();

        File file = this.uiHelper.newFileChooser("Save as ...", AESComboBlockMode.getScene().getWindow());

        CipherSettings settings = new CipherSettings();
        settings.setPadding(padding);
        settings.setBlockmode(blockMode);
        settings.setKey(key);

        Cipher cipher = CipherFactory.getInstance(Cipher.TYPE.AES, settings);

        byte[] contentAsByte = this.document.toByte();
        byte[] encrypted = cipher.encrypt(contentAsByte);

        ArrayList<String> line = new ArrayList<>();
        line.add(new String(encrypted, Charset.forName(Document.charset)));

        Document newDoc = Document.newInstance(file.toPath(), line);
        Document.save(newDoc);

        this.cancelDialog();
    }

    @FXML
    public void cancelDialog() {
        Stage stage = (Stage) AESComboBlockMode.getScene().getWindow();
        stage.close();
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public void setEditorController(EditorController controller) {
        this.editorController = controller;
    }
}
