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
    
    @FXML private StackPane cardInfoSlot;
    @FXML private StackPane deckSlot;
    @FXML private StackPane statsSlot;
    @FXML private StackPane topFieldSlot;
    @FXML private StackPane bottomFieldSlot;
    @FXML private ScrollPane handSlot;
    
    private DeckController deckController;
    private BottomFieldController bottomFieldController;
    private TopFieldController topFieldController;
    private StatsController statsController;
    private HandController handController;

    private Player playerA;
    private Player playerB;

    private boolean landCardUsed;
    private Phase phase;
    // For storing current active player's reference
    private Player activePlayer;
    private Player otherPlayer;
    private int selectedCardIndex;
    
    public void initialize() {
        
        // Initialize game controller requirements
        this.playerA = new Player("A");
        this.playerB = new Player("B");
        this.activePlayer = this.playerA;
        this.otherPlayer = this.playerB;
        this.phase = Phase.DRAW;
        this.setHandInterface();
        this.setDeckInterface();
        this.setStatsInterface();
        this.setFieldInterface(this.activePlayer, this.otherPlayer);
        this.phase = Phase.DRAW;
        this.landCardUsed = false;
    }

    // Get active player
    public Player getActivePlayer()
    {
        return this.activePlayer;
    }

    // Get active player
    public Player getOtherPlayer()
    {
        return this.otherPlayer;
    }

    // Get active card info
    public StackPane getCardInfo()
    {
        return this.cardInfoSlot;
    }

    // Get hand controller
    public HandController getHandController()
    {
        return this.handController;
    }

    // Get stats controller
    public StatsController getStatsController()
    {
        return this.statsController;
    }

    public int getSelectedCardIndex()
    {
        return this.selectedCardIndex;
    }

    public void setSelectedCardIndex(int idx)
    {
        this.selectedCardIndex = idx;
    }

    // Set top field interface
    @FXML
    public void setFieldInterface(final Player bottomPlayer, final Player topPlayer)
    {
        this.bottomFieldSlot.getChildren().clear();
        this.bottomFieldController = new BottomFieldController(this, bottomPlayer);
        this.bottomFieldSlot.getChildren().add(this.bottomFieldController);

        this.topFieldSlot.getChildren().clear();
        this.topFieldController = new TopFieldController(this, topPlayer);
        this.topFieldSlot.getChildren().add(this.topFieldController);
    }

    // Set interface to current player's hand
    @FXML
    public void setHandInterface()
    {
        this.handController = new HandController(this);
        this.handSlot.setContent(this.handController);
    }
    
    // Set interface to current player's deck
    @FXML
    public void setDeckInterface()
    {
        this.deckSlot.getChildren().clear();
        
        this.deckController = new DeckController(this);
        this.deckSlot.getChildren().add(this.deckController);
    }
    
    // Set interface to current player's stats
    @FXML
    public void setStatsInterface()
    {
        this.statsSlot.getChildren().clear();

        this.statsController = new StatsController(this);
        this.statsSlot.getChildren().add(this.statsController);
    }
    
    // Set interface to current player's card info when hovered
    @FXML
    public void setCardInfo(final CharacterCard c)
    {
        this.cardInfoSlot.getChildren().clear();
        this.cardInfoSlot.getChildren().add(new CardController(c));        
    }

    @FXML
    public void setCardInfo(final SkillCard c)
    {
        this.cardInfoSlot.getChildren().clear();
        this.cardInfoSlot.getChildren().add(new CardController(c));        
    }

    @FXML
    public void setCardInfo(final LandCard c)
    {
        this.cardInfoSlot.getChildren().clear();
        this.cardInfoSlot.getChildren().add(new CardController(c));        
    }

    @FXML
    public void changePhase(){
        try {
            switch (this.phase) {
                case DRAW:
                    if(!this.deckController.hasDraw()){
                        throw new ErrorException("You need to draw first");
                    }
                    this.phase = Phase.MAIN;
                    break;
                case MAIN:
                    this.phase = Phase.BATTLE;
                    break;
                case BATTLE:
                    this.phase = Phase.END;
                    break;
                case END:
                    changeTurn();
                    this.phase = Phase.DRAW;
                    break;
    
                default:
                    break;
                }
            if (this.phase != Phase.DRAW){
                this.setHandInterface();
                this.setDeckInterface();
                this.setStatsInterface();
            }
        } catch (final ErrorException e) {
            //TODO: handle exception
            ShowError.showError(e.getMessage());
        }
    }

    public void changeTurn(){
        this.otherPlayer = this.activePlayer;
        this.activePlayer = this.activePlayer == this.playerA ? this.playerB : this.playerA;
        
        this.setHandInterface();
        this.setDeckInterface();

        this.activePlayer.getElementStats().resetStats();
        this.setStatsInterface();

        this.setFieldInterface(this.activePlayer, otherPlayer);

        this.cardInfoSlot.getChildren().clear();
        this.landCardUsed = false;

    }

    public Phase getPhase(){
        return this.phase;
    }

    public void setLandCardUsed(){
        this.landCardUsed = true;
    }

    public boolean isLandCardUsed(){
        return this.landCardUsed;
    }

}