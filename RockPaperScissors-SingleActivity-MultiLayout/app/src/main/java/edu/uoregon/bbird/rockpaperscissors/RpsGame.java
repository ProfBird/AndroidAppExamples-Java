package edu.uoregon.bbird.rockpaperscissors;

import java.util.Random;

/**
 * Created by Brian Bird on 7/1/2015.
 */

public class RpsGame {

    private Random rand = new Random();
    private int compWins = 0;
    private int humanWins = 0;

    public RpsGame(int cWins, int hWins) {
        compWins = cWins;
        humanWins = hWins;
    }

    public Winner whoWon(Hand computerHand, Hand humanHand)
    {
        Winner win;
        if (computerHand == humanHand) {
            win = Winner.tie;
        }
        else if ((computerHand == Hand.rock && humanHand == Hand.scissors) ||
                (computerHand == Hand.paper && humanHand == Hand.rock) ||
                (computerHand == Hand.scissors && humanHand == Hand.paper)) {
            win = Winner.computer;
            compWins++;
        }
        else {
            win = Winner.human;
            humanWins++;
        }
        return win;
    }

    public Hand computerMove()
    {
        return Hand.values()[rand.nextInt(3)];
    }
    public int getCompWins() {return compWins;}
    public int getHumanWins() {return humanWins;}
}
