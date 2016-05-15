package de.die_beckerei.keditor.app.editor;

import de.die_beckerei.keditor.app.file.File;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;

import java.net.URL;
import java.util.ArrayList;
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
    private Label newFile;


    @FXML
    private void initialize() {
        Tab defaultTab = new Tab(new File());

        this.addNewTab(defaultTab, true);
    }


    public void onNewFileTab() {
        Tab tab = new Tab(new File());
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
