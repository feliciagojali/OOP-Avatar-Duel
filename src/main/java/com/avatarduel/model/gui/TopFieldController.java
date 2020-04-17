package com.avatarduel.model.gui;

import java.io.IOException;

import com.avatarduel.model.player.Player;

public class TopFieldController extends FieldController{

    public TopFieldController(GameController controller, Player player)
    {
        super(controller, player, "gui/topField.fxml");
        super.displayField();
        for(int i = 1; i <= 6; i++) if(!this.gameController.getOtherPlayer().getField().getCharacterStance(i-1))
        {
            this.getCharacterMinicard(i).setStanceColor();
        }
    }

}