package com.avatarduel.model.player;

import com.avatarduel.model.cards.*;

public class Field {
    private CharacterCard[] characterRow;
    private SkillCard[] skillRow;
    private boolean[] characterHasAtk;
    private boolean[] characterStance; // true menyerang false bertahan
    private boolean[] canChangeStance;

    public Field(){
        this.characterRow = new CharacterCard [8];
        this.skillRow = new SkillCard [8];
        this.characterHasAtk = new boolean[8];
        this.characterStance = new boolean[8];
        this.canChangeStance = new boolean[8];
        for (int i = 0; i<= 7 ; i++){
            this.characterHasAtk[i] = false;
            this.characterStance[i] = false;
            this.canChangeStance[i] = true;
        }


    }

    public CharacterCard[] getCharacterCards(){
        return this.characterRow;
    }

    public SkillCard[] getSkillCards(){
        return this.skillRow;
    }

    public CharacterCard getCharacterCard(int pos){
        return this.characterRow[pos-1];
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
    
    public void changeStance(int pos){
        if (this.canChangeStance[pos-1]) {
            this.characterStance[pos-1] = !this.characterStance[pos-1];
        }
    }

    public void setHasAtk(int pos){
        this.characterHasAtk[pos-1] = true;
    }

    public void resetHasAtk(){
        for(int i=0; i<=7 ;i++){
            this.characterHasAtk[i] = false;
        }
    }
    public boolean getCharacterStance (int pos){
        return(this.characterStance[pos-1]);
    }
    public boolean canAttack(int pos){
        return(!this.characterHasAtk[pos-1] && this.characterStance[pos-1]);
    }

    public void discardCharaCard(int pos){
        this.characterRow[pos-1] = null;
    }

    public void discardSkillCard(int pos){
        this.skillRow[pos-1] = null;
    }
    public void unableChange(int pos){
        this.canChangeStance[pos-1] = false;
    }
}