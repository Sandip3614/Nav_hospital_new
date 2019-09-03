package sk.nav_hospital;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;


class GetNearbyPlacesData extends AsyncTask<Object, String, Cursor> {

    private GoogleMap mMap;
    private Algorithm algorithm;
    private double latitude, longitude;
    private String search, search_txt;
    MapsActivity context;
    private Database db;

    public GetNearbyPlacesData(MapsActivity context) {
        this.context = context;
        this.db = new Database(context);
        this.algorithm = new Algorithm();

    }

    @Override
    protected Cursor doInBackground(Object... objects) {
        mMap = (GoogleMap) objects[0];
        latitude = (double) objects[1];
        longitude = (double) objects[2];
        search = (String) objects[3];
        Cursor near;
        if(search.equals("nearby")){
             near = db.getAllData();
        }
        else {
            search_txt = (String) objects[4];
            near = db.getSearch(search_txt);
        }

        return near;
    }

    @Override
    protected void onPostExecute(Cursor s) {
        showNearbyPlaces(s);
    }

    private void showNearbyPlaces(Cursor nearbyPlaceList) {
        while (nearbyPlaceList.moveToNext()) {
            MarkerOptions markerOptions = new MarkerOptions();
            final String placeName = nearbyPlaceList.getString(0);
            String vicinity = nearbyPlaceList.getString(1);
            double lat = nearbyPlaceList.getDouble(5);
            double lng = nearbyPlaceList.getDouble(6);

            double distance = algorithm.Haversine(latitude, longitude, lat, lng);

            if((int)distance < algorithm.NEAR_BY_DISTANCE) {

                LatLng latLng = new LatLng(lat, lng);
                markerOptions.position(latLng);
                markerOptions.title(placeName + ":" + vicinity);
                markerOptions.snippet("Distance: "+new DecimalFormat("##.##").format(distance/1000)+" km");
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                mMap.addMarker(markerOptions);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(12.0f));
                mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick(Marker marker) {
                        String marker_place = marker.getTitle().substring(0, marker.getTitle().indexOf(':'));
                        context.sendinfo(marker_place);
                    }
                });
            }
        }
    }




}


