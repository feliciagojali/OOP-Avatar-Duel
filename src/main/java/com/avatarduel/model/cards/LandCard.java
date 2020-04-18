package com.avatarduel.model.cards;

/**
 * LandCard is the class that defines a land card in the game by
 * extending the base Card class.
 * @author mkamadeus
 */
public class LandCard extends Card {

  /**
   * LandCard constructor for its required values. 
   * @param id LandCard id
   * @param name LandCard name
   * @param element LandCard element
   * @param description LandCard description
   * @param imagePath LandCard image path relative to the AvatarDuel class
   */
  public LandCard(int id, String name, Element element, String description, String imagePath) {
    super(id, name, element, description, imagePath);
  }
}