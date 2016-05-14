package de.die_beckerei.keditor.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Flo on 14.05.16.
 */
public class mainApp extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.primaryStage = primaryStage;

        //load root layout
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("rootLayout.fxml"));

        this.primaryStage.setScene(new Scene(loader.load()));
        this.primaryStage.setTitle("TextKEdit");
        this.primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
