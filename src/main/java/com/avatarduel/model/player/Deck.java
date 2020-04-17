package com.avatarduel.model.player;


import java.util.Collections;
import java.util.Random;
import java.util.Stack;
import com.avatarduel.model.cards.*;




public class Deck {
    private Stack<Integer> cards;
    private int size;
    private static final int LAND_CARD_WEIGHT = 2;
    private static final int CHARACTER_CARD_WEIGHT = 2;
    private static final int SKILL_CARD_WEIGHT = 1;

    public Deck() {
        this.cards = new Stack<>();
        this.size = 67;
    }

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

    // Shuffle cards
    public void shuffle() {
        Collections.shuffle(this.cards);
    }

    // Take card id from deck
    public int drawCard()
    {
        return this.cards.pop();
    }

    // Get cards left in deck
    public int getCardsLeft()
    {
        return this.cards.size();
    }

    // Returns true when deck is empty
    public boolean isDeckEmpty()
    {
        return this.cards.isEmpty();
    }
}