package com.avatarduel.model.cards;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.avatarduel.util.*;
import com.avatarduel.enums.Element;

public class SkillCardList 
{
    private static SkillCardList skillCardListInstance = null;

    private Map<Integer, String[]> skillCardList;
    private Set<Integer> skillCardIdList;

    // Define filepath to the CSV file
    private static final String SKILL_CARD_FILE_PATH = "../../card/data/skill.csv";

    private SkillCardList() throws URISyntaxException, IOException
    {
        // Define file from known filepath
        File skillCardFile = new File(getClass().getResource(SKILL_CARD_FILE_PATH).toURI());
        
        // Define CSV readers for each file
        CSVReader skillCardReader = new CSVReader(skillCardFile, "\t");

        skillCardReader.setSkipHeader(true);
        
        // Generate list of entry for card data
        List<String[]> skillCardData = skillCardReader.read();
        
        // Input data to map
        this.skillCardList = new HashMap<Integer, String[]>();
        this.skillCardIdList = new TreeSet<Integer>();
        for(String[] skillCardEntry : skillCardData)
        {
            this.skillCardList.put(Integer.valueOf(skillCardEntry[0]), skillCardEntry);
            this.skillCardIdList.add(Integer.parseInt(skillCardEntry[0]));
        }

    }

    public Map<Integer, String[]> getSkillCardList()
    {
        return this.skillCardList;
    }

    public Set<Integer> getSkillCardIdList()
    {
        return this.skillCardIdList;
    }

    public static int getSkillCardCount()
    {
        return skillCardListInstance.getSkillCardIdList().size();
    }

    public static boolean isIdSkillCard(int id)
    {
        return skillCardListInstance.getSkillCardIdList().contains(new Integer(id));
    }

    public static SkillCard getSkillCardById(int id) throws URISyntaxException, IOException
    {
        if(skillCardListInstance == null)
            skillCardListInstance = new SkillCardList();
        
        String[] cardData = skillCardListInstance.getSkillCardList().get(id);

        return new SkillCard(
            id,
            cardData[1],
            Element.valueOf(cardData[2]),
            cardData[3],
            cardData[4],
            Integer.parseInt(cardData[7]),
            Integer.parseInt(cardData[5]),
            Integer.parseInt(cardData[6])
        );
    }
     
}