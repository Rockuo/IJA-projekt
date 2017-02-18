package Klondike;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("klondike.fxml"));
        primaryStage.setTitle("Klondike Solitaire");
        primaryStage.setScene(new Scene(root, 1024, 768));
        primaryStage.show();
        Controller a = new Controller();
    }


    public static void main(String[] args) {
        launch(args);
    }
}