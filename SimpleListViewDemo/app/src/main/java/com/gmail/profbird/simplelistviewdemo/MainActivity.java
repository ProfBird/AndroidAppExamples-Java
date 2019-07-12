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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create a List that will hold Map objects
        ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
        // Create Map objects, add data and put them in the List
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("FirstName", "Clyde");
        map.put("LastName", "Drexler");
        map.put("Phone", "456-789-1011");
        data.add(map);
        map = new HashMap<String, String>();
        map.put("FirstName", "Satya");
        map.put("LastName", "Nadella");
        map.put("Phone", "360-890-2345");
        data.add(map);
        map = new HashMap<String, String>();
        map.put("FirstName", "John");
        map.put("LastName", "Grisham");
        map.put("Phone", "345-678-9012");
        data.add(map);

        // Create the adapter object
        SimpleAdapter adapter = new SimpleAdapter(this,
                data,
                R.layout.contact_list_layout,
                new String[]{"FirstName", "LastName", "Phone"},
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
