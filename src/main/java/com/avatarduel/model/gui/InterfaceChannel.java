package com.avatarduel.model.gui;

import com.avatarduel.model.cards.*;

public class InterfaceChannel {

    public void sendCardInfo(CharacterCard c)
    {
        GameController.gameControllerInstance.setCardInfo(c);
    }
    
    public void sendCardInfo(SkillCard c)
    {
        GameController.gameControllerInstance.setCardInfo(c);
    }

    public void sendCardInfo(LandCard c)
    {
        GameController.gameControllerInstance.setCardInfo(c);
    }
}