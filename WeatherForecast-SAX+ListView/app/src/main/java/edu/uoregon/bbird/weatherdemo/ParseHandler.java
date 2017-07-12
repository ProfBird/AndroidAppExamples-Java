package edu.uoregon.bbird.weatherdemo;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;

public class ParseHandler extends DefaultHandler {
    private WeatherItems weatherItems;
    private WeatherItem item;
    
    private boolean isDate = false;
    private boolean isDescription = false;
    private boolean isMorningLow = false;
    private boolean isDaytimeHigh = false;
    
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
        
        if (qName.equals("Forecast")) {
            item = new WeatherItem();
        }
        else if (qName.equals("Date")) {
            isDate = true;
        }
        else if (qName.equals("Desciption")) {
            isDescription = true;
        }
        else if (qName.equals("MorningLow")) {
            isMorningLow = true;
        }
        else if (qName.equals("DaytimeHigh")) {
            isDaytimeHigh = true;
        }
    }
    
    @Override
    public void endElement(String namespaceURI, String localName, 
            String qName) throws SAXException
    {
        if (qName.equals("Forecast")) {
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
    }
}

