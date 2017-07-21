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
    static final String DATE = "date";
    static final String IMAGE_NAME = "imageName";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        FileIO io = new FileIO(getApplicationContext());
        weatherItems = io.readFile();

        // ** Start setting things up for the data adapter that will be used by the ListView **
        // This ArrayList of HashMaps will be the data source for the adapter
        ArrayList<HashMap<String, String>> data = new
        		ArrayList<HashMap<String, String>>();

		// Put fields from each WeatherItem into a HashMap, put the HashMaps into the ArrayList
        // Keys match the column names in the SimpleAdapter
        for (WeatherItem item : weatherItems)
        {
        	HashMap<String, String> map = new HashMap<String, String>();
        	map.put(DATE, item.getForecastDateFormatted());
        	// map.put("lowTemp", getString(R.string.low) + item.getLowTemp());
        	// map.put("highTemp", getString(R.string.high) + item.getHighTemp());
	    	map.put(IMAGE_NAME,
        			Integer.toString(getResources().getIdentifier(
        					"s" + item.getSymbol(), "drawable", getPackageName()
        					)));
       	data.add(map);
        }

        // Instantiate a data adapter using the ArrayList of HashMaps, data, as a data source
        // The String array contains the column names
        // The int array contains the ids of the widgets in each row of the ListView
        // The order of the elements associates column names with widget ids
        SimpleAdapter adapter = new SimpleAdapter(this,
        	data,
        	R.layout.listview_items,
        	new String[]{IMAGE_NAME, DATE},
        	new int[]{R.id.iconImageView,
        			  R.id.dateTextView, 
        			  }
        );

        // Pass the data adapter to the List View
        ListView itemsListView = (ListView)findViewById(R.id.weatherListView);
        itemsListView.setAdapter(adapter);
        itemsListView.setOnItemClickListener(this);
    }

    // ** Event Handler **

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		WeatherItem item = weatherItems.get(position);
		Toast.makeText(this, 
				"Low: " + item.getLowTemp() + "\r\n" +
				"High:" + item.getHighTemp(),
				Toast.LENGTH_LONG).show();
	}    
}
