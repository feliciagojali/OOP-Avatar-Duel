package com.avatarduel.model.player;

import java.util.ArrayList;

import com.avatarduel.model.cards.*;

/**
 * Field is the class that defines the field in game.
 * @author feliciagojali
 * @author mkamadeus
 */
public class Field {
    private CharacterCard[] characterRow;
    private boolean[] hasAttack;
    private boolean[] stance; // true for attack, false for defense
    private SkillCard[] skillRow;

    private ArrayList<ArrayList<SkillCard>> attachedSkill;
    private int[] attackAURA;
    private int[] defenseAURA;
    private boolean[] powerup;

    /**
     * Field constructor is the default constructor for the field class
     */
    public Field(){
        this.characterRow = new CharacterCard [6];
        this.skillRow = new SkillCard [6];
        this.hasAttack = new boolean[6];
        this.stance = new boolean[6];
        this.attackAURA = new int[6];
        this.defenseAURA = new int[6];
        this.powerup = new boolean[6];
        this.attachedSkill = new ArrayList<ArrayList<SkillCard>>();

        for (int i = 0; i<= 5 ; i++){
            this.attackAURA[i] = 0;
            this.defenseAURA[i] = 0;
            this.powerup[i] = false;
            this.hasAttack[i] = false;
            this.stance[i] = true;
            this.attachedSkill.add(new ArrayList<SkillCard>());
        }

    }

    /**
     * Character card getter in field 
     * @param pos defines the position of the character card (0 indexed)
     * @return returns CharacterCard class that lies in the array, if none returns null
     */
    public CharacterCard getCharacterCard(int pos){
        return this.characterRow[pos];
    }

    /**
     * Skill card getter in field
     * @param pos defines the position of the skill card (0 indexed)
     * @return returns SkillCard class that lies in the array, if none returns null
     */
    public SkillCard getSkillCard(int pos){
        return this.skillRow[pos];
    }
    
    /**
     * Getter for the list of attached skill card to a certain character card in position.
     * @param pos defines the position of the character card that its skill cards attached wanted to be known
     * @return returns ArrayList of SkillCard that is attached to the character card
     */
    public ArrayList<SkillCard> getAttachedList(int pos){
        return this.attachedSkill.get(pos);
    }

    /**
     * Getter for the availability of a character card slot
     * @param pos the position checked
     * @return boolean value, true if available and false if occupied
     */
    public boolean isSkillPositionAvailable(int pos){
        return this.skillRow[pos] == null;
    }
    
    /**
     * Getter for the availability of a skill card slot
     * @param pos the position checked
     * @return boolean value, true if available and false if occupied
     */
    public boolean isCharacterPositionAvailable(int pos){
        return this.characterRow[pos] == null;
    }
    
    /**
     * Procedure for adding skill card to field
     * @param card the skill card being added
     * @param pos the position where the skill card want to be added
     */
    public void addSkillCard(SkillCard card ,int pos){
        this.skillRow[pos] = card;
    }
    
    /**
     * Procedure for adding character card to field
     * @param card the character card being added
     * @param pos the position where the character card want to be added
     */
    public void addCharacterCard(CharacterCard x, int pos){
        this.characterRow[pos] = x;
    }
    
    /**
     * Procedure for toggling a card position
     * @param pos the position of card that wanted to be toggled
     */
    public void toggleStance(int pos){
        this.stance[pos] = !this.stance[pos];
    }
    
    /**
     * Procedure used when a character has attacked
     * @param pos the position of card
     */
    public void setHasAttack(int pos){
        this.hasAttack[pos] = true;
    }
    
    /**
     * Getter for current character stance
     * @param pos the position of card
     * @return boolean value of the stance, true if attack, false if defense
     */
    public boolean getStance (int pos){
        return this.stance[pos];
    }

    /**
     * Test whether a character can attack
     * @param pos the position of the card
     * @return boolean value, true if the card can attack, false otherwise
     */
    public boolean canAttack(int pos){
        return !this.hasAttack[pos] && this.stance[pos];
    }
    
    /**
     * Discard a character card from the field
     * (removes the skill cards linked to the character as well)
     * @param pos the position of the card
     */
    public void discardCharacterCard(int pos){
        this.characterRow[pos] = null;
        this.attackAURA[pos] = 0;
        this.defenseAURA[pos] = 0;
        this.hasAttack[pos] = false;
        this.stance[pos] = true;
        this.powerup[pos] = false;
        this.getAttachedList(pos).clear();
    }

    /**
     * Discard a skill card from the field
     * (for detaching a skill card)
     * @param pos the position of the card
     */
    public void discardSkillCard(int pos){
        this.skillRow[pos] = null;
    }

    /**
     * Getter for attack value from skill's effect.
     * @param pos the position of card
     * @return integer value of the attack.
     */
    public int getAtk(int pos){
        return this.attackAURA[pos];
    }

    /**
     * Getter for defense value from skill's effect.
     * @param pos the position of card
     * @return integer value of the defense.
     */
    public int getDef(int pos){
        return this.defenseAURA[pos];
    }

    /**
     * Getter for boolean powerup from skill's effect.
     * @param pos the position of card
     * @return boolean, true if it's under the effect, false if not.
     */
    public boolean getPowerUp(int pos){
        return this.powerup[pos];
    }

    /**
     * Setter for attack value from skill's effect.
     * @param pos the position of card
     * @param atk integer value of the skill's attack
     */
    public void setAtk(int atk, int pos){
        this.attackAURA[pos] = atk;
    }

    /**
     * Setter for defense value from skill's effect.
     * @param pos the position of card
     * @param def integer value of the skill's defense
     */
    public void setDef(int def, int pos){
        this.defenseAURA[pos] = def;
    }
    
    /**
     * Setter for powerup boolean from skill's effect.
     * @param pos the position of card
     * @param bool boolean value.
     */
    public void setPowerUp(int pos,boolean bool){
        this.powerup[pos] = bool;
    }

    /**
     * Checking if there is no character card in the field.
     * @return boolan value.
     */
    public boolean isFieldEmpty(){
        int j = 0;
        for (int i = 0; i<=5 ; i++){
            if (characterRow[i] == null){
                j++;
            }
        }
        return (j==6);
    }

    /**
     * Reset all boolean in hasAttack to false.
     */
    public void resetHasAttacked(){
        for(int i =0 ; i<=5;i++){
            this.hasAttack[i] = false;
        } 
    }


}