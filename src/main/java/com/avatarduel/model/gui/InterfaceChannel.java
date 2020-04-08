package com.avatarduel.model.gui;

import com.avatarduel.model.cards.Card;

public class InterfaceChannel {

    public void sendCardInfo(Card c)
    {
        GameController.gameControllerInstance.setCardInfo(c);
    }
}