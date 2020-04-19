package com.avatarduel.model.gui;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.avatarduel.AvatarDuel;
import com.avatarduel.model.cards.CharacterCard;
import com.avatarduel.model.player.Phase;
import com.avatarduel.model.player.Player;
import com.avatarduel.util.AlertBox;
import com.avatarduel.util.InvalidActionException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class BottomFieldController extends FieldController{

    @FXML private Button attackButton1;
    @FXML private Button attackButton2;
    @FXML private Button attackButton3;
    @FXML private Button attackButton4;
    @FXML private Button attackButton5;
    @FXML private Button attackButton6;

    @FXML private Button stanceButton1;
    @FXML private Button stanceButton2;
    @FXML private Button stanceButton3;
    @FXML private Button stanceButton4;
    @FXML private Button stanceButton5;
    @FXML private Button stanceButton6;

    @FXML private Button attachSelfButton1;
    @FXML private Button attachSelfButton2;
    @FXML private Button attachSelfButton3;
    @FXML private Button attachSelfButton4;
    @FXML private Button attachSelfButton5;
    @FXML private Button attachSelfButton6;

    @FXML private Button attachEnemyButton1;
    @FXML private Button attachEnemyButton2;
    @FXML private Button attachEnemyButton3;
    @FXML private Button attachEnemyButton4;
    @FXML private Button attachEnemyButton5;
    @FXML private Button attachEnemyButton6;

    @FXML private Label attachLabel1;
    @FXML private Label attachLabel2;
    @FXML private Label attachLabel3;
    @FXML private Label attachLabel4;
    @FXML private Label attachLabel5;
    @FXML private Label attachLabel6;
    
    @FXML private Button detachButton1;
    @FXML private Button detachButton2;
    @FXML private Button detachButton3;
    @FXML private Button detachButton4;
    @FXML private Button detachButton5;
    @FXML private Button detachButton6;

    private Map<String, Button> buttonsMap;
    private Map<String, Label> labelsMap;
    private int indexForAttack;
    private boolean attackDone;

    public BottomFieldController(GameController controller, Player player)
    {
        super(controller, player, "gui/bottomField.fxml");
        this.buttonsMap = new HashMap<String, Button>();
        this.labelsMap = new HashMap<String, Label>();

        this.buttonsMap.put("attackButton1", attackButton1);
        this.buttonsMap.put("attackButton2", attackButton2);
        this.buttonsMap.put("attackButton3", attackButton3);
        this.buttonsMap.put("attackButton4", attackButton4);
        this.buttonsMap.put("attackButton5", attackButton5);
        this.buttonsMap.put("attackButton6", attackButton6);
        
        this.buttonsMap.put("stanceButton1", stanceButton1);
        this.buttonsMap.put("stanceButton2", stanceButton2);
        this.buttonsMap.put("stanceButton3", stanceButton3);
        this.buttonsMap.put("stanceButton4", stanceButton4);
        this.buttonsMap.put("stanceButton5", stanceButton5);
        this.buttonsMap.put("stanceButton6", stanceButton6);

        this.buttonsMap.put("attachSelfButton1", attachSelfButton1);
        this.buttonsMap.put("attachSelfButton2", attachSelfButton2);
        this.buttonsMap.put("attachSelfButton3", attachSelfButton3);
        this.buttonsMap.put("attachSelfButton4", attachSelfButton4);
        this.buttonsMap.put("attachSelfButton5", attachSelfButton5);
        this.buttonsMap.put("attachSelfButton6", attachSelfButton6);
        
        this.buttonsMap.put("attachEnemyButton1", attachEnemyButton1);
        this.buttonsMap.put("attachEnemyButton2", attachEnemyButton2);
        this.buttonsMap.put("attachEnemyButton3", attachEnemyButton3);
        this.buttonsMap.put("attachEnemyButton4", attachEnemyButton4);
        this.buttonsMap.put("attachEnemyButton5", attachEnemyButton5);
        this.buttonsMap.put("attachEnemyButton6", attachEnemyButton6);
        
        this.buttonsMap.put("detachButton1", detachButton1);
        this.buttonsMap.put("detachButton2", detachButton2);
        this.buttonsMap.put("detachButton3", detachButton3);
        this.buttonsMap.put("detachButton4", detachButton4);
        this.buttonsMap.put("detachButton5", detachButton5);
        this.buttonsMap.put("detachButton6", detachButton6);
        
        this.labelsMap.put("attachLabel1", attachLabel1);
        this.labelsMap.put("attachLabel2", attachLabel2);
        this.labelsMap.put("attachLabel3", attachLabel3);
        this.labelsMap.put("attachLabel4", attachLabel4);
        this.labelsMap.put("attachLabel5", attachLabel5);
        this.labelsMap.put("attachLabel6", attachLabel6);
        
        this.indexForAttack = -1;
        this.attackDone = true;
        this.displayField();
    }

    public void displayField()
    {
        super.displayField();
        this.displayAttackButton();
        this.displayStanceButton();
        this.displayAttachButton();
        this.displayDetachButton();
        for(int i = 1; i <= 6; i++)
        {
            if(!this.gameController.getActivePlayer().getField().getStance(i-1)) { this.getCharacterMinicard(i).setStanceColor(); }
            this.toggleAttachButton(i, this.gameController.getActivePlayer().getField().isSkillPositionAvailable(i-1));
        }
    }

    public void displayAttackButton()
    {
        for(int i=1;i<=6;i++)
        {
            final int buttonIndex = i;
            this.buttonsMap.get("attackButton" + i).setText("Attack");
            this.buttonsMap.get("attackButton" + i).setOnAction(e ->{
                try {
                    
                    if(this.gameController.getPhase() != Phase.BATTLE) { throw new InvalidActionException("You can't do this action in this phase."); }
                    this.indexForAttack = buttonIndex-1;
                    if (!this.gameController.getActivePlayer().canAttack(this.indexForAttack)){
                        throw new InvalidActionException("You cannot attack with this card");
                    }
                    if (this.gameController.getOtherPlayer().getField().isFieldEmpty()){
                        this.gameController.getActivePlayer().AttackEnemy(this.gameController.getOtherPlayer(),this.indexForAttack);
                        this.gameController.setFieldInterface(this.gameController.getActivePlayer(), this.gameController.getOtherPlayer());
                        if (this.gameController.getOtherPlayer().getHp() <= 0) {
                            AlertBox.endGame(this.gameController.getActivePlayer().getName());
                        }
                        this.gameController.getStatsController().displayStats();
                        this.displayAttackButton();
                    } else {
                        this.attackDone = false;
                        this.displayTargetButton();
                    }
                } catch (InvalidActionException msg) 
                {
                    AlertBox.showError(msg.getMessage());
                }

            });
        }
    }
    
    public void displayTargetButton()
    {
        for(int i=1;i<=6;i++)
        {
            final int buttonIndex = i;
            this.buttonsMap.get("attackButton" + i).setText("Target");
            this.buttonsMap.get("attackButton" + i).setOnAction(e ->{
                try{
                    
                    int enemyIndex = 6 - buttonIndex;
                    if(this.gameController.getOtherPlayer().getField().getCharacterCard(enemyIndex) == null)
                    throw new InvalidActionException("Can't attack here, there's no enemy.");
                    
                    
                    this.gameController.getActivePlayer().attack(this.gameController.getOtherPlayer(), this.indexForAttack, enemyIndex);
                    
                    this.gameController.setFieldInterface(this.gameController.getActivePlayer(), this.gameController.getOtherPlayer());
                    this.indexForAttack = -1;
                    if (this.gameController.getOtherPlayer().getHp() <= 0) {
                        AlertBox.endGame(this.gameController.getActivePlayer().getName());
                    }
                    this.attackDone = true;
                    this.displayAttackButton();
                    this.gameController.getStatsController().displayStats();

                }
                catch(InvalidActionException msg)
                {
                    AlertBox.showError(msg.getMessage());
                    displayAttackButton();
                }
            });
        }
    }
    
    public void displayStanceButton()
    {
        for(int i = 1; i <= 6; i++)
        {
            final int buttonIndex = i;
            this.buttonsMap.get("stanceButton" + i).setOnAction(e -> {
                try
                {
                    if(this.gameController.getPhase() != Phase.MAIN) { throw new InvalidActionException("Can't change stance in this phase."); }

                    // boolean isStanceAttack = this.gameController.getActivePlayer().getField().getCharacterStance(buttonIndex - 1);
                    this.gameController.getActivePlayer().getField().toggleStance(buttonIndex - 1);
                    this.gameController.setFieldInterface(this.gameController.getActivePlayer(), this.gameController.getOtherPlayer());
                }
                catch(InvalidActionException msg)
                {
                    AlertBox.showError(msg.getMessage());
                }
            });
        }
    }

    public void displayAttachButton()
    {
        for(int i = 1; i <= 6; i++)
        {
            final int buttonIndex = i;
            this.buttonsMap.get("attachSelfButton" + i).setOnAction(e -> {
                try
                {
                    if(this.gameController.getPhase() != Phase.MAIN) { throw new InvalidActionException("Can't do this action in this phase."); }
                    if(this.gameController.getActivePlayer().getField().isCharacterPositionAvailable(buttonIndex - 1)) {throw new InvalidActionException("You have no card to attach to."); }
                    this.gameController.getActivePlayer().playCard(this.gameController.getSelectedCardIndex(), buttonIndex - 1);
                    this.gameController.getActivePlayer().useSkill(this.gameController.getActivePlayer(), buttonIndex - 1, buttonIndex -1);
                }
                catch(InvalidActionException msg)
                {
                    AlertBox.showError(msg.getMessage());
                }
                finally
                {
                    this.gameController.setFieldInterface(this.gameController.getActivePlayer(), this.gameController.getOtherPlayer());
                    this.gameController.setHandInterface();
                }
            });
            this.buttonsMap.get("attachEnemyButton" + i).setOnAction(e -> {
                try
                {
                    
                    if(this.gameController.getPhase() != Phase.MAIN) { throw new InvalidActionException("Can't do this action in this phase."); }
                    if(this.gameController.getOtherPlayer().getField().isCharacterPositionAvailable(6 - buttonIndex)) {throw new InvalidActionException("There's no enemy to attach to."); }
                    
                    this.gameController.getActivePlayer().playCard(this.gameController.getSelectedCardIndex(), buttonIndex - 1);
                    this.gameController.getActivePlayer().useSkill(this.gameController.getOtherPlayer(), buttonIndex -1, 6- buttonIndex);
                
                }
                catch(InvalidActionException msg)
                {
                    AlertBox.showError(msg.getMessage());
                }
                finally
                {
                    this.gameController.setFieldInterface(this.gameController.getActivePlayer(), this.gameController.getOtherPlayer());
                    this.gameController.setHandInterface();
                }
            });
        }
    }

    public void displayDetachButton()
    {
        for(int i = 1; i <= 6; i++)
        {
            final int buttonIndex = i;
            this.buttonsMap.get("detachButton" + i).setOnAction(e -> {
                try
                {
                    if(this.gameController.getPhase() != Phase.MAIN) { throw new InvalidActionException("Can't do this action in this phase."); }
                    if(!this.gameController.getActivePlayer().getField().isSkillPositionAvailable(buttonIndex - 1)) { throw new InvalidActionException("Theres no skill card to detach."); }

                }
                catch(InvalidActionException msg)
                {
                    AlertBox.showError(msg.getMessage());
                }
                finally{
                    this.gameController.setFieldInterface(this.gameController.getActivePlayer(), this.gameController.getOtherPlayer());
                }
            });

        }
    }

    public void disableStanceButton()
    {
        for(int i = 1; i <= 6; i++) { this.disableStanceButton(i); }
    }
    
    public void disableStanceButton(int buttonIndex)
    {
        this.buttonsMap.get("stanceButton" + buttonIndex).setDisable(true);
    }
    
    public void disableAttackButton()
    {
        for(int i = 1; i <= 6; i++) { this.disableAttackButton(i); }
    }
    
    public void disableAttackButton(int buttonIndex)
    {
        this.buttonsMap.get("attackButton" + buttonIndex).setDisable(true);
    }

    public void toggleAttachButton(boolean visible)
    {
        for(int i = 1; i <=6; i++) {this.toggleAttachButton(i, visible);}
    }

    public void toggleAttachButton(int buttonIndex, boolean visible)
    {
        this.buttonsMap.get("attachSelfButton" + buttonIndex).setVisible(visible);
        this.buttonsMap.get("attachEnemyButton" + buttonIndex).setVisible(visible);
    }

    public boolean getAttackDone(){
        return this.attackDone;
    }
}