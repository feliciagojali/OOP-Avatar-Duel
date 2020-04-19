package com.avatarduel.model.gui;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import com.avatarduel.AvatarDuel;
import com.avatarduel.model.cards.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

/**
 * CardController is the class that controls the card info shown
 * when a player hovers over a minicard.
 * @author mkamadeus
 */
public class CardController extends VBox{
    /**
     * Holds reference to all FXML given classes
     */
    @FXML private Label cardName;
    @FXML private Label cardElement;
    @FXML private ImageView cardImage;
    @FXML private Label cardType;
    @FXML private Label cardDescription;
    @FXML private Label cardAttack;
    @FXML private Label cardDefense;
    @FXML private Label cardPower;

    /**
     * The CardController constructor using LandCard
     * @param card the land card shown
     */
    public CardController(LandCard card)
    {
        FXMLLoader cardLoader = new FXMLLoader(AvatarDuel.class.getResource("gui/card.fxml"));
        cardLoader.setRoot(this);
        cardLoader.setController(this);
        
        
        try
        {
            cardLoader.load();
            this.cardName.setText(card.getName());
            this.cardElement.setText(card.getElement().toString());
            Image img = new Image(AvatarDuel.class.getResourceAsStream("card/image/land/" + card.getImagePath()));
            this.cardImage.setImage(img);
            this.cardType.setText("LAND");
            this.cardDescription.setText(card.getDescription());
            this.cardAttack.setText("-");
            this.cardDefense.setText("-");
            this.cardPower.setText("-");
            
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * The CardController constructor using CharacterCard
     * @param card the character card shown
     */
    public CardController(CharacterCard card)
    {
        FXMLLoader cardLoader = new FXMLLoader(AvatarDuel.class.getResource("gui/card.fxml"));
        cardLoader.setRoot(this);
        cardLoader.setController(this);
        
        try
        {
            cardLoader.load();
            this.cardName.setText(card.getName());
            this.cardElement.setText(card.getElement().toString());
            Image img = new Image(AvatarDuel.class.getResourceAsStream("card/image/character/" + card.getImagePath()));
            this.cardImage.setImage(img);
            this.cardType.setText("CHAR");
            this.cardDescription.setText(card.getDescription());
            this.cardAttack.setText(Integer.toString(card.getAttack()));
            this.cardDefense.setText(Integer.toString(card.getDefense()));
            this.cardPower.setText(Integer.toString(card.getPower()));
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * The CardController constructor using SkillCard
     * @param card the skill card shown
     */
    public CardController(SkillCard card)
    {
        FXMLLoader cardLoader = new FXMLLoader(AvatarDuel.class.getResource("gui/card.fxml"));
        cardLoader.setRoot(this);
        cardLoader.setController(this);
        
        try
        {
            cardLoader.load();
            this.cardName.setText(card.getName());
            this.cardElement.setText(card.getElement().toString());
            Image img = new Image(AvatarDuel.class.getResourceAsStream("card/image/skill/" + card.getImagePath()));
            this.cardImage.setImage(img);
            this.cardType.setText("SKILL");
            this.cardDescription.setText(card.getDescription());

            if(card.getEffect() != Effect.DESTROY)
            {
                this.cardAttack.setText(Integer.toString(card.getAttack()));
                this.cardDefense.setText(Integer.toString(card.getDefense()));
                this.cardPower.setText(Integer.toString(card.getPower()));
            }
            else
            {
                this.cardAttack.setText("-");
                this.cardDefense.setText("-");
                this.cardPower.setText("-");    
            }
            

        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}