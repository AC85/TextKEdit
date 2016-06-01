package de.die_beckerei.keditor.app.editor.dialog;

import de.die_beckerei.keditor.app.crypto.CipherFactory;
import de.die_beckerei.keditor.app.crypto.CipherSettings;
import de.die_beckerei.keditor.app.crypto.cipher.Cipher;
import de.die_beckerei.keditor.app.editor.EditorController;
import de.die_beckerei.keditor.app.editor.UiHelper;
import de.die_beckerei.keditor.app.file.Document;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by Flo on 22.05.16.
 */
public class EncryptionDialogController extends AbstractDialog {

    @FXML
    ComboBox ComboCipher;

    @FXML
    ComboBox ComboPadding;

    @FXML
    ComboBox ComboBlockMode;

    @FXML
    TextField Password;

    private Document document;

    @Override
    public void initialize() {
        super.initialize();

        //set available Ciphers
        ComboCipher.getItems().setAll(Cipher.TYPE.values());
        ComboCipher.getSelectionModel().selectFirst();

        //set available Paddings
        ComboPadding.getItems().setAll(CipherSettings.PADDING.values());
        ComboPadding.getSelectionModel().selectFirst();

        //set available BlockModes
        ComboBlockMode.getItems().setAll(CipherSettings.BLOCK.values());
        ComboBlockMode.getSelectionModel().selectFirst();

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
        Cipher.TYPE cipherType = (Cipher.TYPE) ComboCipher.getSelectionModel().getSelectedItem();
        CipherSettings.PADDING padding = (CipherSettings.PADDING) ComboPadding.getSelectionModel().getSelectedItem();
        CipherSettings.BLOCK blockMode = (CipherSettings.BLOCK) ComboBlockMode.getSelectionModel().getSelectedItem();
        String key = Password.getText();

        File file = this.uiHelper.newFileChooser("Save as ...", ComboBlockMode.getScene().getWindow());

        CipherSettings settings = new CipherSettings();
        settings.setPadding(padding);
        settings.setBlockmode(blockMode);
        settings.setKey(key);

        Cipher cipher = CipherFactory.getInstance(cipherType, settings);

        byte[] contentAsByte = this.document.toByte();
        byte[] encrypted = cipher.encrypt(contentAsByte);

       // ArrayList<String> line = new ArrayList<>();
       // line.add(new String(encrypted, Charset.forName(Document.charset)));

        Document newDoc = Document.newInstance(file.toPath(), encrypted);
        Document.save(newDoc);

        this.cancelDialog();
    }

    public void setDocument(Document document) {
        this.document = document;
    }

}
