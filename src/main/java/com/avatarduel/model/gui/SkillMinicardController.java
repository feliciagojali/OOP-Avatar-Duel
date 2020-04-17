package com.avatarduel.model.gui;

import com.avatarduel.model.cards.Effect;
import com.avatarduel.model.cards.SkillCard;
import com.avatarduel.model.player.Phase;

public class SkillMinicardController extends MinicardController implements FieldCard, OneUseCard {

    private SkillCard card;

    public SkillMinicardController(GameController controller, SkillCard card)
    {
        super(controller);
        this.card = card;
        this.cardType.setText("Skill (" + this.card.getEffect().toString() + ")");
        this.cardName.setText(card.getName());
        this.cardElement.setText(card.getElement().toString());
        this.cardUseButton.setText("Select");

        if(card.getEffect() == Effect.DESTROY)
        {
            this.cardAttack.setText("-");
            this.cardDefense.setText("-");
            this.cardPower.setText("-");
            this.cardUseButton.setOnAction(e -> {
                this.selectCard();
                this.useCard();
            });
        }
        else
        {
            this.cardAttack.setText(Integer.toString(card.getAttack()));
            this.cardDefense.setText(Integer.toString(card.getDefense()));
            this.cardPower.setText(Integer.toString(card.getPower()));
            this.cardUseButton.setOnAction(e -> {
                this.selectCard();
            });
        }
    }

    public void useCard()
    {
        // ..?
    }
    
    public void selectCard()
    {
        try
        {
            if(this.gameController.getPhase() != Phase.MAIN) { throw new ErrorException("You can't do this action in this phase."); }
            
            this.gameController.getActivePlayer().selectCard(this.getMinicardIndexInHand());
            this.gameController.setSelectedCardIndex(this.getMinicardIndexInHand());
    
        }
        catch(ErrorException e)
        {
            ShowError.showError(e.getMessage());
        }
    }
}