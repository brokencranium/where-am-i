package com.vv.buildstuff.whereami;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;


public class WhereAmI extends Activity {

    //        Location listener to fire updateWithNewLocation method
    private final LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            updateWithNewLocation(location);
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_where_am_i);

        String context = Context.LOCATION_SERVICE;
        LocationManager locationManager = (LocationManager) getSystemService(context);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(true);
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        String provider = locationManager.getBestProvider(criteria,true);
        Location location = locationManager.getLastKnownLocation(provider);

//        Simulate GPS
        updateWithNewLocation(location, provider);
        locationManager.requestLocationUpdates(provider,2000,10,locationListener);

    }


    private void updateWithNewLocation(Location location ) {
        String latLongString;
        TextView myLocationText;

        myLocationText = (TextView) findViewById(R.id.myLocationText);

        if (location != null) {
            double lat = location.getLatitude();
            double lng = location.getLongitude();
            latLongString = "Lat: " + lat + "\nLong: " + lng;
        } else {
            latLongString = "No location found";
        }
        myLocationText.setText("Your current position is: \n" + latLongString + " \nProvider:");

    }

    private void updateWithNewLocation(Location location, String provider ) {
        String latLongString;
        TextView myLocationText;

        myLocationText = (TextView) findViewById(R.id.myLocationText);

        if (location != null) {
            double lat = location.getLatitude();
            double lng = location.getLongitude();
            latLongString = "Lat: " + lat + "\nLong: " + lng;
        } else {
            latLongString = "No location found";
        }
        myLocationText.setText("Your current position is: \n" + latLongString + " \nProvider:" + provider);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.where_am_i, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
