package de.die_beckerei.keditor.app.editor;

import de.die_beckerei.keditor.app.file.File;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**
 * This custom Tab construct its layout by adding a Textarea
 * It needs an de.die_beckerei.keditor.app.file.File to construct itself
 */
public class Tab extends javafx.scene.control.Tab {

    File file;

    TextArea textArea;

    public Tab(File file) {
        super();

        this.file = file;

        this.setText(file.getFilename());

        this.textArea = new TextArea();
        this.file.getContent().forEach(
                line -> this.textArea.appendText(line + "\n")
        );

        HBox hbox = new HBox();
        HBox.setHgrow(this.textArea, Priority.ALWAYS);
        hbox.getChildren().add(this.textArea);

        this.setContent(hbox);

    }

}
