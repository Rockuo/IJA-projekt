package gui;


import gui.controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("views/klondike.fxml"));
        Parent root = fxmlLoader.load();
        ((MainController) fxmlLoader.getController()).setStage(primaryStage);
        primaryStage.setTitle("Solitaire");
        primaryStage.setScene(new Scene(root,1536,1060));
        primaryStage.maxHeightProperty().setValue(1060);
        primaryStage.maxWidthProperty().setValue(1536);
        primaryStage.show();
        ((MainController) fxmlLoader.getController()).initDone();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
