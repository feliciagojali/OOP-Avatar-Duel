package com.avatarduel.model.player;
import java.util.LinkedList;

import com.avatarduel.model.cards.*;

public class Hand {
    private LinkedList<Card> cards;

    public Hand(){
        this.cards = new LinkedList<Card>();        
    }
    
    public LinkedList<Card> getCards(){
        return this.cards;
    }

    public void addCard(Card x){
        this.cards.add(x);
    }
    
    public Card getCard(int pos){
        return this.cards.get(pos);
    }

    public boolean isPosValid(int pos){
        return this.cards.get(pos) != null;
    }
    public void discardCard(int pos){
        this.cards.remove(pos);
        
    }


}