package com.quakewatch.ekos.quakewatchaustria.Tablayout_Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quakewatch.ekos.quakewatchaustria.R;

import org.osmdroid.views.MapView;

/**
 * Created by pkogler on 22.10.2015.
 */
public class FRAGMENT_MAP extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.maplayout,container,false);
        MapView mapView = (MapView) v.findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);
        mapView.setMultiTouchControls(true);
        mapView.setMaxZoomLevel(10);
        return v;
    }
}
