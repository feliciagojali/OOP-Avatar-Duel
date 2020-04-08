package com.avatarduel.model.gui;

import com.avatarduel.model.cards.Card;
import com.avatarduel.model.cards.CharacterCard;
import com.avatarduel.model.cards.CharacterCardList;
import com.avatarduel.model.cards.LandCard;
import com.avatarduel.model.cards.LandCardList;
import com.avatarduel.model.cards.SkillCard;
import com.avatarduel.model.cards.SkillCardList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.input.MouseEvent;

public class GameController{
    
    @FXML private DeckController deckController;
    @FXML private Button drawButton;
    @FXML private HBox handSlot;

    public void initialize() {}

    @FXML
    public void drawCard(MouseEvent event)
    {
        int id = this.deckController.decrement();

        Card card;
        if(CharacterCardList.isIdCharacterCard(id))
        {
            card = CharacterCardList.getCharacterCardById(id);
            System.out.println(card);
            this.handSlot.getChildren().addAll(new MinicardController((CharacterCard)card));
        }
        else if(LandCardList.isIdLandCard(id))
        {
            card = LandCardList.getLandCardById(id);
            System.out.println(card);
            this.handSlot.getChildren().addAll(new MinicardController((LandCard)card));
        }
        else
        {
            card = SkillCardList.getSkillCardById(id);
            System.out.println(card);
            this.handSlot.getChildren().addAll(new MinicardController((SkillCard)card));
        }
    }


}