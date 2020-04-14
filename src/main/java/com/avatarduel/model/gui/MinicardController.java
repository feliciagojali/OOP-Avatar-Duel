package com.avatarduel.model.gui;

import java.io.IOException;

import javax.management.RuntimeErrorException;

import com.avatarduel.AvatarDuel;
import com.avatarduel.model.cards.Card;
import com.avatarduel.model.cards.CharacterCard;
import com.avatarduel.model.cards.LandCard;
import com.avatarduel.model.cards.SkillCard;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.input.MouseEvent;

public class MinicardController extends AnchorPane {
    @FXML private Label cardType;
    @FXML private Label cardName;
    @FXML private Label cardElement;    
    @FXML private Label cardAttack;
    @FXML private Label cardDefense;
    @FXML private Button cardUseButton;

    private GameController gameController;
    private Card card;

    public MinicardController(LandCard c, GameController controller) {
        FXMLLoader minicardLoader = new FXMLLoader(AvatarDuel.class.getResource("gui/minicard.fxml"));
        minicardLoader.setRoot(this);
        minicardLoader.setController(this);

        try {
            minicardLoader.load();
            this.card = c;
            this.cardType.setText("Land");
            this.cardName.setText(c.getName());
            this.cardElement.setText(c.getElement().toString());
            this.cardAttack.setText("-");
            this.cardDefense.setText("-");
            this.gameController = controller;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public MinicardController(CharacterCard c, GameController controller) {
        FXMLLoader minicardLoader = new FXMLLoader(AvatarDuel.class.getResource("gui/minicard.fxml"));
        minicardLoader.setRoot(this);
        minicardLoader.setController(this);

        try {
            minicardLoader.load();
            this.card = c;
            this.cardType.setText("Character");
            this.cardName.setText(c.getName());
            this.cardElement.setText(c.getElement().toString());
            this.cardAttack.setText(Integer.toString(c.getAttack()));
            this.cardDefense.setText(Integer.toString(c.getDefense()));
            this.gameController = controller;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public MinicardController(SkillCard c, GameController controller) {
        FXMLLoader minicardLoader = new FXMLLoader(AvatarDuel.class.getResource("gui/minicard.fxml"));
        minicardLoader.setRoot(this);
        minicardLoader.setController(this);

        try {
            minicardLoader.load();
            this.card = c;
            this.cardType.setText("Skill");
            this.cardName.setText(c.getName());
            this.cardElement.setText(c.getElement().toString());
            this.cardAttack.setText(Integer.toString(c.getAttack()));
            this.cardDefense.setText(Integer.toString(c.getDefense()));
            this.gameController = controller;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void useCard()
    {
        int cardIndex = ((HBox)this.getParent()).getChildren().indexOf(this);
        System.out.println(cardIndex);
        this.gameController.getActivePlayer().useCard(cardIndex);
        if(card instanceof LandCard)
        {
            this.gameController.setStatsInterface();
            ((HBox)this.getParent()).getChildren().remove(this);
        }
    }

    // @FXML
    // public void putCard()
    // {
    //     int slotIndex = ((HBox)this.getParent()).getChildren().indexOf(this)
    //     this.gameController.getActivePlayer().playCard();
    // }
    
    @FXML
    public void showCardInfo()
    {
        if(card instanceof CharacterCard) this.gameController.setCardInfo((CharacterCard)this.card);
        else if(card instanceof LandCard) this.gameController.setCardInfo((LandCard)this.card);
        else if(card instanceof SkillCard) this.gameController.setCardInfo((SkillCard)this.card);
    }

}