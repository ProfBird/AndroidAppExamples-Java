package edu.uoregon.bbird.mulit_activitydemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
