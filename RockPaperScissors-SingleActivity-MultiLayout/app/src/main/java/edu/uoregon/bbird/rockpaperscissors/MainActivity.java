package edu.uoregon.bbird.rockpaperscissors;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RpsGame game;
    Hand humanHand;
    ImageView rpsImage;
    EditText rpsText;
    TextView winnerText;
    TextView compMoveText;
    TextView hScoreTextView;
    TextView cScoreTextView;
    private static final String HUMAN_WINS = "human_wins";
    private static final String COMP_WINS = "comp_wins";
    private static final String COMP_CHOICE = "comp_choice";
    private static final String HUMAN_CHOICE = "human_choice";

    // Event handler for the playButton's onClick event (handler is set in the layout XML)
    public void play(View v) {

        // Close the soft keyboard
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

        // The user might enter an invalid choice, so catch it and propmt for the right choices
        try {
            humanHand = Hand.valueOf(rpsText.getText().toString().toLowerCase());
        }
        catch(Exception e)
        {
            Toast.makeText(this, "Please enter: rock, paper, or scissors", Toast.LENGTH_LONG).show();
            return;
        }

        // Android makes a random hand choice and the winner is determined
        Hand compHand = game.computerMove();
        compMoveText.setText(compHand.toString());
        game.play(humanHand);
        displayImage(compHand);
        displayScores();
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
        rpsImage.setImageResource(id);
    }

    private void displayScores() {
        hScoreTextView.setText(Integer.toString(game.getHumanWins()));
        cScoreTextView.setText(Integer.toString(game.getCompWins()));
        winnerText.setText( game.getWinner(humanHand).toString());
    }

    /* ------- Callback Methods ---------- */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        rpsImage = (ImageView)findViewById(R.id.rpsImage);
        rpsText = (EditText)findViewById(R.id.rpsEditText);
        winnerText = (TextView)findViewById(R.id.winnerLabel);
        compMoveText = (TextView)findViewById(R.id.compMoveTextView);
        hScoreTextView = (TextView)findViewById(R.id.hScoreTextView);
        cScoreTextView = (TextView)findViewById(R.id.cScoreTextView);

        int humanWins = 0, compWins = 0;
        Hand compChoice = Hand.none;
        if(savedInstanceState != null) {
            humanWins = savedInstanceState.getInt(HUMAN_WINS);
            compWins = savedInstanceState.getInt(COMP_WINS);
            compChoice = Hand.values()[savedInstanceState.getInt(COMP_CHOICE)];
            humanHand = Hand.values()[savedInstanceState.getInt(HUMAN_CHOICE)];
        }
        game = new RpsGame(humanWins, compWins, compChoice);
        displayScores();
        displayImage(compChoice);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(HUMAN_WINS, game.getHumanWins());
        outState.putInt(COMP_WINS, game.getCompWins());
        outState.putInt(COMP_CHOICE, game.getCompChoice().ordinal());
        outState.putInt(HUMAN_CHOICE, humanHand.ordinal());
        super.onSaveInstanceState(outState);
    }
}
