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
    public Player(String name){
        this.name = name;
        this.hp = 80;
        this.cardInHand = new Hand();
        Deck d = new Deck();
        d.initializeDeck();
        d.shuffle();
        this.deck = d;
        for(int i=0;i<=6;i++){
            this.drawCardfromDeck();
        }
        this.field =  new Field();
        this.stats = new ElemenStats();
    }
    
    public Field getField(){
        return this.field;
    }

    public int getHp(){
        return this.hp;
    }

    public void setHp(int x){
        this.hp = x;
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
            else if(CharacterCardList.isIdCharacterCard(x.getId())) {
                
            }

        }       
    }

    public void playCard(int posTangan, int posField){
        Card x = this.cardInHand.getCard(posTangan);
        if (CharacterCardList.isIdCharacterCard(x.getId())){
            if(this.field.isPosCharacterAvail(posField)){
                this.cardInHand.discardCard(posTangan);
                CharacterCard C = CharacterCardList.getCharacterCardById(x.getId());
                this.field.addCharacterRow(C, posField);
            }   
        } else if (SkillCardList.isIdSkillCard(x.getId())) {
            if (this.field.isPosSkillAvail(posField)){
                this.cardInHand.discardCard(posTangan);
                SkillCard C = SkillCardList.getSkillCardById(x.getId());
                this.field.addSkillRow(C, posField);
            }
        }
    }
    
    public boolean canAttack(int posisi){
        return (!this.field.isPosCharacterAvail(posisi) && this.field.canAttack(posisi));
    }
    public void attack(Player enemy,int Pos, int EnemyPos){
        if (canAttack(Pos)) {
            if (!this.field.isPosCharacterAvail(EnemyPos)) {
                // posisi kartu enemy menyerang
                if (enemy.getField().getCharacterStance(EnemyPos)) {
                    if (enemy.getField().getCharacterCard(EnemyPos).getAttack() < this.field.getCharacterCard(Pos).getAttack()) {
                        int selisihattack = this.field.getCharacterCard(Pos).getAttack() - enemy.getField().getCharacterCard(EnemyPos).getAttack();
                        enemy.getField().discardCharaCard(EnemyPos);
                        enemy.setHp(enemy.getHp()-selisihattack);
                        this.field.setHasAtk(Pos);
                        this.field.unableChange(Pos);
                    }
                } else {
                    // posisi kartu bertahan
                    if (enemy.getField().getCharacterCard(EnemyPos).getDefense() < this.field.getCharacterCard(Pos).getDefense()){
                        enemy.getField().discardCharaCard(EnemyPos);
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