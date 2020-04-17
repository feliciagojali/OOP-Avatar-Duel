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

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;

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

    private Map<String, Button> buttonsMap;
    private int indexForAttack;

    public BottomFieldController(GameController controller, Player player)
    {
        super(controller, player, "gui/bottomField.fxml");
        this.buttonsMap = new HashMap<String, Button>();
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

        this.indexForAttack = -1;
        this.displayField();
    }

    public void displayField()
    {
        super.displayField();
        for(int i = 1; i <= 6; i++) if(!this.gameController.getActivePlayer().getField().getCharacterStance(i-1))
        {
            this.getCharacterMinicard(i).setStanceColor();
        }
        this.displayAttackButton();
        this.displayStanceButton();
    }

    public void displayAttackButton()
    {
        for(int i=1;i<=6;i++)
        {
            final int buttonIndex = i;
            this.buttonsMap.get("attackButton" + i).setText("Attack");
            this.buttonsMap.get("attackButton" + i).setOnAction(e ->{
                try {
                    if(this.gameController.getPhase() != Phase.MAIN) { throw new ErrorException("You can't do this action in this phase."); }
                    this.indexForAttack = buttonIndex-1;
                    this.displayTargetButton();
                } catch (ErrorException msg) 
                {
                    ShowError.showError(msg.getMessage());
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
                        throw new ErrorException("Can't attack here, there's no enemy.");
                    
                    this.gameController.getActivePlayer().attack(this.gameController.getOtherPlayer(), this.indexForAttack, enemyIndex);
                    this.gameController.setFieldInterface(this.gameController.getActivePlayer(), this.gameController.getOtherPlayer());
                    this.indexForAttack = -1;
                    
                    this.displayAttackButton();
                }
                catch(ErrorException msg)
                {
                    ShowError.showError(msg.getMessage());
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
                    if(this.gameController.getPhase() != Phase.MAIN) { throw new ErrorException("Can't change stance in this phase."); }

                    // boolean isStanceAttack = this.gameController.getActivePlayer().getField().getCharacterStance(buttonIndex - 1);
                    this.gameController.getActivePlayer().getField().changeStance(buttonIndex - 1);
                    this.gameController.setFieldInterface(this.gameController.getActivePlayer(), this.gameController.getOtherPlayer());
                }
                catch(ErrorException msg)
                {
                    ShowError.showError(msg.getMessage());
                }
            });
        }
    }

    public void disableStanceButton()
    {

    }

    public void disableAttackButton()
    {

    }
}