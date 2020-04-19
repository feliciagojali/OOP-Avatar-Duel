package com.avatarduel.model.gui;

import java.io.IOException;

import com.avatarduel.AvatarDuel;
import com.avatarduel.model.cards.*;

import javafx.scene.layout.HBox;
import javafx.fxml.FXMLLoader;

/**
 * HandController is the class to control the hand interface
 * to store the minicards that is in the hand
 * @author mkamadeus
 */
public class HandController extends HBox{

    private GameController gameController;

    /**
     * The constructor for hand controller
     * @param controller the game root controller
     */
    public HandController(GameController controller)
    {
        FXMLLoader handLoader = new FXMLLoader(AvatarDuel.class.getResource("gui/hand.fxml"));
        handLoader.setRoot(this);
        handLoader.setController(this);

        try
        {
            handLoader.load();
            this.gameController = controller;
            this.displayHand();
        }
        catch(IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * Procedure to display/reset the hand interface based
     * on the current hand
     */
    public void displayHand()
    {
        this.getChildren().clear();

        for(Card card : this.gameController.getActivePlayer().getHand().getCards())
        {
            if(card instanceof CharacterCard)
                this.getChildren().add(new CharacterMinicardController(this.gameController, (CharacterCard)card));
            else if(card instanceof LandCard)
                this.getChildren().add(new LandMinicardController(this.gameController, (LandCard)card));
            else
                this.getChildren().add(new SkillMinicardController(this.gameController, (SkillCard)card));
        }
    }
}