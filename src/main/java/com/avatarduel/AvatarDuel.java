package com.avatarduel;

import javafx.fxml.FXMLLoader;
import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.control.ScrollPane;
import com.avatarduel.model.player.*;
import com.avatarduel.model.cards.*;


public class AvatarDuel extends Application {
  @Override
  public void start(Stage stage) throws Exception {
    FXMLLoader gameLoader = new FXMLLoader(AvatarDuel.class.getResource("gui/game.fxml"));
    ScrollPane gameRoot = gameLoader.load();
    
    // Media media = new Media(AvatarDuel.class.getResource("music/song.mp3").toString());
    // MediaPlayer mp = new MediaPlayer(media);
    // mp.setVolume(0.1);
    // mp.play();

    Scene scene = new Scene(gameRoot, 800, 600);

    stage.setTitle("Avatar Duel");
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    
    launch();
    // Player A = new Player("A");
    // int pos = 0;

    // while(pos<=A.getHand().getCards().size()){
    //   Card X = A.getHand().getCard(pos);
    //   if(SkillCardList.isIdSkillCard(X.getId())){
    //     SkillCard Y = SkillCardList.getSkillCardById(X.getId());
    //     A.getHand().discardCard(pos);
    //     A.getField().addSkillRow(Y, 1);
    //     break;
    //   }
    //   pos++;
    // }

    // pos = 0;

    // while(pos<=A.getHand().getCards().size()){
    //   Card X = A.getHand().getCard(pos);
    //   if(CharacterCardList.isIdCharacterCard(X.getId())){
    //     CharacterCard Y = CharacterCardList.getCharacterCardById(X.getId());
    //     A.getHand().discardCard(pos);
    //     A.getField().addCharacterRow(Y, 1);
    //     break;
    //   }
    //   pos++;
    // }

    // A.useSkill(A, 1, 1);

    // A.detach(A, 1, 0);

    // A.drawCard();
    

    
  }
}