// package com.avatarduel.model;

// import java.io.File;
// import java.io.IOException;
// import java.net.URISyntaxException;
// import java.util.List;
// import java.util.Map;
// import com.avatarduel.util.*;

// class CardList 
// {
//     private static Map<Integer, String[]> landCardList;
//     private static Map<Integer, String[]> characterCardList;
//     private static Map<Integer, String[]> skillCardList;

//     // Define filepaths to the CSV file
//     private static final String LAND_CARD_FILE_PATH = "card/data/land.csv";
//     private static final String CHARACTER_CARD_FILE_PATH = "card/data/character.csv";
//     private static final String SKILL_CARD_FILE_PATH = "card/data/skill.csv";

//     private CardList() throws URISyntaxException, IOException
//     {
//         // Define file from known filepaths
//         File landCardFile = new File(getClass().getResource(LAND_CARD_FILE_PATH).toURI());
//         File characterCardFile = new File(getClass().getResource(CHARACTER_CARD_FILE_PATH).toURI());
//         File skillCardFile = new File(getClass().getResource(SKILL_CARD_FILE_PATH).toURI());

//         // Define CSV readers for each file
//         CSVReader landCardReader = new CSVReader(landCardFile, "\t");
//         CSVReader characterCardReader = new CSVReader(characterCardFile, "\t");
//         CSVReader skillCardReader = new CSVReader(skillCardFile, "\t");

//         landCardReader.setSkipHeader(true);
//         characterCardReader.setSkipHeader(true);
//         skillCardReader.setSkipHeader(true);

//         // Generate list of entry for card data
//         List<String[]> landCardData = landCardReader.read();
//         List<String[]> characterCardData = characterCardReader.read();
//         List<String[]> skillCardData = skillCardReader.read();

//         // Input data to map
//         for(String[] landCardEntry : landCardData)
//             landCardList.put(Integer.valueOf(landCardEntry[0]), landCardEntry);
//         for(String[] characterCardEntry : characterCardData)
//             characterCardList.put(Integer.valueOf(characterCardEntry[0]), characterCardEntry);
//         for(String[] skillCardEntry : skillCardData)
//             skillCardList.put(Integer.valueOf(skillCardEntry[0]), skillCardEntry);

//     }

//     public static getLandCardById(int id)
//     {
//         if(this.landCardList == null)
            
//         return new Card(id)
//     }
     
// }