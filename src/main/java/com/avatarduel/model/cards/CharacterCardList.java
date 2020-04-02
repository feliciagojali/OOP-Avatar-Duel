package com.avatarduel.model.cards;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.avatarduel.util.*;
import com.avatarduel.enums.Element;

public class CharacterCardList 
{
    private static CharacterCardList characterCardListInstance = null;

    private Map<Integer, String[]> characterCardList;

    // Define filepaths to the CSV file
    private static final String CHARACTER_CARD_FILE_PATH = "../card/data/character.csv";

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
        for(String[] characterCardEntry : characterCardData)
            characterCardList.put(Integer.valueOf(characterCardEntry[0]), characterCardEntry);

    }

    public Map<Integer, String[]> getCharacterCardList()
    {
        return this.characterCardList;
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