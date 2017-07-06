package edu.uoregon.bbird.rockpaperscissors;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by brian on 7/6/17.
 */

public class SettingsActivity extends PreferenceActivity {
    @Override
    @SuppressWarnings("deprecation")
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // addPreferencesFromResource is deprecated.
        // The preferred technique is to put preferences in a Fragment
        // TODO: We'll use a PreferenceFragment in the next version of this app
        addPreferencesFromResource(R.xml.preferences);
    }
}