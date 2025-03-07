package edu.lanecc.birdb.spanish_vocabulary_sql_demo;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.cursoradapter.widget.SimpleCursorAdapter;

import java.util.ArrayList;

public class VocabListActivity extends ListActivity {

    ArrayList<VocabItem> vocabItems;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String partOfSpeech = getIntent().getStringExtra(MainActivity.POS);

        // Initialize database
        VocabSqliteHelper helper = new VocabSqliteHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();

        String query = "SELECT * " +
                        " FROM " +
                        VocabSqliteHelper.VOCABULARY;

        if (!partOfSpeech.equals("all")) {
            query += " WHERE " +
                    VocabSqliteHelper.POS +
                    " = '" + partOfSpeech + "';";
        }
        else
        {
            query += ";";
        }

        Cursor cursor = db.rawQuery(query, null);

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this,
                R.layout.listview_items,
                cursor,
                new String[]{VocabSqliteHelper.SPANISH,
                                VocabSqliteHelper.POS,
                        },
                new int[]{
                        R.id.spanishTextView,
                        R.id.posTextView
                },
                0 );	// no flags
        setListAdapter(adapter);
    }
}
