package com.avatarduel.model.player;

import com.avatarduel.model.cards.CharacterCard;
import com.avatarduel.model.cards.Effect;
import com.avatarduel.model.cards.Element;
import com.avatarduel.model.cards.SkillCard;
import com.avatarduel.util.InvalidActionException;
import junit.framework.TestCase;

public class PlayerTest extends TestCase {


    public void testDrawCard() {
        Player A = new Player("A");
        A.drawCard();
        assertEquals(A.getHand().getCards().size(),1);
    }

    public void testUseCard() {


        try {
            Player A = new Player("A");
            A.drawCard();
            A.useCard(0);
            assertEquals(A.getHand().getCards().size(),0);
        } catch (InvalidActionException e){
            assert false;
        }

    }

    public void testSelectCard() {


        try{
            Player A = new Player("A");
            A.drawCard();
            A.selectCard(0);
        } catch(InvalidActionException e){
            assert true;
        }
    }

    public void testPlayCard() {

        try {
            Player A = new Player("A");
            A.drawCard();
            A.playCard(0,0);
            assertEquals(A.getHand().getCards().size(),0);
        } catch (InvalidActionException e){
            assert false;
        }

    }

    public void testAttack() {

        try{
            Player A = new Player("A");
            Player B = new Player("B");
            CharacterCard card = new CharacterCard(1,"A", Element.ENERGY,"perfect","perfect.png",8,5,5);
            CharacterCard card1 = new CharacterCard(1,"A", Element.ENERGY,"perfect","perfect.png",5,5,5);

            A.getField().addCharacterCard(card,0);
            B.getField().addCharacterCard(card1,0);
            A.attack(B,0,0);
            assertEquals(B.getField().isFieldEmpty(),true);
        } catch (InvalidActionException e){
            assert false;
        }
    }

    public void testAttackEnemy() {
        try {
            Player A = new Player("A");
            Player B = new Player("B");
            CharacterCard card = new CharacterCard(1,"A", Element.ENERGY,"perfect","perfect.png",8,5,5);
            A.getField().addCharacterCard(card,0);
            A.attackEnemy(B,0);
            assertEquals(B.getHp(),72);
        }catch (InvalidActionException e){
            assert false;
        }
    }

    public void testUseSkill() {
        Player A = new Player("A");
        Player B = new Player("B");
        CharacterCard card = new CharacterCard(1,"A", Element.ENERGY,"perfect","perfect.png",8,5,5);
        SkillCard card1 = new SkillCard(1,"A", Element.ENERGY,"perfect","perfect.png",5,5,5,Effect.POWER_UP);

        A.getField().addCharacterCard(card,0);
        A.getField().addSkillCard(card1,0);
        A.useSkill(A,B,0,0);
        assertEquals(A.getField().getPowerUp(0),true);
    }

    public void testDetach() {
        Player A = new Player("A");
        Player B = new Player("B");
        CharacterCard card = new CharacterCard(1,"A", Element.ENERGY,"perfect","perfect.png",8,5,5);
        SkillCard card1 = new SkillCard(1,"A", Element.ENERGY,"perfect","perfect.png",5,5,5,Effect.POWER_UP);

        A.getField().addCharacterCard(card,0);
        A.getField().addSkillCard(card1,0);
        A.useSkill(A,B,0,0);
        A.detach(A,0);
        assertEquals(A.getField().getPowerUp(0),false);

    }

    public void testDiscardCharacterCard() {
        Player A = new Player("A");
        CharacterCard card = new CharacterCard(1,"A", Element.ENERGY,"perfect","perfect.png",8,5,5);
        A.getField().addCharacterCard(card,0);
        A.discardCharacterCard(A,0);
        assertEquals(A.getField().isFieldEmpty(),true);

    }
}