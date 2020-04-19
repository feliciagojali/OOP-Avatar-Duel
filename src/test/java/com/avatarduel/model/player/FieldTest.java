package com.avatarduel.model.player;

import com.avatarduel.model.cards.CharacterCard;
import com.avatarduel.model.cards.Effect;
import com.avatarduel.model.cards.Element;
import com.avatarduel.model.cards.SkillCard;
import junit.framework.TestCase;

public class FieldTest extends TestCase {

    public void testAddSkillCard() {
        Field field = new Field();
        SkillCard card = new SkillCard(1,"A", Element.ENERGY,"perfect","perfect.png",5,5,5, Effect.AURA);
        field.addSkillCard(card,0);
        assertEquals(field.getSkillCard(0),card);
    }

    public void testAddCharacterCard() {
        Field field = new Field();
        CharacterCard card = new CharacterCard(1,"A", Element.ENERGY,"perfect","perfect.png",5,5,5);
        field.addCharacterCard(card,0);
        assertEquals(field.getCharacterCard(0),card);
    }

    public void testDiscardCharacterCard() {
        Field field = new Field();
        CharacterCard card = new CharacterCard(1,"A", Element.ENERGY,"perfect","perfect.png",5,5,5);
        field.addCharacterCard(card,0);
        field.discardCharacterCard(0);
        assertEquals(null,field.getCharacterCard(0));
    }

    public void testIsFieldEmpty() {
        Field field = new Field();
        assertEquals(true,field.isFieldEmpty());


    }

}