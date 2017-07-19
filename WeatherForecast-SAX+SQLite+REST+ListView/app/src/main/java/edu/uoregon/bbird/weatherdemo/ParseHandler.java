package edu.uoregon.bbird.weatherdemo;

// Written by Brian Bird 7/11/15, updated 7/13/18


import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;

import static edu.uoregon.bbird.weatherdemo.WeatherSQLiteHelper.*;

public class ParseHandler extends DefaultHandler {
    private WeatherItems weatherItems;
    private WeatherItem item;
    private boolean isDate = false;
    private boolean isDescription = false;
    private boolean isMorningLow = false;
    private boolean isDaytimeHigh = false;
    private boolean isDayPrecip = false;
    private boolean isNightPrecip = false;
    
    public WeatherItems getItems() {
        return weatherItems;
    }
      
    @Override
    public void startDocument() throws SAXException {
        weatherItems = new WeatherItems();
        item = new WeatherItem();
    }
    
    @Override
    public void startElement(String namespaceURI, String localName, 
            String qName, Attributes atts) throws SAXException {
        

    	if (qName.equals(FORECAST_DAY)) {
            item = new WeatherItem();
        }
        else if (qName.equals(DATE)) {
            isDate = true;
        }
        else if (qName.equals(ICON)) {
            isDescription = true;
        }
        else if (qName.equals(FCT_TEXT)) {
            isMorningLow = true;
        }
        else if (qName.equals(TITLE)) {
            isDaytimeHigh = true;
        }
        else if (qName.equals(PERIOD)) {
            isNightPrecip = true;
        }else if (qName.equals(POP)) {
            isDayPrecip = true;
        }
    }
    
    @Override
    public void endElement(String namespaceURI, String localName, 
            String qName) throws SAXException
    {
        if (qName.equals(FORECAST_DAY)) {
            weatherItems.add(item);
        }
    }
    
    @Override
    public void characters(char ch[], int start, int length)
    {
        String s = new String(ch, start, length);
        if (isDate) {
            item.setForecastDate(s);
            isDate = false;
    }
        else if (isDescription) {
            item.setDescription(s);
            isDescription = false;
        }
         else if (isMorningLow) {
            item.setLowTemp(s);
            isMorningLow = false;
        }
        else if (isDaytimeHigh) {
            item.setHighTemp(s);
            isDaytimeHigh = false;
        }
        else if (isNightPrecip) {
            item.setNightPrecip(s);
            isNightPrecip = false;
        }
        else if (isDayPrecip) {
            item.setDayPrecip(s);
            isDayPrecip = false;
        }
    }
}

