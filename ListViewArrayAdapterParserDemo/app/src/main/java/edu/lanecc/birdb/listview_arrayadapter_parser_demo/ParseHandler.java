package edu.lanecc.birdb.listview_arrayadapter_parser_demo;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.ArrayList;

/************** ParseHandler **********************************************
 * This class contains helper methods for the SAX event-based parser
 * framework. These methods are tailored to parse the XML tide prediction
 * data files from https://tidesandcurrents.noaa.gov/noaacurrents/Regions
 *
 * This class is used by the Dal (Data Access Layer) class.
 * This class depends on the VocabItem class.
 **************************************************************************/

public class ParseHandler extends DefaultHandler {

    private ArrayList<VocabItem> VocabItems; // Will hold the parser output
    private VocabItem item;           // Holds data for one word
    private boolean isSpanish = false; // This and the other booleans are used by SAX
    private boolean isEnglish = false;
    private boolean isPos = false;

    public ArrayList<VocabItem> getItems() {
        return VocabItems;
    }

    @Override
    public void startDocument() throws SAXException {
        VocabItems = new ArrayList<VocabItem>();
    }

    @Override
    public void startElement(String namespaceURI, String localName,
                             String qName, Attributes atts) throws SAXException {

        if (qName.equals("word")) {
            item = new VocabItem();
            return;
        }
        else if (qName.equals("spanish")) {
            isSpanish = true;
            return;
        }
        else if (qName.equals("english")) {
            isEnglish = true;
            return;
        }
        else if (qName.equals("pos")) {
            isPos = true;
            return;
        }
    }


    @Override
    public void characters(char ch[], int start , int length) {
        //print out the attributes' value
        String valueString = new String(ch, start, length);

        if (isSpanish) {
            item.setSpanish(valueString);
            isSpanish = false;
        } else if (isEnglish) {
            item.setEnglish(valueString);
            isEnglish = false;
        }else if (isPos) {
            item.setPos(valueString);
            isPos = false;
        }
    }

    @Override
    public void endElement(String namespaceURI, String localName,
                           String qName) throws SAXException
    {
        if (qName.equals("word")) {
            VocabItems.add(item);
        }
        return;
    }
}
