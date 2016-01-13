package com.quakewatch.ekos.quakewatchaustria.SubACtivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.quakewatch.ekos.quakewatchaustria.R;

public class Subactivity_ZusatzFragen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int a = getIntent().getIntExtra("position",1);
        if (getIntent().getIntExtra("position", 1) > 2) {
            setContentView(R.layout.activity_subactivity__zusatz_fragen_v2);
        } else {
            setContentView(R.layout.activity_subactivity__zusatz_fragen_std);
        }
        final Spinner spinner1 = (Spinner) findViewById(R.id.spinnerStock);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getBaseContext(),
                R.array.stock, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_subactivity__zusatz_fragen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
