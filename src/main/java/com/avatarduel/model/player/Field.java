package com.avatarduel.model.player;

import com.avatarduel.model.cards.*;

public class Field {
    private CharacterCard[] characterRow;
    private SkillCard[] skillRow;
    private boolean[] characterHasAtk;
    private boolean[] characterStance; // true menyerang false bertahan


    public Field(){
        this.characterRow = new CharacterCard [8];
        this.skillRow = new SkillCard [8];
        this.characterHasAtk = new boolean[8];
        this.characterStance = new boolean[8];
        for (int i = 0; i<= 7 ; i++){
            this.characterHasAtk[i] = false;
            this.characterStance[i] = false;
        }

    }

    public CharacterCard[] getCharacterCards(){
        return this.characterRow;
    }

    public SkillCard[] getSkillCards(){
        return this.skillRow;
    }

    public boolean isPosSkillAvail(int pos){
        return(this.skillRow[pos-1] == null);
    }

    public boolean isPosCharacterAvail(int pos){
        return(this.characterRow[pos-1] == null);
    }

    public void addSkillRow(SkillCard x ,int pos){
        this.skillRow[pos-1] = x;
    }

    public void addCharacterRow(CharacterCard x, int pos){
        this.characterRow[pos-1] = x;
    }
    
    public void setStance(int pos){
       this.characterStance[pos-1] = !this.characterStance[pos-1];
    }

    public void setHasAtk(int pos){
        this.characterHasAtk[pos-1] = true;
    }

    public void resetHasAtk(){
        for(int i=0; i<=7 ;i++){
            this.characterHasAtk[i] = false;
        }
    }
}