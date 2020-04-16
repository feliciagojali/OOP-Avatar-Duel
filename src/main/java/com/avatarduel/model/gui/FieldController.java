package com.avatarduel.model.gui;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.avatarduel.AvatarDuel;
import com.avatarduel.model.cards.CharacterCard;
import com.avatarduel.model.player.Player;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;

public class FieldController extends GridPane{

    @FXML protected StackPane topSlot1;
    @FXML protected StackPane topSlot2;
    @FXML protected StackPane topSlot3;
    @FXML protected StackPane topSlot4;
    @FXML protected StackPane topSlot5;
    @FXML protected StackPane topSlot6;
    @FXML protected StackPane bottomSlot1;
    @FXML protected StackPane bottomSlot2;
    @FXML protected StackPane bottomSlot3;
    @FXML protected StackPane bottomSlot4;
    @FXML protected StackPane bottomSlot5;
    @FXML protected StackPane bottomSlot6;

    protected StackPane topSlots[];
    protected StackPane bottomSlots[];
    protected Map<String, StackPane> slotsMap;

    protected GameController gameController;
    protected Player owner;

    public FieldController(GameController controller, Player player, String fxmlPath)
    {
        FXMLLoader fieldLoader = new FXMLLoader(AvatarDuel.class.getResource(fxmlPath));
        fieldLoader.setRoot(this);
        fieldLoader.setController(this);
        
        try
        {
            fieldLoader.load();
            this.owner = player;
            this.gameController = controller;

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
    
            this.slotsMap = new HashMap<String, StackPane>();
            for(int i = 1; i <= 6; i++)
            {
                this.slotsMap.put("topSlot" + i, this.topSlots[i-1]);
                this.slotsMap.put("bottomSlot" + i, this.bottomSlots[i-1]);
            }
            
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
            if(this.owner.getField().getCharacterCards()[i] != null)
            {
                MinicardController minicard = new MinicardController(this.owner.getField().getCharacterCard(i), this.gameController);
                minicard.removeCardUseButton();
                this.topSlots[i].getChildren().add(minicard);
            }
            if(this.owner.getField().getSkillCards()[i] != null)
            {
                MinicardController minicard = new MinicardController(this.owner.getField().getSkillCard(i), this.gameController);
                minicard.removeCardUseButton();
                this.bottomSlots[i].getChildren().add(minicard);
            }
        }
    }

    @FXML
    public void putCard(MouseEvent event) {
        // Get the clicked slot
        StackPane slot = this.slotsMap.get(((StackPane)event.getSource()).getId().toString());

        boolean isTopSlot = true;

        int slotIndex = Arrays.asList(this.topSlots).indexOf(slot);
        if(slotIndex == -1)
        {
            slotIndex = Arrays.asList(this.bottomSlots).indexOf(slot);
            isTopSlot = false;
        }

        // Check whether the card is a character
        boolean isCharacter = this.gameController.getActivePlayer().getHand().getCard(this.gameController.getActivePlayer().getSelectedCardIndex()) instanceof CharacterCard;
        System.out.println(slotIndex);
        System.out.println(isCharacter);
        System.out.println(isTopSlot);
        
        
        try {
            if((isCharacter && !isTopSlot) || (!isCharacter && isTopSlot))
                throw new ErrorException("Invalid card position");
    
            if(!isCharacter && this.gameController.getActivePlayer().getField().getCharacterCards()[slotIndex] == null)
                throw new ErrorException("Skill cards must be used in conjuction of character cards");

            this.gameController.getActivePlayer().playCard(slotIndex);
            this.displayField();
            this.gameController.getHandController().displayHand();
            this.gameController.getStatsController().displayStats();

        } catch (ErrorException e) {
            //TODO: handle exception
            ShowError.showError(e.getMessage());
        }
    }
}