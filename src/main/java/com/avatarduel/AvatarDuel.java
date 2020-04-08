package com.avatarduel;

import javafx.fxml.FXMLLoader;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import com.avatarduel.model.cards.*;
import com.avatarduel.model.gui.*;
import com.avatarduel.model.player.*;

public class AvatarDuel extends Application {
  @Override
  public void start(Stage stage) throws Exception {
    FXMLLoader game = new FXMLLoader(AvatarDuel.class.getResource("gui/game.fxml"));
    Parent gameRoot = game.load();
    GameGUI con = (GameGUI)game.getController();
    
    CharacterCard l = CharacterCardList.getCharacterCardById(29);
    
    // Parent root = cardLoader.load();
    // CardGUI con = (CardGUI)cardLoader.getController();
    // con.setAttack("-");
    // con.setDefense("-");
    
    AnchorPane root = new CardGUIBuilder()
    .setName(l.getName())
    .setElement(l.getElement())
    .setImage("card/image/character/" + l.getImagePath())
    .setDescription(l.getDescription())
    .setAttack(Integer.toString(l.getAttack()))
    .setDefense(Integer.toString(l.getDefense()))
    .setPower(Integer.toString(l.getPower()))
    .build();

    FXMLLoader fieldLoader = new FXMLLoader(AvatarDuel.class.getResource("gui/field.fxml"));
    AnchorPane field = fieldLoader.load();
    FieldGUI conField = (FieldGUI)fieldLoader.getController();

    FXMLLoader fieldLoader2 = new FXMLLoader(AvatarDuel.class.getResource("gui/field.fxml"));
    AnchorPane field2 = fieldLoader2.load();

    FXMLLoader deckLoader = new FXMLLoader(AvatarDuel.class.getResource("gui/deck.fxml"));
    AnchorPane deck = deckLoader.load();

    con.setCardInfo(root);
    con.setBottomField(field);
    con.setTopField(field2);
    con.setDeckContainer(deck);
    
    Scene scene = new Scene(gameRoot, 1400, 900);
    BorderPane bp = (BorderPane)game.getNamespace().get("main");
    bp.setLeft(root);

    stage.setTitle("Avatar Duel");
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    // try{

      Deck d = new Deck();
      d.initializeDeck();
      d.shuffle();
      while(!d.isDeckEmpty())
      {
        int id = d.drawCard();
        
        if(LandCardList.isIdLandCard(id)) System.out.println(LandCardList.getLandCardById(id));
        else if(CharacterCardList.isIdCharacterCard(id)) System.out.println(CharacterCardList.getCharacterCardById(id));
        else if(SkillCardList.isIdSkillCard(id)) System.out.println(SkillCardList.getSkillCardById(id));

      }
    // }
    // catch(Exception e)
    // {
    //   System.out.println("abcdef");
    // }
    System.out.println("hee");
    launch();
  }
}