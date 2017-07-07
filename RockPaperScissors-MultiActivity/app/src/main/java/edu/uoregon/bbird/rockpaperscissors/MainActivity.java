package edu.uoregon.bbird.rockpaperscissors;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * Created by Brian Bird on 7/15/2015, revised 7/6/17
 */

public class MainActivity extends AppCompatActivity
    implements RadioGroup.OnCheckedChangeListener {

    RpsGame game = new RpsGame();
    ImageView humanImageView;
    RadioButton rockRadioButton;
    RadioButton paperRadioButton;
    RadioButton scissorsRadioButton;
    Hand humanHand;
    public static final String HUMAN_HAND = "humanHand";

    /* --------- Activity Callback Methods --------- */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        humanImageView = (ImageView)findViewById(R.id.humanImageView);
        rockRadioButton = (RadioButton)findViewById(R.id.rockRadioButton);
        paperRadioButton = (RadioButton)findViewById(R.id.paperRadioButton);
        scissorsRadioButton = (RadioButton)findViewById(R.id.scissorsRadioButton);
        RadioGroup rpsRadioGroup = (RadioGroup)findViewById(R.id.rpsRadioGroup);
        rpsRadioGroup.setOnCheckedChangeListener(this);
    }

    /* ---------------- Event Handlers ---------------------- */

    // Handler for the playButton's onClick event defined in activity_main.xml
    public void play(View v) {
        // Start second activity and send it the player's hand selection
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(HUMAN_HAND, humanHand.toString());  // send data to 2nd activity
        startActivity(intent);
    }

    // Radio Button Listener Callback Method

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        int id = 0;
        switch (checkedId)
        {
            case R.id.rockRadioButton:
                id = R.drawable.rock;
                humanHand = Hand.rock;
                break;
            case R.id.paperRadioButton:
                id = R.drawable.paper;
                humanHand = Hand.paper;
                break;
            case R.id.scissorsRadioButton:
                id = R.drawable.scissors;
                humanHand = Hand.scissors;
                break;
        }
        humanImageView.setImageResource(id);
        humanImageView.setVisibility(View.VISIBLE);
    }
}
