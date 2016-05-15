package de.die_beckerei.keditor.app.editor;

import de.die_beckerei.keditor.app.file.Document;
import javafx.fxml.FXML;
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

        //onSelectionChanged is triggered on startup and will create a new empty document by default
    }


    /**
     * Create a new Tab for a empty Document
     * This function is executed once on application startup since the event onSelectionChanged is triggered by the "+"-Tab
     */
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

    /**
     * closes current Tab
     */
    public void closeCurrentTab() {
        Tab tab = (Tab) this.tabbar.getSelectionModel().getSelectedItem();

        this.closeTab(tab);
    }

    /**
     * closes given tab
     * will reopen a new tab if this was the last open tab
     * @param tab
     */
    private void closeTab(Tab tab) {
        this.tabbar.getTabs().remove(tab);

        //create empty document if nore more tabs are present
        if(this.tabbar.getTabs().isEmpty()) {
            this.onNewFileTab();
        }
    }
}
