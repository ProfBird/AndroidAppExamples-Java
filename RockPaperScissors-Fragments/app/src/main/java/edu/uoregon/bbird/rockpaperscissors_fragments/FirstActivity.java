package edu.uoregon.bbird.rockpaperscissors_fragments;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Brian Bird on 7/15/2015, updted 7/10/2017.
 */

public class FirstActivity extends AppCompatActivity {

    private RpsGame game;   // Created and managed in FirstFragment

    public RpsGame getGame() {
        return game;
    }

    public void setGame(RpsGame game) {
        this.game = game;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.first_activity);
    }

    // Only used when two fragments are loaded in this activity
    // This is called when the paly button is clicked in the FristFragment
    public void computerMove(RpsGame g) {
        game = g;
        SecondFragment secondFragment = (SecondFragment)getFragmentManager()
                .findFragmentById(R.id.second_fragment);
        secondFragment.computerMove(game);

    }
}


