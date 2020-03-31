package com.avatarduel.model;

class CharacterCard extends Card{
    private int attack;
    private int defense;
    private int power;

    public CharacterCard(int id, String name, Element element, String description, String imagepath, int attack, int defense, int power){
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