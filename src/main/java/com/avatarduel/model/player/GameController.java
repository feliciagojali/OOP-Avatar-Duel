package com.avatarduel.model.player;
import java.util.LinkedList;

public class GameController {
    private LinkedList<Player> listOfPlayers;
    private int playerActive;
    private Phase phase;

    public GameController(){
        Player A = new Player("A");
        Player B = new Player("B");
        this.listOfPlayers = new LinkedList<Player>();
        this.listOfPlayers.add(A);
        this.listOfPlayers.add(B);
        this.playerActive = 0;
        this.phase = Phase.draw;
    }

    public void nextPhase(){
        switch (this.phase) {
            case draw:
                this.phase = Phase.main1;
                break;
            case main1:
                this.phase = Phase.battle;
                break;
            case battle:
                this.phase = Phase.main2;
                break;
            case main2:
                this.phase = Phase.end;
                break;
            case end:
                this.phase = Phase.draw;
                if (this.playerActive == 0){
                    this.playerActive = 1;
                } else {
                    this.playerActive = 0;
                }
            default:
                break;
        }

    }
}