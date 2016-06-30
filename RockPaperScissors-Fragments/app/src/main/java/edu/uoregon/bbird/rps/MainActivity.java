package edu.uoregon.bbird.rps;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    RpsGame game = new RpsGame();
    ImageView rpsImageView;
    EditText rpsEditText;
    TextView winnerTextView;
    TextView compMoveTextView;
    SharedPreferences savedValues;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        rpsImageView = (ImageView)findViewById(R.id.rpsImage);
        rpsEditText = (EditText)findViewById(R.id.rpsEditText);
        winnerTextView = (TextView)findViewById(R.id.winnerTextView);
        compMoveTextView = (TextView)findViewById(R.id.compMoveTextView);
        savedValues = PreferenceManager.getDefaultSharedPreferences(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Restore saved state
        Hand humanHand = Hand.values()[savedValues.getInt("humanHand", 0)];
        rpsEditText.setText(humanHand.toString());
       Hand computerHand = Hand.values()[savedValues.getInt("computerHand", 0)];
        displayImage((computerHand));
        game = new RpsGame(computerHand, humanHand);
        winnerTextView.setText(game.whoWon().toString());

        // Read and use settings
        if(savedValues.getBoolean("enable_reset_checkbox", true))
            findViewById(R.id.resetButton).setEnabled(true);
        else
            findViewById(R.id.resetButton).setEnabled(false);

    }

    @Override
    protected void onPause() {
        super.onPause();

        //save state
        SharedPreferences.Editor editor = savedValues.edit();
        editor.putInt("humanHand", game.getHumanHand().ordinal());
        editor.putInt("computerHand", game.getComputerHand().ordinal());
        editor.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch(id) {
            case R.id.action_settings:
                //Toast.makeText(this, "settings", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                return true;
            case R.id.action_about:
                Toast.makeText(this, "About", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    // Handler for the paly click event
    public void play(View v) {
        Hand compHand = game.computerMove();
        displayImage(compHand);
        String humanHand = rpsEditText.getText().toString().toLowerCase();
        if (humanHand.equals(""))
            humanHand = "none";
        game.setHumanHand(Hand.valueOf(humanHand));
        winnerTextView.setText(game.whoWon().toString());
    }

    // Handler for the reset click event
    public void reset(View v) {
        rpsEditText.setText("");
        displayImage(Hand.none);
        winnerTextView.setText("");
        game.setComputerHand(Hand.none);
        game.setHumanHand(Hand.none);
    }

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
        compMoveTextView.setText(hand.toString());

    }

}
