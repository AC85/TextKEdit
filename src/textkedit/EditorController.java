package textkedit;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.scene.control.TextArea;

import java.io.File;
import java.util.Arrays;

public class EditorController {

    @FXML
    private TextArea editorArea;

    @FXML
    private Text statusBarFilename;

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

                this.updateFilename();
            }
        }
    }

    @FXML
    private void onClose() {
        this.currentAsciiFile = null;
        this.editorArea.clear();
        this.updateFilename();
    }

    @FXML
    private void onSave() {
        if(this.currentAsciiFile != null) {
            AsciiFile asciiFile = new AsciiFile(currentAsciiFile.getPath(), Arrays.asList(editorArea.getText().split("\n")));
            editorModel.save(asciiFile);
        } else {
            this.onSaveAs();
        }
    }

    @FXML
    private void onSaveAs() {
        FileChooser fileChooser = new FileChooser();
        if(this.currentAsciiFile != null) {
            fileChooser.setInitialDirectory(this.currentAsciiFile.getFile().toFile());
            fileChooser.setInitialDirectory(this.currentAsciiFile.getFile().getParentFile());
        } else {
            fileChooser.setInitialDirectory(new File("./"));
        }
        File file = fileChooser.showSaveDialog(null);
        AsciiFile asciiFile = new AsciiFile(file.toPath(), Arrays.asList(editorArea.getText().split("\n")));
        editorModel.save(asciiFile);
        this.currentAsciiFile = asciiFile;
        this.updateFilename();
    }

    private void updateFilename() {
        String filename = "";
        if(this.currentAsciiFile != null) {
            filename = this.currentAsciiFile.getPath().getFileName().normalize().toString();
        }
        this.statusBarFilename.setText(filename);
    }
}
