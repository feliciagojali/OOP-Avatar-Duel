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


/**
 * LandCardList is the class that contains database for all Land Card.
 * 
 * @author mkamadeus
 */

public class LandCardList 
{
    private static LandCardList landCardListInstance = null;

    private final Map<Integer, String[]> landCardList;
    private final Set<Integer> landCardIdList;

    // Define filepath to the CSV file
    private static final String LAND_CARD_FILE_PATH = "../../card/data/land.csv";

    private LandCardList()
    {

        // Input data to map
        this.landCardList = new HashMap<Integer, String[]>();
        this.landCardIdList = new TreeSet<Integer>();
        // Define file from known filepaths
        File landCardFile;
        try {
            landCardFile = new File(getClass().getResource(LAND_CARD_FILE_PATH).toURI());
            
            final CSVReader landCardReader = new CSVReader(landCardFile, "\t");
            // Define CSV readers for each file
            
            final CSVReader landCardReader2 = landCardReader;
            landCardReader2.setSkipHeader(true);
            // Generate list of entry for card data
            final List<String[]> landCardData = landCardReader2.read();
            
            // Input data to map
            
            for(final String[] landCardEntry : landCardData)
            {
    
                this.landCardList.put(Integer.valueOf(landCardEntry[0]), landCardEntry);
                this.landCardIdList.add(Integer.parseInt(landCardEntry[0]));
            }
        } catch (final URISyntaxException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        

    }

    public Map<Integer, String[]> getLandCardList()
    {
        return this.landCardList;
    }

    public Set<Integer> getLandCardIdList()
    {
        return this.landCardIdList;
    }

    public static int getLandCardCount()
    {
        if(landCardListInstance == null)
            landCardListInstance = new LandCardList();
        
        return landCardListInstance.getLandCardIdList().size();
    }

    public static boolean isIdLandCard(final int id)
    {
        if(landCardListInstance == null)
            landCardListInstance = new LandCardList();

        return landCardListInstance.getLandCardIdList().contains(new Integer(id));
    }

    public static LandCard getLandCardById(final int id)
    {
        if(landCardListInstance == null)
            landCardListInstance = new LandCardList();
        
        final String[] cardData = landCardListInstance.getLandCardList().get(id);

        return new LandCard(id, cardData[1], Element.valueOf(cardData[2]), cardData[3], cardData[4]);
    }
     
}