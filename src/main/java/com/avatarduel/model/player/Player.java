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

    // Set hp
    public void setHp(int x){
        this.hp = x;
    }

    // Draw card from deck
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
    }
    
    // Assume position given is either a CharacterCard or a LandCard
    public void selectCard(int pos) throws ErrorException
    {
        if (!this.hand.isPosValid(pos)) { throw new ErrorException("Invalid hand position"); }

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
                int attack = this.getField().getCharacterCard(Pos).getAttack() + this.getField().getAtk(Pos);
                int defense = this.getField().getCharacterCard(Pos).getDefense() + this.getField().getDef(Pos);
                int enemyatk = enemy.getField().getCharacterCard(enemyPos).getAttack() + enemy.getField().getAtk(enemyPos);
                int enemydef = enemy.getField().getCharacterCard(enemyPos).getDefense() + enemy.getField().getDef(enemyPos);
                boolean powerup = this.field.getPowerUp(Pos);
                // posisi kartu enemy menyerang
                if (enemy.getField().getCharacterStance(enemyPos) || powerup) {
                    if (enemyatk < attack) {
                        int selisihattack = attack - enemyatk;
                        enemy.getField().discardCharaCard(enemyPos);
                        enemy.setHp(enemy.getHp()-selisihattack);
                        this.field.setHasAtk(Pos);
                    }
                } else {
                    // posisi kartu bertahan
                    if (enemydef < defense){
                        enemy.getField().discardCharaCard(enemyPos);
                        this.field.setHasAtk(Pos);

                    }
                }


            }
        }
    }


    // ini attack kalau di field lawan udah gaada kartu samsek
    public void AttackEnemy(Player enemy,int pos){
        int attack = this.field.getCharacterCard(pos).getAttack() + this.getField().getAtk(pos);
        enemy.setHp(enemy.getHp()-attack);

    }

    public boolean canSkill(int pos){
        return(!this.field.isPosSkillAvail(pos));
    }
   
    public void useSkill(Player player, int posSkill){
        if (canSkill(posSkill)) {
            if(!player.getField().isPosCharacterAvail(posSkill)){
                SkillCard X = this.field.getSkillCard(posSkill);
                switch (X.getEffect()) {
                    case AURA:
                        int atk = X.getAttack();
                        int def = X.getDefense();
                        player.getField().setAtk(atk,posSkill);
                        player.getField().setDef(def,posSkill);                
                        break;
                    
                    case DESTROY:
                        player.getField().discardCharaCard(posSkill);
                        break;
                    case POWER_UP:
                        player.getField().setPowerUp(posSkill,true);
                        break;
                    default:
                        break;
                }
                if (X.getEffect() != Effect.DESTROY) {
                    player.getField().getAttachedList(posSkill).add(this.field.getSkillCard(posSkill));
                }
            }   

        }
    }


    public void detach(Player enemy, int pos){
        // cek di tempat sendiri
        int i = 0;
        while ( i < this.field.getAttachedList(pos).size()){
            if (this.field.getAttachedList(pos).get(i) ==  this.field.getSkillCard(pos)){
                SkillCard A = this.field.getAttachedList(pos).remove(i);
                if (A.getEffect() == Effect.AURA){
                    this.getField().setAtk(0,pos);
                    this.getField().setDef(0,pos);
                } else {
                    this.getField().setPowerUp(pos,false);
                }
                break;
            }
            i++;
        }
        if (i == this.field.getAttachedList(pos).size()){
            int j = 0;
            while ( j < enemy.getField().getAttachedList(pos).size()){
                if (enemy.getField().getAttachedList(pos).get(j) == this.field.getSkillCard(pos)){
                    SkillCard A = enemy.getField().getAttachedList(pos).remove(j);
                    if (A.getEffect() == Effect.AURA){
                        enemy.getField().setAtk(0,pos);
                        enemy.getField().setDef(0,pos);
                    } else {
                        enemy.getField().setPowerUp(pos,false);
                    }
                    break;
                }
                j++;
            }
        }
        this.field.discardSkillCard(pos);   
        
    }
        
    
}
    
   

