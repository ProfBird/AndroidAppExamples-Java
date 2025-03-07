package edu.uoregon.bbird.clickcounter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private int count = 0;
    private int countIncrement = 1;
    private TextView countText;
    private Button countButton;
    private SharedPreferences savedValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        savedValues = PreferenceManager.getDefaultSharedPreferences(this);
        countText = (TextView)findViewById(R.id.countText);
        countButton = (Button)findViewById(R.id.countButton);
        Log.d("MainActivity", "In onCreate");
    }

    @Override
    protected void onResume() {
        super.onResume();
        count = savedValues.getInt("count", 0);
        countText.setText(Integer.toString(count));
        countIncrement = Integer.parseInt(savedValues.getString("pref_increment_amount", "1"));
        countButton.setText("+" + Integer.toString(countIncrement));
        Log.d("MainActivity", "In onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = savedValues.edit();
        editor.putInt("count", count);
        editor.commit();
        Log.d("MainActivity", "In onPause");
    }

    public void countButtonOnClick(View v) {
        count += countIncrement;
        countText.setText(Integer.toString(count));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
            //Toast.makeText(this, "Setting", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
            return true;
        }
        else if (id== R.id.action_about) {
            Toast.makeText(this, "About", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
