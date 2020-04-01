package com.avatarduel.model;

public class LandCard extends Card {

  public LandCard(int id, String name, Element element, String description, String imagePath) {
    super(id, name, element, description, imagePath);
  }

  public void printCard()
  {
    System.out.println(this.id + " " + this.name + " " + this.element+ " " + this.description+ " " + this.imagePath);
  }
}