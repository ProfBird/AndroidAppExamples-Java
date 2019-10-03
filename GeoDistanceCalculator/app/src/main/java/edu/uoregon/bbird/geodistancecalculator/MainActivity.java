package edu.uoregon.bbird.geodistancecalculator;

// By Brian Bird, July 15, 2016, updated July 19, 2018 (Refactored to use the FusedLocationProviderClient)
// Demonstrates: 1) getting location using the Fused Location Provider API provided by Google Play Services
// 2) using the Android Geocoder to get an address from latitude and longitude
// 3) using the Android Location class to determine distance between two locaitons

import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity implements OnSuccessListener,
        OnFailureListener {

    FusedLocationProviderClient  locationClient;
    Location myLocation;
    LocationRequest locationRequest;

    TextView currentLocationTextView;
    TextView distanceTextView;
    TextView latLonTextView;
    EditText cityEditText;
    EditText stateEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentLocationTextView = (TextView) findViewById(R.id.currentLocationTextView);
        distanceTextView = (TextView) findViewById(R.id.distanceTextView);
        latLonTextView = (TextView) findViewById(R.id.latLonTextView);
        cityEditText = (EditText) findViewById(R.id.cityEditText);
        stateEditText = (EditText) findViewById(R.id.stateEditText);

        /*
        // This object will be used when we request location updates
        locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setNumUpdates(1)
                .setExpirationDuration(60000);
        // I wanted to set PRIORITY_LOW_POWER, but the emulator doesn't
        // give me a way to test location without GPS, so I'm using
        // PRIORITY_HIGH_ACCURCY for now.
        // NumUpdate is one, because I just need to get the location once
        */

        locationClient = LocationServices.getFusedLocationProviderClient(this);
        final int MY_PERMISSION_ACCESS_COURSE_LOCATION = 42;  // This is an arbitrary value
        // This check is required by Google Play Services APIs beginning with version 9.0.0 (I think)
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // With API 23 and later, users must give permission after the activity runs
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSION_ACCESS_COURSE_LOCATION);
        } else {
            // This is where we request the locaiton
            locationClient.getLastLocation().addOnSuccessListener(this);
        }
    }

  @Override
    public void onSuccess(Object location) {
        // This is the call-back method for getLastLocation
        myLocation = (Location)location;
        Geocoder geo = new Geocoder(this,
                Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = geo.getFromLocation(
                    myLocation.getLatitude(),
                    myLocation.getLongitude(), 1);
        } catch (IOException e) {
            Log.e("onSuccess", e.getMessage());
        }

        if (addresses != null) {
            currentLocationTextView.setText(addresses.get(0).getLocality());
        }
        else
        {
            currentLocationTextView.setText(myLocation.getLongitude() + ", " + myLocation.getLatitude());
        }
    }

    @Override
    public void onFailure(@NonNull Exception e) {
        distanceTextView.setText("Can't connect to location API");
    }

    public void calcDistance(View v) {
        String locationName = cityEditText.getText() + ", " + stateEditText.getText();
        Geocoder geo = new Geocoder(this);
        List<Address> addresses = null;
        try {
            addresses = geo.getFromLocationName(locationName, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (addresses != null) {
            Address address = addresses.get(0);
            double lat = address.getLatitude();
            double lon = address.getLongitude();
            latLonTextView.setText(String.format("%s, %s", Double.toString(lat), Double.toString(lon)));
            if (myLocation != null) {
                Location destinationLocation = new Location("Destination");
                destinationLocation.setLatitude(lat);
                destinationLocation.setLongitude(lon);
                float distance = myLocation.distanceTo(destinationLocation);
                // distance is in meters, will convert to km
                distanceTextView.setText(Float.toString(distance / 1000.0F));                }
            else {
                distanceTextView.setText("Can't determine your location");
            }

        } else {
            distanceTextView.setText("Destination city not found");
        }
    }

}
