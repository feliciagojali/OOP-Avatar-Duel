package com.avatarduel.model.gui;

import com.avatarduel.model.player.Player;

/**
 * The TopFieldController extends the base class FieldController
 * to define other player's field
 * @author mkamadeus
 */
public class TopFieldController extends FieldController{

    /**
     * TopFieldConstructor
     * @param controller the game root controller
     * @param player the owner of the field
     */
    public TopFieldController(GameController controller, Player player)
    {
        super(controller, player, "gui/topField.fxml");
        super.displayField();
        for(int i = 1; i <= 6; i++) if(!this.gameController.getOtherPlayer().getField().getStance(i-1))
        {
            this.getCharacterMinicard(i).setStanceColor();
        }
    }

}