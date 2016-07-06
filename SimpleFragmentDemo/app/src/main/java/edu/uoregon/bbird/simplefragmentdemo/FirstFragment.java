package edu.uoregon.bbird.simplefragmentdemo;


import android.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment implements View.OnClickListener, TextView.OnEditorActionListener {

    EditText messageEditText;
    TextView messageTextView;
    String message;

    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        messageTextView = (TextView)view.findViewById(R.id.messageTextView);
        messageEditText = (EditText)view.findViewById(R.id.messageEditText);
        // Retrieve state saved during rotation
        if (savedInstanceState != null) {
            message = savedInstanceState.getString("message");
            messageTextView.setText(message);
        }
        Button startButton = (Button)view.findViewById(R.id.startButton);
        startButton.setOnClickListener(this);  // this class implements the listener
        messageEditText.setOnEditorActionListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        message = messageEditText.getText().toString();
        Configuration config = getResources().getConfiguration();
        // If orientation is portrait the fragments are in separate activities
        // if it's landscape they're both in the main activity
        if (config.orientation == config.ORIENTATION_PORTRAIT) {
            Intent intent = new Intent(getActivity(), SecondActivity.class);
            intent.putExtra("message", message);
            startActivity(intent);
        }
        else
        {
            ((MainActivity)getActivity()).sendMessage(message);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // put code to save state here
        outState.putString("message", message);
    }

    @Override
    public boolean onEditorAction(TextView view, int id, KeyEvent keyEvent) {
        if(id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_ACTION_UNSPECIFIED) {
                message = messageEditText.getText().toString();
                messageTextView.setText(message);
            }
        return false;
    }
}
