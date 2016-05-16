package de.die_beckerei.keditor.app.editor;

import de.die_beckerei.keditor.app.crypto.CipherFactory;
import de.die_beckerei.keditor.app.crypto.cipher.Cipher;
import de.die_beckerei.keditor.app.editor.tab.EditorTab;
import de.die_beckerei.keditor.app.file.Document;
import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import javafx.stage.FileChooser;

import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Flo on 14.05.16.
 */
public class EditorController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private TabPane tabbar;


    /**
     * will be executed on controller instantiation
     */
    @FXML
    private void initialize() {

        //onSelectionChanged is triggered on startup and will create a new empty document by default
    }


    /**
     * Create a new EditorTab for a empty Document
     * This function is executed once on application startup since the event onSelectionChanged is triggered by the "+"-EditorTab
     */
    public void onNewFileTab() {
        EditorTab editorTab = new EditorTab(Document.newInstance(null));
        this.addNewTab(editorTab, true);
    }

    public void openDocument(Document document) {

        EditorTab editorTab = new EditorTab(document);
        this.addNewTab(editorTab, true);
    }


    /**
     * adds a newEditorTab at the last position, but before the "+"-EditorTab which should always be last
     * @param newEditorTab
     */
    private void addNewTab(EditorTab newEditorTab, boolean selected) {
        int tabbarSize = this.tabbar.getTabs().size();
        if(tabbarSize <= 0) {
            this.tabbar.getTabs().add(newEditorTab);
        } else {
            this.tabbar.getTabs().add(tabbarSize - 1, newEditorTab);
        }

        if(selected) {
            this.tabbar.getSelectionModel().select(newEditorTab);
        }
    }

    /**
     * closes current EditorTab
     */
    public void closeCurrentTab() {
        EditorTab editorTab = (EditorTab) this.tabbar.getSelectionModel().getSelectedItem();

        this.closeTab(editorTab);
    }

    /**
     * closes given editorTab
     * will reopen a new editorTab if this was the last open editorTab
     * @param editorTab
     */
    private void closeTab(EditorTab editorTab) {
        this.tabbar.getTabs().remove(editorTab);

        //create empty document if no more tabs are present
        if(this.tabbar.getTabs().isEmpty()) {
            this.onNewFileTab();
        }
    }

    private void replaceCurrentTab(EditorTab editorTab) {
        EditorTab currentEditorTab = (EditorTab) this.tabbar.getSelectionModel().getSelectedItem();
        int currentTabIndex = this.tabbar.getSelectionModel().getSelectedIndex();

        this.tabbar.getTabs().add(currentTabIndex, editorTab);
        this.tabbar.getTabs().remove(currentEditorTab);
    }

    public void saveCurrentDocument() throws IOException {
        EditorTab editorTab = (EditorTab) this.tabbar.getSelectionModel().getSelectedItem();
        Document document = editorTab.getDocument();

        if(document.isTransient()) {
            this.saveAs();
        } else {
            Document.save(document);
        }
    }


    private void saveDocumentAs(Document document) throws IOException {
        Document.save(document);
    }

    private File newFileChooser(String title) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        return fileChooser.showSaveDialog(null);
    }

    public void saveAs() throws IOException {
        File file = this.newFileChooser("Save as ...");

        Document document = Document.newInstance(file.toPath(), this.getContentFromCurrentTab());

        //this.replaceCurrentTab(new EditorTab(document));

        this.saveDocumentAs(document);
    }

    private Document getCurrentDocument() {
        EditorTab editorTab = (EditorTab) this.tabbar.getSelectionModel().getSelectedItem();
        return editorTab.getDocument();
    }

    private List<String> getContentFromCurrentTab() {
        EditorTab editorTab = (EditorTab) this.tabbar.getSelectionModel().getSelectedItem();
        return editorTab.getDocument().getContent();
    }

    public void encryptToAES() throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, NoSuchProviderException {

        File file = this.newFileChooser("Save as ...");

        Cipher cipher = CipherFactory.getInstance(Cipher.TYPE.AES);
        Document currentDoc = this.getCurrentDocument();
        byte[] asByte = currentDoc.toByte();
        byte[] encrypted = cipher.encrypt(asByte);

        ArrayList<String> newLine = new ArrayList<>();
        newLine.add(new String(encrypted, Charset.forName(Document.charset)));

        Document newdoc = Document.newInstance(file.toPath(), newLine);

        Document.save(newdoc);

    }
}
