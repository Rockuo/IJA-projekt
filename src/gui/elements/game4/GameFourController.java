package gui.elements.game4;

import abstractFactories.AbstractFactorySolitaire;
import gui.elements.game.GameController;
import gui.klondike.MainController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Sample custom control hosting a text field and a button.
 */
public class GameFourController extends AnchorPane implements Initializable{

    @FXML
    private GameController game1;
    @FXML
    private GameController game2;
    @FXML
    private GameController game3;
    @FXML
    private GameController game4;
    @FXML
    private AnchorPane gameWrap1;
    @FXML
    private AnchorPane gameWrap2;
    @FXML
    private AnchorPane gameWrap3;
    @FXML
    private AnchorPane gameWrap4;
    private MainController main;

    public GameFourController(MainController main) {
        this.main = main;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("gameFour.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void configGame(AbstractFactorySolitaire factoryKlondike, int id) {
        switch (id) {
            case 1:
                this.game1.configure(factoryKlondike, id, main);
                break;
            case 2:
                this.game2.configure(factoryKlondike, id, main);
                break;
            case 3:
                this.game3.configure(factoryKlondike, id, main);
                break;
            case 4:
                this.game4.configure(factoryKlondike, id, main);
                break;
            default:
                this.game1.configure(factoryKlondike, id, main);
                break;
        }
    }

    public void setGame(int id, GameController game) {
        AnchorPane wrap;
        switch (id) {
            case 1:
                wrap = this.gameWrap1;
                this.game1 = game;
                break;
            case 2:
                wrap = this.gameWrap2;
                this.game2 = game;
                break;
            case 3:
                wrap = this.gameWrap3;
                this.game3 = game;
                break;
            case 4:
                wrap = this.gameWrap4;
                this.game4 = game;
                break;
            default:
                wrap = this.gameWrap1;
                this.game1 = game;
                break;
        }
        wrap.getChildren().remove(0);
        wrap.getChildren().add(game);
    }

    public boolean isConfd(int id) {
        switch (id) {
            case 1:
                return this.game1.isConfd();
            case 2:
                return this.game2.isConfd();
            case 3:
                return this.game3.isConfd();
            case 4:
                return this.game4.isConfd();
            default:
                return this.game1.isConfd();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.game1.setDefaultValues(this.main,1);
        this.game2.setDefaultValues(this.main,2);
        this.game3.setDefaultValues(this.main,3);
        this.game4.setDefaultValues(this.main,4);
    }
}
