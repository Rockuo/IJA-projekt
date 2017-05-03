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

import backend.History.History;
import gui.elements.card.CardController;
import interfaces.Card;
import interfaces.CardStack;
import interfaces.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.io.Serializable;

/**
 *
 */
public class ColumnController extends AnchorPane implements Controller{
    private CardStack workingPack;
    private boolean big = true;
    private History history;

    @FXML
    private CardController card1;
    @FXML
    private CardController card2;
    @FXML
    private CardController card3;
    @FXML
    private CardController card4;
    @FXML
    private CardController card5;
    @FXML
    private CardController card6;
    @FXML
    private CardController card7;
    @FXML
    private CardController card8;
    @FXML
    private CardController card9;
    @FXML
    private CardController card10;
    @FXML
    private CardController card11;
    @FXML
    private CardController card12;
    @FXML
    private CardController card13;
    @FXML
    private CardController card14;
    @FXML
    private CardController card15;
    @FXML
    private CardController card16;
    @FXML
    private CardController card17;
    @FXML
    private CardController card18;
    @FXML
    private CardController card19;

    public ColumnController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("column.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void confWorkingPack(CardStack workingPack, History history){
        this.workingPack = workingPack;
        this.history = history;
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
    public void updateView(){
        if (!this.workingPack.isEmpty()){
            this.workingPack.get().turnFaceUp();
        }
        for (int i=0; i<this.workingPack.size(); i++){
            switch (i){
                case 0: this.card1.confCard(this.workingPack.get(i), this.history);
                    break;
                case 1: this.card2.confCard(this.workingPack.get(i), this.history);
                    break;
                case 2: this.card3.confCard(this.workingPack.get(i), this.history);
                    break;
                case 3: this.card4.confCard(this.workingPack.get(i), this.history);
                    break;
                case 4: this.card5.confCard(this.workingPack.get(i), this.history);
                    break;
                case 5: this.card6.confCard(this.workingPack.get(i), this.history);
                    break;
                case 6: this.card7.confCard(this.workingPack.get(i), this.history);
                    break;
                case 7: this.card8.confCard(this.workingPack.get(i), this.history);
                    break;
                case 8: this.card9.confCard(this.workingPack.get(i), this.history);
                    break;
                case 9: this.card10.confCard(this.workingPack.get(i), this.history);
                    break;
                case 10: this.card11.confCard(this.workingPack.get(i), this.history);
                    break;
                case 11: this.card12.confCard(this.workingPack.get(i), this.history);
                    break;
                case 12: this.card13.confCard(this.workingPack.get(i), this.history);
                    break;
                case 13: this.card14.confCard(this.workingPack.get(i), this.history);
                    break;
                case 14: this.card15.confCard(this.workingPack.get(i), this.history);
                    break;
                case 15: this.card16.confCard(this.workingPack.get(i), this.history);
                    break;
                case 16: this.card17.confCard(this.workingPack.get(i), this.history);
                    break;
                case 17: this.card18.confCard(this.workingPack.get(i), this.history);
                    break;
                case 18: this.card19.confCard(this.workingPack.get(i), this.history);
                    break;
            }
        }
    }

    @Override
    public void resize(boolean big) {

    }
}
