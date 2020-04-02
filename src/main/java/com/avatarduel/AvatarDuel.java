package com.avatarduel;

import javafx.fxml.FXMLLoader;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import com.avatarduel.model.cards.LandCard;
import com.avatarduel.model.cards.CharacterCard;
import com.avatarduel.model.cards.LandCardList;
import com.avatarduel.model.cards.CharacterCardList;

public class AvatarDuel extends Application {
  @Override
  public void start(Stage stage) throws Exception {
    FXMLLoader loader = new FXMLLoader(AvatarDuel.class.getResource("card.fxml"));
    Parent root = loader.load();

    Scene scene = new Scene(root, 1280, 720);
    
    CharacterCard l = CharacterCardList.getCharacterCardById(29);
    Label cardTitle = (Label)loader.getNamespace().get("cardName");
    Label cardElement = (Label)loader.getNamespace().get("cardElement");
    ImageView cardImage= (ImageView)loader.getNamespace().get("cardImage");
    Label cardDescription = (Label)loader.getNamespace().get("cardDescription");
    
    cardTitle.setText(l.getName());
    cardElement.setText(l.getElement().toString());

    String path = "card/image/character/" + l.getImagePath();
    Image img = new Image(getClass().getResourceAsStream(path));

    cardImage.setImage(img);
    cardDescription.setWrapText(true);
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
    // try{
    //   for(int i = 1; i <=16;++i)
    //   {
    //     CharacterCardList.getCharacterCardById(i).printCard();
    //   }
    // }
    // catch(Exception e){
    //   System.out.println(e);
    // }
    launch();
  }
}