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

import javafx.scene.image.ImageView;

import java.io.*;
import java.util.HashMap;

public class MainController {

    private final String GAME_NAME = "newGame";
    private final FileChooser fileChooser = new FileChooser();

    private int gameNameIterator = 0;
    private boolean expanded = false;
    private Stage stage;
    @FXML
    private AnchorPane gamePane;
    @FXML
    private AnchorPane mainWrap;
    @FXML
    private ImageView mainImage;
    private GameFourController game4;

    private int gameCount = 0;

    public void initialize() throws IOException, ClassNotFoundException {
        AbstractFactorySolitaire factory = new FactoryKlondike();
        //        GameFourController fGame = new GameFourController();
        GameController fGame = new GameController();
        fGame.configure(factory, 1, this);
        this.gamePane.getChildren().add(fGame);
    }

    public void newTab(GameController game) {
        if (!expanded) {
            this.game4 = new GameFourController(this);
            this.expanded = true;
            this.gamePane.getChildren().remove(0);
            this.gamePane.getChildren().add(this.game4);
            this.game4.setGame(1, game);
            this.stage.maxHeightProperty().setValue(1060);
            this.stage.maxWidthProperty().setValue(1536);
            this.stage.minHeightProperty().setValue(1060);
            this.stage.minWidthProperty().setValue(1536);
        }
        for (int i = 1; i <= 4; i++) {
            if (!this.game4.isConfd(i)) {
                this.game4.configGame(new FactoryKlondike(), i);
                return;
            }
        }
        //todo
    }

    public void newGame(int id) {
        GameController emptyGame = new GameController();
        emptyGame.configure(new FactoryKlondike(),id,this);
        if (!expanded) {
            this.gamePane.getChildren().remove(0);
            this.gamePane.getChildren().add(emptyGame);
        } else {
            this.game4.setGame(id, emptyGame);
        }
    }

    public void openGame(int id) throws IOException, ClassNotFoundException {
        File file = fileChooser.showOpenDialog(this.stage);
        FileInputStream fileIn = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        HashMap<String, Object> data = (HashMap<String, Object>) in.readObject();
        in.close();
        fileIn.close();
        GameController game = new GameController();
        game.open(data, new FactoryKlondike(), id, this);
        if (!expanded) {
            this.gamePane.getChildren().remove(0);
            this.gamePane.getChildren().add(game);
        } else {
            this.game4.setGame(id, game);
        }
    }

    public void saveGame(GameController gameController) throws IOException {
        File file = fileChooser.showSaveDialog(this.stage);
        if (file != null) {
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(gameController.save());
            out.close();
            fileOut.close();
        }
    }

    public void exitGame(int id) {
        if (!expanded) {
            System.exit(0);
        } else {
            GameController emptyGame = new GameController();
            emptyGame.setDefaultValues(this, id);
            this.game4.setGame(id, emptyGame);
        }
        for (int i = 2; i <= 4; i++) {
            if (this.game4.isConfd(i)) {
                return;
            }
        }
        this.expanded = false;

        this.stage.maxHeightProperty().setValue(530);
        this.stage.maxWidthProperty().setValue(768);
        this.stage.minHeightProperty().setValue(530);
        this.stage.minWidthProperty().setValue(768);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
