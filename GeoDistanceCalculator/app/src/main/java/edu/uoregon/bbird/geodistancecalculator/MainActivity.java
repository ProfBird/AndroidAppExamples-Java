package edu.uoregon.bbird.geodistancecalculator;

// By Brian Bird, July 15, 2016, updated July 19, 2017
// Demonstrates: 1) getting location using the Fused Location API provided by Google Play Services
// 2) using the Android Geocoder to get an address from latitude and longitude
// 3) using the Android Location class to determine distance between two locaitons

// Android classes

import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

// Google Play Services classes and interfaces
// Java classes


public class MainActivity extends AppCompatActivity
        implements ConnectionCallbacks, OnConnectionFailedListener, LocationListener {

    GoogleApiClient googleApiClient;
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
        currentLocationTextView = (TextView)findViewById(R.id.currentLocationTextView);
        distanceTextView = (TextView)findViewById(R.id.distanceTextView);
        latLonTextView = (TextView)findViewById(R.id.latLonTextView);
        cityEditText = (EditText)findViewById(R.id.cityEditText);
        stateEditText = (EditText)findViewById(R.id.stateEditText);

        // Get the Location API and register the callbacks that it uses
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        // This object will be used when we request location updates in onConnected
        locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setNumUpdates(1)
                .setExpirationDuration(60000);
        // I wanted to set PRIORITY_LOW_POWER, but the emulator doesn't
        // give me a way to test location without GPS, so I'm using
        // PRIORITY_HIGH_ACCURCY for now.
        // NumUpdate is one, because I just need to get the location once
    }


    @Override
    public void onStart() {
        super.onStart();
        googleApiClient.connect();
    }


    @Override
    public void onStop() {
        googleApiClient.disconnect();
        super.onStop();
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        final int MY_PERMISSION_ACCESS_COURSE_LOCATION = 42;  // This is an arbitrary value
        // This check is required by Google Play Services APIs beginning with version 9.0.0 (I think)
        if( ContextCompat.checkSelfPermission( this,
                android.Manifest.permission.ACCESS_FINE_LOCATION )
                != PackageManager.PERMISSION_GRANTED )
        {
            // In API 23 and later users must give permission after the activity runs
            ActivityCompat.requestPermissions( this,
                    new String[] {  android.Manifest.permission.ACCESS_FINE_LOCATION  },
                    MY_PERMISSION_ACCESS_COURSE_LOCATION );
        }
        else
        {
            // This is where we request the locaiton
            LocationServices.FusedLocationApi.requestLocationUpdates(
                    googleApiClient, locationRequest, this);
        }
    }


    @Override
    public void onConnectionSuspended(int i) {
        currentLocationTextView.setText("Connection failed. Code: " + i);
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        currentLocationTextView.setText("Connection failed. Error code: " +
                connectionResult.getErrorCode());
    }


    @Override
    public void onLocationChanged(Location location) {
        // Here it is! This is where we get the current location
        myLocation = location;
        Geocoder geo = new Geocoder(this,
                Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = geo.getFromLocation(
                    myLocation.getLatitude(),
                    myLocation.getLongitude(), 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        currentLocationTextView.setText(addresses.get(0).getLocality());
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
            latLonTextView.setText(Double.toString(lat) + ", " + Double.toString(lon));
           if( ContextCompat.checkSelfPermission( this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION )
                    != PackageManager.PERMISSION_GRANTED ) {
                myLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
            }
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
