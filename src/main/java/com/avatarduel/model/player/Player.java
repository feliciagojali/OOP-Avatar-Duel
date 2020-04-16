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
        
        if (LandCardList.isIdLandCard(x)){
            this.hand.addCard(LandCardList.getLandCardById(x));
        } else if(CharacterCardList.isIdCharacterCard(x)){ 
            this.hand.addCard(CharacterCardList.getCharacterCardById(x));
        } else {
            this.hand.addCard(SkillCardList.getSkillCardById(x));
        }
    }

    // Assume position given is is a LandCard
    public void useCard(int pos) throws ErrorException
    {
        if (!this.hand.isPosValid(pos)) { throw new ErrorException("Invalid hand position"); }
        
        this.stats.addStats(this.hand.getCard(pos).getElement());
        this.hand.discardCard(pos);
        
        if (this.hand.getCard(pos) instanceof LandCard) {
        }
        System.out.println(this.selectedCardIndex);
    }
    
    // Assume position given is either a CharacterCard or a LandCard
    public void selectCard(int pos) throws ErrorException
    {
        if(this.hand.getCard(pos) instanceof CharacterCard) {
            CharacterCard card = (CharacterCard)this.hand.getCard(pos);
    
            // If power > current stats, throw exception
            if(card.getPower() > this.stats.getStats(card.getElement()).getCurrent())
            { throw new ErrorException("Insufficent element stats"); }   
        }
        else 
        {
            SkillCard card = (SkillCard)this.hand.getCard(pos);
            
            // If power > current stats, throw exception
            if(card.getPower() > this.stats.getStats(card.getElement()).getCurrent()) { throw new ErrorException("Insufficent element stats"); }
        }

    }
    
    // Assume position given is either a CharacterCard or a LandCard
    public void playCard(int posHand, int posField) throws ErrorException{
        if (this.hand.getCard(posHand) instanceof CharacterCard)
        {
            if(this.field.isPosCharacterAvail(posField)){
                CharacterCard card = (CharacterCard)this.hand.getCard(posHand);
                this.hand.discardCard(posHand);
                this.field.addCharacterRow(card, posField);
                this.stats.reduceStats(card.getElement(), card.getPower());
            }   
        }
        else
        {
            if (this.field.isPosSkillAvail(posField)){
                SkillCard card = (SkillCard)this.hand.getCard(posHand);
                this.hand.discardCard(posHand);
                this.field.addSkillRow(card, posField);
                this.stats.reduceStats(card.getElement(), card.getPower());
            }
        }

    }
    
    public boolean canAttack(int posisi){
        return (!this.field.isPosCharacterAvail(posisi) && this.field.canAttack(posisi));
    }

    public void attack(Player enemy,int Pos, int enemyPos){
        if (canAttack(Pos)) {
            if (!enemy.getField().isPosCharacterAvail(enemyPos)) {
                // posisi kartu enemy menyerang
                if (enemy.getField().getCharacterStance(enemyPos) || this.field.getCharacterCard(Pos).getPowerUp()) {
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

    public boolean canSkill(int pos){
        return(!this.field.isPosSkillAvail(pos));
    }
   
    public void useSkill(Player player, int posSkill, int pos){
        if (canSkill(posSkill)) {
            if(!player.getField().isPosCharacterAvail(pos)){
                SkillCard X = this.field.getSkillCard(posSkill);
                CharacterCard Y = player.getField().getCharacterCard(pos);
                switch (X.getEffect()) {
                    case AURA:
                        int newatk = Y.getAttack() + X.getAttack();
                        int newdef = Y.getDefense() + X.getDefense();
                        Y.setAttack(newatk);
                        Y.setDefense(newdef);
                        break;
                    
                    case DESTROY:
                        player.getField().discardCharaCard(pos);
                        break;
                    case POWER_UP:
                        player.getField().getCharacterCard(pos).setPowerUp();
                        break;
                    default:
                        break;
                }
                this.field.discardSkillCard(posSkill);
            }   

        }
    }
    
   

}