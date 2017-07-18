package edu.uoregon.bbird.weatherdemo;

// Written by Brian Bird 7/11/15, updated 7/18/17

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Date;

// Data Access Layer

public class Dal  {

    // Instance variables
	private Context context = null;

    /************ ---- Constructor ----- ********************/

	public Dal(Context context)
	{
		this.context = context;
	}

    /************ --- Public methods ---- ********************/

    public Cursor getForcastByLocation(String city, String state, Date date)
    {
        // Initialize the database for reading
        WeatherSQLiteHelper helper = new WeatherSQLiteHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();

        // Set up a query for a weather forecast for one location
        String query = "SELECT * FROM Forecast WHERE City = ? ORDER BY Date ASC";
        String[] variables = new String[]{city};    // rawQuery must not include a trailing ';'

        // Do the query
        Cursor cursor = db.rawQuery(query, variables);

        // If there isn't a forecast in the db for this location (and date?), then get one from the web service
        if(cursor.getCount() == 0) {
           new RestTask().execute(state, city);
            cursor = db.rawQuery(query, variables);
        }

        return cursor;
    }

	/************ --- Private methods ---- *************/
    /*
    private Void parseXmlStream(InputStream in) {
        try {
            // get the XML reader
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XMLReader xmlreader = parser.getXMLReader();

            // set content handler
            ParseHandler handler = new ParseHandler();
            xmlreader.setContentHandler(handler);

            // parse the data
            InputSource is = new InputSource(in);
            xmlreader.parse(is);

            // set the feed in the activity
            items = handler.getItems();
        } catch (Exception e) {
            Log.e("Weather", e.toString());
            items = null;
        }
        return null;
    }
    */
}

    
