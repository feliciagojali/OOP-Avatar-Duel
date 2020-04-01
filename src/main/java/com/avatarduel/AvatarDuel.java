package com.avatarduel;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javafx.fxml.FXMLLoader;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import com.avatarduel.model.Element;
import com.avatarduel.model.LandCard;
import com.avatarduel.util.CSVReader;
import com.avatarduel.model.LandCardList;

public class AvatarDuel extends Application {
  // private static final String LAND_CSV_FILE_PATH = "card/data/land.csv";

  // public void loadCards() throws IOException, URISyntaxException {
  //   File landCSVFile = new File(getClass().getResource(LAND_CSV_FILE_PATH).toURI());
  //   CSVReader landReader = new CSVReader(landCSVFile, "\t");
  //   landReader.setSkipHeader(true);
  //   List<String[]> landRows = landReader.read();
  //   for (String[] row : landRows) {
  //     Land l = new Land(row[1], row[3], Element.valueOf(row[2]));
  //   }
  // }

  @Override
  public void start(Stage stage) throws Exception {
    // Text text = new Text();
    // text.setText("Loading...");
    // text.setX(50);
    // text.setY(50);

    FXMLLoader loader = new FXMLLoader(AvatarDuel.class.getResource("card.fxml"));
    Parent root = loader.load();

    Scene scene = new Scene(root, 1280, 720);
    
    LandCard l = LandCardList.getLandCardById(1);
    Label cardTitle = (Label)loader.getNamespace().get("cardName");
    Label cardElement = (Label)loader.getNamespace().get("cardElement");
    ImageView cardImage= (ImageView)loader.getNamespace().get("cardImage");
    Label cardDescription = (Label)loader.getNamespace().get("cardDescription");
    
    cardTitle.setText(l.getName());
    cardElement.setText(l.getElement().toString());

    Image img = new Image(getClass().getResourceAsStream("./card/image/character/Aang.png"));

    cardImage.setImage(img);
    cardDescription.setWrapText(true);;
    cardDescription.setText(l.getDescription());

    stage.setTitle("Avatar Duel");
    stage.setScene(scene);
    stage.show();
    // try {
    //   text.setText("Avatar Duel!");
    // } catch (Exception e) {
    //   text.setText("Failed to load cards: " + e);
    // }
  }

  public static void main(String[] args) {
    try{
      for(int i = 1; i <=16;++i)
      {
        LandCardList.getLandCardById(i).printCard();
      }
    }
    catch(Exception e){
      System.out.println(e);
    }
    launch();
  }
}