package edu.uoregon.bbird.simplefragmentdemo;


import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */

public class SecondFragment extends Fragment {

    TextView messageTextView;

    public SecondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        messageTextView = (TextView) view.findViewById(R.id.messageTextView);
        return view;
    }

    // Called when the device is rotated
    // This works because the host activity's mainfist has the attribute:
    // android:configChanges="orientation|screenSize"
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Toast.makeText(getActivity(), "In onConfigurationChanged", Toast.LENGTH_SHORT).show();
        // Put your code to handle rotation here
        // Although there's nothing that needs to be done for this app
    }

    // Call this from the host activity to display the message
    public void showMessage(String message) {
        messageTextView.setText(message);
    }

}
