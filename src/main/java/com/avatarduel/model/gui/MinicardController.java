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
import javafx.scene.input.MouseEvent;

public class MinicardController extends AnchorPane {
    private static InterfaceChannel channel = new InterfaceChannel();

    @FXML private Label cardType;
    @FXML private Label cardName;
    @FXML private Label cardElement;    
    @FXML private Label cardAttack;
    @FXML private Label cardDefense;
    @FXML private Button cardUseButton;

    Card card;

    public MinicardController(LandCard c) {
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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public MinicardController(CharacterCard c) {
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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public MinicardController(SkillCard c) {
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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void useCard()
    {
        
    }

   
    
    @FXML
    public void showCardInfo()
    {
        System.out.println(this.card);
        if(card instanceof CharacterCard) channel.sendCardInfo((CharacterCard)this.card);
        else if(card instanceof LandCard) channel.sendCardInfo((LandCard)this.card);
        else if(card instanceof SkillCard) channel.sendCardInfo((SkillCard)this.card);
    }

}