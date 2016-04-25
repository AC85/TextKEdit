package textkedit;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.scene.control.TextArea;

import java.awt.*;
import java.io.File;
import java.util.Arrays;

public class EditorController {

    @FXML
    private TextArea editorArea;

    private AsciiFile currentAsciiFile;

    private EditorModel editorModel;

    public EditorController(EditorModel model) {
        this.editorModel = model;
    }

    @FXML
    private void onOpen() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("./"));
        File file = fileChooser.showOpenDialog(null);

        if(file != null) {
            IOResult<AsciiFile> ioResult = this.editorModel.load(file.toPath());

            if(!ioResult.hasFailed() && ioResult.hasData()) {
                this.currentAsciiFile = ioResult.getData();

                this.editorArea.clear();
                this.currentAsciiFile.getContent().forEach(line -> editorArea.appendText(line + "\n"));
            }
        }
    }

    @FXML
    private void onClose() {

    }

    @FXML
    private void onSave() {
        AsciiFile asciiFile = new AsciiFile(currentAsciiFile.getFile(), Arrays.asList(editorArea.getText().split("\n")));
        editorModel.save(asciiFile);
    }

}
