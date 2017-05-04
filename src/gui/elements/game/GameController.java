/*
 * Copyright (c) 2011, 2014 Oracle and/or its affiliates.
 * All rights reserved. Use is subject to license terms.
 *
 * This file is available and licensed under the following license:
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  - Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the distribution.
 *  - Neither the name of Oracle nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package gui.elements.game;


import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

import abstractFactories.AbstractFactorySolitaire;
import backend.History.History;
import factories.FactoryKlondike;
import gui.elements.column.ColumnController;
import gui.elements.goal.GoalController;
import gui.elements.pack.PackController;
import gui.elements.preview.PreviewController;
import gui.klondike.Main;
import gui.klondike.MainController;
import interfaces.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuItem;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 */
public class GameController extends AnchorPane {

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

    private History history;
    private int id = 0;

    public void setDefaultValues(MainController main, int id) {
        this.main = main;
        this.id = id;
    }
    private boolean confd = false;
    private MainController main;


    public GameController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("game.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.history = new History();
    }

    public void undo() {
        this.history.undo();
        this.updateView();
    }

    public boolean isConfd() {
        return confd;
    }

    public void configure(AbstractFactorySolitaire factory, int id, MainController main) {
        this.confd = true;
        this.id = id;
        this.main = main;
        this.column1.confWorkingPack(factory.createWorkingPack(), this.history);
        this.column2.confWorkingPack(factory.createWorkingPack(), this.history);
        this.column3.confWorkingPack(factory.createWorkingPack(), this.history);
        this.column4.confWorkingPack(factory.createWorkingPack(), this.history);
        this.column5.confWorkingPack(factory.createWorkingPack(), this.history);
        this.column6.confWorkingPack(factory.createWorkingPack(), this.history);
        this.column7.confWorkingPack(factory.createWorkingPack(), this.history);
        this.preview.confPreview(factory.createPreview(), this.history);
        this.pack.confPack(factory.createCardDeck(), this.preview, factory.createCard(Card.Color.CLUBS, 1), this.history);
        this.goal1.confTargetPack(factory.createTargetPack(Card.Color.SPADES), this.history);
        this.goal2.confTargetPack(factory.createTargetPack(Card.Color.CLUBS), this.history);
        this.goal3.confTargetPack(factory.createTargetPack(Card.Color.DIAMONDS), this.history);
        this.goal4.confTargetPack(factory.createTargetPack(Card.Color.HEARTS), this.history);
//        generateCards();
    }

    public void generateCards() {
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
        return saved;
    }

    public void open(HashMap<String, Object> data, AbstractFactorySolitaire factory, int id, MainController main) {
        this.id = id;
        this.main = main;
        ArrayList<CardStack> columns = (ArrayList<CardStack>) data.get("columns");
        ArrayList<CardDeck> goals = (ArrayList<CardDeck>) data.get("goals");
        this.column1.confWorkingPack(columns.get(0), this.history);
        this.column2.confWorkingPack(columns.get(1), this.history);
        this.column3.confWorkingPack(columns.get(2), this.history);
        this.column4.confWorkingPack(columns.get(3), this.history);
        this.column5.confWorkingPack(columns.get(4), this.history);
        this.column6.confWorkingPack(columns.get(5), this.history);
        this.column7.confWorkingPack(columns.get(6), this.history);
        this.preview.confPreview((CardDeck) data.get("preview"), this.history);
        CardDeck deck = (CardDeck) data.get("pack");
        Card backCard = factory.createCard(Card.Color.CLUBS, 1);
        PreviewController prev = this.preview;
        this.pack.confPack(deck, prev, backCard, this.history);
        this.goal1.confTargetPack(goals.get(0), this.history);
        this.goal2.confTargetPack(goals.get(1), this.history);
        this.goal3.confTargetPack(goals.get(2), this.history);
        this.goal4.confTargetPack(goals.get(3), this.history);
    }

    private void updateView() {
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

    public void addToHistory(Command command) {
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

    public void undoHandler(ActionEvent event) {
        this.undo();
    }

    public void newTabHandler(ActionEvent event) {
        this.main.newTab(this);
    }

    public void exitHandler(ActionEvent event) {
        this.main.exitGame(id);
    }

    public void cardsHandler(ActionEvent event) {

    }

    public void helpHandler(ActionEvent event) {

    }

    public void aboutHandler(ActionEvent event) {

    }
}
