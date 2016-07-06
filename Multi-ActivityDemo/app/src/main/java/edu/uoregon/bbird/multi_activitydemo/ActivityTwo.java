package edu.uoregon.bbird.mulit_activitydemo;

import android.app.Activity;
import android.os.Bundle;

public class ActivityTwo extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        // Just setting the parent in the manifest is enough
        // enable the up button. You don't need the method below
       // getActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
