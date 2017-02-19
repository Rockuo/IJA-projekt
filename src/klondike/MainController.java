package klondike;

import elements.game.GameController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.util.HashMap;

public class MainController {

    private final String GAME_NAME = "newGame";

    private int gameNameIterator = 0;

    @FXML
    private AnchorPane gamePane;

    private HashMap<String, GameController> games;

    private int gameCount = 0;

    public void initialize() {
//        this.games = new HashMap<String, GameController>();

    }

    private void expand() {

    }

//    private GameController initGame() {
//        this.gameNameIterator++;
//        String name = this.GAME_NAME + gameNameIterator;
//        GameController game = new GameController(this, name);
//        this.games.put(name, game);
//        return game;
//    }

    public void newGameHandler(ActionEvent event) {
        this.gameCount++;
        if (this.gameCount > 1) {
            this.expand();
        } else {
//            GameController game = initGame();

//            this.gamePane.getChildren().add(game.getElement());
        }
    }

    public void openGameHandler(ActionEvent event) {

    }

    public void variantHandler(ActionEvent event) {

    }

    public void statisticHandler(ActionEvent event) {

    }

    public void exitHandler(ActionEvent event) {

    }

    public void cardsHandler(ActionEvent event) {

    }

    public void aboutHandler(ActionEvent event) {

    }
}
