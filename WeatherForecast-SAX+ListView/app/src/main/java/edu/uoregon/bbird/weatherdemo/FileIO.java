package edu.uoregon.bbird.weatherdemo;

import java.io.InputStream;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import android.content.Context;
import android.util.Log;

public class FileIO {
    
    private final String FILENAME = "weather.xml";
    private Context context = null;
    
    public FileIO (Context context) {
        this.context = context;
    }
    
    public WeatherItems readFile() {
        try {
            // get the XML reader
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XMLReader xmlreader = parser.getXMLReader();

            // set content handler
            ParseHandler handler = new ParseHandler();
            xmlreader.setContentHandler(handler);

            // read the file from internal storage
            InputStream in = context.getAssets().open(FILENAME);

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