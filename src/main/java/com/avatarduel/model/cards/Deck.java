package com.avatarduel.model.cards;

import java.util.ArrayList;
import java.util.Collections;


public class Deck {
    private ArrayList<Card> deck;

    public Deck() {
        this.deck = new ArrayList<Card>();
    }

    public void InsertCard(Card card) {
        this.deck.add(card);
    }

    public void Shuffle() {
        Collections.shuffle(this.deck);
    }
}