package gui.klondike;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.util.Pair;

import java.sql.Struct;

/**
 * Created by Marry on 04.05.2017.
 */
public class alert {

    public static void alertPopUp(String tittle, String text, String buttonType){

        Dialog<Pair<String, String>> dialog = new Dialog<>();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(tittle);
        alert.setHeaderText(null);
        alert.setContentText(text);

        ButtonType buttonTypeCancel = new ButtonType(buttonType, ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeCancel);

        alert.showAndWait();
    }
}
