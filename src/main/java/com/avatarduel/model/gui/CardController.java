package com.avatarduel.model.gui;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

import com.avatarduel.AvatarDuel;
import com.avatarduel.model.cards.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;


public class CardController extends AnchorPane{
    
    @FXML private Label cardName;
    @FXML private Label cardElement;
    @FXML private ImageView cardImage;
    @FXML private Label cardType;
    @FXML private Label cardDescription;
    @FXML private Label cardAttack;
    @FXML private Label cardDefense;
    @FXML private Label cardPower;

    private Card card;

    public CardController(LandCard c)
    {
        FXMLLoader cardLoader = new FXMLLoader(AvatarDuel.class.getResource("gui/card.fxml"));
        cardLoader.setRoot(this);
        cardLoader.setController(this);

        
        try
        {
            cardLoader.load();
            this.card = c;
            this.cardName.setText(c.getName());
            this.cardElement.setText(c.getElement().toString());
            Image img = new Image(AvatarDuel.class.getResourceAsStream("card/image/land/" + c.getImagePath()));
            this.cardImage.setImage(img);
            this.cardType.setText("LAND");
            this.cardDescription.setText(c.getDescription());
            this.cardAttack.setText("-");
            this.cardDefense.setText("-");
            this.cardPower.setText("-");

        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
    public CardController(CharacterCard c)
    {
        FXMLLoader cardLoader = new FXMLLoader(AvatarDuel.class.getResource("gui/card.fxml"));
        cardLoader.setRoot(this);
        cardLoader.setController(this);
        
        try
        {
            cardLoader.load();
            this.card = c;
            this.cardName.setText(c.getName());
            this.cardElement.setText(c.getElement().toString());
            Image img = new Image(AvatarDuel.class.getResourceAsStream("card/image/character/" + c.getImagePath()));
            this.cardImage.setImage(img);
            this.cardType.setText("CHAR");
            this.cardDescription.setText(c.getDescription());
            this.cardAttack.setText(Integer.toString(c.getAttack()));
            this.cardDefense.setText(Integer.toString(c.getDefense()));
            this.cardPower.setText(Integer.toString(c.getPower()));

        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
    public CardController(SkillCard c)
    {
        FXMLLoader cardLoader = new FXMLLoader(AvatarDuel.class.getResource("gui/card.fxml"));
        cardLoader.setRoot(this);
        cardLoader.setController(this);
        
        try
        {
            cardLoader.load();
            this.card = c;
            this.cardName.setText(c.getName());
            this.cardElement.setText(c.getElement().toString());
            Image img = new Image(AvatarDuel.class.getResourceAsStream("card/image/skill/" + c.getImagePath()));
            this.cardImage.setImage(img);
            this.cardType.setText("SKILL");
            this.cardDescription.setText(c.getDescription());
            this.cardAttack.setText(Integer.toString(c.getAttack()));
            this.cardDefense.setText(Integer.toString(c.getDefense()));
            this.cardPower.setText(Integer.toString(c.getPower()));

        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}