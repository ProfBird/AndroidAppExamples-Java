package edu.uoregon.bbird.weatherdemo;
// Written by Brian Bird 7/11/15, updated 7/13/17

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;


public class WeatherSQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "weather.sqlite";
    private static final int DATABASE_VERSION = 1;

	public static final String FORECAST_TABLE = "forecast"; // There is just one forecast element
	public static final String FORECAST_DAY = "forecastday"; // There is just one forecast element
    public static final String DATE = "date";  // The fist day of the forecast
    public static final String STATE = "state";
    public static final String CITY = "city";
    public static final String ICON = "icon";
    public static final String IMAGE_ID = "imageid";
    public static final String FCT_TEXT = "fcttext";
    public static final String TITLE = "title";   // Day and time of day
    public static final String POP = "pop";       // percent chance of precipitation
    public static final String PERIOD = "period";

    private Context context = null;

	public WeatherSQLiteHelper(Context c) {
		super(c, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = c;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + FORECAST_TABLE
				+ "( _id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ DATE + " TEXT,"
				+ STATE + " TEXT,"
				+ CITY + " TEXT,"
				+ ICON + " TEXT,"
				+ IMAGE_ID + " INTEGER,"
				+ FCT_TEXT + " TEXT,"
				+ TITLE + " TEXT,"
				+ PERIOD + " INTEGER,"
				+ POP + " INTEGER"
				+ ")" );
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
