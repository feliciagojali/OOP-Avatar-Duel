package com.avatarduel.model.gui;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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