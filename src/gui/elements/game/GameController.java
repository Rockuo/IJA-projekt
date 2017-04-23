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


import java.io.IOException;

import abstractFactories.AbstractFactorySolitaire;
import gui.elements.column.ColumnController;
import gui.elements.goal.GoalController;
import gui.elements.pack.PackController;
import gui.elements.preview.PreviewController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import interfaces.Card;

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

    private AbstractFactorySolitaire factory;
    public GameController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("game.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void setAllElements(AbstractFactorySolitaire factory) {
        this.factory = factory;
        this.column1.setWorkingPack(factory.createWorkingPack());
        this.column2.setWorkingPack(factory.createWorkingPack());
        this.column3.setWorkingPack(factory.createWorkingPack());
        this.column4.setWorkingPack(factory.createWorkingPack());
        this.column5.setWorkingPack(factory.createWorkingPack());
        this.column6.setWorkingPack(factory.createWorkingPack());
        this.column7.setWorkingPack(factory.createWorkingPack());
        this.preview.setPreview(factory.createPreview());
        this.pack.confPack(factory.createCardDeck(),this.preview);
        this.goal1.setTargetPack(factory.createTargetPack(Card.Color.SPADES));
        this.goal2.setTargetPack(factory.createTargetPack(Card.Color.CLUBS));
        this.goal3.setTargetPack(factory.createTargetPack(Card.Color.DIAMONDS));
        this.goal4.setTargetPack(factory.createTargetPack(Card.Color.HEARTS));
    }

    public void generateCards(){

    }
}
