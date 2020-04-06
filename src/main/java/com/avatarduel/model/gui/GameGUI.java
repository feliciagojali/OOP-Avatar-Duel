package com.avatarduel.model.gui;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.fxml.FXML;

import java.io.IOException;

import com.avatarduel.AvatarDuel;
import com.avatarduel.enums.Element;

public class GameGUI {
    
    @FXML
    private HBox cardInfoContainer;

    @FXML
    private AnchorPane topFieldContainer;
    
    @FXML
    private AnchorPane bottomFieldContainer;

    @FXML
    private StackPane handContainer;

    public GameGUI () {}

    @FXML
    public void setBottomField(AnchorPane field)
    {
        bottomFieldContainer = field;
    }

    @FXML
    public void setTopField(AnchorPane field)
    {
        topFieldContainer = field;
    }

    @FXML
    public void setHand(StackPane hand)
    {
        handContainer = hand;
    }

    @FXML
    public void setCardInfo(AnchorPane card)
    {
        cardInfoContainer.getChildren().addAll(card);
    }

}