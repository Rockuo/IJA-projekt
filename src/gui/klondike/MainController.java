package gui.klondike;

import abstractFactories.AbstractFactorySolitaire;
import factories.FactoryKlondike;
import gui.elements.game.GameController;
import gui.elements.game4.GameFourController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.HashMap;

public class MainController {

    private final String GAME_NAME = "newGame";

    private int gameNameIterator = 0;
    private Stage stage;

    @FXML
    private AnchorPane gamePane;

    private HashMap<String, GameController> games;

    private int gameCount = 0;

    public void initialize() throws IOException, ClassNotFoundException {
        AbstractFactorySolitaire factory = new FactoryKlondike();
        this.games = new HashMap<>();
//        GameFourController fGame = new GameFourController();
//        fGame.configGame(2, factory);
        GameController fGame = new GameController();
        fGame.setAllElements(factory);
        this.games.put("1", fGame);
        this.gamePane.getChildren().add(fGame);
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

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
