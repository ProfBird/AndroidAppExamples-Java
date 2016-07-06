package com.example.xmlparsepractice;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

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
            ParseHandler theRssHandler = new ParseHandler();
            xmlreader.setContentHandler(theRssHandler);

            // read the file from internal storage
            InputStream in = context.getAssets().open(FILENAME);

            // parse the data
            InputSource is = new InputSource(in);
            xmlreader.parse(is);

            // set the feed in the activity
            WeatherItems feed = theRssHandler.getFeed();
            return feed;
        } 
        catch (Exception e) {
            Log.e("News reader", e.toString());
            return null;
        }
    }
}