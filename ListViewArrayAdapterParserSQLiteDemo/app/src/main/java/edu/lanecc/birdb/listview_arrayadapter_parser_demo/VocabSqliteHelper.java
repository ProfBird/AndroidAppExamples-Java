package edu.lanecc.birdb.listview_arrayadapter_parser_demo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class VocabSqliteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "vocab.sqlite";
    private static final int DATABASE_VERSION = 1;
    public static final String VOCABULARY = "vocabulary";
    public static final String SPANISH = "spanish";
    public static final String ENGLISH = "english";
    public static final String POS = "pos";

    private Context context = null;

    public VocabSqliteHelper(Context c) {
        super(c, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = c;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + VOCABULARY
                + "( _id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + SPANISH + " TEXT,"
                + ENGLISH + " TEXT,"
                + POS + " TEXT"
                + ")" );

        Dal dal = new Dal(context);
        dal.loadDbFromXML();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // TODO: Manage schema changes
    }
}
