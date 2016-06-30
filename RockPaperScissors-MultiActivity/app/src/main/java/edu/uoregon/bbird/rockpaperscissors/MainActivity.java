package edu.uoregon.bbird.rockpaperscissors;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    RpsGame game = new RpsGame();
    ImageView rpsImage;
    EditText rpsEditText;
    TextView compMoveText;


    // Handler for the paly click event
    public void play(View v) {
        // Get the player's hand choice
        String humanHand = rpsEditText.getText().toString();

        // Start second activity and send it the player's hand selection
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("humanHand", humanHand);  // send data to 2nd activity
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        rpsImage = (ImageView)findViewById(R.id.rpsImage);
        rpsEditText = (EditText)findViewById(R.id.rpsEditText);
        compMoveText = (TextView)findViewById(R.id.compMoveTextView);
    }

/*
    @Override
    public boolean onCreateOptionsMroenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    */
}
