package com.avatarduel.model.gui;

import javafx.scene.control.Label;

import java.io.IOException;

import com.avatarduel.AvatarDuel;
import com.avatarduel.model.cards.Card;
import com.avatarduel.model.player.Deck;
import com.avatarduel.model.player.Player;

import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class DeckController extends AnchorPane {

    @FXML private Label cardLeftLabel;
    private Player activePlayer;
    private HandController handController;

    public DeckController(Player player, HandController handController)
    {
        FXMLLoader deckLoader = new FXMLLoader(AvatarDuel.class.getResource("gui/deck.fxml"));
        deckLoader.setRoot(this);
        deckLoader.setController(this);
        
        try
        {
            deckLoader.load();
            this.activePlayer = player;
            this.handController = handController;
            this.cardLeftLabel.setText(Integer.toString(this.activePlayer.getDeck().getCardsLeft()));
        }
        catch(IOException e)
        {
            throw new RuntimeException(e);
        }

        // this.cardLeftLabel.setText(Integer.toString(this.deck.getCardsLeft()));
    }
    
    @FXML
    public void drawCard()
    {
        this.activePlayer.drawCard();
        this.setCardLeftLabelText(this.activePlayer.getDeck().getCardsLeft());
        this.handController.displayHand();
    }

    @FXML
    public void setCardLeftLabelText(int x)
    {
        this.cardLeftLabel.setText(Integer.toString(x));
    }

}