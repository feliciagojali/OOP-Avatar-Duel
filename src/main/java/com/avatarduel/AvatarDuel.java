package com.avatarduel;

import javafx.fxml.FXMLLoader;
import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import com.avatarduel.model.player.*;
import com.avatarduel.model.cards.*;

public class AvatarDuel extends Application {
  @Override
  public void start(Stage stage) throws Exception {
    FXMLLoader gameLoader = new FXMLLoader(AvatarDuel.class.getResource("gui/game.fxml"));
    BorderPane gameRoot = gameLoader.load();
    
    // Media media = new Media(AvatarDuel.class.getResource("music/song.mp3").toString());
    // MediaPlayer mp = new MediaPlayer(media);
    // mp.setVolume(0.1);
    // mp.play();

    Scene scene = new Scene(gameRoot, 1200, 1000);
    
    stage.setTitle("Avatar Duel");
    // stage.setMaximized(true);
    stage.setFullScreen(true);
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    
    // launch();
    Player A = new Player("A");
    Player B = new Player("B");
    int pos = 0;

    while(pos<=A.getHand().getCards().size()){
      Card X = A.getHand().getCard(pos);
      if(SkillCardList.isIdSkillCard(X.getId())){
        SkillCard Y = SkillCardList.getSkillCardById(X.getId());
        if (Y.getEffect() == Effect.AURA){
          A.getHand().discardCard(pos);
          A.getField().addSkillRow(Y, 1);
          break;

        }
      }
      pos++;
    }

    pos = 0;

    while(pos<=A.getHand().getCards().size()){
      Card X = A.getHand().getCard(pos);
      if(CharacterCardList.isIdCharacterCard(X.getId())){
        CharacterCard Y = CharacterCardList.getCharacterCardById(X.getId());
        A.getHand().discardCard(pos);
        A.getField().addCharacterRow(Y, 1);
        break;
      }
      pos++;
    }
    pos = 0;
    while(pos<=B.getHand().getCards().size()){
      Card X = B.getHand().getCard(pos);
      if(CharacterCardList.isIdCharacterCard(X.getId())){
        CharacterCard Y = CharacterCardList.getCharacterCardById(X.getId());
        B.getHand().discardCard(pos);
        B.getField().addCharacterRow(Y, 1);
        break;
      }
      pos++;
    }
    A.useSkill(B, 1);
    // A.attack(B, 1, 1);

    A.detach(B, 1);

    A.drawCard();
    

    
  }
}