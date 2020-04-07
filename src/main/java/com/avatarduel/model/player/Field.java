package com.avatarduel.model.player;

import com.avatarduel.model.cards.*;

public class Field {
    private CharacterCard[] characterRow;
    private SkillCard[] skillRow;

    public Field(){
        this.characterRow = new CharacterCard [8];
        this.skillRow = new SkillCard [8];
    }

    public CharacterCard[] getCharacterCards(){
        return this.characterRow;
    }

    public SkillCard[] getSkillCards(){
        return this.skillRow;
    }

    public boolean isSkillRowFull(){
        return(this.skillRow.length == 8);
    }

    public boolean isCharacterRowFull(){
        return(this.characterRow.length == 8);
    }

    public boolean isPosSkillAvail(int pos){
        return(this.skillRow[pos-1] != null);
    }

    public boolean isPosCharacterAvail(int pos){
        return(this.characterRow[pos-1] != null);
    }

    public void addSkillRow(SkillCard x ,int pos){
        this.skillRow[pos-1] = x;
    }

    public void addCharacterRow(CharacterCard x, int pos){
        this.characterRow[pos-1] = x;
    }
}