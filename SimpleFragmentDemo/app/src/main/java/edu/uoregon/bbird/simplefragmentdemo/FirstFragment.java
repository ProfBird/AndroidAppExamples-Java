package edu.uoregon.bbird.simplefragmentdemo;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment implements View.OnClickListener {

    EditText messageEditText;

    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        messageEditText = (EditText)view.findViewById(R.id.messageEditText);
        Button startButton = (Button)view.findViewById(R.id.startButton);
        startButton.setOnClickListener(this);  // this class implements the listener
        return view;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getActivity(), SecondActivity.class);
        intent.putExtra("message", messageEditText.getText().toString());
        startActivity(intent);
    }
}
