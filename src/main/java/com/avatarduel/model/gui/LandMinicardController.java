package com.avatarduel.model.gui;

import com.avatarduel.model.cards.LandCard;
import com.avatarduel.model.player.Phase;

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
    }
    
    public void useCard()
    {
        try
        {
            if (this.gameController.getPhase() != Phase.MAIN) { throw new ErrorException("You can not do this now!");}
            if (this.gameController.isLandCardUsed()) { throw new ErrorException("Land card already used in this turn!"); }

            this.gameController.getActivePlayer().useCard(this.getMinicardIndexInHand());
            this.gameController.setStatsInterface();
            this.gameController.setLandCardUsed();
            this.removeMinicard();
        }
        catch(ErrorException e)
        {
            ShowError.showError(e.getMessage());
        }
    }
    
    @FXML
    public void showCardInfo()
    {
        this.gameController.setCardInfo(this.card);
    }
}