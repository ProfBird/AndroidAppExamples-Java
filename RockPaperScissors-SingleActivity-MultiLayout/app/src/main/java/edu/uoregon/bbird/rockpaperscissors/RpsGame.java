package edu.uoregon.bbird.rockpaperscissors;

import java.util.Random;

/**
 * Created by Brian Bird on 7/1/2015, revised 7/8/17.
 */

public class RpsGame {

    private Random rand = new Random();
    private int compWins = 0;
    private int humanWins = 0;
    private int addWin = 0;
    private Hand compChoice = Hand.none;
    private Winner winner = Winner.none;

    public RpsGame(int hWins, int cWins, Hand cChoice) {
        humanWins = hWins;
        compWins = cWins;
        compChoice = cChoice;
    }

    public Winner play(Hand humanHand)
    {
        addWin = 1;
        return getWinner(humanHand);
    }

    public Hand computerMove()
    {
        compChoice = Hand.values()[rand.nextInt(3) + 1];
        return compChoice;
    }

    public Winner getWinner(Hand humanHand) {
        Winner win;
        if (compChoice == humanHand) {
            win = Winner.tie;
        }
        else if ((compChoice == Hand.rock && humanHand == Hand.scissors) ||
                (compChoice == Hand.paper && humanHand == Hand.rock) ||
                (compChoice == Hand.scissors && humanHand == Hand.paper)) {
            win = Winner.computer;
            compWins += addWin;
        }
        else {
            win = Winner.human;
            humanWins += addWin;
        }
        addWin = 0;
        return win;
    }

    public int getCompWins() {return compWins;}
    public int getHumanWins() {return humanWins;}
    public Hand getCompChoice() {return compChoice;}
}
