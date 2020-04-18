package com.avatarduel.model.cards;

/**
 * CharacterCard is the class that defines a character card by extending
 * the base Card class.
 * 
 * @author mkamadeus
 */
public class CharacterCard extends Card {
    private int attack;
    private int defense;
    private int power;
    private boolean powerup;

    /**
     * CharacterCard cosntructor for its required values.
     * @param id CharacterCard id
     * @param name CharacterCard name
     * @param element CharacterCard element
     * @param description CharacterCard description
     * @param imagePath CharacterCard image path
     * @param attack CharcterCard attack value
     * @param defense CharacterCard defense value
     * @param power CharacterCard power value (works as card cost)
     */
    public CharacterCard(int id, String name, Element element, String description, String imagePath, int attack, int defense, int power){
        super(id, name, element, description, imagePath);
        this.attack = attack;
        this.defense = defense;
        this.power = power;
        this.powerup = false;
    }

    /**
     * Getter for card's attack value
     * @return integer of card's attack value
     */
    public int getAttack(){
        return this.attack;
    }
    
    /**
     * Getter for card's defense value
     * @return integer of card's defense value
     */
    public int getDefense(){
        return this.defense;
    }
    
    /**
     * Getter for card's power value
     * @return integer of card's power value
     */
    public int getPower(){
        return this.power;
    }
    
    /**
     * Getter for card's power value
     * @return integer of card's power value
     */
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