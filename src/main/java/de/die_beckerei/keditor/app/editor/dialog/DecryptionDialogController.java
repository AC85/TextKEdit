package de.die_beckerei.keditor.app.editor.dialog;

import de.die_beckerei.keditor.app.crypto.CipherSettings;
import de.die_beckerei.keditor.app.crypto.cipher.Cipher;
import de.die_beckerei.keditor.app.editor.EditorController;
import de.die_beckerei.keditor.app.editor.UiHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.bouncycastle.crypto.tls.CipherType;

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

    private EditorController editorController;
    private UiHelper uiHelper;

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

    public void decryptCurrent() {

    }

    public void decryptFile() {

    }
}
