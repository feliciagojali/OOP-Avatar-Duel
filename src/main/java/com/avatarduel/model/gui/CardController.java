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
    public CardController(CharacterCard c)
    {
        FXMLLoader cardLoader = new FXMLLoader(AvatarDuel.class.getResource("gui/card.fxml"));
        cardLoader.setRoot(this);
        cardLoader.setController(this);
        
        try
        {
            cardLoader.load();
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

    /**
     * The CardController constructor using SkillCard
     * @param card the skill card shown
     */
    public CardController(SkillCard c)
    {
        FXMLLoader cardLoader = new FXMLLoader(AvatarDuel.class.getResource("gui/card.fxml"));
        cardLoader.setRoot(this);
        cardLoader.setController(this);
        
        try
        {
            cardLoader.load();
            this.cardName.setText(c.getName());
            this.cardElement.setText(c.getElement().toString());
            Image img = new Image(AvatarDuel.class.getResourceAsStream("card/image/skill/" + c.getImagePath()));
            this.cardImage.setImage(img);
            this.cardType.setText("SKILL");
            this.cardDescription.setText(c.getDescription());

            if(c.getEffect() != Effect.DESTROY)
            {
                this.cardAttack.setText(Integer.toString(c.getAttack()));
                this.cardDefense.setText(Integer.toString(c.getDefense()));
                this.cardPower.setText(Integer.toString(c.getPower()));
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