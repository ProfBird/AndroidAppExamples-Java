package edu.uoregon.bbird.rps;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Brian Bird on 7/15/2015.
 */

public class SecondFragment extends Fragment implements View.OnClickListener{

    ImageView rpsImageView;
    TextView compMoveTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // inflate the layout for this fragment
        View view = inflater.inflate(R.layout.second_fragment, container, false);

        // Set this fragment to listen for the "New Game" button's click event
        Button b = (Button) view.findViewById(R.id.newGameButton);
        b.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.newGameButton:
                startActivity(new Intent(getActivity(), FirstActivity.class));
                break;
        }
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
