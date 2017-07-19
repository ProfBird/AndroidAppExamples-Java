package edu.uoregon.bbird.weatherdemo;

// Written by Brian Bird 7/11/15, updated 7/18/17

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.InputStream;
import java.util.Date;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import static edu.uoregon.bbird.weatherdemo.WeatherSQLiteHelper.*;
import static edu.uoregon.bbird.weatherdemo.WeatherSQLiteHelper.FCT_TEXT;
import static edu.uoregon.bbird.weatherdemo.WeatherSQLiteHelper.FORECAST_TABLE;

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

    public Cursor getForcastFromDb(String state, String city, Date date)
    {
        // Initialize the database for reading
        WeatherSQLiteHelper helper = new WeatherSQLiteHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();

        // Set up a query for a weather forecast for one location
        String query = "SELECT * FROM " + FORECAST_TABLE + " WHERE " + CITY + " = ? ORDER BY Date ASC";
        String[] variables = new String[]{city};    // rawQuery must not include a trailing ';'

        // Do the query
        Cursor cursor = db.rawQuery(query, variables);

        return cursor;
    }

    protected WeatherItems parseXmlStream(InputStream in) {
        WeatherItems items = null;
        if (in != null) {
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
                items = handler.getItems();
            } catch (Exception e) {
                Log.e("Weather", "parseXMLStream error: " + e.toString());
            }
        }
        return items;
    }

    protected void putForecastIntoDb(WeatherItems items) {

        // Initialize database
        WeatherSQLiteHelper helper = new WeatherSQLiteHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        // Put weather forecast in the database
        ContentValues cv = new ContentValues();

        for (WeatherItem item : items) {
//            cv.put(WeatherSQLiteHelper.DATE, item.getForecastDateFormatted());
            cv.put(TITLE, item.getTitle() );
            cv.put(STATE, items.getState() );
            cv.put(CITY, items.getCity() );
            cv.put(ICON, item.getIcon() );
            cv.put(IMAGE_ID,
                    Integer.toString(context.getResources().getIdentifier(
                            item.getIcon().toLowerCase().replaceAll("\\s+", ""),
                            "drawable", context.getPackageName())));
            cv.put(FCT_TEXT, item.getForecastText());
            cv.put(TITLE, item.getTitle());
            cv.put(POP, item.getPOP());
            cv.put(PERIOD, item.getPeriod());
            db.insert(FORECAST_TABLE, null, cv);
        }
        db.close();
    }
}

    
