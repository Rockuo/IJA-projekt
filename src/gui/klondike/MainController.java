package gui.klondike;

import abstractFactories.AbstractFactorySolitaire;
import factories.FactoryKlondike;
import gui.elements.game.GameController;
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
        AbstractFactorySolitaire factory = new FactoryKlondike();
        this.games = new HashMap<>();
        GameController tmp = (GameController)gamePane.getChildren().get(0);
        tmp.setAllElements(factory);
        this.games.put("1",  tmp);
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
        System.out.println("bbb");
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
