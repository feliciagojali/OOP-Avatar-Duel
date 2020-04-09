package com.avatarduel.model.gui;

import javafx.scene.control.Label;

import java.io.IOException;

import com.avatarduel.AvatarDuel;
import com.avatarduel.model.cards.Card;
import com.avatarduel.model.player.Deck;

import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class DeckController extends AnchorPane {

    @FXML private Label cardLeftLabel;

    public DeckController(Deck deck)
    {
        FXMLLoader deckLoader = new FXMLLoader(AvatarDuel.class.getResource("gui/deck.fxml"));
        deckLoader.setRoot(this);
        deckLoader.setController(this);
        
        try
        {
            deckLoader.load();
            this.cardLeftLabel.setText(Integer.toString(deck.getCardsLeft()));
        }
        catch(IOException e)
        {
            throw new RuntimeException(e);
        }

        // this.cardLeftLabel.setText(Integer.toString(this.deck.getCardsLeft()));
    }
    
    @FXML
    public void setCardLeftLabelText(int x)
    {
        this.cardLeftLabel.setText(Integer.toString(x));
    }

}