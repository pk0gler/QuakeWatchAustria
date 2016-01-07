package com.quakewatch.ekos.quakewatchaustria.SubACtivities;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.quakewatch.ekos.quakewatchaustria.Custom_Adapter_Listener.ContactAdapter;
import com.quakewatch.ekos.quakewatchaustria.Custom_Adapter_Listener.ContactInfo;
import com.quakewatch.ekos.quakewatchaustria.R;

import java.util.ArrayList;
import java.util.List;

public class SubActivity_News extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_activity__news);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        ArrayList<ContactInfo> temp = new ArrayList<>();
        this.createContent(temp);
        mAdapter = new ContactAdapter(temp);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void createContent(ArrayList<ContactInfo> temp) {
        temp.add(new ContactInfo(R.drawable.canada, "Canada"));
        temp.add(new ContactInfo(R.drawable.dubai, "Dubai"));
        temp.add(new ContactInfo(R.drawable.hongkong, "Hongkong"));
        temp.add(new ContactInfo(R.drawable.iceland, "Iceland"));
        temp.add(new ContactInfo(R.drawable.canada, "Canada"));
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
