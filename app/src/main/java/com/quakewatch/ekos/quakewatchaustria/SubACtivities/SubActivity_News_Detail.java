package com.quakewatch.ekos.quakewatchaustria.SubACtivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.quakewatch.ekos.quakewatchaustria.Custom_Adapter_Listener.CustomIntensity;
import com.quakewatch.ekos.quakewatchaustria.R;
import com.quakewatch.ekos.quakewatchaustria.Tablayout_Fragments.FinalJson;

public class SubActivity_News_Detail extends AppCompatActivity {

    private final static String[] text = {
            "Leichtes Zittern oder Rütteln.Lampen/hängende Objekte pendeln schwach. \n\n" +
                    "„Sie haben das Erdbeben schwach verspürt.“",
            "Deutliches Rütteln von Möbeln (z. B. Sessel, Bett), Türen und Fensterläden. Gläser und Geschirr klirren.Wenige Schlafende erwachen. \n\n" +
                    "„Sie haben das Erdbeben deutlich verspürt.“",
            "Erschreckend. Viele Schlafende erwachen.Starkes Rütteln oder Schaukeln des gesamten Gebäudes. Geschirr und Gläser klirren laut. Lampen pendeln stark. Kleine Objekte werden verschoben oder fallen um.Vereinzelt sind feine Risse im Verputz möglich.\n\n" +
                    "„Sie haben das Erdbeben stark verspürt.“",

            "Viele erschrecken, einige flüchten aus Angst ins Freie. Möbel und Bilderwerden verschoben. Gegenstände fallen um. Möglich sind Risse in Wänden, Abfallen von Verputzteilen und leichte Beschädigung von Rauchfängen.\n\n" +
                    "„Sie haben das Erdbeben stark verspürt. Leichte Gebäudeschäden sind möglich.“",

            "Die meisten sind verängstigt, viele flüchten ins Freie. Gleichgewichtsprobleme. Viele Gegenstände fallen aus den Regalen. Tiefe Mauerrisse.Teilweiser Einsturz von Rauchfängen. In älteren Gebäuden können Zwischenwände einstürzen.\n\n" +
                    "„Sie haben das Erdbeben sehr stark verspürt. Es gibt beträchtliche Gebäudeschäden.“",

            "Panik. Verlieren des Gleichgewichts. Schwere Gegenstände fallen zu Boden. Umfallen von Möbelstücken möglich. Schwere Mauerschäden.Strukturelle Schäden an Gebäuden. Ältere Bauwerkestürzen ein.\n\n" +
                    "„Sie haben das Erdbeben sehr stark verspürt. Es gibt schwere Gebäudeschäden.“"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final int position = getIntent().getIntExtra("position", 1) + 1;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_news);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //toolbar.setNavigationIcon(R.drawable.ic_menu_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView placeName = (TextView) findViewById(R.id.placeName);
        TextView cartoonText = (TextView) findViewById(R.id.cartoonText);
        cartoonText.setMovementMethod(new ScrollingMovementMethod());

        switch (getIntent().getIntExtra("position", 1)) {
            case 0:
                toolbar.setBackgroundDrawable(getResources().getDrawable(getBaseContext().getResources().getIdentifier(CustomIntensity.bilder[0], null, getBaseContext().getPackageName())));
                cartoonText.setText(text[0]);
                placeName.setText("Schwach verspürt: ");
                break;
            case 1:
                toolbar.setBackgroundDrawable(getResources().getDrawable(getBaseContext().getResources().getIdentifier(CustomIntensity.bilder[1], null, getBaseContext().getPackageName())));
                cartoonText.setText(text[1]);
                placeName.setText("Deutlich verspürt: ");
                break;
            case 2:
                toolbar.setBackgroundDrawable(getResources().getDrawable(getBaseContext().getResources().getIdentifier(CustomIntensity.bilder[2], null, getBaseContext().getPackageName())));
                cartoonText.setText(text[2]);
                placeName.setText("Stark verspürt: ");
                break;
            case 3:
                toolbar.setBackgroundDrawable(getResources().getDrawable(getBaseContext().getResources().getIdentifier(CustomIntensity.bilder[3], null, getBaseContext().getPackageName())));
                cartoonText.setText(text[3]);
                placeName.setText("Leichte Gebäudeschäden: ");
                break;
            case 4:
                toolbar.setBackgroundDrawable(getResources().getDrawable(getBaseContext().getResources().getIdentifier(CustomIntensity.bilder[4], null, getBaseContext().getPackageName())));
                cartoonText.setText(text[4]);
                placeName.setText("Gebäudeschäden: ");
                break;
            case 5:
                toolbar.setBackgroundDrawable(getResources().getDrawable(getBaseContext().getResources().getIdentifier(CustomIntensity.bilder[5], null, getBaseContext().getPackageName())));
                cartoonText.setText(text[5]);
                placeName.setText("Starke Gebäudeschäden: ");
                break;
        }

        Button next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FinalJson.klassifikation = "" + position;
                Intent i = new Intent(getBaseContext(), Subactivity_ZusatzFragen.class);
                i.putExtra("position", position);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sub_activity__app_guide, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
