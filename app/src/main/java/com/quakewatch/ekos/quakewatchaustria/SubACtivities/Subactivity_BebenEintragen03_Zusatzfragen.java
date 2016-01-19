package com.quakewatch.ekos.quakewatchaustria.SubACtivities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.quakewatch.ekos.quakewatchaustria.MainActivity;
import com.quakewatch.ekos.quakewatchaustria.R;
import com.quakewatch.ekos.quakewatchaustria.Json.FinalJson;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Subactivity_BebenEintragen03_Zusatzfragen extends AppCompatActivity {
    private TextView t1;
    private TextView t2;
    private TextView t3;
    private TextView t4;
    private TextView t5;
    private CardView card_view4;

    /**
     *Weitere Fragen, welche beschreiben wie die Beben waren
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent().getIntExtra("position", 1) > 2) {
            setContentView(R.layout.activity_subactivity__zusatz_fragen_v2);
            t1 = (TextView) findViewById(R.id.text);
            t2 = (TextView) findViewById(R.id.text2);
            t3 = (TextView) findViewById(R.id.text3);
            t4 = (TextView) findViewById(R.id.text4);
            t5 = (TextView) findViewById(R.id.text5);
            card_view4 = (CardView) findViewById(R.id.card_view4);
        } else {
            setContentView(R.layout.activity_subactivity__zusatz_fragen_std);
        }
        findViewById(R.id.com).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.setFocusable(true);
                v.setFocusableInTouchMode(true);
                return false;
            }
        });

        final Spinner spinner1 = (Spinner) findViewById(R.id.spinnerStock);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getBaseContext(),
                R.array.stock, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        TextView title5;
        TextView title6;

        Switch sw0 = (Switch) findViewById(R.id.mySwitch0);
        int temp = getIntent().getIntExtra("position", 1);
        switch (getIntent().getIntExtra("position", 1)) {
            case 3: //Stark verspürt
                break;
            case 4: //Leichte Gebäudeschäden
                t2.setText("Sind Sie aus Angst ins Freie geflüchtet ?");
                t3.setText("Haben Sie Gebäudeschäden beobachtet ?\nRisse im Verputz, Abfallen von Verputzteilen, Mauerrisse, Beschädigung des Rauchfanges, Herabfallen des Rauchfanges, Herabfallen von Dachziegeln, Einsturz von Zwischenwänden");
                card_view4.setVisibility(View.GONE);
                title5 = (TextView) findViewById(R.id.title5);
                title5.setText("Frage 4");
                title6 = (TextView) findViewById(R.id.title6);
                title6.setText("Frage 5");
                break;
            case 5: //Gebäudeschäden
                t1.setText("Hatten Sie Gewichtsprobleme ?");
                spinner1.setVisibility(View.GONE);
                sw0.setVisibility(View.VISIBLE);
                t2.setText("Sind viele Gegenstände aus den Regalen gefallen ?");
                t3.setText("- Haben Sie Gebäudeschäden beobachtet ?\nRisse im Verputz, Abfallen von Verputzteilen, Mauerrisse, Beschädigung des Rauchfanges, Herabfallen des Rauchfanges, Herabfallen von Dachziegeln, Einsturz von Zwischenwänden");
                card_view4.setVisibility(View.GONE);
                title5 = (TextView) findViewById(R.id.title5);
                title5.setText("Frage 4");
                title6 = (TextView) findViewById(R.id.title6);
                title6.setText("Frage 5");
                break;
            case 6: //Schwere Gebäudeschäden
                t1.setText("Sind Möbel umgekippt ?");
                t2.setText("- Haben Sie Gebäudeschäden beobachtet ?\nRisse im Verputz, Abfallen von Verputzteilen, Mauerrisse, Beschädigung des Rauchfanges, Herabfallen des Rauchfanges, Herabfallen von Dachziegeln, Einsturz von Zwischenwänden");
                findViewById(R.id.card_view3).setVisibility(View.GONE);
                card_view4.setVisibility(View.GONE);
                title5 = (TextView) findViewById(R.id.title5);
                title5.setText("Frage 3");
                title6 = (TextView) findViewById(R.id.title6);
                title6.setText("Frage 4");
        }
        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText com;
                switch (getIntent().getIntExtra("position", 1)) {
                    case 1:
                        FinalJson.stockwerk = spinner1.getSelectedItem().toString();
                        com = (EditText) findViewById(R.id.com);
                        FinalJson.kommentar = com.getText().toString();
                        break;
                    case 2:
                        FinalJson.stockwerk = spinner1.getSelectedItem().toString();
                        com = (EditText) findViewById(R.id.com);
                        FinalJson.kommentar = com.getText().toString();
                        break;
                    case 3:
                        FinalJson.stockwerk = spinner1.getSelectedItem().toString();
                        com = (EditText) findViewById(R.id.com);
                        FinalJson.kommentar = com.getText().toString();
                        break;
                    case 4:
                        FinalJson.stockwerk = spinner1.getSelectedItem().toString();
                        com = (EditText) findViewById(R.id.com);
                        FinalJson.kommentar = com.getText().toString();
                        break;
                    case 5:
                        com = (EditText) findViewById(R.id.com);
                        FinalJson.kommentar = com.getText().toString();
                        break;
                    case 6:
                        com = (EditText) findViewById(R.id.com);
                        FinalJson.kommentar = com.getText().toString();
                        break;
                }
                FinalJson.context = getBaseContext();
                FinalJson.toJson();
                new SendfeedbackJob().execute();
                Intent i = new Intent(getBaseContext(), MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
    }

    /**
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_subactivity__zusatz_fragen, menu);
        return true;
    }

    /**
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public class SendfeedbackJob extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String[] params) {
            // do above Server call here
            makePostRequest();
            return null;
        }

        @Override
        protected void onPostExecute(String message) {
            //process message
        }
    }

    private void makePostRequest() {

        /*
        HttpClient httpClient = new DefaultHttpClient();
        // replace with your url
        HttpPost httpPost = new HttpPost("http://geoweb.zamg.ac.at/quakeapi/v01/message");

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);

        //Encoding POST data
        //httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Authorization", "Basic cXVha2VhcGk6I3FrcCZtbGRuZyM=");
        httpPost.setHeader("X-QuakeAPIKey", prefs.getString("apikey", ""));

        //making POST request.
        try {
            HttpResponse response = httpClient.execute(httpPost);
        } catch (ClientProtocolException e) {
            // Log exception
            e.printStackTrace();
        } catch (Exception e) {
            // Log exception
            e.printStackTrace();
        }*/
        URL myURL = null;
        try {
            JSONObject Erdbeben = new JSONObject();
            Erdbeben.put("referenzID","12345abc");
            Erdbeben.put("mlocPLZ:","1200");
            Erdbeben.put("mlocOrtsname:", "Wien");
            Erdbeben.put("stockwerk:", "1");
            Erdbeben.put("klassifikation:", "3");
            Erdbeben.put("verspuert:","2016-01-14T11:54:07+01:00");
            Erdbeben.put("kommentar:", "testkommentar");
            Erdbeben.put("kontakt:","harald.bamberger@zamg.ac.at" );

            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
            myURL = new URL("http://geoweb.zamg.ac.at/quakeapi/v01/message");
            HttpURLConnection myURLConnection = (HttpURLConnection) myURL.openConnection();
            myURLConnection.setRequestMethod("POST");
            myURLConnection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            myURLConnection.setRequestProperty("Authorization", "Basic cXVha2VhcGk6I3FrcCZtbGRuZyM=");
            myURLConnection.setRequestProperty("X-QuakeAPIKey", prefs.getString("apikey", ""));
            myURLConnection.setDoOutput(true);
            myURLConnection.setDoInput(true);
            myURLConnection.getOutputStream().write(Erdbeben.toString().getBytes("utf-8"));

            myURLConnection.connect();

            String a = myURLConnection.getResponseCode()+"|"+myURLConnection.getResponseMessage();
            System.out.print("");

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    myURLConnection.getErrorStream()));
            String inputLine;
            String temp="";
            while ((inputLine = in.readLine()) != null)
                temp += inputLine;
            in.close();
            String ups = temp;
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
