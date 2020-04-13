package com.avatarduel.model.gui;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

import com.avatarduel.AvatarDuel;
import com.avatarduel.model.player.Player;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class FieldController extends GridPane{

    @FXML private StackPane topSlot1;
    @FXML private StackPane topSlot2;
    @FXML private StackPane topSlot3;
    @FXML private StackPane topSlot4;
    @FXML private StackPane topSlot5;
    @FXML private StackPane topSlot6;
    @FXML private StackPane bottomSlot1;
    @FXML private StackPane bottomSlot2;
    @FXML private StackPane bottomSlot3;
    @FXML private StackPane bottomSlot4;
    @FXML private StackPane bottomSlot5;
    @FXML private StackPane bottomSlot6;

    private StackPane topSlots[];
    private StackPane bottomSlots[];
    private boolean isFlipped;

    private Player activePlayer;

    public FieldController(Player player, boolean flip)
    {
        FXMLLoader fieldLoader = new FXMLLoader(AvatarDuel.class.getResource("gui/field.fxml"));
        fieldLoader.setRoot(this);
        fieldLoader.setController(this);
        
        // Store FXML slot references in array for easy referencing
        this.topSlots = new StackPane[6];
        this.topSlots[0] = this.topSlot1;
        this.topSlots[1] = this.topSlot2;
        this.topSlots[2] = this.topSlot3;
        this.topSlots[3] = this.topSlot4;
        this.topSlots[4] = this.topSlot5;
        this.topSlots[5] = this.topSlot6;
        
        this.bottomSlots = new StackPane[6];
        this.bottomSlots[0] = this.bottomSlot1;
        this.bottomSlots[1] = this.bottomSlot2;
        this.bottomSlots[2] = this.bottomSlot3;
        this.bottomSlots[3] = this.bottomSlot4;
        this.bottomSlots[4] = this.bottomSlot5;
        this.bottomSlots[5] = this.bottomSlot6;

        try
        {
            fieldLoader.load();
            this.activePlayer = player;
            this.isFlipped = flip;
        }
        catch(IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void displayField()
    {
        for(int i=0;i<6;i++)
        {
            int index = this.isFlipped ? 6-i-1 : i;
            this.topSlots[index].getChildren().add(new MinicardController(this.activePlayer.getField().getCharacterCards()[i]));
            this.bottomSlots[index].getChildren().add(new MinicardController(this.activePlayer.getField().getSkillCards()[i]));
        }
    }


}