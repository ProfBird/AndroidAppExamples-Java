package edu.uoregon.bbird.rockpaperscissors;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RpsGame game = new RpsGame();
    ImageView rpsImageView;
    EditText rpsEditText;
    TextView winnerTextView;
    TextView compMoveTextView;
    private static final String RPS_GAME = "MainActivity";

    // Event handler for the playButton's onClick event (handler is set in the layout XML)
    public void play(View v) {

        // Close the soft keyboard
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

        Hand humanHand;
        // The user might enter an invalid choice, so catch it and propmt for the right choices
        try {
            humanHand = Hand.valueOf(rpsEditText.getText().toString().toLowerCase());
        }
        catch(Exception e)
        {
            Toast.makeText(this, "Please enter: rock, paper, or scissors", Toast.LENGTH_LONG).show();
            return;
        }

        // Android makes a random hand choice and the winner is determined
        Hand compHand = game.computerMove();
        compMoveTextView.setText(compHand.toString());
        displayImage(compHand);
        winnerTextView.setText( game.whoWon(compHand, humanHand).toString());
    }

    // Display the correct hand image based on a Hand enum
    private void displayImage(Hand hand) {
        int id = 0;

        switch(hand)
        {
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
    }

    /* ------- Callback Methods ---------- */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        rpsImageView = (ImageView)findViewById(R.id.rpsImage);
        rpsEditText = (EditText)findViewById(R.id.rpsEditText);
        winnerTextView = (TextView)findViewById(R.id.winnerTextView);
        compMoveTextView = (TextView)findViewById(R.id.compMoveTextView);
        Log.d(RPS_GAME,"In OnCreate");
    }

}
