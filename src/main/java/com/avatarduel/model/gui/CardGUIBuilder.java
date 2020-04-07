package com.avatarduel.model.gui;

import javafx.scene.layout.AnchorPane;

import java.io.IOException;

import com.avatarduel.AvatarDuel;
import com.avatarduel.model.cards.Element;

import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;

public class CardGUIBuilder {
    FXMLLoader cardLoader;
    CardGUI cardController;
    AnchorPane card;

    public CardGUIBuilder() throws IOException
    {
        this.cardLoader = new FXMLLoader(AvatarDuel.class.getResource("gui/card.fxml"));
        this.card = cardLoader.load();
        this.cardController =  (CardGUI)cardLoader.getController();
    }

    public CardGUIBuilder setName(String name)
    {
        cardController.setName(name);
        return this;
    }
    
    public CardGUIBuilder setElement(Element element)
    {
        cardController.setElement(element);
        return this;
    }
    
    public CardGUIBuilder setImage(String imagePath)
    {
        Image image = new Image(AvatarDuel.class.getResourceAsStream(imagePath));
        cardController.setImage(image);
        return this;
    }
    
    public CardGUIBuilder setDescription(String description)
    {
        cardController.setDescription(description);
        return this;
    }
    
    public CardGUIBuilder setAttack(String attack)
    {
        cardController.setAttack(attack);
        return this;
    }    
    
    public CardGUIBuilder setDefense(String defense)
    {
        cardController.setDefense(defense);
        return this;
    }    
    
    public CardGUIBuilder setPower(String power)
    {
        cardController.setPower(power);
        return this;
    }

    public AnchorPane build()
    {
        return this.card;
    }

}