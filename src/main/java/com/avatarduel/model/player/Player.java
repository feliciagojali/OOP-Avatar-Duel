package com.avatarduel.model.player;

import java.io.IOException;
import java.net.URISyntaxException;

import com.avatarduel.model.cards.*;
import com.avatarduel.model.gui.ErrorException;

public class Player {
    // General player fields
    private String name;
    private int hp;
    private ElementStats stats;

    // Card related fields
    private Hand hand;
    private Deck deck;
    private Field field;
    private int selectedCardIndex;

    public Player(String name){
        this.name = name;
        this.hp = 80;
        
        // Initialize player deck
        this.deck = new Deck();
        this.deck.initializeDeck();
        this.deck.shuffle();
        
        // Draw 7 cards from deck to hand
        this.hand = new Hand();
        for(int i=0;i<7;i++) {
            this.drawCard();
        }
        
        this.field =  new Field();
        this.stats = new ElementStats();
        this.selectedCardIndex = -1;
    }
    
    public Deck getDeck(){
        return this.deck;
    }

    public Hand getHand(){
        return this.hand;
    }

    public Field getField(){
        return this.field;
    }

    public ElementStats getElementStats(){
        return this.stats;
    }

    public int getHp(){
        return this.hp;
    }

    public int getSelectedCardIndex()
    {
        return this.selectedCardIndex;
    }

    public void setHp(int x){
        this.hp = x;
    }

    public void drawCard(){
        int x = this.deck.drawCard();
        System.out.println(x);
        
        if (LandCardList.isIdLandCard(x)){
            this.hand.addCard(LandCardList.getLandCardById(x));
        } else if(CharacterCardList.isIdCharacterCard(x)){ 
            this.hand.addCard(CharacterCardList.getCharacterCardById(x));
        } else {
            this.hand.addCard(SkillCardList.getSkillCardById(x));
        }
    }

    public void useCard(int pos) throws ErrorException {
        if (!this.hand.isPosValid(pos))
            throw new ErrorException("Invalid hand position");

        if (this.hand.getCard(pos) instanceof LandCard) {
            this.stats.addStats(this.hand.getCard(pos).getElement());
            this.hand.discardCard(pos);
        }
        else if(this.hand.getCard(pos) instanceof CharacterCard) {
            CharacterCard card = (CharacterCard)this.hand.getCard(pos);

            // If power > current stats, throw exception
            if(card.getPower() > this.stats.getStats(card.getElement()).getCurrent())
            throw new ErrorException("Insufficent element stats");
            
            this.selectedCardIndex = pos;
        }
        else 
        {
            SkillCard card = (SkillCard)this.hand.getCard(pos);
        
            // If power > current stats, throw exception
            if(card.getPower() > this.stats.getStats(card.getElement()).getCurrent())
                throw new ErrorException("Insufficent element stats");
            
            this.selectedCardIndex = pos;
        }
        System.out.println(this.selectedCardIndex);
    }

    public void playCard(int posField) throws ErrorException{
        // If havent selected any card (-1)..
        if(this.selectedCardIndex == -1)
            throw new ErrorException("No card selected");
        
        if (this.hand.getCard(this.selectedCardIndex) instanceof CharacterCard)
        {
            if(this.field.isPosCharacterAvail(posField)){
                CharacterCard card = (CharacterCard)this.hand.getCard(this.selectedCardIndex);
                this.hand.discardCard(this.selectedCardIndex);
                this.field.addCharacterRow(card, posField);
                this.stats.reduceStats(card.getElement(), card.getPower());
            }   
        }
        else{
            if (this.field.isPosSkillAvail(posField)){
                SkillCard card = (SkillCard)this.hand.getCard(this.selectedCardIndex);
                this.hand.discardCard(this.selectedCardIndex);
                this.field.addSkillRow(card, posField);
                this.stats.reduceStats(card.getElement(), card.getPower());
            }
        }

        this.selectedCardIndex = -1;
    }
    
    public boolean canAttack(int posisi){
        return (!this.field.isPosCharacterAvail(posisi) && this.field.canAttack(posisi));
    }

    public void attack(Player enemy,int Pos, int enemyPos){
        if (canAttack(Pos)) {
            if (!this.field.isPosCharacterAvail(enemyPos)) {
                // posisi kartu enemy menyerang
                if (enemy.getField().getCharacterStance(enemyPos)) {
                    if (enemy.getField().getCharacterCard(enemyPos).getAttack() < this.field.getCharacterCard(Pos).getAttack()) {
                        int selisihattack = this.field.getCharacterCard(Pos).getAttack() - enemy.getField().getCharacterCard(enemyPos).getAttack();
                        enemy.getField().discardCharaCard(enemyPos);
                        enemy.setHp(enemy.getHp()-selisihattack);
                        this.field.setHasAtk(Pos);
                        this.field.unableChange(Pos);
                    }
                } else {
                    // posisi kartu bertahan
                    if (enemy.getField().getCharacterCard(enemyPos).getDefense() < this.field.getCharacterCard(Pos).getDefense()){
                        enemy.getField().discardCharaCard(enemyPos);
                        this.field.setHasAtk(Pos);
                        this.field.unableChange(Pos);

                    }
                }


            }
        }
    }


    // ini attack kalau di field lawan udah gaada kartu samsek
    public void AttackEnemy(Player enemy,int pos){
        int attack = this.field.getCharacterCard(pos).getAttack();
        enemy.setHp(enemy.getHp()-attack);

    }

   

    
   

}