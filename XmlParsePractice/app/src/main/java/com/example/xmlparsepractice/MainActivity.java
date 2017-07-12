package com.example.xmlparsepractice;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends Activity {

	private TextView textView;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FileIO io;
        WeatherItems weatherItems;
        io = new FileIO(getApplicationContext());
        weatherItems = io.readFile();
        
        ArrayList<HashMap<String, String>> data = new
        		ArrayList<HashMap<String, String>>();
        
        for (WeatherItem item : weatherItems.getItems())
        {
        	HashMap<String, String> map = new HashMap<String, String>();
        	map.put("date", item.getForecastDate());
        	map.put("lowTemp", item.getLowTemp());
        	map.put("highTemp", item.getHighTemp());
        	data.add(map);
        }
        
        SimpleAdapter adapter = new SimpleAdapter(this,
        	data,
        	R.layout.listview_items,
        	new String[]{"date", "lowTemp", "highTemp"},
        	new int[]{R.id.dateTextView,
        			  R.id.lowTempTextView,
        			  R.id.highTempTextView }
        );
        ListView itemsListView = (ListView)findViewById(R.id.weatherListView);
        itemsListView.setAdapter(adapter);
    }
        
        /*
        for(WeatherItem item : weatherItems.getItems())
        {
        	String allText = textView.getText().toString();
        	allText += "\r\n" + item.getForecastDate();
        	textView.setText(allText);
        }
        */
    
}
