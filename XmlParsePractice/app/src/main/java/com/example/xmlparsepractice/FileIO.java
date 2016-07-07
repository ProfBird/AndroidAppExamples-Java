package com.example.xmlparsepractice;

import android.content.Context;
import android.util.Log;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

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
            ParseHandler weatherParseHandler = new ParseHandler();
            xmlreader.setContentHandler(weatherParseHandler);

            // read the file from internal storage
            InputStream in = context.getAssets().open(FILENAME);

            // parse the data
            InputSource is = new InputSource(in);
            xmlreader.parse(is);

            // return a forecast object which contains a list of parsed weather data
            WeatherItems forecast = weatherParseHandler.getFeed();
            return forecast;
        } 
        catch (Exception e) {
            Log.e("News reader", e.toString());
            return null;
        }
    }
}