package edu.uoregon.bbird.weatherdemo;

// Written by Brian Bird 7/11/15, updated 7/21/17

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import static edu.uoregon.bbird.weatherdemo.WeatherSQLiteHelper.CITY;
import static edu.uoregon.bbird.weatherdemo.WeatherSQLiteHelper.FCT_TEXT;
import static edu.uoregon.bbird.weatherdemo.WeatherSQLiteHelper.FORECAST_TABLE;
import static edu.uoregon.bbird.weatherdemo.WeatherSQLiteHelper.ICON;
import static edu.uoregon.bbird.weatherdemo.WeatherSQLiteHelper.IMAGE_ID;
import static edu.uoregon.bbird.weatherdemo.WeatherSQLiteHelper.PERIOD;
import static edu.uoregon.bbird.weatherdemo.WeatherSQLiteHelper.POP;
import static edu.uoregon.bbird.weatherdemo.WeatherSQLiteHelper.START_DATE;
import static edu.uoregon.bbird.weatherdemo.WeatherSQLiteHelper.STATE;
import static edu.uoregon.bbird.weatherdemo.WeatherSQLiteHelper.TITLE;

// Data Access Layer

public class Dal  {

    /********** ------  Instance variables ----- ***********/
	private Context context = null;
    // This is the format we want in our output
    private  SimpleDateFormat dateOutFormat =
            new SimpleDateFormat("MM'/'dd'/'yyyy", Locale.US); // Example: 07/21/2017


    /************ ---- Constructor ----- ********************/

	public Dal(Context context)
	{
		this.context = context;
	}


    /************ --- Public methods ---- ********************/

    public Cursor getForcastFromDb(String state, String city)
    {
        // Initialize the database for reading
        WeatherSQLiteHelper helper = new WeatherSQLiteHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();

        // Set up a query for a weather forecast for one city and starting date
        String query = "SELECT * FROM " + FORECAST_TABLE + " WHERE " + STATE + " = ? AND " +
                CITY + " = ? AND " + START_DATE + " = ? ORDER BY " + PERIOD + " ASC";
        // We're looking for a forecast that was downloaded today, if the forecast is older, we want to get a new one
        // So if there isn't a forecast from today we'll return an empty cursor
        String[] variables = new String[]{state, city, getTodaysDate()};    // rawQuery must not include a trailing ';'

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
                items.setForecastDate(getTodaysDate()); // Today's date
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
            cv.put(START_DATE, items.getForecastDate());  // Just the date, not the time
            cv.put(TITLE, item.getTitle() );    // Weekday + morning or evening
            cv.put(STATE, items.getState() );
            cv.put(CITY, items.getCity() );
            cv.put(ICON, item.getIcon() );      // Description of the icon
            cv.put(IMAGE_ID,
                    Integer.toString(context.getResources().getIdentifier(
                            item.getIcon().toLowerCase().replaceAll("\\s+", ""),
                            "drawable", context.getPackageName())));
            cv.put(FCT_TEXT, item.getForecastText());
            cv.put(POP, item.getPOP());           // Percent chance of precipitation
            cv.put(PERIOD, item.getPeriod());     // sequential number of forecast
            db.insert(FORECAST_TABLE, null, cv);
        }
        db.close();
    }

    public  String getTodaysDate() {
        return dateOutFormat.format(Calendar.getInstance(Locale.US).getTime());
    }
}

    
