package com.avatarduel.model.gui;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

import com.avatarduel.AvatarDuel;
import com.avatarduel.enums.Element;


public class CardGUI{
    // private FXMLLoader cardLoader;
    // private static final String CARD_FXML_PATH = "gui/card.fxml";
    
    @FXML
    private Label cardName;

    @FXML
    private Label cardElement;

    @FXML
    private ImageView cardImage;

    @FXML
    private Label cardDescription;

    @FXML
    private Label cardAttack;

    @FXML
    private Label cardDefense;

    @FXML
    private Label cardPower;

    public CardGUI() {}
    
    @FXML
    public void setName(String name)
    {
        this.cardName.setText(name);
    }
    
    @FXML
    public void setElement(Element element)
    {
        this.cardElement.setText(element.toString());
        
    }
    
    @FXML
    public void setImage(String imagePath)
    {
        Image image = new Image(getClass().getResourceAsStream(imagePath));
        cardImage.setImage(image);
    }
    
    @FXML
    public void setDescription(String description)
    {
        this.cardDescription.setText(description);
        
    }
    
    @FXML
    public void setAttack(String attack)
    {
        this.cardAttack.setText(attack);
        
    }    
    
    @FXML
    public void setDefense(String defense)
    {
        this.cardDefense.setText(defense);
        
    }    
    
    @FXML
    public void setPower(String power)
    {
        this.cardPower.setText(power);
        
    }
}