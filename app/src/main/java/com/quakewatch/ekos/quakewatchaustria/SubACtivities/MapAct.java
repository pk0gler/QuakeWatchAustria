package com.quakewatch.ekos.quakewatchaustria.SubACtivities;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.quakewatch.ekos.quakewatchaustria.R;

public class MapAct extends Activity {
    static final LatLng TutorialsPoint = new LatLng(21, 57);
    private GoogleMap googleMap;

    /**
     * die Map wird erstellt und in das Fragment eingebunden
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapact);

        try {
            if (googleMap == null) {
                googleMap = ((MapFragment) getFragmentManager().
                        findFragmentById(R.id.map)).getMap();
            }
            googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            Marker TP = googleMap.addMarker(new MarkerOptions().
                    position(TutorialsPoint).title("TutorialsPoint"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}