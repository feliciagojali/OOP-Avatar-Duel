package com.avatarduel.model.cards;


public class CharacterCard extends Card {
    private int attack;
    private int defense;
    private int power;
    private boolean powerup;

    public CharacterCard(int id, String name, Element element, String description, String imagePath, int attack, int defense, int power){
        super(id, name, element, description, imagePath);
        this.attack = attack;
        this.defense = defense;
        this.power = power;
        this.powerup = false;
    }

    public int getAttack(){
        return this.attack;
    }

    public int getDefense(){
        return this.defense;
    }

    public int getPower(){
        return this.power;
    }

    public boolean getPowerUp(){
        return this.powerup;
    }

    public void setPowerUp(boolean set){
        this.powerup = set;
    }
    
    public void setAttack(int atk){
        this.attack = atk;
    }

    public void setDefense(int def){
        this.defense = def;
    }
}