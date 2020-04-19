package com.avatarduel.model.gui;

import com.avatarduel.AvatarDuel;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * MinicardController class is the base class
 * for all of the minicards.
 * @author mkamadeus
 */
public class MinicardController extends AnchorPane {
    @FXML protected Label cardType;
    @FXML protected Label cardName;
    @FXML protected Label cardElement;    
    @FXML protected Label cardAttack;
    @FXML protected Label cardDefense;
    @FXML protected Label cardPower;
    @FXML protected Button cardUseButton;
    
    protected GameController gameController;
    
    /**
     * The Minicard base concstructor, loads the FXML only
     * @param controller the game root controller
     */
    public MinicardController(GameController controller)
    {
        FXMLLoader minicardLoader = new FXMLLoader(AvatarDuel.class.getResource("gui/minicard.fxml"));
        minicardLoader.setRoot(this);
        minicardLoader.setController(this);
        try {
            minicardLoader.load();
            this.gameController = controller;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * Removes the use button (for field usage)
     */
    public void removeCardUseButton()
    {
        this.getChildren().remove(this.cardUseButton);
    }

    /**
     * Gets the current index of minicard when in hand
     */
    public int getMinicardIndexInHand()
    {
        return ((HandController)this.getParent()).getChildren().indexOf(this);
    }

    /**
     * Remove self from hand
     */
    public void removeMinicard()
    {
        ((HandController)this.getParent()).getChildren().remove(this);
    }
}