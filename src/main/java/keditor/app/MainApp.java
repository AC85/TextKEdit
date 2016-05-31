package keditor.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Alexander Christoph
 * BMI
 */

public class MainApp extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.primaryStage = primaryStage;

        //load root layout
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("rootLayout.fxml"));

        this.primaryStage.setScene(new Scene(loader.load()));
        this.primaryStage.setTitle("TextKEdit");
        this.primaryStage.setResizable(false);
        this.primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
