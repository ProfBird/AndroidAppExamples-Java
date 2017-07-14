package edu.uoregon.bbird.weatherdemo;
// Written by Brian Bird 7/11/15, updated 7/13/17

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;


public class WeatherSQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "weather.sqlite";
    private static final int DATABASE_VERSION = 1;

    public static final String FORECAST = "Forecast";
    public static final String DATE = "Date";
    public static final String ZIP = "Zip";
    public static final String CITY = "City";
    public static final String DESCRIPTION = "Desciption";  // misspelled in the XML schema!
    public static final String IMAGE_ID = "ImageId";
    public static final String MORNING_LOW = "MorningLow";
    public static final String DAYTIME_HIGH = "DaytimeHigh";
    public static final String DAY_PRECIP = "Daytime";
    public static final String NIGHT_PRECIP = "Nighttime";

    private Context context = null;

	public WeatherSQLiteHelper(Context c) {
		super(c, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = c;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + FORECAST
				+ "( _id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ DATE + " TEXT,"
				+ ZIP + " INTEGER,"
				+ CITY + " TEXT,"
				+ DESCRIPTION + " TEXT,"
				+ IMAGE_ID + " TEXT,"
				+ MORNING_LOW + " INTEGER,"
				+ DAYTIME_HIGH + " INTEGER,"
				+ NIGHT_PRECIP + " INTEGER,"
				+ DAY_PRECIP + " INTEGER"
				+ ")" );
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
