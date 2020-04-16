package com.avatarduel.model.gui;

import com.avatarduel.model.cards.CharacterCard;
import com.avatarduel.model.player.Phase;

import javafx.fxml.FXML;

public class CharacterMinicardController extends MinicardController implements FieldCard {
    
    private CharacterCard card;

    public CharacterMinicardController(GameController controller, CharacterCard card)
    {
        super(controller);
        this.card = card;
        this.cardType.setText("Character"); 
        this.cardName.setText(card.getName());
        this.cardElement.setText(card.getElement().toString());
        this.cardAttack.setText(Integer.toString(card.getAttack()));
        this.cardDefense.setText(Integer.toString(card.getDefense()));
        this.cardPower.setText(Integer.toString(card.getPower()));
        this.cardUseButton.setText("Select");
        this.cardUseButton.setOnAction(e -> {
            this.selectCard();
        });
    }

    public void selectCard()
    {
        try{
            if(this.gameController.getPhase() != Phase.MAIN) { throw new ErrorException("You can't do this action in this phase."); }

            this.gameController.getActivePlayer().selectCard(this.getMinicardIndexInHand());
            this.gameController.setSelectedCardIndex(this.getMinicardIndexInHand());
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