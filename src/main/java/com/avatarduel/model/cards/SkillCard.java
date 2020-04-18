package com.avatarduel.model.cards;

/**
 * SkillCard is the class that defines a skill card by extending
 * the base Card class.
 * 
 * @author mkamadeus
 */
public class SkillCard extends Card{
    protected int attack;
    protected int defense;
    protected int power;
    protected Effect effect;

    /**
     * SkillCard cosntructor for its required values.
     * @param id SkillCard id
     * @param name SkillCard name
     * @param element SkillCard element
     * @param description SkillCard description
     * @param imagePath SkillCard image path
     * @param attack CharcterCard attack value
     * @param defense SkillCard defense value
     * @param power SkillCard power value (works as card cost)
     * @param effect SkillCard effect
     */
    public SkillCard(int id, String name, Element element, String description, String imagepath, int attack, int defense, int power,Effect effect){
        super(id, name, element, description, imagepath);
        this.attack = attack;
        this.defense = defense;
        this.power = power;
        this.effect = effect;
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
     * Getter for card's effect 
     * @return Effect of card's effect
     */
    public Effect getEffect(){
        return this.effect;
    }
}