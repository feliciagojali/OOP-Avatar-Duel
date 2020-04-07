package com.avatarduel.model.player;

import java.io.IOException;
import java.net.URISyntaxException;

import com.avatarduel.model.cards.*;

public class Player {
    private String name;
    private int hp;
    private Hand cardInHand;
    private ElemenStats stats;
    private Deck deck;
    private Field field;

    public Player(String name) throws URISyntaxException,IOException{
        this.name = name;
        this.hp = 80;
        this.cardInHand = new Hand();
        Deck d = new Deck();
        d.initializeDeck();
        d.shuffle();
        this.deck = d;
        for(int i=0;i<=7;i++){
            this.drawCardfromDeck();
        }
        this.field =  new Field();
        this.stats = new ElemenStats();
    }
    
    public void drawCardfromDeck(){
        int x = this.deck.drawCard();
        boolean landcard =  LandCardList.isIdLandCard(x);
        boolean character = CharacterCardList.isIdCharacterCard(x);
        if (landcard){
            LandCard L = LandCardList.getLandCardById(x);
            this.cardInHand.addCard(L);
        } else if(character){ 
            CharacterCard C = CharacterCardList.getCharacterCardById(x);
            this.cardInHand.addCard(C);;
        } else{
            SkillCard S = SkillCardList.getSkillCardById(x);
            this.cardInHand.addCard(S);
        }
    }

    public void discardCard(int posTangan){
        if (this.cardInHand.isPosValid(posTangan)) {
            Card x = this.cardInHand.getCard(posTangan);
            if (LandCardList.isIdLandCard(x.getId())) {
                if (x.getElement() == Element.WATER)
                {
        
                }
                else if (x.getElement() == Element.AIR)
                {
        
                }
                else if (x.getElement() == Element.FIRE)
                {
        
                }
                else {
        
                }
                this.cardInHand.discardCard(posTangan);
            } 

        }       
    }

    public void playCard(int posTangan, int posField){
        Card x = this.cardInHand.getCard(posTangan);
        if (CharacterCardList.isIdCharacterCard(x.getId())){
            if(!this.field.isCharacterRowFull() && !this.field.isPosCharacterAvail(posField)){
                this.cardInHand.discardCard(posTangan);
                CharacterCard C = CharacterCardList.getCharacterCardById(x.getId());
                this.field.addCharacterRow(C, posField);
            }   
        } else {
            if (!this.field.isSkillRowFull() && !this.field.isPosSkillAvail(posField)){
                this.cardInHand.discardCard(posTangan);
                SkillCard C = SkillCardList.getSkillCardById(x.getId());
                this.field.addSkillRow(C, posField);
            }
        }
    }

    
   

}