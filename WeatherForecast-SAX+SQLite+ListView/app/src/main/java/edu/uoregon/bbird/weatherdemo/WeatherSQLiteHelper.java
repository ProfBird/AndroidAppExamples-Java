package edu.uoregon.bbird.weatherdemo;
// Written by Brian Bird 7/11/15, updated 7/13/17

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;


public class WeatherSQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "weather.sqlite";
    private static final int DATABASE_VERSION = 1;
    private Context context = null;

	public WeatherSQLiteHelper(Context c) {
		super(c, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = c;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE Forecast"
				+ "( _id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "Date TEXT,"
				+ "Zip INTEGER,"
				+ "City TEXT,"
				+ "Description TEXT,"
				+ "ImageId TEXT,"
				+ "LowTemp INTEGER,"
				+ "HighTemp INTEGER,"
				+ "NightPrecip INTEGER,"
				+ "DayPrecip INTEGER"
				+ ")" );
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
