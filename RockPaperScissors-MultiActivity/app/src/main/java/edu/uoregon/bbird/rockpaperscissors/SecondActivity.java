package edu.uoregon.bbird.rockpaperscissors;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Brian Bird on 7/15/2015, revised 6/30/2016
 */
public class SecondActivity extends AppCompatActivity {

    private RpsGame game;
    ImageView rpsImageView;
    TextView compMoveTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.second_activity);
   //     getActionBar().setDisplayHomeAsUpEnabled(true);

    }
/*
    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        super.onNewIntent(intent);
    }
*/
    @Override
    protected void onResume() {
        super.onResume();

        // Get the game state sent from the FirstActivity
        Intent intent = getIntent();
        String handString = intent.getExtras().getString("humanHand");
        Hand humanHand = Hand.valueOf(handString);
        if (game == null)   // We might already have a game object
            game = new RpsGame();
        game.setHumanHand(humanHand);

        // Display the computer's move
        rpsImageView = (ImageView)findViewById(R.id.rpsImage);
        compMoveTextView = (TextView)findViewById(R.id.compMoveTextView);
        displayImage(game.computerMove());   // Computer makes a move and we display it

        // Display the winner
        TextView winnerTextView = (TextView)findViewById(R.id.winnerTextView);
        winnerTextView.setText(game.whoWon().toString());
    }

    /*
    public  void newGame(View v ) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
*/

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
        rpsImageView.setImageResource(id);
        compMoveTextView.setText(hand.toString());
    }
}
