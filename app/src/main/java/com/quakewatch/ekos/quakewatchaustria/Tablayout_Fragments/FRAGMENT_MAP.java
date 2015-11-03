package com.quakewatch.ekos.quakewatchaustria.Tablayout_Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quakewatch.ekos.quakewatchaustria.R;

import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;

import java.util.Map;

/**
 * Created by pkogler on 22.10.2015.
 */
public class FRAGMENT_MAP extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.maplayout,container,false);
        //asd
        MapView mapView = (MapView) v.findViewById(R.id.mapview);
        MapController mc = (MapController) mapView.getController();
        mapView.setTileSource(TileSourceFactory.MAPQUESTAERIAL);
        mapView.setMultiTouchControls(true);

        GeoPoint point = new GeoPoint(48.2083537, 16.3725042);
        mc.setCenter(point);
        mc.setZoom(12);

        mapView.setMinZoomLevel(4);
        mapView.setMaxZoomLevel(19);

        return v;
    }
}
