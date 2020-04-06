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

public class CardFieldGUI {
    @FXML
    private ImageView cardFieldImage;

    public CardFieldGUI() {}

    @FXML
    public void setImage(Image image)
    {
        // Image image = new Image(AvatarDuel.class.getResource(imagePath));
        this.cardFieldImage.setImage(image);
    }
}