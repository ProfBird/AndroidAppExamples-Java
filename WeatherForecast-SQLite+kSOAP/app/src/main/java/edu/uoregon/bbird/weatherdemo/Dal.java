package edu.uoregon.bbird.weatherdemo;

import java.io.StringReader;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

// Data Access Layer

public class Dal {
	private Context context = null;
	
	public Dal(Context context)
	{
		this.context = context;
	}
	
	
	// Parse the XML files and put the data in the db
	public void loadDbFromWebService(String xmlData, String zipCode) {
		// Get the data from the XML string
		WeatherItems items = parseXml(xmlData);
		items.setZip(zipCode);	// This field isn't in the xml file, so we add it here
		
		  // Initialize database
        WeatherSQLiteHelper helper = new WeatherSQLiteHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        
        // Put weather forecast in the database
        ContentValues cv = new ContentValues();
        
        for(WeatherItem item : items)
        {
	        cv.put("Date", item.getForecastDateFormatted());
	        cv.put("Zip", items.getZip());				// stored in items, not item
	        cv.put("City", items.getCity());			// stored in items, not item
	        cv.put("Description", item.getDescription());
	        cv.put("LowTemp", item.getLowTemp());
	        cv.put("HighTemp", item.getHighTemp());
	        cv.put("NightPrecip", item.getNightPrecip());
	        cv.put("DayPrecip", item.getDayPrecip());
	        db.insert("Forecast", null, cv);
        }
        db.close();
	}
	
    public Cursor getForcastFromDb(String location)
    {
        // Initialize the database
        WeatherSQLiteHelper helper = new WeatherSQLiteHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();
 
        // Get a weather forecast for one location
        String query = "SELECT * FROM Forecast WHERE Zip = ? ORDER BY Date ASC";
        // "SELECT * FROM Forecast WHERE Zip = ? AND Date = ? ORDER BY Date ASC";
        String[] variables = new String[]{location};    // rawQuery must not include a trailing ';'
        return db.rawQuery(query, variables); 
    }

    public WeatherItems parseXml(String xmlData) {
        try {
            // get the XML reader
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XMLReader xmlreader = parser.getXMLReader();

            // set content handler
            ParseHandler handler = new ParseHandler();
            xmlreader.setContentHandler(handler);

            // parse the data
            xmlreader.parse(new InputSource(new StringReader(xmlData)));

            // set the weather items
            WeatherItems items = handler.getItems();
            return items;
        } 
        catch (Exception e) {
            Log.e("Weather data parser", e.toString());
            return null;
        }
    }
    
   
}
