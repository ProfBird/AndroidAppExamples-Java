package com.gmail.profbird.simplerecyclerviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    public final int NUMBER_OF_COLUMNS = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.contactRecyclerView);

        // use a linear layout manager to create a list
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Create a data-set as a list of map objects
        // Note: since we are writing the adapter code, the data-set can be any kind of data structure
        ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
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

        // specify an adapter (see also next example)
        mAdapter = new ContactRecyclerViewAdapter(data);
        recyclerView.setAdapter(mAdapter);
    }
}
