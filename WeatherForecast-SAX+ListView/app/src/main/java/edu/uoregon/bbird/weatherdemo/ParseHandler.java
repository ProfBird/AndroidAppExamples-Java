package edu.uoregon.bbird.weatherdemo;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;

public class ParseHandler extends DefaultHandler {
    private WeatherItems weatherItems;
    private WeatherItem item;
    
    private boolean isTime = false;
    private boolean isClouds = false;
    private boolean isTemperature = false;
    private boolean isPrecipitation = false;
    
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
        
        if (qName.equals("forecast")) {
            item = new WeatherItem();
        }
        else if (qName.equals("time")) {
            isTime = true;
            item.setForecastDate(atts.getValue(0));
        }
        else if (qName.equals("clouds")) {
            isClouds = true;
        }
        else if (qName.equals("temperature")) {
            isTemperature = true;
        }
        else if (qName.equals("precipitation")) {
            isPrecipitation = true;
        }
    }
    
    @Override
    public void endElement(String namespaceURI, String localName, 
            String qName) throws SAXException
    {
        if (qName.equals("forecast")) {
            weatherItems.add(item);
        }
    }


    @Override
    public void characters(char ch[], int start, int length)
    {
        String s = new String(ch, start, length);
       if (isClouds) {
            item.setDescription(s);
            isClouds = false;
        }
        else if (isPrecipitation) {
            item.setHighTemp(s);
            isPrecipitation = false;
        }
    }

}

