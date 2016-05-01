package textkedit;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class KEditorApp extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainLayout.fxml"));
        loader.setControllerFactory(t -> new EditorController(new EditorModel()));

        primaryStage.setScene(new Scene(loader.load()));

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("TextKEdit");
        this.primaryStage.show();
        this.primaryStage.setResizable(false);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}