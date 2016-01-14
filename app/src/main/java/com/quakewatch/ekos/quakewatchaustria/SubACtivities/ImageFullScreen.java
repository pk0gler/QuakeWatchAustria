package com.quakewatch.ekos.quakewatchaustria.SubACtivities;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.quakewatch.ekos.quakewatchaustria.Custom_Adapter_Listener.CustomIntensity;
import com.quakewatch.ekos.quakewatchaustria.R;

public class ImageFullScreen extends AppCompatActivity {

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_full_screen);
        int position = (int) getIntent().getExtras().get("pos");
        ImageView img = (ImageView) findViewById(R.id.img);
        int imageRes = getBaseContext().getResources().getIdentifier(CustomIntensity.bilder[position], null, getBaseContext().getPackageName());
        Drawable res = getBaseContext().getResources().getDrawable(imageRes);
        img.setImageDrawable(res);

    }

    /**
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_image_full_screen, menu);
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
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }
}
