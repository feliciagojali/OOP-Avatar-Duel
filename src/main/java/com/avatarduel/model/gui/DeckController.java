package com.avatarduel.model.gui;

import javafx.scene.control.Label;

import java.io.IOException;

import com.avatarduel.AvatarDuel;
import com.avatarduel.model.player.Phase;
import com.avatarduel.util.InvalidActionException;
import com.avatarduel.util.AlertBox;

import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

/**
 * DeckController is the class that defines the deck interface.
 * @author mkamadeus
 */
public class DeckController extends AnchorPane {

    @FXML private Label cardLeftLabel;
    private GameController gameController;
    private boolean hasDraw;

    public DeckController(GameController controller)
    {
        FXMLLoader deckLoader = new FXMLLoader(AvatarDuel.class.getResource("gui/deck.fxml"));
        deckLoader.setRoot(this);
        deckLoader.setController(this);
        
        try
        {
            deckLoader.load();
            this.gameController = controller;
            this.hasDraw = false;
            this.cardLeftLabel.setText(Integer.toString(this.gameController.getActivePlayer().getDeck().getCardsLeft()));
        }
        catch(IOException e)
        {
            throw new RuntimeException(e);
        }

    }
    
    /**
     * The method to draw a card via the GUI.
     */
    @FXML
    public void drawCard() {
        try {
            if (this.hasDraw){
                throw new InvalidActionException("You can only draw once!");
            } else if (this.gameController.getPhase() != Phase.DRAW) {
                throw new InvalidActionException("You can not do this now!");
            }
            this.gameController.getActivePlayer().drawCard();
            this.setCardLeftLabelText(this.gameController.getActivePlayer().getDeck().getCardsLeft());
            this.gameController.getHandController().displayHand();
            this.hasDraw = true;
            if (this.gameController.getActivePlayer().getDeck().isDeckEmpty()){
                AlertBox.endGame(this.gameController.getOtherPlayer().getName());
            }
        } catch (InvalidActionException e) {
            AlertBox.showError(e.getMessage());
        }

    }    

    /**
     * The method to change the card left label/
     * @param x
     */
    @FXML
    public void setCardLeftLabelText(int x)
    {
        this.cardLeftLabel.setText(Integer.toString(x));
    }

    /**
     * The method to check whether a player has drawn a card or not.
     * @return true if has drawn a card, false if has not
     */
    public boolean hasDrawn(){
        return this.hasDraw;
    }
}

    