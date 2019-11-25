package edu.lanecc.birdb.listview_arrayadapter_parser_demo;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import java.io.InputStream;
import java.util.ArrayList;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


/*************** Dal class: Data Access Layer ***************/
/* The purpose of this class is to provide an API for reading
   and writing data from multiple sources. Currently it only
   reads data from files, but in the future it will also have
   methods for pulling data from an internet web service and
   for doing database operations.

   This class depends on the following classes:
   -  ParseHandler
      Used for parsing XML tide prediction data into an array of VocabItem objects
   -  VocabItem
      Contains fields with getters and setters for relevant tide data
 */

public class Dal {

    private Context context = null;  // Android application context--required to access the Android file system.
    private SQLiteDatabase db = null;
    // A context object should be passed to this constructor from the activity where this class is instantiated.
    public Dal(Context c)
    {
        context = c;

        // Initialize database
        VocabSqliteHelper helper = new VocabSqliteHelper(context);
        db = helper.getWritableDatabase();
    }

    // This method accepts the name of a file in the assets folder as an argument
    // and returns an ArrayList of VocabItem objects.
    public ArrayList<VocabItem> parseXmlFile(String fileName) {
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
            ArrayList<VocabItem> items = handler.getItems();
            return items;
        }
        catch (Exception e) {
            Log.e("Vocab parse error", e.toString());
            return null;
        }
    }

    // Parse the XML files and put the data in the db
    public void loadDbFromXML() {
        // Get the data from the XML file
        ArrayList<VocabItem> items = parseXmlFile("spanish-english.xml");

        // Put weather forecast in the database
        ContentValues cv = new ContentValues();

        for(VocabItem item : items)
        {
            cv.put(VocabSqliteHelper.ENGLISH, item.getEnglish());
            cv.put(VocabSqliteHelper.SPANISH, item.getSpanish());				// stored in items, not item
            cv.put(VocabSqliteHelper.POS, item.getPos());			// stored in items, not item

            db.insert(VocabSqliteHelper.VOCABULARY, null, cv);
        }
        db.close();
    }
}
