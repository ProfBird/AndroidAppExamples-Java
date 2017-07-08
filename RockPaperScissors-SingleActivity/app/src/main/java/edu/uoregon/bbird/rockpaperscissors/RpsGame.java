package edu.uoregon.bbird.rockpaperscissors;

import java.util.Random;

/**
 * Created by Brian Bird on 7/1/2015.
 */

public class RpsGame {

    private Random rand = new Random();

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
        }
        else {
            win = Winner.human;
        }
        return win;
    }

    public Hand computerMove()
    {
        return Hand.values()[rand.nextInt(3)];
    }

}
