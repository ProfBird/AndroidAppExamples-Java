package edu.uoregon.bbird.weatherdemo;

// Written by Brian Bird 7/11/15, updated 7/13/18


import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;

import static edu.uoregon.bbird.weatherdemo.WeatherSQLiteHelper.*;

public class ParseHandler extends DefaultHandler {
    private WeatherItems weatherItems;
    private WeatherItem item;
    private boolean isDate = false;
    private boolean isIcon = false;
    private boolean isForecastText = false;
    private boolean isTitle = false;
    private boolean isPOP = false;
    private boolean isPeriod = false;
    private boolean isDone = false;
    
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
        

    	if (isDone) {
            // Do nothing because we've parsed everything we need
        }
        else if (qName.equals(FORECAST_DAY)) {
            item = new WeatherItem();
        }
        else if (qName.equals(DATE)) {
            isDate = true;
        }
        else if (qName.equals(ICON)) {
            isIcon = true;
        }
        else if (qName.equals(FCT_TEXT)) {
            isForecastText = true;
        }
        else if (qName.equals(TITLE)) {
            isTitle = true;
        }
        else if (qName.equals(PERIOD)) {
            isPeriod = true;
        }else if (qName.equals(POP)) {
            isPOP = true;
        }
    }
    
    @Override
    public void endElement(String namespaceURI, String localName, 
            String qName) throws SAXException
    {
        if (!isDone && qName.equals(FORECAST_DAY)) {
            weatherItems.add(item);
        }
        else if (qName.equals("txt_forecast")) {
            isDone = true;
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
        else if (isIcon) {
            item.setIcon(s);
            isIcon = false;
        }
         else if (isForecastText) {
            item.setForecastText(s);
            isForecastText = false;
        }
        else if (isTitle) {
            item.setTitle(s);
            isTitle = false;
        }
        else if (isPeriod) {
            item.setPeriod(s);
            isPeriod = false;
        }
        else if (isPOP) {
            item.setPOP(s);
            isPOP = false;
        }
    }
}

