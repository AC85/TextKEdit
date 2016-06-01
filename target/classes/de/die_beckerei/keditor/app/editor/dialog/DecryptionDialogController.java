package de.die_beckerei.keditor.app.editor.dialog;

import de.die_beckerei.keditor.app.crypto.CipherFactory;
import de.die_beckerei.keditor.app.crypto.CipherSettings;
import de.die_beckerei.keditor.app.crypto.cipher.Cipher;
import de.die_beckerei.keditor.app.editor.EditorController;
import de.die_beckerei.keditor.app.editor.UiHelper;
import de.die_beckerei.keditor.app.file.Document;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.bouncycastle.crypto.tls.CipherType;

import java.util.ArrayList;

/**
 * Created by Flo on 22.05.16.
 */
public class DecryptionDialogController extends AbstractDialog {

    @FXML
    ComboBox DecryptCipherTyp;

    @FXML
    ComboBox DecryptComboPadding;

    @FXML
    ComboBox DecryptComboBlockMode;

    @FXML
    TextField DecryptPassword;

    private Document document;

    @Override
    public void initialize() {
        super.initialize();

        //set available Ciphers
        DecryptCipherTyp.getItems().setAll(Cipher.TYPE.values());
        DecryptCipherTyp.getSelectionModel().selectFirst();

        //set available Paddings
        DecryptComboPadding.getItems().setAll(CipherSettings.PADDING.values());
        DecryptComboPadding.getSelectionModel().selectFirst();

        //set available BlockModes
        DecryptComboBlockMode.getItems().setAll(CipherSettings.BLOCK.values());
        DecryptComboBlockMode.getSelectionModel().selectFirst();
    }

    public void decryptCurrent() throws Exception {
        if(this.document == null)
            throw  new Exception("No document for decryption selected");

        //gather decryption info
        Cipher.TYPE cipherType = (Cipher.TYPE) DecryptCipherTyp.getSelectionModel().getSelectedItem();
        CipherSettings.PADDING padding = (CipherSettings.PADDING) DecryptComboPadding.getSelectionModel().getSelectedItem();
        CipherSettings.BLOCK blockMode = (CipherSettings.BLOCK) DecryptComboBlockMode.getSelectionModel().getSelectedItem();
        String key = DecryptPassword.getText();

        CipherSettings settings = new CipherSettings();
        settings.setPadding(padding);
        settings.setBlockmode(blockMode);
        settings.setKey(key);

        Cipher cipher = CipherFactory.getInstance(cipherType, settings);

        byte[] contentAsByte = this.document.toByte();
        byte[] decrypted = cipher.decrypt(contentAsByte);


        System.out.println(new String(decrypted));
        //ArrayList<String> line = new ArrayList<>();


    }

    public void decryptFile() {
        //this.uiHelper.newFileChooser("Select encrypted file", this.currentStage);
    }

    public void setDocument(Document document) {
        this.document = document;
    }
}
