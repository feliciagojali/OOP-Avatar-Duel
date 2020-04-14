package com.avatarduel.model.gui;

import java.io.IOException;

import javax.management.RuntimeErrorException;

import com.avatarduel.AvatarDuel;
import com.avatarduel.model.cards.Card;
import com.avatarduel.model.cards.CharacterCard;
import com.avatarduel.model.cards.Element;
import com.avatarduel.model.cards.LandCard;
import com.avatarduel.model.cards.SkillCard;
import com.avatarduel.model.player.ElementStats;
import com.avatarduel.model.player.Stats;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.input.MouseEvent;

public class StatsController extends AnchorPane {

    @FXML private Label waterStats;
    @FXML private Label earthStats;
    @FXML private Label fireStats;
    @FXML private Label airStats;
    @FXML private Label energyStats;

    private GameController gameController;

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
    
    public void displayStats()
    {
        
        this.waterStats.setText(this.gameController.getActivePlayer().getElementStats().getStats(Element.WATER).getCurrent() + "/" + this.gameController.getActivePlayer().getElementStats().getStats(Element.WATER).getMax());
        this.earthStats.setText(this.gameController.getActivePlayer().getElementStats().getStats(Element.EARTH).getCurrent() + "/" + this.gameController.getActivePlayer().getElementStats().getStats(Element.EARTH).getMax());
        this.fireStats.setText(this.gameController.getActivePlayer().getElementStats().getStats(Element.FIRE).getCurrent() + "/" + this.gameController.getActivePlayer().getElementStats().getStats(Element.FIRE).getMax());
        this.airStats.setText(this.gameController.getActivePlayer().getElementStats().getStats(Element.AIR).getCurrent() + "/" + this.gameController.getActivePlayer().getElementStats().getStats(Element.AIR).getMax());
        this.energyStats.setText(this.gameController.getActivePlayer().getElementStats().getStats(Element.ENERGY).getCurrent() + "/" + this.gameController.getActivePlayer().getElementStats().getStats(Element.ENERGY).getMax());
    }

}