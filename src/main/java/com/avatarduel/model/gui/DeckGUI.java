package com.avatarduel.model.gui;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

import com.avatarduel.AvatarDuel;
import com.avatarduel.model.cards.Element;
import com.avatarduel.model.player.Deck;
import com.avatarduel.model.cards.CharacterCard;

public class DeckGUI {
    @FXML
    private Label cardLeftLabel;
    Deck deck;

    @FXML
    public void initialize()
    {
        this.deck = new Deck();
        deck.initializeDeck();
        deck.shuffle();
        this.setCardLeftText(deck.getCardsLeft());
    }

    @FXML
    public void setCardLeftText(int n)
    {
        cardLeftLabel.setText(Integer.toString(n));
    }

    @FXML
    public void drawCard(MouseEvent event)
    {
        System.out.println(deck.drawCard());
        this.setCardLeftText(deck.getCardsLeft());
    }
}