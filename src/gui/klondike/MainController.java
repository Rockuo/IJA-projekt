package gui.klondike;

import abstractFactories.AbstractFactorySolitaire;
import factories.FactoryKlondike;
import gui.elements.game.GameController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.HashMap;

public class MainController {

    private final String GAME_NAME = "newGame";
    private final FileChooser fileChooser = new FileChooser();
    private int gameNameIterator = 0;
    private Stage stage;
    @FXML
    private AnchorPane gamePane;

    private HashMap<String, GameController> games;

    private int gameCount = 0;

    public void initialize() throws IOException, ClassNotFoundException {
        AbstractFactorySolitaire factory = new FactoryKlondike();
        this.games = new HashMap<>();
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

    public void openGameHandler(ActionEvent event) throws IOException, ClassNotFoundException {
        File file = fileChooser.showOpenDialog(this.stage);
        FileInputStream fileIn = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        HashMap<String, Object> data = (HashMap<String, Object>) in.readObject();
        in.close();
        fileIn.close();
        GameController game = new GameController();
        game.open(data, new FactoryKlondike());
        this.gamePane.getChildren().remove(0);
        gamePane.getChildren().add(game);
    }

    public void saveGameHandler(ActionEvent event) throws IOException {
        File file = fileChooser.showSaveDialog(this.stage);
        if (file != null) {
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(games.get("1").save());
            out.close();
            fileOut.close();
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
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
