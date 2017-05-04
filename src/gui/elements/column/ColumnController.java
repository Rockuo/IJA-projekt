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

package gui.elements.column;

import backend.History.CommonCommand;
import backend.History.History;
import backend.History.Logger;
import gui.elements.card.CardController;
import gui.elements.game.GameController;
import interfaces.Card;
import interfaces.CardStack;
import interfaces.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 */
public class ColumnController extends AnchorPane implements Controller{
    private CardStack workingPack;
    private boolean big = true;
    private GameController game;
    private ArrayList<CardController> cards;

    public ColumnController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("column.fxml"));
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
        this.cards = new ArrayList<>();
    }

    public void confWorkingPack(CardStack workingPack, GameController game){
        this.workingPack = workingPack;
        this.game = game;
    }

    public CardStack save() {
        return this.workingPack;
    }

    public CardStack getBackend() {
        return this.workingPack;
    }

    public void putForce(Card card){
        this.workingPack.putForce(card);
    }

    @Override
    public void updateView() {
        while (!this.cards.isEmpty()) {
            this.cards.remove(0);
            this.getChildren().remove(0);
        }

        if (!this.workingPack.isEmpty()) {
            this.workingPack.get().turnFaceUp();
        }
        for (int i=0; i<this.workingPack.size(); i++){
            CardController cardController = new CardController();
            cardController.confCard(this.workingPack.get(i), this.game);
            cardController.setLayoutY(15*i);
            this.cards.add(cardController);
            this.getChildren().add(cardController);
        }
    }

    @Override
    public void resize(boolean big) {

    }

    private void dragFrom(MouseEvent event) {
        System.out.print(Logger.getCard());
        if(this.workingPack.isEmpty()) return;
        Logger.setSrc(this.workingPack);
        ClipboardContent content = new ClipboardContent();
        content.putString("");
        this.startDragAndDrop(TransferMode.ANY).setContent(content);
        event.consume();
    }

    private void dragOver(DragEvent event) {
        if (event.getGestureSource() != this) {
            event.acceptTransferModes(TransferMode.MOVE);
        }
        event.consume();
    }

    private void dragDropped(DragEvent event) {
        Logger.setDest(this.workingPack);
        CommonCommand command = new CommonCommand();
        if(command.exec()){
            this.game.addToHistory(command);
            this.game.updateView();
        }
        event.consume();
    }
}
