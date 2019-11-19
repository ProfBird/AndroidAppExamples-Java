package edu.lanecc.birdb.listview_arrayadapter_parser_demo;
import android.os.Bundle;
import android.app.ListActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.ArrayList;

import androidx.annotation.Nullable;

public class VocabListActivity extends ListActivity  implements AdapterView.OnItemClickListener {

    ArrayList<VocabItem> vocabItems;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    Dal dal = new Dal(this);
        vocabItems = dal.parseXmlFile("Spanish-english.xml");

        // The ArrayAdapter constructor requires these parameters:
        // Application context, Row layout, and an array


        ArrayAdapter<VocabItem> adapter = new ArrayAdapter<VocabItem>(
           this, android.R.layout.simple_list_item_1, vocabItems);

        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
        getListView().setFastScrollEnabled(true);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this, vocabItems.get(i).getEnglish(), Toast.LENGTH_SHORT).show();
    }
}
