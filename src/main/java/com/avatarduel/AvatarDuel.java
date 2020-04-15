package com.avatarduel;

import javafx.fxml.FXMLLoader;
import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.control.ScrollPane;

public class AvatarDuel extends Application {
  @Override
  public void start(Stage stage) throws Exception {
    FXMLLoader gameLoader = new FXMLLoader(AvatarDuel.class.getResource("gui/game.fxml"));
    ScrollPane gameRoot = gameLoader.load();
    
    // Media media = new Media(AvatarDuel.class.getResource("music/song.mp3").toString());
    // MediaPlayer mp = new MediaPlayer(media);
    // mp.setVolume(0.1);
    // mp.play();

    Scene scene = new Scene(gameRoot, 2000, 1000);

    stage.setTitle("Avatar Duel");
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    
    launch();
  }
  
}