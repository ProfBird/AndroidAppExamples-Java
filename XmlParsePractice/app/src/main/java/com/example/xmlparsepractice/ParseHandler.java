package com.example.xmlparsepractice;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;

public class ParseHandler extends DefaultHandler {
    private WeatherItems weatherItems;
    private WeatherItem item;
    
    private boolean isDate = false;
    private boolean isTemperatures = false;
    private boolean isMorningLow = false;
    private boolean isDaytimeHigh = false;
    
    public WeatherItems getFeed() {
        return weatherItems;
    }
        
    public void startDocument() throws SAXException {
        weatherItems = new WeatherItems();
        item = new WeatherItem();
    }
    
    public void startElement(String namespaceURI, String localName, 
            String qName, Attributes atts) throws SAXException {
        
        if (qName.equals("Forecast")) {
            item = new WeatherItem();
            return;
        }
        else if (qName.equals("Date")) {
            isDate = true;
            return;
        }
        else if (qName.equals("Temperatures")) {
            isTemperatures = true;
            return;
        }
        else if (qName.equals("MorningLow")) {
            isMorningLow = true;
            return;
        }
        else if (qName.equals("DaytimeHigh")) {
            isDaytimeHigh = true;
            return;
        }
    }
    
    public void endElement(String namespaceURI, String localName, 
            String qName) throws SAXException
    {
        if (qName.equals("Forecast")) {
            weatherItems.getItems().add(item);
        }
        return;    
    }
     
    public void characters(char ch[], int start, int length)
    {
        String s = new String(ch, start, length);
        if (isDate) {
        		item.setForecastDate(s);
        		isDate = false;
        }
        else if (isMorningLow) {
            item.setLowTemp(s);
            isMorningLow = false;
        }
        else if (isDaytimeHigh) {
            item.setHighTemp(s);
            isDaytimeHigh = false;
        }
    }
}

