package edu.uoregon.bbird.weatherdemo;

// Written by Brian Bird 7/11/15, updated 7/13/17

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import static edu.uoregon.bbird.weatherdemo.WeatherSQLiteHelper.*;

// Data Access Layer

public class Dal {
	private Context context = null;
	
	public Dal(Context context)
	{
		this.context = context;
	}
	
	// This is a temporary method for loading fixed data into the db
	public void loadTestData(String zipCode)
	{
		  // Initialize database
        WeatherSQLiteHelper helper = new WeatherSQLiteHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();	
	   // load the database with test data if it isn't already loaded
	   if (db.rawQuery("SELECT * FROM Forecast WHERE " + ZIP
               + " = " + zipCode, null).getCount() == 0)
	   {
		   loadDbFromXML("97405");	// Eugene
		   loadDbFromXML("97439"); // Florence
		   loadDbFromXML("99515"); // Anchorage
	   }
	   db.close();	
	}

	
	// Parse the XML files and put the data in the db
	public void loadDbFromXML(String zipCode) {
		// Get the data from the XML file
		String fileName = "weather" + zipCode +".xml";
		WeatherItems items = parseXmlFile(fileName);
		items.setZip(zipCode);	// This field isn't in the xml file, so we add it here
		
		  // Initialize database
        WeatherSQLiteHelper helper = new WeatherSQLiteHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        
        // Put weather forecast in the database
        ContentValues cv = new ContentValues();
        
        for(WeatherItem item : items)
        {
	        cv.put(DATE, item.getForecastDateFormatted());
	        cv.put(ZIP, items.getZip());				// stored in items, not item
	        cv.put(CITY, items.getCity());			// stored in items, not item
	        cv.put(DESCRIPTION, item.getDescription());
            cv.put(IMAGE_ID,
                    Integer.toString(context.getResources().getIdentifier(
                            item.getDescription().toLowerCase().replaceAll("\\s+",""),
                            "drawable", context.getPackageName())));
	        cv.put(MORNING_LOW, item.getLowTemp());
	        cv.put(DAYTIME_HIGH, item.getHighTemp());
	        cv.put(NIGHT_PRECIP, item.getNightPrecip());
	        cv.put(DAY_PRECIP, item.getDayPrecip());
	        db.insert(FORECAST, null, cv);
        }
        db.close();
	}
	
    public Cursor getForcastByLocation(String location)
    {
        // Ensure there is data in the database for this location
        loadTestData(location);

        // Initialize the database
        WeatherSQLiteHelper helper = new WeatherSQLiteHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();
 
        // Get a weather forecast for one location
        String query = "SELECT * FROM Forecast WHERE Zip = ? ORDER BY Date ASC";
        String[] variables = new String[]{location};    // rawQuery must not include a trailing ';'
        return db.rawQuery(query, variables); 
    }

    public WeatherItems parseXmlFile(String fileName) {
        try {
            // get the XML reader
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XMLReader xmlreader = parser.getXMLReader();

            // set content handler
            ParseHandler handler = new ParseHandler();
            xmlreader.setContentHandler(handler);

            // read the file from internal storage
            InputStream in = context.getAssets().open(fileName);

            // parse the data
            InputSource is = new InputSource(in);
            xmlreader.parse(is);

            // set the feed in the activity
            WeatherItems items = handler.getItems();
            return items;
        } 
        catch (Exception e) {
            Log.e("News reader", e.toString());
            return null;
        }
    }	
}
