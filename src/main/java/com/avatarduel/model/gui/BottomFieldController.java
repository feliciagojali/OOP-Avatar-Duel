package com.avatarduel.model.gui;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.avatarduel.AvatarDuel;
import com.avatarduel.model.cards.CharacterCard;
import com.avatarduel.model.player.Player;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;

public class BottomFieldController extends FieldController{

    @FXML private Button slotButton1;
    @FXML private Button slotButton2;
    @FXML private Button slotButton3;
    @FXML private Button slotButton4;
    @FXML private Button slotButton5;
    @FXML private Button slotButton6;

    private Map<String, Button> buttonsMap;
    private int indexForAttack;

    public BottomFieldController(GameController controller, Player player)
    {
        super(controller, player, "gui/bottomField.fxml");
        System.out.println("pisang");
        this.buttonsMap = new HashMap<String, Button>();
        this.buttonsMap.put("slotButton1", slotButton1);
        this.buttonsMap.put("slotButton2", slotButton2);
        this.buttonsMap.put("slotButton3", slotButton3);
        this.buttonsMap.put("slotButton4", slotButton4);
        this.buttonsMap.put("slotButton5", slotButton5);
        this.buttonsMap.put("slotButton6", slotButton6);
        this.indexForAttack = -1;
        this.displayField();
    }

    public void displayField()
    {
        super.displayField();
        this.displayAttackButton();
    }

    public void displayAttackButton()
    {
        for(int i=1;i<=6;i++)
        {
            final int buttonIndex = i;
            this.buttonsMap.get("slotButton" + i).setText("Attack");
            this.buttonsMap.get("slotButton" + i).setOnAction(e ->{
                System.out.println(buttonIndex);
                this.indexForAttack = buttonIndex;
                this.displayTargetButton();
            });
        }
    }
    
    public void displayTargetButton()
    {
        for(int i=1;i<=6;i++)
        {
            final int buttonIndex = i;
            this.buttonsMap.get("slotButton" + i).setText("Target");
            this.buttonsMap.get("slotButton" + i).setOnAction(e ->{
                System.out.println(buttonIndex);
                try{
                    if(this.gameController.getOtherPlayer().getField().getCharacterCard(buttonIndex) != null)
                        throw new ErrorException("kenot atak, no enemi");
                    
                    int enemyIndex = 6 - buttonIndex + 1;
                    this.gameController.getActivePlayer().AttackEnemy(this.gameController.getOtherPlayer(), enemyIndex);
                    System.out.println("Attack: " + this.indexForAttack + " to " + (6 - buttonIndex + 1));
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
}