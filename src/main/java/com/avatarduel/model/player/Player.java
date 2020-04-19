package com.avatarduel.model.player;

import java.io.IOException;
import java.net.URISyntaxException;

import com.avatarduel.model.cards.*;
import com.avatarduel.util.AlertBox;
import com.avatarduel.util.InvalidActionException;

/**
 * Stats is the class that defines a player.
 * @author feliciagojali
 * @author mkamadeus
 */

public class Player {
    // General player fields
    private String name;
    private int hp;
    private ElementStats stats;

    // Card related fields
    private Hand hand;
    private Deck deck;
    private Field field;

    /**
     * Player constructor.
     * @param name Player's name.
     */
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
    /**
     * Getter for player's deck
     * @return Deck of the player
     */
    public Deck getDeck(){
        return this.deck;
    }

    /**
     * Getter for player's Hand
     * @return Hand of the player
     */
    public Hand getHand(){
        return this.hand;
    }

    /**
     * Getter for player's Field
     * @return Field of the player
     */
    public Field getField(){
        return this.field;
    }

    /**
     * Getter for player's Elemen status
     * @return ElemenStats of the player
     */
    public ElementStats getElementStats(){
        return this.stats;
    }

    /**
     * Getter for player's HP
     * @return integer value of the player's hp
     */
    public int getHp(){
        return this.hp;
    }

    /**
     * Getter for player's name
     * @return String value of the player's name
     */
    public String getName(){
        return this.name;
    }

    /**
     * Setter for player's HP
     * @param x player's HP
     */
    public void setHp(int x){
        this.hp = x;
    }

    /**
     * Drawing a card from player's deck.
     * Adding the card to player's hand.
     */
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

    /**
     * Getting a card from the player's hand.
     * @param pos position of the card in the player's hand.
     */
    // Assume position given is is a LandCard
    public void useCard(int pos) throws InvalidActionException
    {
        if (!this.hand.isPosValid(pos)) { throw new InvalidActionException("Invalid hand position"); }
        
        this.stats.addStats(this.hand.getCard(pos).getElement());
        this.hand.discardCard(pos);
        
        if (this.hand.getCard(pos) instanceof LandCard) {
        }
    }
    
    /**
     * Getting a card from the player's hand.
     * Checking if the stats of the player is sufficient to play the card on field.
     * @param pos position of the card in player's hand.
     */
    // Assume position given is either a CharacterCard or a LandCard
    public void selectCard(int pos) throws InvalidActionException
    {
        if (!this.hand.isPosValid(pos)) { throw new InvalidActionException("Invalid hand position"); }

        if(this.hand.getCard(pos) instanceof CharacterCard) {
            CharacterCard card = (CharacterCard)this.hand.getCard(pos);
    
            // If power > current stats, throw exception
            if(card.getPower() > this.stats.getStats(card.getElement()).getCurrent())
            { throw new InvalidActionException("Insufficent element stats"); }   
        }
        else 
        {
            SkillCard card = (SkillCard)this.hand.getCard(pos);
            
            // If power > current stats, throw exception
            if(card.getPower() > this.stats.getStats(card.getElement()).getCurrent()) { throw new InvalidActionException("Insufficent element stats"); }
        }

    }
    /**
     * Discard a card from the player's hand.
     * Put the card on the player's field.
     * @param posHand position of the card in player's hand.
     * @param posField position of the player's field.
     */
    // Assume position given is either a CharacterCard or a LandCard
    public void playCard(int posHand, int posField) throws InvalidActionException{
        if (this.hand.getCard(posHand) instanceof CharacterCard)
        {
            if(this.field.isCharacterPositionAvailable(posField)){
                CharacterCard card = (CharacterCard)this.hand.getCard(posHand);
                this.hand.discardCard(posHand);
                this.field.addCharacterCard(card, posField);
                this.stats.reduceStats(card.getElement(), card.getPower());
            }   
        }
        else
        {
            if (this.field.isSkillPositionAvailable(posField)){
                SkillCard card = (SkillCard)this.hand.getCard(posHand);
                this.hand.discardCard(posHand);
                this.field.addSkillCard(card, posField);
                this.stats.reduceStats(card.getElement(), card.getPower());
            }
        }

    }
    /**
     * Checking if a card can attack or not.
     * @param posisi position of the card in player's field.
     * @return boolean of the card's ability to attack.
     */
    public boolean canAttack(int posisi){
        return (!this.field.isCharacterPositionAvailable(posisi) && this.field.canAttack(posisi));
    }

    public void attack(Player enemy,int Pos, int enemyPos) throws InvalidActionException{
        if (canAttack(Pos)) {
            if (!enemy.getField().isCharacterPositionAvailable(enemyPos)) {
                int attack = this.getField().getCharacterCard(Pos).getAttack() + this.getField().getAtk(Pos);
                int enemyatk = enemy.getField().getCharacterCard(enemyPos).getAttack() + enemy.getField().getAtk(enemyPos);
                int enemydef = enemy.getField().getCharacterCard(enemyPos).getDefense() + enemy.getField().getDef(enemyPos);
                boolean powerup = this.field.getPowerUp(Pos);
                // posisi kartu enemy menyerang
                if (enemy.getField().getStance(enemyPos) || powerup) {
                    if (enemyatk >= attack) {
                        throw new InvalidActionException("Your attack is not enough!");
                    }
                        int selisihattack = attack - enemyatk;
                        enemy.getField().discardCharacterCard(enemyPos);
                        enemy.setHp(enemy.getHp()-selisihattack);
                        this.field.setHasAttack(Pos);
                
                } else {
                    // posisi kartu bertahan
                    if (enemydef >= attack){
                        throw new InvalidActionException("Your attack is not enough!");
                    }
                        enemy.getField().discardCharacterCard(enemyPos);
                        this.field.setHasAttack(Pos);

                    }
                }


            }
        }
    


    // ini attack kalau di field lawan udah gaada kartu samsek
    public void AttackEnemy(Player enemy,int pos){
        if (canAttack(pos)){
            int attack = this.field.getCharacterCard(pos).getAttack() + this.getField().getAtk(pos);
            enemy.setHp(enemy.getHp()-attack);
            this.field.setHasAttack(pos);
        } 
            

    }

    public boolean canSkill(int pos){
        return(!this.field.isSkillPositionAvailable(pos));
    }
   
    public void useSkill(Player player, int posSkill){
        if (canSkill(posSkill)) {
            if(!player.getField().isCharacterPositionAvailable(posSkill)){
                SkillCard X = this.field.getSkillCard(posSkill);
                switch (X.getEffect()) {
                    case AURA:
                        int atk = X.getAttack();
                        int def = X.getDefense();
                        player.getField().setAtk(atk,posSkill);
                        player.getField().setDef(def,posSkill);                
                        break;
                    
                    case DESTROY:
                        player.getField().discardCharacterCard(posSkill);
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
 
    public Player whoseSkill(Player player,int pos){
        int i = 0;
        while (i < this.field.getAttachedList(pos).size()){
            if (this.field.getAttachedList(pos).get(i) == this.field.getSkillCard(pos)){
                return this;
            }
            i++;
        }
        return player;
    }
    
}
    
   

