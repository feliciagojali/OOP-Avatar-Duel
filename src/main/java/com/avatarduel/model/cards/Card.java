package com.avatarduel.model.cards;

/**
 * Card is a class that holds the base data that all card must have.
 *
 * @author mkamadeus
 */
public class Card {
    protected int id;
    protected String name;
    protected Element element;
    protected String description;
    protected String imagePath;

    /**
     * The Card class constructor, using the required data for a card.
     * @param id Card id
     * @param name Card name
     * @param element Card Element, using the Element enum provided in this package
     * @param description Card description
     * @param imagePath Card image path relative to the AvatarDuel class
     */
    public Card(int id, String name, Element element, String description, String imagePath){
        this.id = id;
        this.name = name;
        this.element = element;
        this.description = description;
        this.imagePath = imagePath;
    }

    /**
     * Getter for card id
     * @return integer of the card's id
     */
    public int getId()
    {
        return this.id;
    }

    /**
     * Getter for card name
     * @return String of the card's name
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Getter for card element
     * @return one of the defined Element enum value depending on card's element
     */
    public Element getElement()
    {
        return this.element;
    }

    /**
     * Getter for card description
     * @return String of card's description
     */
    public String getDescription()
    {
        return this.description;
    }

    /**
     * Getter for card image path
     * @return String of card's image path relative to the AvatarDuel class
     */
    public String getImagePath()
    {
        return this.imagePath;
    }
    
    /**
     * Overriden version of the toString() method to print basic card info in the console.
     */
    @Override
    public String toString()
    {
        return Integer.toString(this.id) + " " + this.name  + " " + String.valueOf(this.element);
    }
}