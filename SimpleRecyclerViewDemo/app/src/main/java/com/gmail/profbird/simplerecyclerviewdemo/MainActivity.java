package com.gmail.profbird.simplerecyclerviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    public static final String FIRST_NAME = "FirstName";
    public static final String LAST_NAME = "LastName";
    public static final String PHONE = "Phone";
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

        // specify an adapter (see also next example)
        mAdapter = new ContactRecyclerViewAdapter(data);
        recyclerView.setAdapter(mAdapter);
    }
}
