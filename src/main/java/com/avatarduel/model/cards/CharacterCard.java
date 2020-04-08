package com.avatarduel.model.cards;


public class CharacterCard extends Card implements FieldCard{
    private int attack;
    private int defense;
    private int power;

    public CharacterCard(int id, String name, Element element, String description, String imagePath, int attack, int defense, int power){
        super(id, name, element, description, imagePath);
        this.attack = attack;
        this.defense = defense;
        this.power = power;
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
}