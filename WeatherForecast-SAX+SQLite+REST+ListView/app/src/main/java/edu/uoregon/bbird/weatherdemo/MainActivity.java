package edu.uoregon.bbird.weatherdemo;

// Written by Brian Bird 7/11/15, updated 7/18/17

import android.app.Activity;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import java.io.InputStream;
import java.util.Date;

import static edu.uoregon.bbird.weatherdemo.WeatherSQLiteHelper.*;

import static java.lang.System.in;

public class MainActivity extends Activity 
				implements OnItemClickListener, OnItemSelectedListener {

    /******* ------  Instance Variables -------------- *************/
    private Dal dal = new Dal(this);
	Cursor cursor = null;
	String locationSelection = "Eugene";
	SimpleCursorAdapter adapter = null;

	/********* -------- Activity Lifecycle Callback Methods --------- ***********/


	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Set up location selection spinner
        Spinner locationSpinner = (Spinner)findViewById(R.id.locationSpinner);
        locationSpinner.setOnItemSelectedListener(this);
        
        // Get Forecast for the default location
        // TODO: replace hard-coded state with selected state
        String state = "OR";
        cursor = dal.getForcastFromDb(locationSelection,state, new Date());
        if(cursor.getCount() == 0)
        {
            new RestTask().execute(state, locationSelection);
        }
        else {
            displayForecast();
        }

        ListView itemsListView = (ListView)findViewById(R.id.forecastListView);
        itemsListView.setAdapter(adapter);
        itemsListView.setOnItemClickListener(this);
    }

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		cursor.moveToPosition(position);
		int dayPrecip = cursor.getInt(cursor.getColumnIndex(POP));
		int nightPrecip = cursor.getInt(cursor.getColumnIndex(PERIOD));
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
			locationSelection = "Eugene";
			break;
		case 1:
			locationSelection = "Newport";
			break;
		case 2:
			locationSelection = "Anchorage";
		}
        // Get a weather forecast the selected location
        cursor = dal.getForcastFromDb(locationSelection, "OR", new Date() );
        adapter.changeCursor(cursor);
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub	
	}

	/************* ----------- Private Methods ------------- ***************/

	private void getForecast(String state, String city) {
		// If there isn't a forecast in the db for this location (and date?), then get one from the web service
		Cursor cursor = dal.getForcastFromDb(state, city, new Date());
		if(cursor.getCount() == 0) {
			new RestTask().execute(state, city);
		}
	}
	private void displayForecast() {
		// Set up the adapter for the ListView to display the forecast
		adapter = new SimpleCursorAdapter(
				this,
				R.layout.listview_items,
				cursor,
				new String[]{DATE,
						ICON,
						IMAGE_ID,
						FCT_TEXT,
						POP},
				new int[]{
						R.id.dateTextView,
						R.id.descriptionTextView,
						R.id.iconImageView,
						R.id.lowTempTextView,
						R.id.highTempTextView
				},
				0 );	// no flags

	}


	/************* ----------- Nested Class ------------- ***************/

	public class RestTask extends AsyncTask<String, Void, InputStream> {

        private String state;
		private String city;

		// TODO:Replace deprecated HTTPEntity with a more up-to-date class
		// HTTPEntity Code adapted from: http://www.techrepublic.com/blog/software-engineer/calling-restful-services-from-your-android-app/

		@Override
		protected InputStream doInBackground(String... params) {

			HttpClient httpClient = new DefaultHttpClient();
			HttpContext localContext = new BasicHttpContext();
			String apiKey = "0e3e69302fba4e56";  // put your own API key here
			// Get a free API key at: https://www.wunderground.com/?apiref=5cdccc9428586099
			String baseUri = "http://api.wunderground.com/api/";
			state = params[0];
			city = params[1];
			String query = "/forecast/q/" + state + "/" + city + ".xml";
			String uri = baseUri + apiKey + query;
			HttpGet httpGet = new HttpGet(uri);
			try {
				HttpResponse response = httpClient.execute(httpGet, localContext);
				HttpEntity entity = response.getEntity();

				// Call our helper method to extract a string from the response
				//  text = getASCIIContentFromEntity(entity);
				InputStream in = entity.getContent();
				dal.parseXmlStream(in);
			} catch (Exception e) {
				Log.e("weatherdemo", e.getLocalizedMessage());
			}
			return in;
		}



		@Override
		protected void onPostExecute(InputStream in) {
            WeatherItems items = dal.parseXmlStream(in);
            dal.putForecastIntoDb(items);
            dal.getForcastFromDb(state, city, new Date() );
            displayForecast();
		}
	}
}
