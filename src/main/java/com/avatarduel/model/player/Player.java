package com.avatarduel.model.player;

import java.io.IOException;
import java.net.URISyntaxException;

import com.avatarduel.model.cards.*;

public class Player {
    private String name;
    private int hp;
    private ElementStats stats;
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

    public void discardCard(int pos) {
        if (!this.hand.isPosValid(pos))
            throw new RuntimeException("Invalid hand position");

        if (this.hand.getCard(pos) instanceof LandCard) {
            System.out.println("pisang");
            this.stats.addStats(this.hand.getCard(pos).getElement());
            this.hand.discardCard(pos);
        }

        /* TODO : Ini ntar ada attribute buat nyimpen last clicked buat ntar action taroh */

        // else if(CharacterCardList.isIdCharacterCard(x.getId())) {
        //     CharacterCard C = CharacterCardList.getCharacterCardById(x.getId());
        //     if (C.getElement() == Element.EARTH){
        //         if (this.stats.getEarthStats().getCurrent() >= C.getPower()) {
        //         }
        //     }
        //     else if (C.getElement() == Element.AIR){
        //         if (this.stats.getAirStats().getCurrent() >= C.getPower()){
        //         }
        //     }
        //     else if (C.getElement() == Element.WATER){
        //         if (this.stats.getWaterStats().getCurrent() >= C.getPower()){
        //         }
        //     }
        //     else {
        //         if (this.stats.getFireStats().getCurrent() >= C.getPower()){
        //         }
        //     }
        // }
        // else {
        //     SkillCard S = SkillCardList.getSkillCardById(x.getId());
        //     if (S.getElement() == Element.EARTH){
        //         if (this.stats.getEarthStats().getCurrent() >= S.getPower()) {
        //         }
        //     }
        //     else if (S.getElement() == Element.AIR){
        //         if (this.stats.getAirStats().getCurrent() >= S.getPower()){
        //         }
        //     }
        //     else if (S.getElement() == Element.WATER){
        //         if (this.stats.getWaterStats().getCurrent() >= S.getPower()){
        //         }
        //     }
        //     else {
        //         if (this.stats.getFireStats().getCurrent() >= S.getPower()){
        //         }
        //     }
        // }

    }

    public void playCard(int posHand, int posField){
        Card x = this.hand.getCard(posHand);
        if (CharacterCardList.isIdCharacterCard(x.getId())){
            if(this.field.isPosCharacterAvail(posField)){
                this.hand.discardCard(posHand);
                CharacterCard C = CharacterCardList.getCharacterCardById(x.getId());
                this.field.addCharacterRow(C, posField);
            }   
        } else if (SkillCardList.isIdSkillCard(x.getId())) {
            if (this.field.isPosSkillAvail(posField)){
                this.hand.discardCard(posHand);
                SkillCard C = SkillCardList.getSkillCardById(x.getId());
                this.field.addSkillRow(C, posField);
            }
        }
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