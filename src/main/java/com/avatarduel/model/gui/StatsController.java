package com.avatarduel.model.gui;

import java.io.IOException;

import javax.management.RuntimeErrorException;

import com.avatarduel.AvatarDuel;
import com.avatarduel.model.cards.Card;
import com.avatarduel.model.cards.CharacterCard;
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

    public StatsController(ElementStats elementStats)
    {
        FXMLLoader statsLoader = new FXMLLoader(AvatarDuel.class.getResource("gui/stats.fxml"));
        statsLoader.setRoot(this);
        statsLoader.setController(this);

        try
        {
            statsLoader.load();
            this.waterStats.setText(elementStats.getWaterStats().getCurrent() + "/" + elementStats.getWaterStats().getMax());
            this.earthStats.setText(elementStats.getEarthStats().getCurrent() + "/" + elementStats.getEarthStats().getMax());
            this.fireStats.setText(elementStats.getFireStats().getCurrent() + "/" + elementStats.getFireStats().getMax());
            this.airStats.setText(elementStats.getAirStats().getCurrent() + "/" + elementStats.getAirStats().getMax());
            this.energyStats.setText(elementStats.getEnergyStats().getCurrent() + "/" + elementStats.getEnergyStats().getMax());
        }
        catch(IOException e)
        {
            throw new RuntimeException(e);
        }
    }

}