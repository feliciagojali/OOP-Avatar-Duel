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

public class CharacterCardList 
{
    private static CharacterCardList characterCardListInstance = null;

    private Map<Integer, String[]> characterCardList;
    private Set<Integer> characterCardIdList;

    // Define filepaths to the CSV file
    private static final String CHARACTER_CARD_FILE_PATH = "../../card/data/character.csv";

    private CharacterCardList() throws URISyntaxException, IOException
    {
        // Define file from known filepaths
        File characterCardFile = new File(getClass().getResource(CHARACTER_CARD_FILE_PATH).toURI());
        
        // Define CSV readers for each file
        CSVReader characterCardReader = new CSVReader(characterCardFile, "\t");

        characterCardReader.setSkipHeader(true);
        
        // Generate list of entry for card data
        List<String[]> characterCardData = characterCardReader.read();
        
        // Input data to map
        this.characterCardList = new HashMap<Integer, String[]>();
        this.characterCardIdList = new TreeSet<Integer>();
        for(String[] characterCardEntry : characterCardData)
        {
            this.characterCardList.put(Integer.valueOf(characterCardEntry[0]), characterCardEntry);
            this.characterCardIdList.add(Integer.parseInt(characterCardEntry[0]));
        }

    }

    public Map<Integer, String[]> getCharacterCardList()
    {
        return this.characterCardList;
    }

    public Set<Integer> getCharacterCardIdList()
    {
        return this.characterCardIdList;
    }

    public static int getCharacterCardCount()
    {
        return characterCardListInstance.getCharacterCardIdList().size();
    }

    public static boolean isIdCharacterCard(int id)
    {
        return characterCardListInstance.getCharacterCardIdList().contains(new Integer(id));
    }

    public static CharacterCard getCharacterCardById(int id) throws URISyntaxException, IOException
    {
        if(characterCardListInstance == null)
            characterCardListInstance = new CharacterCardList();
        
        String[] cardData = characterCardListInstance.getCharacterCardList().get(id);

        return new CharacterCard(
            id,
            cardData[1],
            Element.valueOf(cardData[2]),
            cardData[3],
            cardData[4],
            Integer.parseInt(cardData[5]),
            Integer.parseInt(cardData[6]),
            Integer.parseInt(cardData[7])
        );
    }
     
}