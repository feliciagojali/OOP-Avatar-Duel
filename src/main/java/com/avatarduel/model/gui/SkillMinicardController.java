package com.avatarduel.model.gui;

import com.avatarduel.model.cards.Effect;
import com.avatarduel.model.cards.SkillCard;
import com.avatarduel.model.player.Phase;
import com.avatarduel.util.InvalidActionException;
import com.avatarduel.util.AlertBox;

import javafx.scene.input.MouseEvent;

public class SkillMinicardController extends MinicardController implements FieldCard {

    private SkillCard card;

    public SkillMinicardController(GameController controller, SkillCard card)
    {
        super(controller);
        this.card = card;
        this.cardType.setText("Skill (" + this.card.getEffect().toString() + ")");
        this.cardName.setText(card.getName());
        this.cardElement.setText(card.getElement().toString());
        this.cardUseButton.setText("Select");

        this.cardAttack.setText(Integer.toString(card.getAttack()));
        this.cardDefense.setText(Integer.toString(card.getDefense()));
        this.cardPower.setText(Integer.toString(card.getPower()));
        this.cardUseButton.setOnAction(e -> {
            this.selectCard();
        });

        this.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
            this.gameController.setCardInfo(this.card);
        });
        this.addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
            this.gameController.getCardInfo().getChildren().clear();
        });
    }
    
    public void selectCard()
    {
        try
        {
            if(this.gameController.getPhase() != Phase.MAIN) { throw new InvalidActionException("You can't do this action in this phase."); }
            
            this.gameController.getActivePlayer().selectCard(this.getMinicardIndexInHand());
            this.gameController.setSelectedCardIndex(this.getMinicardIndexInHand());
    
        }
        catch(InvalidActionException e)
        {
            AlertBox.showError(e.getMessage());
        }
    }

}