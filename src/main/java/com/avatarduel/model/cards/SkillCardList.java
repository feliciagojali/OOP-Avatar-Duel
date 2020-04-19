package com.avatarduel.model.cards;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import com.avatarduel.util.*;


/**
 * SkillCardList is the class that uses the singleton pattern
 * for generating new cards using its id.
 * @author mkamadeus
 */
public class SkillCardList 
{
    private static SkillCardList skillCardListInstance = null;

    private final Map<Integer, String[]> skillCardList;
    private final Set<Integer> skillCardIdList;

    // Define filepath to the CSV file
    private static final String SKILL_CARD_FILE_PATH = "../../card/data/skill.csv";

    private SkillCardList()
    {
        // Define file from known filepath
        File skillCardFile;
        this.skillCardList = new HashMap<Integer, String[]>();
        this.skillCardIdList = new TreeSet<Integer>();
        try {
            skillCardFile = new File(getClass().getResource(SKILL_CARD_FILE_PATH).toURI());
            // Define CSV readers for each file
            final CSVReader skillCardReader = new CSVReader(skillCardFile, "\t");
    
            skillCardReader.setSkipHeader(true);
            
            // Generate list of entry for card data
            final List<String[]> skillCardData = skillCardReader.read();
            
            // Input data to map
            
            for(final String[] skillCardEntry : skillCardData)
            {
                this.skillCardList.put(Integer.valueOf(skillCardEntry[0]), skillCardEntry);
                this.skillCardIdList.add(Integer.parseInt(skillCardEntry[0]));
            }
        } catch (final URISyntaxException | IOException e ) {
            e.printStackTrace();
        }
        

    }
    
    /** 
     * Get the list of skill in map form 
     */    
    public Map<Integer, String[]> getSkillCardList()
    {
        return this.skillCardList;
    }
    
    /**
     * Get the id list for the defined id
     */
    public Set<Integer> getSkillCardIdList()
    {
        return this.skillCardIdList;
    }
    
    /**
     * Get count of skill cards
     */
    public static int getSkillCardCount() 
    {   if(skillCardListInstance == null)
            skillCardListInstance = new SkillCardList();

        return skillCardListInstance.getSkillCardIdList().size();
    }
    
    /** 
     * Check whether a card is a skill card or not
     * @param id the card id
     */
    public static boolean isIdSkillCard(final int id) 
    {
        if(skillCardListInstance == null)
            skillCardListInstance = new SkillCardList();

        return skillCardListInstance.getSkillCardIdList().contains(new Integer(id));
    }

    /**
     * Get skill card by id
     * @param id the card id
     * @return SkillCard with data specified from the csv file
     */
    public static SkillCard getSkillCardById(final int id) 
    {
        if(skillCardListInstance == null)
            skillCardListInstance = new SkillCardList();
        
        final String[] cardData = skillCardListInstance.getSkillCardList().get(id);
        Random r = new Random();
        Effect effect;
        int x = r.nextInt(10);
        if (x < 5){
            effect = Effect.AURA;
        } else if ( x < 7){
            effect = Effect.POWER_UP;
        } else {
            effect = Effect.POWER_UP;
        }

        return new SkillCard(
            id,
            cardData[1],
            Element.valueOf(cardData[2]),
            cardData[3],
            cardData[4],
            Integer.parseInt(cardData[6]),
            Integer.parseInt(cardData[7]),
            Integer.parseInt(cardData[5]),
            effect
        );
    }
     
}