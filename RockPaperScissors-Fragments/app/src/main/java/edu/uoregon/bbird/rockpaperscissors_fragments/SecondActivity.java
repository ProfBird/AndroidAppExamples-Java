package edu.uoregon.bbird.rockpaperscissors_fragments;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Brian Bird on 7/15/2015, updted 7/10/2017.
 */
public class SecondActivity extends AppCompatActivity {

    private RpsGame game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.second_activity);

        int handNum = getIntent().getExtras().getInt("humanHand");
        Hand humanHand = Hand.values()[handNum];
        // Crete a new game object.
        // We don't need to initialize it with the computerHand, just the humanHand
        game = new RpsGame(null, humanHand);

        // Pass the fragment a game ref while calling the method that invokes game play
        SecondFragment secondFragment = (SecondFragment)getFragmentManager()
                .findFragmentById(R.id.second_fragment);
        secondFragment.computerMove(game);

    }

}
