package de.die_beckerei.keditor.app.editor;

import de.die_beckerei.keditor.app.file.File;
import javafx.fxml.FXML;
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
    private void initialize() {

        this.tabbar.getTabs().add(new Tab(new File()));
    }
}
