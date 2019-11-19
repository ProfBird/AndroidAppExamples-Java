package edu.lanecc.birdb.listview_arrayadapter_parser_demo;
import android.os.Bundle;
import android.app.ListActivity;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.ArrayList;

import androidx.annotation.Nullable;

public class VocabListActivity extends ListActivity {

    ArrayList<VocabItem> vocabItems;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    Dal dal = new Dal(this);
        vocabItems = dal.parseXmlFile("Spanish-english.xml");

        ArrayAdapter<VocabItem> adapter = new ArrayAdapter<VocabItem>(
                this,
                vocabItems,
                android.R.layout.two_line_list_item,

        )
    }
}
