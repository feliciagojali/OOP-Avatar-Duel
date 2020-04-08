package com.avatarduel.model.gui;

import javafx.scene.control.Label;

import com.avatarduel.model.cards.Card;
import com.avatarduel.model.player.Deck;

import javafx.fxml.FXML;

public class DeckController {
    
    @FXML private Label cardLeftLabel;
    private Deck deck;

    public void initialize()
    {
        this.deck = new Deck();
        this.deck.initializeDeck();
        this.deck.shuffle();
        this.cardLeftLabel.setText(Integer.toString(this.deck.getCardsLeft()));
    }
    
    @FXML
    public int decrement()
    {
        this.cardLeftLabel.setText(Integer.toString(this.deck.getCardsLeft() - 1));
        return this.deck.drawCard();
    }

}