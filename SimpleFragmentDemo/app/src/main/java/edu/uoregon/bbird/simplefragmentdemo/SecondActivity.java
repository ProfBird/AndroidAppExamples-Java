package edu.uoregon.bbird.simplefragmentdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the message that the first fragment's button onClick handler sent
        setContentView(R.layout.activity_second);
        String message = getIntent().getStringExtra("message");
        // The toast is just for testing
        Toast.makeText(this, "SecondActivity: " + message, Toast.LENGTH_SHORT).show();

        // Send the message to the fragment
        SecondFragment fragment =
                (SecondFragment)getFragmentManager().findFragmentById(R.id.fragment_second);
        fragment.showMessage(message);
    }

}
