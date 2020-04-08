package com.avatarduel.model.gui;

import com.avatarduel.model.cards.Card;
import com.avatarduel.model.cards.CharacterCard;
import com.avatarduel.model.cards.CharacterCardList;
import com.avatarduel.model.cards.LandCard;
import com.avatarduel.model.cards.LandCardList;
import com.avatarduel.model.cards.SkillCard;
import com.avatarduel.model.cards.SkillCardList;
import com.avatarduel.model.player.Hand;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.input.MouseEvent;

public class GameController{
    public static GameController gameControllerInstance = null;

    @FXML private DeckController deckController;
    @FXML private Button drawButton;
    @FXML private HBox handSlot;
    @FXML private StackPane cardInfoSlot;

    private Hand hand;

    public void initialize() {
        if(gameControllerInstance == null)
            gameControllerInstance = this;
    }

    @FXML
    public void drawCard(MouseEvent event)
    {
        int id = this.deckController.decrement();

        Card card = null;

        if(CharacterCardList.isIdCharacterCard(id))
        {
            card = (CharacterCard)CharacterCardList.getCharacterCardById(id);
            this.handSlot.getChildren().addAll(new MinicardController((CharacterCard)card));
            this.hand.addCard((CharacterCard)card);
        }
        else if(LandCardList.isIdLandCard(id))
        {
            card = (LandCard) LandCardList.getLandCardById(id);
            this.handSlot.getChildren().addAll(new MinicardController((LandCard)card));
            this.hand.addCard((LandCard)card);
        }
        else
        {
            card = SkillCardList.getSkillCardById(id);
            this.handSlot.getChildren().addAll(new MinicardController((SkillCard)card));
            this.hand.addCard((SkillCard)card);
        }

    }

    @FXML
    public void setCardInfo(Card c)
    {
        System.out.println("pisang goreng tepung");
        
        System.out.println(this.cardInfoSlot.getChildren());
        this.cardInfoSlot.getChildren().clear();
        System.out.println(this.cardInfoSlot.getChildren());

        if(c instanceof CharacterCard)
            this.cardInfoSlot.getChildren().add(new MinicardController((CharacterCard)c));
        else if(c instanceof LandCard)
            this.cardInfoSlot.getChildren().add(new MinicardController((LandCard)c));
        else if(c instanceof SkillCard)
            this.cardInfoSlot.getChildren().add(new MinicardController((SkillCard)c));

    }  

}