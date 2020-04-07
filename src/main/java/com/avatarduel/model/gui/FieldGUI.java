package com.avatarduel.model.gui;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

import com.avatarduel.AvatarDuel;
import com.avatarduel.model.cards.Element;
import com.avatarduel.model.cards.CharacterCard;

public class FieldGUI {

    @FXML
    private AnchorPane characterSlot1;
    @FXML
    private AnchorPane characterSlot2;
    @FXML
    private AnchorPane characterSlot3;
    @FXML
    private AnchorPane characterSlot4;
    @FXML
    private AnchorPane characterSlot5;
    @FXML
    private AnchorPane characterSlot6;
    @FXML
    private AnchorPane characterSlot7;
    @FXML
    private AnchorPane characterSlot8;
    @FXML
    private AnchorPane skillSlot1;
    @FXML
    private AnchorPane skillSlot2;
    @FXML
    private AnchorPane skillSlot3;
    @FXML
    private AnchorPane skillSlot4;
    @FXML
    private AnchorPane skillSlot5;
    @FXML
    private AnchorPane skillSlot6;
    @FXML
    private AnchorPane skillSlot7;
    @FXML
    private AnchorPane skillSlot8;

    private AnchorPane[] characterSlots;

    private AnchorPane[] skillSlots = {
        skillSlot1,
        skillSlot2,
        skillSlot3,
        skillSlot4,
        skillSlot5,
        skillSlot6,
        skillSlot7,
        skillSlot8,
    };

    @FXML
    public void initialize()
    {
        this.characterSlots = new AnchorPane[8];
        this.characterSlots[0] = characterSlot1;
        this.characterSlots[1] = characterSlot2;
        this.characterSlots[2] = characterSlot3;
        this.characterSlots[3] = characterSlot4;
        this.characterSlots[4] = characterSlot5;
        this.characterSlots[5] = characterSlot6;
        this.characterSlots[6] = characterSlot7;
        this.characterSlots[7] = characterSlot8;
        
        // for (AnchorPane card : characterSlots) {
        //     card.setOnAction()
        // }
    }

    @FXML
    public void setCharacter(CharacterCard card, int slot)
    {
        try{
            AnchorPane cardField = new CardFieldGUIBuilder()
                .setImage("card/image/character/" + card.getImagePath())
                .build();
                
                characterSlots[slot].getChildren().add(cardField);
                System.out.println("pisadgbawh");
        }
        catch(Exception e)
        {
                System.out.println(e);
        }
    }

    @FXML
    public void handleOnMouseClick(MouseEvent event)
    {
        String fxId = event.getPickResult().getIntersectedNode().getId();
        int id;

        if(fxId.contains("character")) id = Integer.parseInt(fxId.substring(9));
        else id = Integer.parseInt(fxId.substring(5));

        System.out.println(id);
    }
}