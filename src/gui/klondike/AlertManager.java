package gui.klondike;

import gui.elements.game.GameController;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.util.Pair;

import java.sql.Struct;
import java.util.Optional;

/**
 * Created by Marry on 04.05.2017.
 */
public class AlertManager {

    public static void alertPopUp(String tittle, String text){

        Dialog<Pair<String, String>> dialog = new Dialog<>();

        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alert.setTitle(tittle);
        alert.setHeaderText(null);
        alert.setContentText(text);

        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.showAndWait();
    }

    public static void alertPopUpNew(String tittle, String text, String buttonType, GameController gameController){

        Dialog<Pair<String, String>> dialog = new Dialog<>();

        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alert.setTitle(tittle);
        alert.setHeaderText(null);
        alert.setContentText(text);

        ButtonType buttonTypeNewGame = new ButtonType(buttonType, ButtonBar.ButtonData.CANCEL_CLOSE);
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeNewGame, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeNewGame){
            gameController.newGameHandler(null);
        } else {
            alert.showAndWait();
        }
    }
}
