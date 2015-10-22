package com.quakewatch.ekos.quakewatchaustria.Tablayout_Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quakewatch.ekos.quakewatchaustria.R;

/**
 * Created by pkogler on 22.10.2015.
 */
public class FRAGMENT_WELT extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.tab_1,container,false);
        return v;
    }
}
