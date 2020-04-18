package com.avatarduel.model.player;

import java.util.ArrayList;

import com.avatarduel.model.cards.*;

public class Field {
    private CharacterCard[] characterRow;
    private SkillCard[] skillRow;
    private boolean[] characterHasAtk;
    private boolean[] characterStance; // true menyerang false bertahan
    private boolean[] canChangeStance;
    private ArrayList<ArrayList<SkillCard>> attachedSkill;
    private int[] attackAURA;
    private int[] defenseAURA;
    private boolean[] powerup;

    public Field(){
        this.characterRow = new CharacterCard [6];
        this.skillRow = new SkillCard [6];
        this.characterHasAtk = new boolean[6];
        this.characterStance = new boolean[6];
        this.canChangeStance = new boolean[6];
        this.powerup = new boolean[6];
        this.attackAURA = new int[6];
        this.defenseAURA = new int[6];
        ArrayList<ArrayList<SkillCard>> list = new ArrayList<ArrayList<SkillCard>>();
        for (int i = 0; i<= 5 ; i++){
            this.characterHasAtk[i] = false;
            this.characterStance[i] = true;
            this.canChangeStance[i] = true;
            list.add(new ArrayList<SkillCard>());
            this.attackAURA[i] = 0;
            this.defenseAURA[i] = 0;
            this.powerup[i] = false;
        }
        this.attachedSkill = list;

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

    public ArrayList<SkillCard> getAttachedList(int pos){
        return this.attachedSkill.get(pos);
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
    
    public int getAtk(int pos){
        return this.attackAURA[pos];
    }

    public int getDef(int pos){
        return this.defenseAURA[pos];
    }

    public boolean getPowerUp(int pos){
        return this.powerup[pos];
    }
    public void setAtk(int atk, int pos){
        this.attackAURA[pos] = atk;
    }

    public void setDef(int def, int pos){
        this.defenseAURA[pos] = def;
    }
    
    public void setPowerUp(int pos,boolean bool){
        this.powerup[pos] = bool;
    }

    



}