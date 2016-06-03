package alex;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by alexanderchristoph on 03.06.16.
 */
// Java FX Anwendung
public class MainApp extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.primaryStage = primaryStage;

        //editor layout laden
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/editor.fxml"));

        this.primaryStage.setScene(new Scene(fxmlLoader.load()));
        this.primaryStage.setTitle("Text Editor");
        this.primaryStage.setResizable(false);
        this.primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
