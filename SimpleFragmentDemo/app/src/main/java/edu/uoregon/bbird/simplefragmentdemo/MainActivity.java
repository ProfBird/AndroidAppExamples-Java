package edu.uoregon.bbird.simplefragmentdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendMessage(String message) {
        SecondFragment secondFragment = (SecondFragment)getFragmentManager().findFragmentById(R.id.fragment_second);
        Toast.makeText(this, "MainActivity: " + message, Toast.LENGTH_SHORT).show();
        secondFragment.showMessage(message);

    }
}
