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
    @FXML private StackPane deckSlot;
    
    private Player playerA;
    private Player playerB;
    private Player activePlayer;

    // private Phase phase;
    
    public void initialize() {
        // Initialize static instance for reference
        gameControllerInstance = this;
       
        this.playerA = new Player("A");
        this.playerB = new Player("B");
        this.activePlayer = this.playerA;
        this.setHandInterface();
        this.setDeckInterface();
        // this.phase = Phase.draw;
        
    }
    
 
    @FXML
    public void drawCard(MouseEvent event)
    {
        int id = this.activePlayer.getDeck().drawCard();

        Card card = null;

        if(CharacterCardList.isIdCharacterCard(id))
        {
            card = (CharacterCard)CharacterCardList.getCharacterCardById(id);
            this.handSlot.getChildren().addAll(new MinicardController((CharacterCard)card));
            this.activePlayer.getHand().addCard((CharacterCard)card);
        }
        else if(LandCardList.isIdLandCard(id))
        {
            card = (LandCard) LandCardList.getLandCardById(id);
            this.handSlot.getChildren().addAll(new MinicardController((LandCard)card));
            this.activePlayer.getHand().addCard((LandCard)card);

        }
        else
        {
            card = SkillCardList.getSkillCardById(id);
            this.handSlot.getChildren().addAll(new MinicardController((SkillCard)card));
            this.activePlayer.getHand().addCard((SkillCard)card);

        }
        
        this.deckController.setCardLeftLabelText(this.activePlayer.getDeck().getCardsLeft());
    }
    
    @FXML
    public void setHandInterface()
    {
        this.handSlot.getChildren().clear();

        for(Card card : this.activePlayer.getHand().getCards())
        {
            if(card instanceof CharacterCard)
                this.handSlot.getChildren().add(new MinicardController((CharacterCard)card));
            else if(card instanceof LandCard)
                this.handSlot.getChildren().add(new MinicardController((LandCard)card));
            else
                this.handSlot.getChildren().add(new MinicardController((SkillCard)card));
        }
    }

    @FXML
    public void setDeckInterface()
    {
        this.deckSlot.getChildren().clear();
        
        this.deckController = new DeckController(this.activePlayer.getDeck());
        this.deckSlot.getChildren().add(this.deckController);
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

    @FXML
    public void changeTurn()
    {
        this.activePlayer = this.activePlayer == this.playerA ? this.playerB : this.playerA;
        this.setHandInterface();
        this.setDeckInterface();
    }

}