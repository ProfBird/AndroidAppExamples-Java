package com.example.xmlparsepractice;

import java.util.ArrayList;

public class WeatherItems {

    private ArrayList<WeatherItem> items = 
    		new ArrayList<WeatherItem>();

	public ArrayList<WeatherItem> getItems() {
		return items;
	}

	public void setItems(ArrayList<WeatherItem> items) {
		this.items = items;
	}
        
  }