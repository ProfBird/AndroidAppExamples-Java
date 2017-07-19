package edu.uoregon.bbird.multi_activitydemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import edu.uoregon.bbird.mulit_activitydemo.ActivityTwo;
import edu.uoregon.bbird.mulit_activitydemo.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startActivityTwo(View v) {
        startActivity(new Intent(getApplicationContext(), ActivityTwo.class));

    }
}
