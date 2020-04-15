package com.avatarduel.model.gui;

import javafx.scene.control.Label;

import java.io.IOException;

import com.avatarduel.AvatarDuel;
import com.avatarduel.model.cards.*;
import com.avatarduel.model.player.Deck;
import com.avatarduel.model.player.Player;
import com.avatarduel.model.player.Phase;

import javafx.scene.layout.HBox;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class HandController extends HBox{

    private GameController gameController;

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

    public void displayHand()
    {
        this.getChildren().clear();

        for(Card card : this.gameController.getActivePlayer().getHand().getCards())
        {
            if(card instanceof CharacterCard)
                this.getChildren().add(new MinicardController((CharacterCard)card, this.gameController));
            else if(card instanceof LandCard)
                this.getChildren().add(new MinicardController((LandCard)card, this.gameController));
            else
                this.getChildren().add(new MinicardController((SkillCard)card, this.gameController));
        }
    }
}