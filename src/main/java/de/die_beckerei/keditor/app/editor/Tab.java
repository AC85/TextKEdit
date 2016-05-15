package de.die_beckerei.keditor.app.editor;

import de.die_beckerei.keditor.app.file.Document;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.util.Arrays;

/**
 * This custom Tab construct its layout by adding a Textarea
 * It needs an de.die_beckerei.keditor.app.file.Document to construct itself
 */
public class Tab extends javafx.scene.control.Tab {

    Document document;

    TextArea textArea;

    public Tab(Document file) {
        super();

        this.document = file;

        this.setText(file.getFilename());

        this.textArea = new TextArea();
        this.document.getContent().forEach(
                line -> this.textArea.appendText(line + "\n")
        );

        HBox hbox = new HBox();
        HBox.setHgrow(this.textArea, Priority.ALWAYS);
        hbox.getChildren().add(this.textArea);

        this.setContent(hbox);

    }

    public Document getDocument() {

        this.document.setContent(
                Arrays.asList(this.textArea.getText().split("\n"))
        );

        return this.document;
    }
}
