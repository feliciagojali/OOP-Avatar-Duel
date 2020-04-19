package com.avatarduel.model.gui;

import java.io.IOException;

import com.avatarduel.AvatarDuel;
import com.avatarduel.model.cards.Element;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * StatsController is the class to control the stats info of
 * the active player
 * @author mkamadeus
 */
public class StatsController extends AnchorPane {

    @FXML private Label waterStats;
    @FXML private Label earthStats;
    @FXML private Label fireStats;
    @FXML private Label airStats;
    @FXML private Label energyStats;
    @FXML private Label playerHealth;
    @FXML private Label enemyHealth;

    private GameController gameController;

    /**
     * The constructor of the stats controller
     * @param controller the root game controller
     */
    public StatsController(GameController controller)
    {
        FXMLLoader statsLoader = new FXMLLoader(AvatarDuel.class.getResource("gui/stats.fxml"));
        statsLoader.setRoot(this);
        statsLoader.setController(this);

        try
        {
            statsLoader.load();
            this.gameController = controller;
            this.displayStats();
        }
        catch(IOException e)
        {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * Procedure to show and sync the display of the StatsController
     * with the current values.
     */
    public void displayStats()
    {
        this.playerHealth.setText(Integer.toString(this.gameController.getActivePlayer().getHp()));
        this.enemyHealth.setText(Integer.toString(this.gameController.getOtherPlayer().getHp()));
        this.waterStats.setText(this.gameController.getActivePlayer().getElementStats().getStats(Element.WATER).getCurrent() + "/" + this.gameController.getActivePlayer().getElementStats().getStats(Element.WATER).getMax());
        this.earthStats.setText(this.gameController.getActivePlayer().getElementStats().getStats(Element.EARTH).getCurrent() + "/" + this.gameController.getActivePlayer().getElementStats().getStats(Element.EARTH).getMax());
        this.fireStats.setText(this.gameController.getActivePlayer().getElementStats().getStats(Element.FIRE).getCurrent() + "/" + this.gameController.getActivePlayer().getElementStats().getStats(Element.FIRE).getMax());
        this.airStats.setText(this.gameController.getActivePlayer().getElementStats().getStats(Element.AIR).getCurrent() + "/" + this.gameController.getActivePlayer().getElementStats().getStats(Element.AIR).getMax());
        this.energyStats.setText(this.gameController.getActivePlayer().getElementStats().getStats(Element.ENERGY).getCurrent() + "/" + this.gameController.getActivePlayer().getElementStats().getStats(Element.ENERGY).getMax());
    }

}