package edu.lanecc.birdb.spanish_vocabulary_sql_demo;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class VocabSqliteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "vocab.sqlite";
    private static final int DATABASE_VERSION = 1;
    public static final String VOCABULARY = "vocabulary";
    public static final String SPANISH = "spanish";
    public static final String ENGLISH = "english";
    public static final String POS = "pos";

    private Context context = null;
    private ArrayList<VocabItem> vocabItems = null;

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

        // Get the vocab data and put it in the new table
        loadDatabaseFromXmlFile(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // TODO: Manage schema changes
    }

    private void loadDatabaseFromXmlFile(SQLiteDatabase db) {
        Dal dal = new Dal(context);
        vocabItems = dal.parseXmlFile("Spanish-english.xml");
        // Put weather forecast in the database
        ContentValues cv = new ContentValues();

        for(VocabItem item : vocabItems)
        {
            cv.put(ENGLISH, item.getEnglish());
            cv.put(SPANISH, item.getSpanish());				// stored in items, not item
            cv.put(POS, item.getPos());			// stored in items, not item

            db.insert(VOCABULARY, null, cv);
        }
    }
}
