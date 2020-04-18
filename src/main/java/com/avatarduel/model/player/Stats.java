package com.avatarduel.model.player;

/**
 * Stats is the class that defines a stats attribute for the player.
 * @author feliciagojali
 */
public class Stats {
    private int current;
    private int max;

    /**
     * The default stats constructor
     */
    public Stats(){
        this.current = 99;
        this.max = 99;
    }

    /**
     * Getter for current stats
     * @return integer of current stats value
     */
    public int getCurrent(){
        return this.current;
    }

    /**
     * Getter for the max value of stats
     * @return integer of max stats value
     */
    public int getMax(){
        return this.max;
    }

    /**
     * Setter for current stats value
     * @param value
     */
    public void setCurrent(int value){
        this.current = value;
    }
    /**
     * Setter for max value
     * @param value
     */
    public void setMax(int value){
        this.max = value;
    }
}