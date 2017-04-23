package gui.klondike;


import abstractFactories.AbstractFactorySolitaire;
import factories.FactoryKlondike;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.Queue;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        AbstractFactorySolitaire factory = new FactoryKlondike();

        Parent root = FXMLLoader.load(getClass().getResource("klondike.fxml"));
        primaryStage.setTitle("gui.klondike Solitaire");
        primaryStage.setScene(new Scene(root, 1024, 768));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
