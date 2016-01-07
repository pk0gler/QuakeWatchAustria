package com.quakewatch.ekos.quakewatchaustria.Tablayout_Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.quakewatch.ekos.quakewatchaustria.R;

/**
 * Created by pkogler on 22.10.2015.
 */
public class FRAGMENT_MAP extends android.support.v4.app.Fragment {
private GoogleMap googleMap;
    static final LatLng TutorialsPoint = new LatLng(21 , 57);
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_mapact, container, false);
        GoogleMap mGoogleMap = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map)).getMap();
        mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(37.78,-121.97)).title("Okis Marker"));
        mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(37.78,-121.97 )).title("SAN FRANCISCO BAY AREA"));
        mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(37.25,-98.0 )).title("KANSAS"));
        mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(-19.19, -69.96 )).title("TARAPACA, CHILE"));
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mGoogleMap.setMyLocationEnabled(true);
        CameraUpdate center=
                CameraUpdateFactory.newLatLng(new LatLng(-19.19, -69.96));
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(1);

        mGoogleMap.moveCamera(center);
        mGoogleMap.animateCamera(zoom);
        return v;
    }
}
