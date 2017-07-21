package edu.uoregon.bbird.weatherdemo;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;

public class ParseHandler extends DefaultHandler {
    private WeatherItems weatherItems;
    private WeatherItem item;
    
    public WeatherItems getItems() {
        return weatherItems;
    }
      
    @Override
    public void startDocument() throws SAXException {
        weatherItems = new WeatherItems();
    }
    
    @Override
    public void startElement(String namespaceURI, String localName, 
            String qName, Attributes atts) throws SAXException {

        if (qName.equals("time")) {
            item = new WeatherItem();
            item.setForecastDate(atts.getValue(0));
        }
        else if (qName.equals("clouds")) {
            item.setDescription(atts.getValue(0));
        }
        else if (qName.equals("temperature")) {
            item.setLowTemp(atts.getValue(2));
            item.setHighTemp(atts.getValue(3));
        }
        else if (qName.equals("precipitation")) {
            item.setPrecipitation(atts.getValue(0));
        }
        else if (qName.equals("symbol")) {
            item.setSymbol(atts.getValue(2));
        }    }
    
    @Override
    public void endElement(String namespaceURI, String localName, 
            String qName) throws SAXException
    {
        if (qName.equals("time")) {
            weatherItems.add(item);
        }
    }
}

