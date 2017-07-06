package edu.uoregon.bbird.rockpaperscissors;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Instance variable for game logic
    RpsGame game = new RpsGame();
    // Instance variables for UI Widgets
    ImageView rpsImage;
    EditText rpsText;
    TextView winnerText;
    TextView compMoveText;
    // Instance variables for preferences
    SharedPreferences prefs;
    boolean showImages;         // if true, images are displayed for the computer's hand choices

    // Event handler for the playButton's onClick event (handler is set in activity_main.xml)
    public void play(View v) {

        // Close the soft keyboard
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

        Hand humanHand;
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
        if (showImages)
            displayImage(compHand);
        winnerText.setText( game.whoWon(compHand, humanHand).toString());
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

    /* ------- Activity Callback Methods ---------- */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // Get references to UI widgets
        rpsImage = (ImageView)findViewById(R.id.rpsImage);
        rpsText = (EditText)findViewById(R.id.rpsEditText);
        winnerText = (TextView)findViewById(R.id.winnerLabel);
        compMoveText = (TextView)findViewById(R.id.compMoveTextView);

        // Set preferences to the default values defined in preferences.xml
        // readAgain is false, so the values will only be set once
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        showImages = prefs.getBoolean(
                getResources().getString(R.string.pref_show_images),
                true);

        if (!showImages)
            rpsImage.setVisibility(View.GONE);
        else
            rpsImage.setVisibility(View.VISIBLE);
    }


    /* -------- Activity Callback Methods for the Menu ------- */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

      @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_settings) {
            startActivity(new Intent(
                    getApplicationContext(), SettingsActivity.class));
            return true;
        }
        else if (id == R.id.menu_about) {
            Toast.makeText(this, "This game was written by Brian Bird", Toast.LENGTH_LONG).show();
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }

}
