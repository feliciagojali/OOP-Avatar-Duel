package com.avatarduel.model.player;


import java.util.Collections;
import java.util.Random;
import java.util.Stack;
import com.avatarduel.model.cards.*;


/**
 * Deck is a class that holds cards for each player.
 *
 * @author mkamadeus
 */
public class Deck {
    private Stack<Integer> cards;
    private int size;
    private static final int LAND_CARD_WEIGHT = 2;
    private static final int CHARACTER_CARD_WEIGHT = 2;
    private static final int SKILL_CARD_WEIGHT = 1;

    /**
   * Deck cosntructor for its required values.
   */
    public Deck() {
        this.cards = new Stack<>();
        this.size = 67;
    }
    /**
    * Filling the Deck with cards.
    */
    public void initializeDeck() 
    {
        int totalWeight = LAND_CARD_WEIGHT + CHARACTER_CARD_WEIGHT + SKILL_CARD_WEIGHT;
        int totalCardCount = LandCardList.getLandCardCount() + CharacterCardList.getCharacterCardCount() + SkillCardList.getSkillCardCount();

        int landCardCount = (int)(((double)LAND_CARD_WEIGHT/totalWeight) * this.size);
        int characterCardCount = (int)(((double)CHARACTER_CARD_WEIGHT/totalWeight) * this.size);
        int skillCardCount = (int)(((double)SKILL_CARD_WEIGHT/totalWeight) * this.size);

        Random random = new Random();

        // Select random IDs for land cards
        while(landCardCount > 0)
        {
            int id = random.nextInt(totalCardCount) + 1;
            if(LandCardList.isIdLandCard(id))
            {
                landCardCount--;
                this.cards.push(id);
            }
        }
        
        // Select random IDs for character cards
        while(characterCardCount > 0)
        {
            int id = random.nextInt(totalCardCount) + 1;
            if(CharacterCardList.isIdCharacterCard(id))
            {
                characterCardCount--;
                this.cards.push(id);
            }
        }

        // Select random IDs for skill cards
        while(skillCardCount > 0)
        {
            int id = random.nextInt(totalCardCount) + 1;
            if(SkillCardList.isIdSkillCard(id))
            {
                skillCardCount--;
                this.cards.push(id);
            }
        }

        // Fill in the rest (taking care of rounding)
        while(this.cards.size() < this.size)
        {
            int id = random.nextInt(totalCardCount) + 1;
            this.cards.push(id);
        }
    }

    /**
    * Shuffling the cards in the deck.
    */
    public void shuffle() {
        Collections.shuffle(this.cards);
    }

    /**
    * Drawing a card from the deck.
    * @return integer of the card's id.
    */
    public int drawCard()
    {
        return this.cards.pop();
    }

    /**
    * Get how many cards left in the deck.
    * @return integer of cards' count in the deck.
    */
    public int getCardsLeft()
    {
        return this.cards.size();
    }

    /**
    * Checking if the deck is empty.
    * @return boolean if the deck is empty.
    */
    public boolean isDeckEmpty()
    {
        return this.cards.isEmpty();
    }
}