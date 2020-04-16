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
            this.characterStance[i] = true;
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
        return this.characterRow[pos];
    }

    public SkillCard getSkillCard(int pos){
        return this.skillRow[pos];
    }

    public boolean isPosSkillAvail(int pos){
        return(this.skillRow[pos] == null);
    }

    public boolean isPosCharacterAvail(int pos){
        return(this.characterRow[pos] == null);
    }
    
 
    public void addSkillRow(SkillCard x ,int pos){
        this.skillRow[pos] = x;
    }

    public void addCharacterRow(CharacterCard x, int pos){
        this.characterRow[pos] = x;
    }
    
    public void changeStance(int pos){
        if (this.canChangeStance[pos]) {
            this.characterStance[pos] = !this.characterStance[pos];
        }
    }

    public void setHasAtk(int pos){
        this.characterHasAtk[pos] = true;
    }

    public void resetHasAtk(){
        for(int i=0; i<=7 ;i++){
            this.characterHasAtk[i] = false;
        }
    }
    public boolean getCharacterStance (int pos){
        return(this.characterStance[pos]);
    }
    public boolean canAttack(int pos){
        return(!this.characterHasAtk[pos] && this.characterStance[pos]);
    }

    public void discardCharaCard(int pos){
        this.characterRow[pos] = null;
    }

    public void discardSkillCard(int pos){
        this.skillRow[pos] = null;
    }
    public void unableChange(int pos){
        this.canChangeStance[pos] = false;
    }
}