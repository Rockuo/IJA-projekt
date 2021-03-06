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

import backend.History.DragAndDropCommand;
import backend.History.Logger;
import gui.controllers.GameController;
import gui.controllers.CardController;
import interfaces.CardDeck;
import interfaces.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;


/**
 * Třída ovládající Cílový balíček
 *
 * @author xbures29+xhalam14
 */
public class GoalController extends AnchorPane implements Controller {
    private CardDeck targetPack;
    private GameController game;
    @FXML
    private CardController cardImage;

    /**
     * Vykreslí balíček a nastaví event handlery
     */
    public GoalController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/views/goal.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.setOnDragDetected(this::dragFrom);
        this.setOnDragOver(this::dragOver);
        this.setOnDragDropped(this::dragDropped);
    }

    void confTargetPack(CardDeck target, GameController game) {
        this.setOpacity(1);
        this.targetPack = target;
        this.game = game;
        cardImage.setDefaultImage(targetPack.getColor().toString());
        this.updateView();
    }

    @Override
    public CardDeck save() {
        return this.targetPack;
    }

    @Override
    public void updateView() {
        if (!this.targetPack.isEmpty()) {
            this.setOpacity(1);
        }
        this.cardImage.confCard(this.targetPack.get(), this.game);
        this.hideHint();
    }

    private void dragFrom(MouseEvent event) {
        if (this.targetPack.isEmpty()) return;
        Logger.setSrc(this.targetPack);
        ClipboardContent content = new ClipboardContent();
        content.putString("");
        this.startDragAndDrop(TransferMode.ANY).setContent(content);
        event.consume();
    }

    private void dragOver(DragEvent event) {
        if (event.getGestureSource() != this && this.game.getGameId() == Logger.getGameId()) {
            event.acceptTransferModes(TransferMode.MOVE);
        }
        event.consume();
    }

    private void dragDropped(DragEvent event) {
        Logger.setDest(this.targetPack);
        DragAndDropCommand command = new DragAndDropCommand();
        if (command.exec()) {
            this.game.addToHistory(command);
            this.game.updateView();
        }
        event.consume();
        this.game.winGame();
    }

    /**
     * Získej Backend Cíle
     *
     * @return Backend Cíle
     */
    public CardDeck getBackend() {
        return this.targetPack;
    }

    /**
     * Zobraz nápovědu
     */
    public void showHint() {
        this.getChildren().get(0).setStyle("-fx-background-radius: 5; -fx-border-radius: 5; -fx-border-color: #25ff20;");
    }

    private void hideHint() {
        this.getChildren().get(0).setStyle("-fx-background-radius: 10; -fx-border-radius: 10;");
    }
}
