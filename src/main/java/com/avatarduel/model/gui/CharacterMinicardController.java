package com.avatarduel.model.gui;

import com.avatarduel.model.cards.CharacterCard;
import com.avatarduel.model.player.Phase;
import com.avatarduel.util.AlertBox;
import com.avatarduel.util.InvalidActionException;
import com.avatarduel.util.AlertBox;

import javafx.scene.input.MouseEvent;

/**
 * CharacterMinicardController is the class that extends the base class
 * MinicardController and it implements the FieldCard interface, 
 * meaning it can be used on the field.
 * This class is used to make a representation of a character card.
 * @author mkamadeus
 * 
 */
public class CharacterMinicardController extends MinicardController implements FieldCard {
    
    private CharacterCard card;

    /**
     * Constructor for CharacterMinicardController class
     * @param controller the game root controller
     * @param card the character card shown
     */

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
        this.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
            this.gameController.setCardInfo(this.card);
        });
        this.addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
            this.gameController.getCardInfo().getChildren().clear();
        });
    }

    /**
     * Procedure for selecting a card
     * Saves the index of the card selected in the root controller
     */
    public void selectCard()
    {
        try{
            if(this.gameController.getPhase() != Phase.MAIN) { throw new InvalidActionException("You can't do this action in this phase."); }

            this.gameController.getActivePlayer().selectCard(this.getMinicardIndexInHand());
            this.gameController.setSelectedCardIndex(this.getMinicardIndexInHand());
        }
        catch(InvalidActionException e)
        {
            AlertBox.showError(e.getMessage());
        }
    }

    /**
     * Procedure to change the appearance of a character card on defense
     */
    public void setStanceColor()
    {
        this.setStyle("-fx-background-color: #64ffda;-fx-border-radius:5;-fx-border-color:#14cba8;");
    }
}