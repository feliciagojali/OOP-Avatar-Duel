package com.avatarduel.model.cards;

import junit.framework.TestCase;

public class CardTest extends TestCase {
    public void testGetId() {
        Card card = new Card (1,"A",Element.AIR,"perfect","card.png");
        assertEquals(1,card.getId());
    }

    public void testTestGetName() {
        Card card = new Card (1,"A",Element.AIR,"perfect","card.png");
        assertEquals("A",card.getName());
    }

    public void testGetElement() {
        Card card = new Card (1,"A",Element.AIR,"perfect","card.png");
        assertEquals(Element.AIR,card.getElement());
    }

    public void testGetDescription() {
        Card card = new Card (1,"A",Element.AIR,"perfect","card.png");
        assertEquals("perfect",card.getDescription());
    }

    public void testGetImagePath() {
        Card card = new Card (1,"A",Element.AIR,"perfect","card.png");
        assertEquals("card.png",card.getImagePath());
    }

    public void testTestToString() {
        Card card = new Card (1,"A",Element.AIR,"perfect","card.png");
        assertEquals("1 A AIR",card.toString());
    }
}