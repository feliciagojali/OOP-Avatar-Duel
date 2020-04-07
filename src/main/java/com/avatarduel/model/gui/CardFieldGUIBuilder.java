package com.avatarduel.model.gui;

import javafx.scene.layout.AnchorPane;

import java.io.IOException;

import com.avatarduel.AvatarDuel;

import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;


public class CardFieldGUIBuilder {
    FXMLLoader cardFieldLoader;
    CardFieldGUI cardFieldController;
    AnchorPane cardField;

    public CardFieldGUIBuilder() throws IOException
    {
        this.cardFieldLoader = new FXMLLoader(AvatarDuel.class.getResource("gui/cardField.fxml"));
        this.cardField = cardFieldLoader.load();
        this.cardFieldController =  (CardFieldGUI)cardFieldLoader.getController();
    }

    public CardFieldGUIBuilder setImage(String imagePath)
    {
        Image image = new Image(AvatarDuel.class.getResourceAsStream(imagePath));
        cardFieldController.setImage(image);
        return this;
    }

    public AnchorPane build()
    {
        return this.cardField;
    }
}