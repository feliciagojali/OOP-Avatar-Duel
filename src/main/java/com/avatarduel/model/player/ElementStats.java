package com.avatarduel.model.player;

import com.avatarduel.model.cards.Element;

public class ElementStats {
    private Stats water;
    private Stats earth;
    private Stats fire;
    private Stats air;
    private Stats energy;
    
    public ElementStats(){
        water = new Stats();
        earth = new Stats();
        fire = new Stats();
        air = new Stats();
        energy = new Stats();
    }

    public Stats getStats(Element element)
    {
        switch(element)
        {
            case WATER:
                return this.water;
            case EARTH:
                return this.earth;
            case FIRE:
                return this.fire;
            case AIR:
                return this.air;
            default:
                return this.energy;
        }
    }

    // Add up stats
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
                energy.setCurrent(energy.getCurrent() + 1);
                energy.setMax(energy.getMax() + 1);
                break;
        }
    }
    
    // Use stat
    public void reduceStats(Element element, int deduction)
    {
        switch(element)
        {
            case WATER:
                water.setCurrent(water.getCurrent() - deduction);
                break;
            case EARTH:
                earth.setCurrent(earth.getCurrent() - deduction);
                break;
            case FIRE:
                fire.setCurrent(fire.getCurrent() - deduction);
                break;
            case AIR:
                air.setCurrent(air.getCurrent() - deduction);
                break;
            default:
                energy.setCurrent(energy.getCurrent() - deduction);
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
        energy.setCurrent(energy.getMax());
    }
}