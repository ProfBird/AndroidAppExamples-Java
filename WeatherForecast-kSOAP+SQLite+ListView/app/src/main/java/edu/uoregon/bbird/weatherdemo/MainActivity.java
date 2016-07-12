package edu.uoregon.bbird.weatherdemo;
// references: 
// www.drdobbs.com/database/using-sqlite-on-android/232900584?pgno=1
// http://www.reigndesign.com/blog/using-your-own-sqlite-database-in-android-applications/
// http://www.vogella.com/tutorials/AndroidSQLite/article.html
// http://stackoverflow.com/questions/5457699/cursor-adapter-and-sqlite-example
// http://developer.android.com/reference/android/widget/SimpleCursorAdapter.html


import android.app.Activity;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpResponseException;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.net.Proxy;

public class  MainActivity extends Activity
				implements OnItemClickListener, OnEditorActionListener {

	private Dal dal = new Dal(this);
	Cursor cursor = null;
	String locationSelection = "97405";
	SimpleCursorAdapter adapter = null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Set up location selection input widget
        EditText zipEditText = (EditText)findViewById(R.id.zipEditText);
        zipEditText.setOnEditorActionListener(this);
               
        // Set up the adapter for the ListView to display the forecast info
        adapter = new SimpleCursorAdapter(
                this, 
                R.layout.listview_items, 
                cursor, 
                new String[]{"Date", "Description", "LowTemp", "HighTemp"}, 
                new int[]{
          			  		R.id.dateTextView, 
          			  		R.id.descriptionTextView,
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
		int dayPrecip = cursor.getInt(cursor.getColumnIndex("DayPrecip"));
		int nightPrecip = cursor.getInt(cursor.getColumnIndex("NightPrecip"));		
		//WeatherItem item = weatherItems.get(position);
		Toast.makeText(this, 
				"Daytime chance of precipitation: " + Integer.toString(dayPrecip) + "%" + "\r\n" +
				"Nighttime chance of preciptiation: " + Integer.toString(nightPrecip) + "%",
				Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		int keyCode = -1; 
        if (event != null) {
           keyCode = event.getKeyCode();
        }
        if (actionId == EditorInfo.IME_ACTION_DONE ||
            actionId == EditorInfo.IME_ACTION_UNSPECIFIED ||
            keyCode == KeyEvent.KEYCODE_DPAD_CENTER || 
            keyCode == KeyEvent.KEYCODE_ENTER) {
			    // Get a weather forecast for the selected location
	        	locationSelection = v.getText().toString();
			    cursor = dal.getForcastFromDb(locationSelection);
				// If there isn't a forecast for that location in the database,
				// get it from the web service
			    if(cursor.getCount() == 0)
			    {
			        // Get weather forecast from the web service
			        WeatherTask weatherTask = new WeatherTask();
			        weatherTask.execute(locationSelection);		// get's the forecast and puts it in the db    	
			        // Can't get cursor data here, have to do it in onPostExecute
			        //  cursor = dal.getForcastByLocation(locationSelection);	// reads it back from the db
			    }
			    else
			    {
			    	adapter.changeCursor(cursor);	
			    }
		}
		return false;		// false means the action wasn't consumed, it is passed on to the EditText widget
	}  

	
	/*** Background task to get a forecast from the web service ***/
	private class WeatherTask extends AsyncTask<String, Void, String> {
	
		@Override
		protected String doInBackground(String... params) {
			
			// Create a SOAP request object and put it in an envelope
            final String REQUEST_URL = "http://ws.cdyne.com/WeatherWS/";
            final String SOAP_OP = "GetCityForecastByZIP";
			SoapObject request = new SoapObject(REQUEST_URL, SOAP_OP);
			request.addProperty("ZIP", params[0]);
			
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER12);
			envelope.dotNet = true;
			envelope.implicitTypes = true;
			envelope.setOutputSoapObject(request);
			
			// Send the request (call the SOAP method)
			HttpTransportSE ht = new HttpTransportSE(Proxy.NO_PROXY,
					REQUEST_URL + "Weather.asmx", 60000);
			ht.setXmlVersionTag("<!--?xml version=\"1.0\" encoding= \"UTF-8\" ?-->");
			ht.debug = true;

			try {
				ht.call(REQUEST_URL + SOAP_OP, envelope); // first parameter is soapAction
			} catch (HttpResponseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			// Get the response from the SOAP service
			/*
			// This works, but doesn't give us the raw XML
			SoapObject response = null;
			try {
				response = (SoapObject)envelope.getResponse();
			} catch (SoapFault e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			weatherXml = response.getPropertyAsString("GetCityForecastByZIPResult");
			*/
			
			// This gives us the raw XML
			String weatherXml = ht.responseDump;
			return weatherXml;
		}
	
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

            if (result.contains("resource cannot be found")) {
                Toast.makeText(MainActivity.this, "Web Service:No data available", Toast.LENGTH_SHORT).show();
            }
            else {
                dal.loadDbFromWebService(result, locationSelection);
            }

            cursor = dal.getForcastFromDb(locationSelection);    // reads it back from the db
            if (cursor.getCount() == 0) {
                Toast.makeText(MainActivity.this, "Database:No data available", Toast.LENGTH_SHORT).show();
            } else {
                adapter.changeCursor(cursor);
            }
		}
		
	}
}
