package de.die_beckerei.keditor.app.editor;

import de.die_beckerei.keditor.app.file.Document;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;

import java.util.ResourceBundle;

/**
 * Created by Flo on 14.05.16.
 */
public class EditorController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private TabPane tabbar;

    @FXML
    private void initialize() {
        Tab defaultTab = new Tab(new Document());

        this.addNewTab(defaultTab, true);
    }


    public void onNewFileTab() {
        Tab tab = new Tab(new Document());
        this.addNewTab(tab, true);
    }

    public void openDocument(Document document) {

        Tab tab = new Tab(document);
        this.addNewTab(tab, true);
    }


    /**
     * adds a newTab at the last position, but before the "+"-Tab which should always be last
     * @param newTab
     */
    private void addNewTab(Tab newTab, boolean selected) {
        int tabbarSize = this.tabbar.getTabs().size();
        if(tabbarSize <= 0) {
            this.tabbar.getTabs().add(newTab);
        } else {
            this.tabbar.getTabs().add(tabbarSize - 1, newTab);
        }

        if(selected) {
            this.tabbar.getSelectionModel().select(newTab);
        }
    }
}
