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

        // this.cardLeftLabel.setText(Integer.toString(this.deck.getCardsLeft()));
    }
    
    @FXML
    public void drawCard() {
        try {
            drawCards();
        } catch (InvalidActionException e) {
            AlertBox.showError(e.getMessage());
        }

    }
    public void drawCards() throws InvalidActionException {
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
    }
    

    @FXML
    public void setCardLeftLabelText(int x)
    {
        this.cardLeftLabel.setText(Integer.toString(x));
    }

    public boolean hasDrawn(){
        return this.hasDraw;
    }
}

    