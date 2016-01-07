package com.quakewatch.ekos.quakewatchaustria.SubACtivities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.quakewatch.ekos.quakewatchaustria.Custom_Adapter_Listener.ContactAdapter;
import com.quakewatch.ekos.quakewatchaustria.Custom_Adapter_Listener.ContactInfo;
import com.quakewatch.ekos.quakewatchaustria.R;

import java.util.ArrayList;

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
