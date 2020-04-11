package com.avatarduel.model.gui;

import javafx.scene.control.Label;

import java.io.IOException;

import com.avatarduel.AvatarDuel;
import com.avatarduel.model.cards.*;
import com.avatarduel.model.player.Deck;
import com.avatarduel.model.player.Player;

import javafx.scene.layout.HBox;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class HandController extends HBox{

    private Player activePlayer;

    public HandController(Player player)
    {
        FXMLLoader handLoader = new FXMLLoader(AvatarDuel.class.getResource("gui/hand.fxml"));
        handLoader.setRoot(this);
        handLoader.setController(this);

        try
        {
            handLoader.load();
            this.activePlayer = player;
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

        for(Card card : this.activePlayer.getHand().getCards())
        {
            if(card instanceof CharacterCard)
                this.getChildren().add(new MinicardController((CharacterCard)card));
            else if(card instanceof LandCard)
                this.getChildren().add(new MinicardController((LandCard)card));
            else
                this.getChildren().add(new MinicardController((SkillCard)card));
        }
    }
}