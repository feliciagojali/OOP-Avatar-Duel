package com.avatarduel.model.player;

import com.avatarduel.model.cards.Element;

public class ElementStats {
    private Stats water;
    private Stats earth;
    private Stats fire;
    private Stats air;
    
    public ElementStats(){
        water = new Stats();
        earth = new Stats();
        fire = new Stats();
        air = new Stats();
    }

    public Stats getWstats(){
        return this.water;
    }

    public Stats getEstats(){
        return this.earth;
    }

    public Stats getFstats(){
        return this.fire;
    }

    public Stats getAstats(){
        return this.air;
    }

    // When using landcard
    public void addStats(Element element)
    {
        switch(element)
        {
            case WATER:
                water.setCurrent(water.getCurrent() + 1);
                water.setMax(water.getMax() + 1);
                break;
            case EARTH:
                earth.setCurrent(earth.getCurrent() + 1);
                earth.setMax(earth.getMax() + 1);
                break;
            case FIRE:
                fire.setCurrent(fire.getCurrent() + 1);
                fire.setMax(fire.getMax() + 1);
                break;
            case AIR:
                air.setCurrent(air.getCurrent() + 1);
                air.setMax(air.getMax() + 1);
                break;
            default:
                break;
        }
    }

    // Reset stat to max
    public void resetStats()
    {
        water.setCurrent(water.getMax());
        earth.setCurrent(earth.getMax());
        fire.setCurrent(fire.getMax());
        air.setCurrent(air.getMax());
    }
}