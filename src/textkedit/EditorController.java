package textkedit;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.File;
import java.util.Arrays;

public class EditorController {

    @FXML
    private TextArea editorArea;

    @FXML
    private Label statusBarFilename;

    private AsciiFile currentAsciiFile;

    private EditorModel editorModel;

    private FileChooser.ExtensionFilter allowedExtensions;

    public EditorController(EditorModel model) {
        this.editorModel = model;

        this.allowedExtensions = new FileChooser.ExtensionFilter("Text Files (*.txt)", "*.txt");
    }

    @FXML
    private void onOpen() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open file ...");
        fileChooser.getExtensionFilters().add(this.allowedExtensions);
        fileChooser.setInitialDirectory(new File("./"));
        File file = fileChooser.showOpenDialog(null);

        if(file != null) {
            IOResult<AsciiFile> ioResult = this.editorModel.load(file.toPath());

            if(ioResult.wasSuccessful() && ioResult.hasData()) {
                this.currentAsciiFile = ioResult.getData();

                this.editorArea.clear();
                this.currentAsciiFile.getContent().forEach(line -> editorArea.appendText(line + "\n"));

                this.updateFilename();
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("File Error");
                alert.setHeaderText("File could not be opened");
                alert.showAndWait();
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

            IOResult<AsciiFile> ioResult = editorModel.save(asciiFile);

            if(!ioResult.wasSuccessful()) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("File Error");
                alert.setHeaderText("File could not be saved");
                alert.showAndWait();
            }
        } else {
            this.onSaveAs();
        }
    }

    @FXML
    private void onSaveAs() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save as ...");
        fileChooser.getExtensionFilters().add(this.allowedExtensions);

        if(this.currentAsciiFile != null) {
            fileChooser.setInitialDirectory(this.currentAsciiFile.getFile().getParentFile());
        } else {
            fileChooser.setInitialDirectory(new File("./"));
        }

        File file = fileChooser.showSaveDialog(null);

        if(file != null) {
            AsciiFile asciiFile = new AsciiFile(file.toPath(), Arrays.asList(editorArea.getText().split("\n")));

            IOResult<AsciiFile> ioResult = editorModel.save(asciiFile);

            if(ioResult.wasSuccessful()) {
                this.currentAsciiFile = asciiFile;
                this.updateFilename();
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("File Error");
                alert.setHeaderText("File could not be saved");
                alert.showAndWait();
            }
        }
    }

    private void updateFilename() {
        String filename = "";

        if(this.currentAsciiFile != null) {
            filename = this.currentAsciiFile.getPath().getFileName().normalize().toString();
            this.statusBarFilename.setTooltip(
                    new Tooltip(this.currentAsciiFile.getPath().toString())
            );
        } else {
            //remove tooltip if no file is present
            this.statusBarFilename.setTooltip(null);
        }

        this.statusBarFilename.setText(filename);
    }
}
