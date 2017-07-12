package edu.uoregon.bbird.rockpaperscissors_fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Brian Bird on 7/15/2015, updted 7/10/2017.
 */

public class FirstFragment extends Fragment implements OnClickListener {

    private RpsGame game;
    private boolean twoPaneLayout;
    private EditText rpsEditText;
    private FirstActivity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

         View view = inflater.inflate(R.layout.first_fragment, container, false);

        // Set this fragment to listen for the Play button's click event
        Button b = (Button) view.findViewById(R.id.playButton);
        b.setOnClickListener(this);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Get a references from the host activity
        activity = (FirstActivity)getActivity();
        rpsEditText = (EditText) activity.findViewById(R.id.rpsEditText);

        // Make a new game object
        game = new RpsGame();
        // Give the host activity a reference to the game object
        activity.setGame(game);

        // Check to see if FirstActivity has loaded a single or dual pane layout
        twoPaneLayout = activity.findViewById(R.id.second_fragment) != null;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.playButton) {
            String humanHand = rpsEditText.getText().toString();
            game.setHumanHand(humanHand);

            if (twoPaneLayout) {
                activity.computerMove(game);
            } else {
                Intent intent = new Intent(getActivity(), SecondActivity.class);
                int humanHandNum = game.getHumanHand().ordinal();
                intent.putExtra("humanHand", humanHandNum);  // send state to 2nd activity
                startActivity(intent);
            }
        }
    }
}
