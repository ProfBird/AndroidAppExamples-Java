package edu.uoregon.bbird.weatherdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends Activity 
				implements OnItemClickListener {

	private WeatherItems weatherItems;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        FileIO io = new FileIO(getApplicationContext());
        weatherItems = io.readFile();
           
        ArrayList<HashMap<String, String>> data = new
        		ArrayList<HashMap<String, String>>();
        
        for (WeatherItem item : weatherItems)
        {
        	HashMap<String, String> map = new HashMap<String, String>();
        	map.put("date", item.getForecastDateFormatted());
        	// map.put("lowTemp", getString(R.string.low) + item.getLowTemp());
        	// map.put("highTemp", getString(R.string.high) + item.getHighTemp());
	    	map.put("imageName",
        			Integer.toString(getResources().getIdentifier(
        					item.getDescription().toLowerCase().replaceAll("\\s+",""), "drawable", getPackageName() 
        					)));
       	data.add(map);
        }
        
        SimpleAdapter adapter = new SimpleAdapter(this,
        	data,
        	R.layout.listview_items,
        	new String[]{"imageName", "date"},
        	new int[]{R.id.iconImageView,
        			  R.id.dateTextView, 
        			  }
        );
        ListView itemsListView = (ListView)findViewById(R.id.weatherListView);
        itemsListView.setAdapter(adapter);
        itemsListView.setOnItemClickListener(this);
    }

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		WeatherItem item = weatherItems.get(position);
		Toast.makeText(this, 
				"Low: " + item.getLowTemp() + "\r\n" +
				"High:" + item.getHighTemp(),
				Toast.LENGTH_LONG).show();
	}    
}
