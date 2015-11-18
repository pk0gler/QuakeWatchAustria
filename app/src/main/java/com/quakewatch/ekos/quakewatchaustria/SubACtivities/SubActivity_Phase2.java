package com.quakewatch.ekos.quakewatchaustria.SubACtivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.quakewatch.ekos.quakewatchaustria.Custom_Adapter_Listener.CustomIntensity;
import com.quakewatch.ekos.quakewatchaustria.R;

/**
 * Created by philippkogler on 05.11.15.
 */
public class SubActivity_Phase2 extends AppCompatActivity {
    /**
     * OnCreate
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //finish();
        super.onCreate(savedInstanceState);
       setContentView(R.layout.intensity_layout);

        String[] text = {"Schwach verspürt: \n\n" +
                "Leichtes Zittern oder Rütteln.Lampen/hängende Objekte pendeln schwach. \n\n" +
                "„Sie haben das Erdbeben schwach verspürt.“",
                "Deutlich verspürt:\n\n" +
                        "Deutliches Rütteln von Möbeln (z. B. Sessel, Bett), Türen und Fensterläden. Gläser und Geschirr klirren.Wenige Schlafende erwachen. \n\n" +
                        "„Sie haben das Erdbeben deutlich verspürt.“",
                "Stark verspürt:\n\n" +
                        "Erschreckend. Viele Schlafende erwachen.Starkes Rütteln oder Schaukeln des gesamten Gebäudes. Geschirr und Gläser klirren laut. Lampen pendeln stark. Kleine Objekte werden verschoben oder fallen um.Vereinzelt sind feine Risse im Verputz möglich.\n\n" +
                        "„Sie haben das Erdbeben stark verspürt.“",
                "Leichte Gebäudeschäden:\n\n" +
                        "Viele erschrecken, einige flüchten aus Angst ins Freie. Möbel und Bilderwerden verschoben. Gegenstände fallen um. Möglich sind Risse in Wänden, Abfallen von Verputzteilen und leichte Beschädigung von Rauchfängen.\n\n" +
                        "„Sie haben das Erdbeben stark verspürt. Leichte Gebäudeschäden sind möglich.“",
                "Gebäudeschäden:\n\n" +
                        "Die meisten sind verängstigt, viele flüchten ins Freie. Gleichgewichtsprobleme. Viele Gegenstände fallen aus den Regalen. Tiefe Mauerrisse.Teilweiser Einsturz von Rauchfängen. In älteren Gebäuden können Zwischenwände einstürzen.\n\n" +
                        "„Sie haben das Erdbeben sehr stark verspürt. Es gibt beträchtliche Gebäudeschäden.“",
                "Schwere Gebäudeschäden:\n\n" +
                        "Panik. Verlieren des Gleichgewichts. Schwere Gegenstände fallen zu Boden. Umfallen von Möbelstücken möglich. Schwere Mauerschäden.Strukturelle Schäden an Gebäuden. Ältere Bauwerkestürzen ein.\n\n" +
                        "„Sie haben das Erdbeben sehr stark verspürt. Es gibt schwere Gebäudeschäden.“"};

        ListAdapter test = new CustomIntensity(this, text);
        ListView list = (ListView) findViewById(R.id.list);
        list.setAdapter(test);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), "Hi", Toast.LENGTH_SHORT).show();
                Intent detail = new Intent(view.getContext(),BebenInfo.class);
                startActivity(detail);
            }
        });
    }
}
