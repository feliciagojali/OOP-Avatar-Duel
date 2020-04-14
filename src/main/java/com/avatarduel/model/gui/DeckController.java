package com.avatarduel.model.gui;

import javafx.scene.control.Label;

import java.io.IOException;

import com.avatarduel.AvatarDuel;
import com.avatarduel.model.cards.Card;
import com.avatarduel.model.player.Deck;
import com.avatarduel.model.player.Player;
import com.avatarduel.model.player.Phase;


import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
public class DeckController extends AnchorPane {

    @FXML private Label cardLeftLabel;
    private Player activePlayer;
    private Phase phase;
    private HandController handController;
    private boolean hasDraw;

    public DeckController(Player player, HandController handController,Phase phase)
    {
        FXMLLoader deckLoader = new FXMLLoader(AvatarDuel.class.getResource("gui/deck.fxml"));
        deckLoader.setRoot(this);
        deckLoader.setController(this);
        
        try
        {
            deckLoader.load();
            this.activePlayer = player;
            this.handController = handController;
            this.phase = phase;
            this.hasDraw = false;
            this.cardLeftLabel.setText(Integer.toString(this.activePlayer.getDeck().getCardsLeft()));
        }
        catch(IOException e)
        {
            throw new RuntimeException(e);
        }

        // this.cardLeftLabel.setText(Integer.toString(this.deck.getCardsLeft()));
    }
    
    @FXML
    public void drawCard() {
        try {
            drawCards();
        } catch (ErrorException e) {
            ShowError.showError(e.getMessage());
        }

    }
    public void drawCards() throws ErrorException {
        if (this.hasDraw){
            throw new ErrorException("You can only draw once!");
        } else if (this.phase != Phase.draw) {
            throw new ErrorException("You can not do this now!");
        }
        this.activePlayer.drawCard();
        this.setCardLeftLabelText(this.activePlayer.getDeck().getCardsLeft());
        this.handController.displayHand();
        this.hasDraw = true;
    }
    

    @FXML
    public void setCardLeftLabelText(int x)
    {
        this.cardLeftLabel.setText(Integer.toString(x));
    }

    public boolean hasDraw(){
        return this.hasDraw;
    }
}

    