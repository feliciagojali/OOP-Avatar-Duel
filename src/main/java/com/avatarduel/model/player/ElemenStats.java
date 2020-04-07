package com.avatarduel.model.player;

public class ElemenStats {
    private StatsAir air;
    private StatsApi api;
    private StatsTanah tanah;
    private StatsAngin angin;
    
    public ElemenStats(){
        air = new StatsAir();
        api = new StatsApi();
        tanah = new StatsTanah();
        angin = new StatsAngin();
    }
}