package com.quakewatch.ekos.quakewatchaustria.Tablayout_Fragments;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.quakewatch.ekos.quakewatchaustria.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by pkogler on 22.10.2015.
 */
public class FRAGMENT_MAP extends android.support.v4.app.Fragment {
    private LatLng currentClick = new LatLng(0, 0);
    private GoogleMap mGoogleMap;
    Marker mine;
    private Erdbeben[] marker;
    View v;
    private HashMap<String, Erdbeben> markerId;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        markerId = new HashMap<>();
        /**
         * OSM MAP
         * package com.quakewatch.ekos.quakewatchaustria.Tablayout_Fragments;

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
         import org.osmdroid.views.overlay.OverlayItem;

         import java.util.ArrayList;

         /**
         * Created by pkogler on 22.10.2015.
         public class FRAGMENT_MAP extends Fragment {
         ArrayList<OverlayItem> overlayItemArray;

         @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         View v = inflater.inflate(R.layout.maplayout, container, false);
         //asd
         MapView mapView = (MapView) v.findViewById(R.id.mapview);
         MapController mc = (MapController) mapView.getController();
         mapView.setTileSource(TileSourceFactory.MAPQUESTAERIAL);
         mapView.setMultiTouchControls(true);

         GeoPoint point = new GeoPoint(48.2083537, 16.3725042);
         mc.setCenter(point);
         mc.setZoom(6);

         mapView.setMinZoomLevel(4);
         mapView.setMaxZoomLevel(19);

         return v;
         }
         }
         */
        v = inflater.inflate(R.layout.activity_mapact, container, false);
        mGoogleMap = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map)).getMap();

        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        CameraUpdate center =
                CameraUpdateFactory.newLatLng(new LatLng(-19.19, -69.96));
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(1);

        mGoogleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Toast.makeText(getContext(), "Hi", Toast.LENGTH_LONG).show();
                Map_Fragment dFragment = new Map_Fragment(markerId.get(marker.getId()));
                // Show DialogFragmen
                dFragment.show(getFragmentManager(), "Dialog Fragment");
            }
        });


        mGoogleMap.moveCamera(center);
        mGoogleMap.animateCamera(zoom);
        new AsyncTaskParseJson().execute();
        return v;
    }

    @Override
    public void setMenuVisibility(final boolean visible) {
        super.setMenuVisibility(visible);
        if (!visible) {
            //mine.remove();
        }
    }

    public void setCurrentLoc(Erdbeben temp) {
        float tempf = 1;
        double mag = temp.getMag();
        if ((mag >= 1) && (mag <= 2.4)) {
            tempf = 0.4f;
        } else if ((mag >= 2.5) && (mag <= 3.9)) {
            tempf = 0.5f;
        } else if ((mag >= 4) && (mag <= 5.4)) {
            tempf = 0.6f;
        } else if ((mag >= 5.5) && (mag <= 6.9)) {
            tempf = 0.7f;
        } else if ((mag >= 7) && (mag <= 8.9)) {
            tempf = 0.9f;
        }
        Color.RGBToHSV(183, 28, 28, new float[3]);
        //Log.d("Hue", );
        mine = mGoogleMap.addMarker(new MarkerOptions()
                .position(new LatLng(temp.lat, temp.lon))
                .title(temp.getRegion())
                .icon(BitmapDescriptorFactory.defaultMarker(getHue(mag)))
                .snippet("Mag: " + String.valueOf(temp.getMag())));
        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mine.getPosition(), 6));
        mine.showInfoWindow();
        this.markerId.put(mine.getId(), temp);
    }

    public void setMarker(Erdbeben[] marker) {
        this.marker = marker;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_frag_map, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh:
                Toast.makeText(getContext(), "hi", Toast.LENGTH_LONG).show();
                new AsyncTaskParseJson().execute();
                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public class AsyncTaskParseJson extends AsyncTask<String, String, String> {
        ProgressDialog mDialog;

        final String TAG = "AsyncTaskParseJson.java";

        // contacts JSONArray
        JSONArray dataJsonArr = null;
        private Erdbeben[] values;

        @Override
        protected void onPreExecute() {
            mDialog = new ProgressDialog(getContext());
            mDialog.setMessage("Beben werden geladen...");
            mDialog.setCancelable(false);
            mDialog.setCanceledOnTouchOutside(false);
            mDialog.show();
        }

        @Override
        protected String doInBackground(String... arg0) {
            values = new Erdbeben[30];
            try {
                // instantiate our json parser
                JsonParser jParser = new JsonParser();

                // get the array of users
                JSONObject json = JsonParser.readJsonFromUrl("http://geoweb.zamg.ac.at/fdsnws/app/1/query?location=Welt&limit=30");
                dataJsonArr = json.getJSONArray("features");

                // loop through all users
                for (int i = 0; i < dataJsonArr.length(); i++) {

                    JSONObject c = dataJsonArr.getJSONObject(i);
                    JSONObject b = c.getJSONObject("properties");

                    //Json Parsing Methode
                    //Server umstellungen dynamisch

                    // Storing each json item in variable
                    Double mag = Double.parseDouble(b.getString("mag"));
                    String flynn_region = b.getString("region");
                    String time = b.getString("time");
                    Double lat = b.getDouble("lat");
                    Double lon = b.getDouble("lon");
                    double depth = Double.parseDouble(b.getString("depth"));
                    //String username = c.getString("magtype");
                    int id = c.getInt("id");
                    JSONArray places = b.getJSONArray("places");

                    //String username = c.getString("magtype");


                    values[i] = new Erdbeben(mag, flynn_region, time, depth, lat, lon, places, id);
                }
                //JSONObject ob = json.getJSONObject("properties");
                //values.add(0,ob.getString("magType"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            //values.add(0,"hi");
            return null;
        }


        @Override
        protected void onPostExecute(String strFromDoInBg) {
            for (int i = 0; i < values.length; i++) {
                mDialog.dismiss();
                float tempf = 1;
                double mag = values[i].getMag();
                if ((mag >= 1) && (mag <= 2.4)) {
                    tempf = 0.4f;
                } else if ((mag >= 2.5) && (mag <= 3.9)) {
                    tempf = 0.5f;
                } else if ((mag >= 4) && (mag <= 5.4)) {
                    tempf = 0.6f;
                } else if ((mag >= 5.5) && (mag <= 6.9)) {
                    tempf = 0.7f;
                } else if ((mag >= 7) && (mag <= 8.9)) {
                    tempf = 0.9f;
                }
                Marker temp = mGoogleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(values[i].lat, values[i].lon))
                        .title(values[i].getRegion())
                        .icon(BitmapDescriptorFactory.defaultMarker(getHue(values[i].getMag())))
                        .snippet("Mag: " + values[i].getMag()));
                markerId.put(temp.getId(), values[i]);
            }
        }
    }

    public float getHue(double mag) {
        float erg = 0;
        if ((mag >= 1.0) && (mag <= 1.49)) {
            erg = 123;
        } else if ((mag >= 1.50) && (mag <= 1.99)) {
            erg = 80;
        } else if ((mag >= 2.0) && (mag <= 2.49)) {
            erg = 80;
        } else if ((mag >= 2.50) && (mag <= 2.99)) {
            erg = 80;
        }
        if ((mag >= 3.0) && (mag <= 3.49)) {
            erg = 54;
        }
        if ((mag >= 3.50) && (mag <= 3.99)) {
            erg = 49;
        } else if ((mag >= 4.0) && (mag <= 4.49))

        {
            erg = 36;
        }

        if ((mag >= 4.50) && (mag <= 4.99))

        {
            erg = 36;
        }

        if ((mag >= 5.0) && (mag <= 5.49))

        {
            erg = 33;
        } else if ((mag >= 5.50) && (mag <= 5.99)) {
            erg = 231;
        }
        if ((mag >= 6.0) && (mag <= 6.49)) {
            erg = 231;
        }
        if ((mag >= 6.50) && (mag <= 6.99)) {
           erg = 231;
        } else if ((mag >= 7.0) && (mag <= 7.99)) {
            erg = 262;
        }
        if ((mag >= 8.0) && (mag <= 8.99)) {
            erg = 260;
        } else if ((mag >= 9.0) && (mag <= 12)) {
            erg =0;
        }
        return erg;
    }

}
