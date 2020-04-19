package com.avatarduel.model.gui;

import com.avatarduel.model.cards.Effect;
import com.avatarduel.model.cards.SkillCard;
import com.avatarduel.model.player.Phase;
import com.avatarduel.util.InvalidActionException;
import com.avatarduel.util.AlertBox;

import javafx.scene.input.MouseEvent;

/**
 * SkillMinicardController extends the base MinicardController class and it implements
 * the FieldCard interface signifies that it can be put on the field
 */
public class SkillMinicardController extends MinicardController implements FieldCard {

    private SkillCard card;

    /**
     * The SkillMinicardController constructor
     * @param controller the game root controller
     * @param card the card shown
     */
    public SkillMinicardController(GameController controller, SkillCard card)
    {
        super(controller);
        this.card = card;
        this.cardType.setText("Skill (" + this.card.getEffect().toString() + ")");
        this.cardName.setText(card.getName());
        this.cardElement.setText(card.getElement().toString());
        this.cardUseButton.setText("Select");

        if(card.getEffect() != Effect.DESTROY)
        {
            this.cardAttack.setText(Integer.toString(card.getAttack()));
            this.cardDefense.setText(Integer.toString(card.getDefense()));
            this.cardPower.setText(Integer.toString(card.getPower()));
        }
        else
        {
            this.cardAttack.setText("-");
            this.cardDefense.setText("-");
            this.cardPower.setText("-");    
        }

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
    
    /**
     * The method to select this card as the current selected card
     */
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