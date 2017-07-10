package edu.uoregon.bbird.rockpaperscissors_fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Brian Bird on 7/15/2015, updted 7/10/2017.
 */

public class SecondFragment extends Fragment {

    ImageView rpsImageView;
    TextView compMoveTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // inflate the layout for this fragment
        View view = inflater.inflate(R.layout.second_fragment, container, false);

        return view;
    }

    public void computerMove(RpsGame game) {
        Activity activity = getActivity();   // Get a reference to the host activity

        // Display the computer's move
        rpsImageView = (ImageView)activity.findViewById(R.id.rpsImage);
        compMoveTextView = (TextView)activity.findViewById(R.id.compMoveTextView);
        displayImage(game.computerMove());   // Computer makes a move and we display it

        // Display the winner
        TextView winnerTextView = (TextView)activity.findViewById(R.id.winnerTextView);
        winnerTextView.setText(game.whoWon().toString());
        getView().invalidate();
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
