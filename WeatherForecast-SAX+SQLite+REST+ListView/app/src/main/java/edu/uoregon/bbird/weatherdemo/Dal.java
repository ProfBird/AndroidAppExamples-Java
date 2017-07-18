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

import static edu.uoregon.bbird.weatherdemo.WeatherSQLiteHelper.FCT_TEXT;

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

    public Cursor getForcastFromDb(String city, String state, Date date)
    {
        // Initialize the database for reading
        WeatherSQLiteHelper helper = new WeatherSQLiteHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();

        // Set up a query for a weather forecast for one location
        String query = "SELECT * FROM Forecast WHERE City = ? ORDER BY Date ASC";
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
                Log.e("Weather", e.toString());
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
            cv.put(WeatherSQLiteHelper.DATE, item.getForecastDateFormatted());
            cv.put(WeatherSQLiteHelper.STATE, items.getZip());
            cv.put(WeatherSQLiteHelper.CITY, items.getCity());
            cv.put(WeatherSQLiteHelper.ICON, item.getDescription());
            cv.put(WeatherSQLiteHelper.IMAGE_ID,
                    Integer.toString(context.getResources().getIdentifier(
                            item.getDescription().toLowerCase().replaceAll("\\s+", ""),
                            "drawable", context.getPackageName())));
            cv.put(FCT_TEXT, item.getLowTemp());
            cv.put(WeatherSQLiteHelper.TITLE, item.getHighTemp());
            cv.put(WeatherSQLiteHelper.POP, item.getNightPrecip());
            cv.put(WeatherSQLiteHelper.PERIOD, item.getDayPrecip());
            db.insert(WeatherSQLiteHelper.FORECAST, null, cv);
        }
        db.close();
    }
}

    
