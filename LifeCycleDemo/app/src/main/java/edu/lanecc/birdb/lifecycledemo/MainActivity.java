package edu.lanecc.birdb.lifecycledemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
    implements View.OnClickListener {

    private TextView stateTextView;
    private TextView countTextView;
    private Button countButton;
    private String stateString = "";
    private int count = 0;
    private SharedPreferences sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stateTextView = findViewById(R.id.stateTextView);
        countTextView = findViewById(R.id.countTextView);
        countTextView.setOnClickListener(this);
        countButton = findViewById(R.id.countButton);
        stateString += "\nonCreate";
        stateTextView.setText(stateString);

        sharedPrefs = getSharedPreferences("LifeCycleState", MODE_PRIVATE);
    }

    @Override
    protected void onStart()
    {
        stateString += "\nonStart";
        stateTextView.setText(stateString);
        super.onStart();
    }

    @Override
    protected void onResume()
    {
        stateString += "\nonResume";
        stateTextView.setText(stateString);

        count = sharedPrefs.getInt("count", 0);
        countButton.setText(Integer.toString(count));
        super.onResume();
    }

    @Override
    protected void onPause()
    {
        stateString += "\nonPause";
        stateTextView.setText(stateString);

        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putInt("count", count);
        editor.apply();
        super.onPause();
    }

    @Override
    protected void onStop()
    {
        stateString += "\nonStop";
        stateTextView.setText(stateString);
        super.onStop();
    }

    public void onTap(View v)
    {
        count++;
        countButton.setText(Integer.toString(count));
    }

    @Override
    public void onClick(View view) {
        countTextView.setText("Touched");
    }
}
