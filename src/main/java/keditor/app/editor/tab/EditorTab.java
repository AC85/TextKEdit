package keditor.app.editor.tab;

import keditor.app.file.Document;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.Arrays;

/**
 * Alexander Christoph
 * BMI
 */

/**
 * This custom EditorTab construct its layout by adding a Textarea
 * It needs an Document to construct itself
 */
public class EditorTab extends javafx.scene.control.Tab {

    Document document;

    TextArea textArea;

    Label statusbar;

    public EditorTab(Document file) {
        super();

        this.document = file;

        this.textArea = new TextArea();
        this.document.getContent().forEach(
                line -> this.textArea.appendText(line + "\n")
        );

        this.textProperty().bind(this.document.filenameProperty());

        //setup layout for tab content

        VBox vBox = new VBox();
        vBox.getStyleClass().add("tabContent");
        this.statusbar = new Label();

        HBox hbox = new HBox();
        HBox.setHgrow(this.textArea, Priority.ALWAYS);
        hbox.getChildren().add(this.textArea);

        VBox.setVgrow(hbox, Priority.ALWAYS);
        vBox.getChildren().add(hbox);
        vBox.getChildren().add(this.statusbar);

        this.setContent(vBox);

        vBox.getStylesheets().add(this.getClass().getResource("tab.css").toString());
    }

    public Document getDocument() {

        this.document.setContent(
                Arrays.asList(this.textArea.getText().split("\n"))
        );

        return this.document;
    }
}
