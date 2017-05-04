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

package gui.elements.pack;

import backend.History.DragAndDropCommand;
import backend.History.PrevToPackCommand;
import gui.elements.card.CardController;
import gui.elements.game.GameController;
import gui.elements.preview.PreviewController;
import interfaces.Card;
import interfaces.CardDeck;
import interfaces.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


import java.io.IOException;
import java.util.ArrayList;

/**
 * Sample custom control hosting a text field and a button.
 */
public class PackController extends AnchorPane implements Controller{

    private CardDeck cardDeck;
    private PreviewController preview;
    @FXML
    private CardController cardFX;
    private Card backCard;
    private GameController game;

    public PackController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("pack.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.setOnMouseClicked(this::mouseClick);
    }

    public void confPack(CardDeck deck, PreviewController preview, Card backCard, GameController game) {
        this.cardDeck = deck;
        this.preview = preview;
        this.backCard = backCard;
        this.game = game;
        this.cardFX.confCard(this.backCard, game);
        this.cardFX.setDefaultImage("circle");
    }

    public Card getCard() {
        return cardDeck.pop();
    }

    private void mouseClick(MouseEvent event) {
        if (this.cardDeck.isEmpty()) {
            ArrayList<Card> cards = this.preview.getAllCards();
            if (cards.size() == 0) return;
            int size = cards.size();
            for (int i = 0; i < size; i++) {
                this.cardDeck.put(cards.remove(0));
            }
            this.game.addToHistory(new PrevToPackCommand(this.preview.getDeck(), this.cardDeck));
        } else {
            Card card = this.cardDeck.pop();
            card.turnFaceUp();
            preview.addCard(card);
            this.game.addToHistory(new DragAndDropCommand(this.cardDeck, this.preview.getDeck(),null));
        }
        this.updateView();
    }

    public CardDeck save(){
        return this.cardDeck;
    }

    @Override
    public void updateView() {
        if (this.cardDeck.isEmpty()) {
            this.cardFX.updateCard(null);
        } else {
            this.cardFX.updateCard(this.backCard);
        }
        this.hideHint();
    }

    @Override
    public void resize(boolean big) {

    }

    public void showHint(){
        this.getChildren().get(0).setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: aliceblue;");
    }


    public void hideHint(){
        this.getChildren().get(0).setStyle("-fx-background-radius: 10; -fx-border-radius: 10;");
    }
}
