package com.avatarduel.model.player;

public class StatsApi {
    private int current;
    private int max;
    private int regen;

    public StatsApi(){
        this.current = 0;
        this.max = 0;
        this.regen = 0;
    }
    public int getCurrent(){
        return this.current;
    }

    public int getMax(){
        return this.max;
    }

    public int getRegen(){
        return this.regen;
    }

    public void setCurrent(int x){
        this.current = x;
    }

    public void setMax(int x){
        this.max = x;
    }

    public void setRegen(int x){
        this.regen = x;
    }
}