package com.avatarduel.util;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * AlertBox is a class that is used to hold
 * static methods relating to the AlertBox
 * @author feliciagojali
 */
public class AlertBox{

    @FXML
    public static void showError(String message){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public static void endGame(String player){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Congratulations!");
        alert.setHeaderText(null);
        alert.setContentText(player + " has won the game!");

        alert.showAndWait();
        System.exit(0);
    }
}