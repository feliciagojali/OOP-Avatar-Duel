package com.avatarduel.model.gui;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.fxml.FXML;

import java.io.IOException;

import com.avatarduel.AvatarDuel;
import com.avatarduel.model.cards.Card;
import com.avatarduel.model.cards.Element;

public class GameGUI {
    
    @FXML
    private HBox cardInfoContainer;

    @FXML
    private AnchorPane topFieldContainer;
    
    @FXML
    private AnchorPane bottomFieldContainer;

    @FXML
    private HBox handContainer;

    @FXML
    private AnchorPane deckContainer;

    public GameGUI () {}

    @FXML
    public void setBottomField(AnchorPane field)
    {
        bottomFieldContainer.getChildren().addAll(field);
    }
    
    @FXML
    public void setTopField(AnchorPane field)
    {
        topFieldContainer.getChildren().addAll(field);
    }

    @FXML
    public void setCardInfo(AnchorPane card)
    {
        cardInfoContainer.getChildren().addAll(card);
    }

    @FXML
    public void setDeckContainer(AnchorPane deck)
    {
        deckContainer.getChildren().addAll(deck);
    }

    @FXML
    public void addCardToHand(Card card)
    {
        try {
            
            AnchorPane cardDisplay = new CardFieldGUIBuilder()
                .setImage("card/image/character/" + card.getImagePath())
                .build();
    
            handContainer.getChildren().add(cardDisplay);
        }
        catch(Exception e)
        {
            System.out.println("error cuy");
        }
    }

}