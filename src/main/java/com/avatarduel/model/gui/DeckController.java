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
    private GameController gameController;

    public DeckController(GameController controller)
    {
        FXMLLoader deckLoader = new FXMLLoader(AvatarDuel.class.getResource("gui/deck.fxml"));
        deckLoader.setRoot(this);
        deckLoader.setController(this);
        
        try
        {
            deckLoader.load();
            this.gameController = controller;
            this.cardLeftLabel.setText(Integer.toString(this.gameController.getActivePlayer().getDeck().getCardsLeft()));
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
        this.gameController.getActivePlayer().drawCard();
        this.setCardLeftLabelText(this.gameController.getActivePlayer().getDeck().getCardsLeft());
        this.gameController.getHandController().displayHand();
    }

    @FXML
    public void setCardLeftLabelText(int x)
    {
        this.cardLeftLabel.setText(Integer.toString(x));
    }

}