package com.avatarduel.model.cards;


public class SkillCard extends Card{
    protected int attack;
    protected int defense;
    protected int power;

    public SkillCard(int id, String name, Element element, String description, String imagepath, int attack, int defense, int power){
        super(id, name, element, description, imagepath);
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