package edu.uoregon.bbird.weatherdemo;

// XML weather files can be manually downloaded from http://wsf.cdyne.com/WeatherWS/Weather.asmx
// Interesting zip codes: Eugene 97405, Anchorage 99515, Florence 97439

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;

public class ParseHandler extends DefaultHandler {
    private WeatherItems weatherItems;
    private WeatherItem item;
    private boolean isCity = false;
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
        
    	if (qName.equals("City")) {
            isCity = true;;
            return;
        }
    	else if (qName.equals("Forecast")) {
            item = new WeatherItem();
            return;
        }
        else if (qName.equals("Date")) {
            isDate = true;
            return;
        }
        else if (qName.equals("Desciption")) {
            isDescription = true;
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
        else if (qName.equals("Nighttime")) {
            isNightPrecip = true;
            return;
        }else if (qName.equals("Daytime")) {
            isDayPrecip = true;
            return;
        }
    }
    
    @Override
    public void endElement(String namespaceURI, String localName, 
            String qName) throws SAXException
    {
        if (qName.equals("Forecast")) {
            weatherItems.add(item);
        }
        return;    
    }
    
    @Override
    public void characters(char ch[], int start, int length)
    {
        String s = new String(ch, start, length);
        if (isCity) {
    		weatherItems.setCity(s);
    		isCity = false;
        }   
        else if (isDate) {
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

