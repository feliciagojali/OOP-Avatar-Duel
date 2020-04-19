package com.avatarduel.model.cards;

/**
 * SkillCard is the class that defines a skill card by
 * extending the base class Card.
 * @author mkamadeus
 * @author feliciagojali
 */
public class SkillCard extends Card{
    protected int attack;
    protected int defense;
    protected int power;
    protected Effect effect;

    /**
     * SkillCard constructor for its required values.
     * @param id SkillCard id
     * @param name SkiilCard name
     * @param element SkillCard element
     * @param description SkillCard description
     * @param imagepath SkillCard image path relative to the AvatarDuel class
     * @param attack SkillCard attack value
     * @param defense SkillCard defense value
     * @param power SkillCard power value (works as cost)
     * @param effect SKillCard effect using the Effect enum provided
     */
    public SkillCard(int id, String name, Element element, String description, String imagepath, int attack, int defense, int power,Effect effect){
        super(id, name, element, description, imagepath);
        this.attack = attack;
        this.defense = defense;
        this.power = power;
        this.effect = effect;
    }

    /**
     * Getter for attack value
     * @return integer of the skill card's attack value
     */
    public int getAttack(){
        return this.attack;
    }
    
    /**
     * Getter for defense value
     * @return integer of the skill card's defense value
     */
    public int getDefense(){
        return this.defense;
    }
    
    /**
     * Getter for power value
     * @return integer of the skill card's power value
     */
    public int getPower(){
        return this.power;
    }
    
    /**
     * Getter for effect on field
     * @return integer of the skill card's effect on field
     */
    public Effect getEffect(){
        return this.effect;
    }
}