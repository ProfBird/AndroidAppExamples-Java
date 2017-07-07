package edu.uoregon.bbird.rockpaperscissors;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Brian Bird on 7/15/2015, revised 7/6/17
 */
public class SecondActivity extends AppCompatActivity {

    private RpsGame game;
    ImageView compImageView;
    TextView compMoveTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        // Add an "up" button to the app bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Get the game state sent from the FirstActivity
        Intent intent = getIntent();
        String handString = intent.getExtras().getString(MainActivity.HUMAN_HAND);
        if (game == null)   // We might already have a game object
            game = new RpsGame();
        game.setHumanHand(handString);

        // Display the computer's move
        compImageView = (ImageView)findViewById(R.id.compImageView);
        compMoveTextView = (TextView)findViewById(R.id.compMoveTextView);
        displayImage(game.computerMove());   // Computer makes a move and we display it

        // Display the winner
        TextView winnerTextView = (TextView)findViewById(R.id.winnerTextView);
        winnerTextView.setText(game.whoWon().toString());
    }

    private void displayImage(Hand hand) {
        int id = 0;

        switch (hand) {
            case rock:
                id = R.drawable.rock;
                break;
            case paper:
                id = R.drawable.paper;
                break;
            case scissors:
                id = R.drawable.scissors;
                break;
        }
        compImageView.setImageResource(id);
        compMoveTextView.setText(hand.toString());
    }
}
