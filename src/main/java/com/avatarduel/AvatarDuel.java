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

import com.avatarduel.model.cards.CharacterCard;
import com.avatarduel.model.cards.CharacterCardList;
import com.avatarduel.model.cards.Deck;
import com.avatarduel.model.cards.LandCard;
import com.avatarduel.model.cards.LandCardList;
import com.avatarduel.model.cards.SkillCard;
import com.avatarduel.model.cards.SkillCardList;
import com.avatarduel.model.gui.CardGUI;
import com.avatarduel.model.gui.CardGUIBuilder;
import com.avatarduel.model.gui.FieldGUI;
import com.avatarduel.model.gui.GameGUI;

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
    FXMLLoader fieldLoader2 = new FXMLLoader(AvatarDuel.class.getResource("gui/field.fxml"));
    AnchorPane field2 = fieldLoader2.load();
    FieldGUI conField = (FieldGUI)fieldLoader.getController();

    conField.setCharacter(l, 0);

    con.setCardInfo(root);
    con.setBottomField(field);
    con.setTopField(field2);
    // .setName(l.getName())
    // .setAttack(Integer.toString(l.getAttack()))
    // .setDefense(Integer.toString(l.getDefense()))
    // .setPower(Integer.toString(l.getPower()))
    // .setDescription(l.getDescription())
    // .setImage("/card/image/character/" + l.getImagePath())
    
    Scene scene = new Scene(gameRoot, 1400, 900);
    BorderPane bp = (BorderPane)game.getNamespace().get("main");
    bp.setLeft(root);

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
    }
    catch(Exception e)
    {
      System.out.println("abcdef");
    }

    launch();
  }
}