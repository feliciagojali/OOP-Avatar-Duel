package com.avatarduel.model.gui;

import java.util.LinkedList;

import com.avatarduel.model.cards.Card;
import com.avatarduel.model.cards.CharacterCard;
import com.avatarduel.model.cards.CharacterCardList;
import com.avatarduel.model.cards.LandCard;
import com.avatarduel.model.cards.LandCardList;
import com.avatarduel.model.cards.SkillCard;
import com.avatarduel.model.cards.SkillCardList;
import com.avatarduel.model.player.Hand;
import com.avatarduel.model.player.Player;
import com.avatarduel.model.player.Phase;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.input.MouseEvent;

public class GameController{
    public static GameController gameControllerInstance = null;

    
    @FXML private DeckController deckController;
    @FXML private FieldController bottomFieldController;
    @FXML private FieldController topFieldController;
    @FXML private Button drawButton;
    @FXML private HBox handSlot;
    @FXML private StackPane cardInfoSlot;
    
    private Hand hand;
    private LinkedList<Player> listofplayers;
    private int activeplayer;
    private Phase phase;
    
    public void initialize() {
        if(gameControllerInstance == null) {
        gameControllerInstance = this;
        Player A = new Player("A");
        Player B = new Player("B");
        this.listofplayers = new LinkedList<Player>();
        this.listofplayers.add(A);
        this.listofplayers.add(B);
        this.activeplayer = 0;
        this.phase = Phase.draw;
       
    }
    
    
}
    
 
    @FXML
    public void drawCard(MouseEvent event)
    {
        int id = this.deckController.decrement();

        Card card = null;

        if(CharacterCardList.isIdCharacterCard(id))
        {
            card = (CharacterCard)CharacterCardList.getCharacterCardById(id);
            this.listofplayers.get(this.activeplayer).drawCard(card);
            this.handSlot.getChildren().addAll(new MinicardController((CharacterCard)card));
            this.hand.addCard((CharacterCard)card);

            
        }
        else if(LandCardList.isIdLandCard(id))
        {
            card = (LandCard) LandCardList.getLandCardById(id);
            this.listofplayers.get(this.activeplayer).drawCard(card);
            this.handSlot.getChildren().addAll(new MinicardController((LandCard)card));
            this.hand.addCard((LandCard)card);

        }
        else
        {
            card = SkillCardList.getSkillCardById(id);
            this.listofplayers.get(this.activeplayer).drawCard(card);
            this.handSlot.getChildren().addAll(new MinicardController((SkillCard)card));
            this.hand.addCard((SkillCard)card);

        }

    }

    @FXML
    public void setCardInfo(CharacterCard c)
    {
        this.cardInfoSlot.getChildren().clear();
        this.cardInfoSlot.getChildren().add(new CardController(c));        
    }

    @FXML
    public void setCardInfo(SkillCard c)
    {
        this.cardInfoSlot.getChildren().clear();
        this.cardInfoSlot.getChildren().add(new CardController(c));        
    }

    @FXML
    public void setCardInfo(LandCard c)
    {
        this.cardInfoSlot.getChildren().clear();
        this.cardInfoSlot.getChildren().add(new CardController(c));        
    }

}