package keditor.app.editor;

import keditor.app.editor.tab.EditorTab;
import keditor.app.file.Document;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Alexander Christoph
 * 634389
 * BMI
 */
public class EditorController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private TabPane tabbar;

    private UiHelper uiHelper;


    /**
     * will be executed on controller instantiation
     */
    @FXML
    private void initialize() {

        this.uiHelper = new UiHelper();

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

    public void saveAs() throws IOException {
        File file = this.uiHelper.newFileChooser("Save as ...");

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

    public void openEncryptionDialog() throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EncryptionDialog.fxml"));
        Parent root = fxmlLoader.load();
        EncryptionDialogController controller = fxmlLoader.getController();
        controller.setDocument(this.getCurrentDocument());
        controller.setEditorController(this);

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UTILITY);
        stage.setTitle("Encryption File");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void openDecryptionDialog() throws Exception {
/*
        Document currentDoc = this.getCurrentDocument();

        Cipher cipher = CipherFactory.getInstance(Cipher.TYPE.AES);

        byte[] asByte = currentDoc.toByte();
        byte[] decrypted = cipher.decrypt(asByte);
        
        Document newdoc = Document.newInstance(null, decrypted);

        EditorTab newTab = new EditorTab(newdoc);

        this.addNewTab(newTab, true);
*/
    }
}
