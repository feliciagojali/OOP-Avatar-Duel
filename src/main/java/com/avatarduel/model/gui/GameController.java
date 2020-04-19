package com.avatarduel.model.gui;

import com.avatarduel.model.cards.CharacterCard;
import com.avatarduel.model.cards.LandCard;
import com.avatarduel.model.cards.SkillCard;
import com.avatarduel.model.player.Player;
import com.avatarduel.model.player.Phase;
import com.avatarduel.util.InvalidActionException;
import com.avatarduel.util.AlertBox;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;

/**
 * GameController class is the controller class for the root game GUI.
 * It is used to store core informations about the game, such as the phase,
 * currently playing player, and other things for the game like showing card information.
 * 
 * @author mkamadeus
 * @author feliciagojali
 * 
 */
public class GameController {
    
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
    
    // Storing selected card index
    private int selectedCardIndex;
    
    /**
     * Used to initialize things required for the game,
     * ran by the FXMLLoader.
     */
    public void initialize() {    
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

        setDrawCondition();
    }

    /**
     * Get currently active player
     * @return the active player; passed the reference by value
     */
    public Player getActivePlayer()
    {
        return this.activePlayer;
    }
    
    /**
     * Get the other (inactive) player
     * @return the other player; passed the reference by value
     */
    public Player getOtherPlayer()
    {
        return this.otherPlayer;
    }
    
    /**
     * Get card info on the left bar
     * @return the card information in StackPane, ready to be appended
     */
    public StackPane getCardInfo()
    {
        return this.cardInfoSlot;
    }

    /**
     * Get hand in the bottom bar
     * @return the hand in HandController, ready to be appended
     */
    public HandController getHandController()
    {
        return this.handController;
    }

    /**
     * Get the stats in the side bar
     * @return the stats in StatsController, 
     */
    public StatsController getStatsController()
    {
        return this.statsController;
    }

    /**
     * Get current selected card index by the active player
     * @return integer of the card index, 0-indexed
     */
    public int getSelectedCardIndex()
    {
        return this.selectedCardIndex;
    }

    /**
     * Setter for the current selected index
     * @param idx the index of the card selected, 0-indexed
     */
    public void setSelectedCardIndex(int idx)
    {
        this.selectedCardIndex = idx;
    }

    /**
     * Sets the bottom interface
     * @param bottomPlayer the player on the bottom
     * @param topPlayer the player on the top
     */
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

    /**
     * Sets the hand interface on the bottom
     */
    @FXML
    public void setHandInterface()
    {
        this.handController = new HandController(this);
        this.handSlot.setContent(this.handController);
    }
    
    /**
     * Sets the deck interface in the bottom left corner
     */
    @FXML
    public void setDeckInterface()
    {
        this.deckSlot.getChildren().clear();
        
        this.deckController = new DeckController(this);
        this.deckSlot.getChildren().add(this.deckController);
    }
    
    /**
     * Sets the stats interface in the top left corner
     */
    public void setStatsInterface()
    {
        this.statsSlot.getChildren().clear();

        this.statsController = new StatsController(this);
        this.statsSlot.getChildren().add(this.statsController);
    }
    
    /**
     * Sets card info for CharacterCard
     * @param card CharacterCard info shown
     */
    public void setCardInfo(CharacterCard card)
    {
        this.cardInfoSlot.getChildren().clear();
        this.cardInfoSlot.getChildren().add(new CardController(card));        
    }

    /**
     * Sets card info for SkillCard
     * @param card SkillCard info shown
     */
    public void setCardInfo(SkillCard card)
    {
        this.cardInfoSlot.getChildren().clear();
        this.cardInfoSlot.getChildren().add(new CardController(card));        
    }
    
    /**
     * Sets card info for LandCard
     * @param card LandCard info shown
     */
    public void setCardInfo(LandCard card)
    {
        this.cardInfoSlot.getChildren().clear();
        this.cardInfoSlot.getChildren().add(new CardController(card));        
    }

    /* -=-=-=-=-= TURN AND PHASE CHANGING =-=-=-=-=- */
    /**
     * Sets the draw condition
     */
    private void setDrawCondition()
    {
        this.bottomFieldController.disableAttackButton(true);
        this.bottomFieldController.disableStanceButton(true);
        this.bottomFieldController.toggleAttachButton(false);
        this.bottomFieldController.disableDetachButton(true);
    }
    
    /**
     * Sets the main condition
     */
    private void setMainCondition() throws InvalidActionException
    {
        if(!this.deckController.hasDrawn()) { throw new InvalidActionException("You haven't drawn a card!");}
        
        this.bottomFieldController.disableStanceButton(false);
        this.bottomFieldController.toggleAttachButton(true);
        this.bottomFieldController.disableDetachButton(false);
    }
    
    /**
     * Sets the battle condition
     */
    private void setBattleCondition() throws InvalidActionException
    {
        this.bottomFieldController.disableAttackButton(false);
        this.bottomFieldController.disableStanceButton(true);
        this.bottomFieldController.toggleAttachButton(false);
        this.bottomFieldController.disableDetachButton(true);
    }
    
    /**
     * Sets the end condition
     */
    private void setEndCondition() throws InvalidActionException
    {
        if(!this.bottomFieldController.getAttackDone()){ throw new InvalidActionException("Finish your Attack!"); }

        this.bottomFieldController.disableAttackButton(true);
        this.bottomFieldController.disableStanceButton(true);

    }

    /**
     * Procedure to set what are needed to change phase
     */
    @FXML
    public void changePhase(){
        try {
            switch (this.phase) {
                case DRAW:
                    setMainCondition();
                    this.phase = Phase.MAIN;
                    break;
                case MAIN:
                    setBattleCondition();
                    this.phase = Phase.BATTLE;
                    break;
                case BATTLE:
                    setEndCondition();
                    this.phase = Phase.END;
                    break;
                case END:
                    changeTurn();
                    setDrawCondition();
                    this.phase = Phase.DRAW;
                    break;
        
                default:
                    break;
            }

            // Reset GUI
            this.setHandInterface();
            this.setDeckInterface();
            this.setStatsInterface();
            this.statsController.displayStats();
        } catch (InvalidActionException e) {
            AlertBox.showError(e.getMessage());
        }
    }

    /**
     * Procedure to change the turn and resets interfaces
     */
    public void changeTurn(){
        this.activePlayer.getField().resetHasAttacked();
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

    /**
     * Get the current phase
     * @return current phase
     */
    public Phase getPhase(){
        return this.phase;
    }

    /**
     * Used when land card is used
     */
    public void setLandCardUsed()
    {
        this.landCardUsed = true;
    }
    
    /**
     * Getter to test whether land card has been used before or not
     * @return boolean whether land card has used or not, true if have, false if haven't
     */
    public boolean isLandCardUsed()
    {
        return this.landCardUsed;
    }

}