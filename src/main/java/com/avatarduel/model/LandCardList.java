package com.avatarduel.model;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.avatarduel.util.*;

public class LandCardList 
{
    private static LandCardList landCardListInstance = null;

    private Map<Integer, String[]> landCardList;

    // Define filepaths to the CSV file
    private static final String LAND_CARD_FILE_PATH = "../card/data/land.csv";

    private LandCardList() throws URISyntaxException, IOException
    {
        // Define file from known filepaths
        File landCardFile = new File(getClass().getResource(LAND_CARD_FILE_PATH).toURI());
        
        // Define CSV readers for each file
        CSVReader landCardReader = new CSVReader(landCardFile, "\t");

        landCardReader.setSkipHeader(true);
        
        // Generate list of entry for card data
        List<String[]> landCardData = landCardReader.read();
        
        // Input data to map
        this.landCardList = new HashMap<Integer, String[]>();
        for(String[] landCardEntry : landCardData)
            landCardList.put(Integer.valueOf(landCardEntry[0]), landCardEntry);

    }

    public Map<Integer, String[]> getLandCardList()
    {
        return this.landCardList;
    }

    public static LandCard getLandCardById(int id) throws URISyntaxException, IOException
    {
        if(landCardListInstance == null)
            landCardListInstance = new LandCardList();
        
        String[] cardData = landCardListInstance.getLandCardList().get(id);

        return new LandCard(id, cardData[1], Element.valueOf(cardData[2]), cardData[3], cardData[4]);
    }
     
}