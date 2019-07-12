package com.gmail.profbird.simplelistviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public static final String FIRST_NAME = "FirstName";
    public static final String LAST_NAME = "LastName";
    public static final String PHONE = "Phone";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create a List that will hold Map objects
        ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
        // Create Map objects, add data and put them in the List
        HashMap<String, String> map = new HashMap<String, String>();
        map.put(FIRST_NAME, "Clyde");
        map.put(LAST_NAME, "Drexler");
        map.put(PHONE, "456-789-1011");
        data.add(map);
        map = new HashMap<String, String>();
        map.put(FIRST_NAME, "Satya");
        map.put(LAST_NAME, "Nadella");
        map.put(PHONE, "360-890-2345");
        data.add(map);
        map = new HashMap<String, String>();
        map.put(FIRST_NAME, "John");
        map.put(LAST_NAME, "Grisham");
        map.put(PHONE, "345-678-9012");
        data.add(map);

        // Create the adapter object
        SimpleAdapter adapter = new SimpleAdapter(this,
                data,
                R.layout.contact_list_layout,
                new String[]{FIRST_NAME, LAST_NAME, PHONE},
                new int[] {
                        R.id.firstNameTextView,
                        R.id.lastNameTextView,
                        R.id.phoneTextView
                    } );

        ListView mainListView = findViewById(R.id.mainListView);
        mainListView.setAdapter(adapter);
        mainListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this, "Row " + i + " was clicked", Toast.LENGTH_SHORT).show();

    }
}
