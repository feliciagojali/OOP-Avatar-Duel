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
    FXMLLoader gameLoader = new FXMLLoader(AvatarDuel.class.getResource("gui/game.fxml"));
    BorderPane gameRoot = gameLoader.load();
    
    Scene scene = new Scene(gameRoot, 600, 400);

    stage.setTitle("Avatar Duel");
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    
    launch();
  }
  
}