package edu.uoregon.bbird.rps;

import java.util.Random;

/**
 * Created by Brian Bird on 7/1/2015. Revised 7/15/15
 */

public class RpsGame {

    // Instance variables
    private Random rand = new Random();
    private Hand computerHand = Hand.none;
    private Hand humanHand = Hand.none;

    // Getters and Setters
    public Hand getHumanHand() {
        return humanHand;
    }

    public void setHumanHand(Hand humanHand) {
        this.humanHand = humanHand;
    }

    public Hand getComputerHand() {
        return computerHand;
    }

    public void setComputerHand(Hand computerHand) {
        this.computerHand = computerHand;
    }

    // Constructors
    public RpsGame() {
        computerHand = Hand.none;
        humanHand = Hand.none;
    }

    public RpsGame(Hand computerHand, Hand humanHand) {
        this.computerHand = computerHand;
        this.humanHand = humanHand;
    }

    // Computer and human moves need to be made before calling this method
    public Winner whoWon()
    {
        Winner win;
        if (computerHand == Hand.none || humanHand == Hand.none)
            win = Winner.none;
        else if (computerHand == humanHand)
            win = Winner.tie;
        else if ((computerHand == Hand.rock && humanHand == Hand.scissors) ||
                (computerHand == Hand.paper && humanHand == Hand.rock) ||
                (computerHand == Hand.scissors && humanHand == Hand.paper))
            win = Winner.computer;
        else
            win = Winner.human;

        return win;
    }

    // This generates a random move for the computer
    public Hand computerMove()
    {
        computerHand = Hand.values()[rand.nextInt(3) + 1];
        return computerHand;
    }
}
