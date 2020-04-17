package com.avatarduel.model.gui;

import com.avatarduel.AvatarDuel;
import com.avatarduel.model.cards.Card;
import com.avatarduel.model.cards.CharacterCard;
import com.avatarduel.model.cards.LandCard;
import com.avatarduel.model.cards.SkillCard;
import com.avatarduel.model.player.Phase;

import org.w3c.dom.events.MouseEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class MinicardController extends AnchorPane {
    @FXML protected Label cardType;
    @FXML protected Label cardName;
    @FXML protected Label cardElement;    
    @FXML protected Label cardAttack;
    @FXML protected Label cardDefense;
    @FXML protected Label cardPower;
    @FXML protected Button cardUseButton;
    
    protected GameController gameController;
    
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
    
    public void removeCardUseButton()
    {
        this.getChildren().remove(this.cardUseButton);
    }

    public int getMinicardIndexInHand()
    {
        return ((HandController)this.getParent()).getChildren().indexOf(this);
    }

    public void removeMinicard()
    {
        ((HandController)this.getParent()).getChildren().remove(this);
    }



}