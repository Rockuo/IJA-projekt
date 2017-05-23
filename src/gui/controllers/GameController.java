package gui.controllers;


import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

import abstractFactories.AbstractFactorySolitaire;
import backend.hinter.Hinter;
import backend.History.History;
import gui.AlertManager;
import interfaces.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

/**
 * Třída ovládající hru
 *
 * @author xbures29+xhalam14
 */
public class GameController extends AnchorPane implements Controller{

    @FXML
    private ColumnController column1;
    @FXML
    private ColumnController column2;
    @FXML
    private ColumnController column3;
    @FXML
    private ColumnController column4;
    @FXML
    private ColumnController column5;
    @FXML
    private ColumnController column6;
    @FXML
    private ColumnController column7;
    @FXML
    private PackController pack;
    @FXML
    private PreviewController preview;
    @FXML
    private GoalController goal1;
    @FXML
    private GoalController goal2;
    @FXML
    private GoalController goal3;
    @FXML
    private GoalController goal4;
    @FXML
    private MenuItem switcher;

    private Hinter hinter;

    private History history;
    private int id = 0;

    void setDefaultValues(MainController main, int id) {
        this.main = main;
        this.id = id;
    }
    private boolean confd = false;
    private MainController main;

    /**
     * Vykreslí hru a nastavé event handlery
     */
    public GameController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/views/game.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.history = new History();
    }

    boolean isConfd() {
        return confd;
    }

    void configure(AbstractFactorySolitaire factory, int id, MainController main) {
        this.confd = true;
        this.id = id;
        this.main = main;
        this.column1.confWorkingPack(factory.createWorkingPack(), this);
        this.column2.confWorkingPack(factory.createWorkingPack(), this);
        this.column3.confWorkingPack(factory.createWorkingPack(), this);
        this.column4.confWorkingPack(factory.createWorkingPack(), this);
        this.column5.confWorkingPack(factory.createWorkingPack(), this);
        this.column6.confWorkingPack(factory.createWorkingPack(), this);
        this.column7.confWorkingPack(factory.createWorkingPack(), this);
        this.preview.confPreview(factory.createPreview(), this);
        this.pack.confPack(factory.createCardDeck(), this.preview, factory.createCard(Card.Color.CLUBS, 1), this);
        this.goal1.confTargetPack(factory.createTargetPack(Card.Color.SPADES), this);
        this.goal2.confTargetPack(factory.createTargetPack(Card.Color.HEARTS), this);
        this.goal3.confTargetPack(factory.createTargetPack(Card.Color.DIAMONDS), this);
        this.goal4.confTargetPack(factory.createTargetPack(Card.Color.CLUBS), this);
        generateCards();
    }

    private void generateCards() {
        for (int i=1; i<8; i++){
            for (int j=1; j<=i; j++){
                switch (i){
                    case 1: this.column1.putForce(this.pack.getCard());
                    break;
                    case 2: this.column2.putForce(this.pack.getCard());
                    break;
                    case 3: this.column3.putForce(this.pack.getCard());
                    break;
                    case 4: this.column4.putForce(this.pack.getCard());
                    break;
                    case 5: this.column5.putForce(this.pack.getCard());
                    break;
                    case 6: this.column6.putForce(this.pack.getCard());
                    break;
                    case 7: this.column7.putForce(this.pack.getCard());
                    break;
                }
            }
        }
        this.column1.updateView();
        this.column2.updateView();
        this.column3.updateView();
        this.column4.updateView();
        this.column5.updateView();
        this.column6.updateView();
        this.column7.updateView();
    }

    @Override
    public HashMap<String, Object> save() {
        HashMap<String, Object> saved = new HashMap<>();
        ArrayList<CardStack> columns = new ArrayList<>();
        ArrayList<CardDeck> goals = new ArrayList<>();
        columns.add(this.column1.save());
        columns.add(this.column2.save());
        columns.add(this.column3.save());
        columns.add(this.column4.save());
        columns.add(this.column5.save());
        columns.add(this.column6.save());
        columns.add(this.column7.save());
        goals.add(this.goal1.save());
        goals.add(this.goal2.save());
        goals.add(this.goal3.save());
        goals.add(this.goal4.save());
        saved.put("columns", columns);
        saved.put("goals", goals);
        saved.put("preview", preview.save());
        saved.put("pack", pack.save());
        saved.put("history", this.history);

        return saved;
    }

    void open(HashMap<String, Object> data, AbstractFactorySolitaire factory, int id, MainController main) {
        this.id = id;
        this.main = main;
        this.confd = true;
        ArrayList<CardStack> columns = (ArrayList<CardStack>) data.get("columns");
        ArrayList<CardDeck> goals = (ArrayList<CardDeck>) data.get("goals");
        this.column1.confWorkingPack(columns.get(0), this);
        this.column2.confWorkingPack(columns.get(1), this);
        this.column3.confWorkingPack(columns.get(2), this);
        this.column4.confWorkingPack(columns.get(3), this);
        this.column5.confWorkingPack(columns.get(4), this);
        this.column6.confWorkingPack(columns.get(5), this);
        this.column7.confWorkingPack(columns.get(6), this);
        this.preview.confPreview((CardDeck) data.get("preview"), this);
        CardDeck deck = (CardDeck) data.get("pack");
        Card backCard = factory.createCard(Card.Color.CLUBS, 1);
        PreviewController prev = this.preview;
        this.pack.confPack(deck, prev, backCard, this);
        this.goal1.confTargetPack(goals.get(0), this);
        this.goal2.confTargetPack(goals.get(1), this);
        this.goal3.confTargetPack(goals.get(2), this);
        this.goal4.confTargetPack(goals.get(3), this);
        this.history = (History) data.get("history");
        this.updateView();
    }

    @Override
    public void updateView() {
        this.column1.updateView();
        this.column2.updateView();
        this.column3.updateView();
        this.column4.updateView();
        this.column5.updateView();
        this.column6.updateView();
        this.column7.updateView();
        this.preview.updateView();
        this.pack.updateView();
        this.goal1.updateView();
        this.goal2.updateView();
        this.goal3.updateView();
        this.goal4.updateView();
    }

    void addToHistory(Command command) {
        this.history.add(command);
    }

    public void newGameHandler(ActionEvent event) {
        this.main.newGame(this.id);
    }

    public void openGameHandler(ActionEvent event) throws IOException, ClassNotFoundException {
        this.main.openGame(this.id);
    }

    public void saveGameHandler(ActionEvent event) throws IOException {
        this.main.saveGame(this);
    }

    public void newTabHandler(ActionEvent event) {
        this.main.newTab(this);
    }

    public void exitHandler(ActionEvent event) {
        this.main.exitGame(id);
    }

    public void undoHandler(ActionEvent event) {
        this.history.undo();
        this.updateView();
    }

    public void hintHandler(ActionEvent event) {
        if(this.hinter == null) createHinter();
        hinter.hint();
    }

    private void createHinter() {
        this.hinter = new Hinter();
        this.hinter.addColumns(this.column1);
        this.hinter.addColumns(this.column2);
        this.hinter.addColumns(this.column3);
        this.hinter.addColumns(this.column4);
        this.hinter.addColumns(this.column5);
        this.hinter.addColumns(this.column6);
        this.hinter.addColumns(this.column7);
        this.hinter.addGoals(this.goal1);
        this.hinter.addGoals(this.goal2);
        this.hinter.addGoals(this.goal3);
        this.hinter.addGoals(this.goal4);
        this.hinter.setPack(this.pack);
        this.hinter.setPreview(this.preview);
    }

    public void helpHandler(ActionEvent event) {
        AlertManager.alertPopUp("Help", "Pravidlá hry: \n\tHra se hrá se standardním balíčkem obsahujícím 52 karet.\n" +
                "Cílem hry je uložit všechny katry do cílových políček\n" +
                "podle barvy a od nejmenší karty (A) po nejvěčší kartu (K). \n");
    }

    public void aboutHandler(ActionEvent event) {
        AlertManager.alertPopUp("About", "Projekt do IJA - Seminář Java\n\nNázov: \tPasians (Solitaire) Klondike\n\nČlenové týmu: \tRichard Bureš (xbures29)\n" +
                "\t\t\tMária Halamová (xhalam14)");
    }

    void winGame(){
        if ((this.goal1.getBackend().size() == 13) && (this.goal2.getBackend().size() == 13)
                && (this.goal3.getBackend().size() == 13) && (this.goal4.getBackend().size() == 13)){
            AlertManager.alertPopUpNew("Congratulatios!", "Gratulujeme k výhre.\n\tChete si zahrať novú hru?", "New Game", this);
        }
    }

    int getGameId() {
        return this.id;
    }
}
