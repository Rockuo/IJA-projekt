package gui.klondike;


import abstractFactories.AbstractFactorySolitaire;
import factories.FactoryKlondike;
import gui.elements.pack.PackController;
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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("klondike.fxml"));
        Parent root = fxmlLoader.load();
        ((MainController) fxmlLoader.getController()).setStage(primaryStage);
        primaryStage.setTitle("Solitaire");
//        primaryStage.setScene(new Scene(root, 1536, 1060));
        primaryStage.setScene(new Scene(root, 768, 530));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
