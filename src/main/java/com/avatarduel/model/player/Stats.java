package com.avatarduel.model.player;

public class Stats {
    private int current;
    private int max;

    public Stats(){
        this.current = 0;
        this.max = 0;
    }
    public int getCurrent(){
        return this.current;
    }

    public int getMax(){
        return this.max;
    }

    public void setCurrent(int x){
        this.current = x;
    }

    public void setMax(int x){
        this.max = x;
    }
}