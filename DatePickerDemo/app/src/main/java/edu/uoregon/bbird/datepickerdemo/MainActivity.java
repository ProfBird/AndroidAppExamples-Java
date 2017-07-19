package edu.uoregon.bbird.datepickerdemo;

/* Written by Brian Bird, 7/19/17
 * Demonstrates the use of a DatePicker
 * The UI is set to spinner in activity_main.xml
 *
 * This example is "old school", Google's current advice is to use a
 * DatePickerDialog instead of a DatePicker. For more info see:
 * https://developer.android.com/guide/topics/ui/controls/pickers.html
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity
                            implements DatePicker.OnDateChangedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatePicker picker = (DatePicker)findViewById(R.id.datePicker);

        // Use the Java Calendar class to manipulate dates
        Calendar c = Calendar.getInstance();

        // initialize the picker to show today's date
        // and pass it the OnDateChangedListener, this
        int seconds = c.get(Calendar.SECOND);
        picker.init(c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH), this);
    }


    @Override
    public void onDateChanged(DatePicker datePicker, int year, int month, int day) {
        // Creating a calendar object set to the date from the picker
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day);

        // Changing the date format
        String pattern = "MM/dd/yyyy";
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        String dateString = df.format(cal.getTime());

        // displaying the date that was selected
        TextView text = (TextView)findViewById(R.id.helloTextView);
        text.setText(dateString);
    }
}
