package com.avatarduel.model.player;
import java.util.LinkedList;

import com.avatarduel.model.cards.*;

/**
 * Hand class is the class that defines the hand
 * that the players use in game.
 * @author feliciagojali
 * @author mkamadeus
 */
public class Hand {
    private LinkedList<Card> cards;

    /**
     * The default constructor for Hand
     */
    public Hand(){
        this.cards = new LinkedList<Card>();
    }
    
    /**
     * Getter for all cards in hand
     * @return LinkedList of card in hand
     */
    public LinkedList<Card> getCards(){
        return this.cards;
    }

    /**
     * Getter for exact card in the position
     * @param pos position of the card
     * @return an uncasted Card of the object (needs casting before specific use)
     */
    public Card getCard(int pos){
        return this.cards.get(pos);
    }
    
    /**
     * Procedure for adding a new card
     * @param card the card wanted to be added
     */
    public void addCard(Card card){
        this.cards.add(card);
    }
    
    /**
     * Check whether hand position is valid for the current hand
     * @param pos the position checked
     * @return boolean value, true if valid, false otherwise
     */
    public boolean isPosValid(int pos){
        return this.cards.get(pos) != null;
    }

    /**
     * Remove card from hand
     * @param pos the position of the card
     */
    public void discardCard(int pos){
        this.cards.remove(pos);
    }


}