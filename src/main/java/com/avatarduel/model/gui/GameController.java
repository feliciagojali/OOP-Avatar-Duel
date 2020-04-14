package com.avatarduel.model.gui;

import java.util.LinkedList;

import javax.xml.bind.annotation.XmlElement.DEFAULT;

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
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.input.MouseEvent;



public class GameController{
    static GameController gameControllerInstance = null;
    
    @FXML private StackPane cardInfoSlot;
    @FXML private StackPane deckSlot;
    @FXML private StackPane statsSlot;
    @FXML private ScrollPane handSlot;
    
    private DeckController deckController;
    private FieldController bottomFieldController;
    private FieldController topFieldController;
    private StatsController statsController;
    private HandController handController;

    private Player playerA;
    private Player playerB;

    private Phase phase;
    // For storing current active player's reference
    private Player activePlayer;

    
    public void initialize() {
        // Initialize static instance for reference
        gameControllerInstance = this;
       
        this.playerA = new Player("A");
        this.playerB = new Player("B");
        this.activePlayer = this.playerA;
        this.phase = Phase.draw;
        this.setHandInterface();
        this.setDeckInterface();
        this.setStatsInterface();
        // this.phase = Phase.draw;
        
    }
 
    // @FXML
    // public void drawCard(MouseEvent event)
    // {
    //     int id = this.activePlayer.getDeck().drawCard();

    //     Card card = null;

    //     if(CharacterCardList.isIdCharacterCard(id))
    //     {
    //         card = (CharacterCard)CharacterCardList.getCharacterCardById(id);
    //         this.handSlot.getChildren().addAll(new MinicardController((CharacterCard)card));
    //         this.activePlayer.getHand().addCard((CharacterCard)card);
    //     }
    //     else if(LandCardList.isIdLandCard(id))
    //     {
    //         card = (LandCard) LandCardList.getLandCardById(id);
    //         this.handSlot.getChildren().addAll(new MinicardController((LandCard)card));
    //         this.activePlayer.getHand().addCard((LandCard)card);

    //     }
    //     else
    //     {
    //         card = SkillCardList.getSkillCardById(id);
    //         this.handSlot.getChildren().addAll(new MinicardController((SkillCard)card));
    //         this.activePlayer.getHand().addCard((SkillCard)card);

    //     }
        
    //     this.deckController.setCardLeftLabelText(this.activePlayer.getDeck().getCardsLeft());
    // }
    
    // // Set interface to current player's hand
    // @FXML
    // public void setHandInterface()
    // {
    //     this.handSlot.getChildren().clear();
        
    //     for(Card card : this.activePlayer.getHand().getCards())
    //     {
    //         if(card instanceof CharacterCard)
    //         this.handSlot.getChildren().add(new MinicardController((CharacterCard)card));
    //         else if(card instanceof LandCard)
    //             this.handSlot.getChildren().add(new MinicardController((LandCard)card));
    //             else
    //             this.handSlot.getChildren().add(new MinicardController((SkillCard)card));
    //         }
    // }
    // Set interface to current player's hand
    @FXML
    public void setHandInterface()
    {
        this.handController = new HandController(this.activePlayer,this.phase);
        this.handSlot.setContent(this.handController);
    }
    
    // Set interface to current player's deck
    @FXML
    public void setDeckInterface()
    {
        this.deckSlot.getChildren().clear();
        
        this.deckController = new DeckController(this.activePlayer, this.handController,this.phase);
        this.deckSlot.getChildren().add(this.deckController);
    }
    
    // Set interface to current player's stats
    @FXML
    public void setStatsInterface()
    {
        this.statsSlot.getChildren().clear();

        this.statsController = new StatsController(this.activePlayer.getElementStats());
        this.statsSlot.getChildren().add(this.statsController);
    }
    
    // Set interface to current player's card info when hovered
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
    public void changePhase(){
        try {
            change();
        } catch (ErrorException e) {
            //TODO: handle exception
            ShowError.showError(e.getMessage());
        }
    }
    public void change() throws ErrorException
    {
        switch (this.phase) {
            case draw:
                if(!this.deckController.hasDraw()){
                    throw new ErrorException("You need to draw first");
                }
                this.phase = Phase.main1;
                break;
            case main1:
                this.phase = Phase.battle;
                break;
            case battle:
                this.phase = Phase.end;
                break;
            case end:
                changeTurn();
                this.phase = Phase.draw;
                break;

            default:
                break;
            }
        this.setHandInterface();
        this.setDeckInterface();
        this.setStatsInterface();
   
    }

    public void changeTurn(){
        this.activePlayer = this.activePlayer == this.playerA ? this.playerB : this.playerA;

    }

    


}