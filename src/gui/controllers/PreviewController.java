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

package gui.controllers;

import backend.History.Logger;
import gui.controllers.CardController;
import gui.controllers.GameController;
import interfaces.CardDeck;
import interfaces.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;

import interfaces.Card;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Sample custom control hosting a text field and a button.
 */
public class PreviewController extends AnchorPane implements Controller {

    private CardDeck preview;
    @FXML
    private CardController cardFX;
    private GameController game;

    public PreviewController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/views/preview.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.setOnDragDetected(this::dragFrom);
    }

    public void confPreview(CardDeck preview, GameController game) {
        this.preview = preview;
        this.game = game;
        updateView();
    }

    public void addCard(Card card) {
        this.preview.put(card);
        this.updateView();
    }

    public ArrayList<Card> getAllCards() {
        ArrayList<Card> cards = new ArrayList<>();
        while (!preview.isEmpty())
            cards.add(this.preview.pop());
        this.updateView();
        return cards;
    }

    public void updateView() {
        if(this.preview.isEmpty()) {
            cardFX.confCard(null, this.game);
            this.setOpacity(0);
        }else {
            cardFX.confCard(this.preview.get(), this.game);
            this.setOpacity(1);
        }
        this.hideHint();
    }

    public CardDeck getDeck() {
        return this.preview;
    }

    public CardDeck save() {
        return this.preview;
    }

    private void dragFrom(MouseEvent event) {
        if(this.preview.isEmpty()) return;
        Logger.setSrc(this.preview);
        ClipboardContent content = new ClipboardContent();
        content.putString("");
        this.startDragAndDrop(TransferMode.ANY).setContent(content);
        event.consume();
    }

    public CardDeck getBackend(){
        return this.preview;
    }

    public void showHint(){
        ((CardController)this.getChildren().get(0)).setStyle("-fx-background-radius: 5; -fx-border-radius: 5; -fx-border-color: #25ff20;");
    }


    public void hideHint(){
        this.getChildren().get(0).setStyle("-fx-background-radius: 10; -fx-border-radius: 10;");
    }

}
