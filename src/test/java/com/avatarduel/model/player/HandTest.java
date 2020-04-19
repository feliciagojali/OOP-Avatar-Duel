package com.avatarduel.model.player;

import com.avatarduel.model.cards.CharacterCard;
import com.avatarduel.model.cards.Element;
import junit.framework.TestCase;

public class HandTest extends TestCase {

    public void testaddCard(){
        Hand hand = new Hand();
        CharacterCard card = new CharacterCard(1,"A", Element.ENERGY,"perfect","perfect.png",5,5,5);
        hand.addCard(card);
        assertEquals(hand.getCard(0),card);
    }
    public void testDiscardCard() {
        Hand hand = new Hand();
        CharacterCard card = new CharacterCard(1,"A", Element.ENERGY,"perfect","perfect.png",5,5,5);
        hand.addCard(card);
        hand.discardCard(0);
        assertEquals(hand.getCards().size(),0);

    }

}