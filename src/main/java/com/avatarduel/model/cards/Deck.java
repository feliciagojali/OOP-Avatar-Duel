package com.avatarduel.model.cards;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;

public class Deck {
    private Stack<Integer> cards;
    private int size;
    private static final int LAND_CARD_WEIGHT = 2;
    private static final int CHARACTER_CARD_WEIGHT = 2;
    private static final int SKILL_CARD_WEIGHT = 1;

    public Deck() {
        this.cards = new Stack<>();
        this.size = 60;
    }

    public void initializeDeck() throws IOException, URISyntaxException
    {
        int totalWeight = LAND_CARD_WEIGHT + CHARACTER_CARD_WEIGHT + SKILL_CARD_WEIGHT;
        int totalCardCount = LandCardList.getLandCardCount() + CharacterCardList.getCharacterCardCount() + SkillCardList.getSkillCardCount();

        int landCardCount = (LAND_CARD_WEIGHT/totalWeight) * this.size;
        int characterCardCount = (CHARACTER_CARD_WEIGHT/totalWeight) * this.size;
        int skillCardCount = (SKILL_CARD_WEIGHT/totalWeight) * this.size;

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

    public void shuffle() {
        Collections.shuffle(this.cards);
    }

    public int drawCard()
    {
        return this.cards.pop();
    }

    public boolean isDeckEmpty()
    {
        return this.cards.isEmpty();
    }
}