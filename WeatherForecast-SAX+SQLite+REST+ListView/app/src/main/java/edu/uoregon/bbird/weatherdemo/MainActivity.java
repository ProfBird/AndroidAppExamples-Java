package edu.uoregon.bbird.weatherdemo;

// Written by Brian Bird 7/11/15, updated 7/13/17

// references:
// www.drdobbs.com/database/using-sqlite-on-android/232900584?pgno=1
// http://www.reigndesign.com/blog/using-your-own-sqlite-database-in-android-applications/
// http://www.vogella.com/tutorials/AndroidSQLite/article.html
// http://stackoverflow.com/questions/5457699/cursor-adapter-and-sqlite-example
// http://developer.android.com/reference/android/widget/SimpleCursorAdapter.html

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import static edu.uoregon.bbird.weatherdemo.WeatherSQLiteHelper.DAY_PRECIP;
import static edu.uoregon.bbird.weatherdemo.WeatherSQLiteHelper.NIGHT_PRECIP;

public class MainActivity extends Activity 
				implements OnItemClickListener, OnItemSelectedListener {

    private Dal dal = new Dal(this);
	Cursor cursor = null;
	String locationSelection = "97405";
	SimpleCursorAdapter adapter = null; 
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Set up location selection spinner
        Spinner locationSpinner = (Spinner)findViewById(R.id.locationSpinner);
        locationSpinner.setOnItemSelectedListener(this);
        
        // Initialize the database
        dal.loadTestData("97405");
        
        // Get Forecast for the default location
        cursor = dal.getForcastByLocation(locationSelection);
        
        // Set up the adapter for the ListView to display the forecast
        adapter = new SimpleCursorAdapter(
            this,
            R.layout.listview_items,
            cursor,
            new String[]{WeatherSQLiteHelper.DATE,
                    WeatherSQLiteHelper.DESCRIPTION,
                    WeatherSQLiteHelper.IMAGE_ID,
                    WeatherSQLiteHelper.MORNING_LOW,
                    WeatherSQLiteHelper.DAYTIME_HIGH},
            new int[]{
                R.id.dateTextView,
                R.id.descriptionTextView,
                R.id.iconImageView,
                R.id.lowTempTextView,
                R.id.highTempTextView
                },
            0 );	// no flags

        ListView itemsListView = (ListView)findViewById(R.id.forecastListView);
        itemsListView.setAdapter(adapter);
        itemsListView.setOnItemClickListener(this);
    }

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		cursor.moveToPosition(position);
		int dayPrecip = cursor.getInt(cursor.getColumnIndex(DAY_PRECIP));
		int nightPrecip = cursor.getInt(cursor.getColumnIndex(NIGHT_PRECIP));
		//WeatherItem item = weatherItems.get(position);
		Toast.makeText(this, 
				"Daytime chance of precipitation: " + Integer.toString(dayPrecip) + "%" + "\r\n" +
				"Nighttime chance of preciptiation: " + Integer.toString(nightPrecip) + "%",
				Toast.LENGTH_LONG).show();
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		switch (position){
		case 0:
			locationSelection = "97405";
			break;
		case 1:
			locationSelection = "97439";
			break;
		case 2:
			locationSelection = "99515";	
		}
        // Get a weather forecast the selected location
        cursor = dal.getForcastByLocation(locationSelection);
        adapter.changeCursor(cursor);
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub	
	}    
}

/* 
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
*/