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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.*;

import interfaces.Card;

import java.io.IOException;

/**
 * Sample custom control hosting a text field and a button.
 */
public class CardController extends AnchorPane {

    @FXML
    private ImageView cardImage;
    private Card card = null;
    private GameController game;
    private String defaultImage = "S";

    public CardController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/card.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.setOnDragDetected(this::dragFrom);
    }

    public void confCard(Card card, GameController game) {
        this.card = card;
        this.game = game;
        this.updateView();
    }

    public void updateCard(Card card) {
        this.card = card;
        this.updateView();
    }

    public void updateView() {
        String imagePath;
        if (card != null) {
            String name = "back";
            if (card.isTurnedFaceUp()) {
                name = card.toString();
            }
            imagePath = getClass().getResource("/images/cardPack/" + name + ".png").toString();
        } else {
            imagePath = getClass().getResource("/images/" + this.defaultImage + ".png").toString();
        }
        cardImage.setImage(new Image(imagePath));
    }

    public void setDefaultImage(String name) {
        this.defaultImage = name;
    }

    private void dragFrom(MouseEvent event) {
        Logger.clean();
        Logger.setGameId(this.game.getGameId());
        Logger.setCard(this.card);
    }

    public Card getCard(){
        return this.card;
    }
}