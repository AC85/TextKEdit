package de.die_beckerei.keditor.app.editor;

import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;

/**
 * Alexander Christoph
 * 634389
 * BMI
 */

/**
 * UI Helper to avoid duplicate code and have common UI Task in one place
 */
public class UiHelper {

    public File newFileChooser(String title, Window ownerWindow) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        return fileChooser.showSaveDialog(ownerWindow);
    }

    public File newFileChooser(String title) {
        return this.newFileChooser(title, null);
    }
}
