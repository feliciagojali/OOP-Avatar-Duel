package com.avatarduel.model.gui;

import java.io.IOException;

import com.avatarduel.model.player.Player;

public class TopFieldController extends FieldController{

    public TopFieldController(GameController controller, Player player)
    {
        super(controller, player, "gui/topField.fxml");
        super.displayField();
    }

}