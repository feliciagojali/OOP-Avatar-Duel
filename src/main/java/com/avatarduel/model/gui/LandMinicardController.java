package com.avatarduel.model.gui;

import com.avatarduel.model.cards.LandCard;
import com.avatarduel.model.player.Phase;
import com.avatarduel.util.InvalidActionException;
import com.avatarduel.util.AlertBox;

import javafx.scene.input.MouseEvent;

import javafx.fxml.FXML;

public class LandMinicardController extends MinicardController implements OneUseCard {

    private LandCard card;

    public LandMinicardController(GameController controller, LandCard card)
    {
        super(controller);
        this.card = card;
        this.cardType.setText("Land");
        this.cardName.setText(card.getName());
        this.cardElement.setText(card.getElement().toString());
        this.cardAttack.setText("-");
        this.cardDefense.setText("-");
        this.cardPower.setText("-");

        this.cardUseButton.setOnAction(e -> {
            this.useCard();
        });
        this.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
            this.gameController.setCardInfo(this.card);
        });
        this.addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
            this.gameController.getCardInfo().getChildren().clear();
        });
    }
    
    public void useCard()
    {
        try
        {
            if (this.gameController.getPhase() != Phase.MAIN) { throw new InvalidActionException("You can not do this now!");}
            if (this.gameController.isLandCardUsed()) { throw new InvalidActionException("Land card already used in this turn!"); }

            this.gameController.getActivePlayer().useCard(this.getMinicardIndexInHand());
            this.gameController.setStatsInterface();
            this.gameController.setLandCardUsed();
            this.removeMinicard();
        }
        catch(InvalidActionException e)
        {
            AlertBox.showError(e.getMessage());
        }
    }
    
    @FXML
    public void showCardInfo()
    {
        this.gameController.setCardInfo(this.card);
    }
}