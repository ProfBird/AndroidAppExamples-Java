package edu.uoregon.bbird.rps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by Brian Bird on 7/15/2015.
 */
public class SecondActivity extends AppCompatActivity {

    private RpsGame game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.second_activity);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        super.onNewIntent(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Get the game state sent from the FirstActivity
        Intent intent = getIntent();
        int handNum = intent.getExtras().getInt("humanHand");
        Hand humanHand = Hand.values()[handNum];
        if (game == null)   // We might already have a game object
            game = new RpsGame();
        game.setHumanHand(humanHand);

        // Pass the fragment a game ref while calling the method invokes game play
        SecondFragment secondFragment = (SecondFragment)getFragmentManager().findFragmentById(R.id.second_fragment);
        secondFragment.computerMove(game);
    }
}
