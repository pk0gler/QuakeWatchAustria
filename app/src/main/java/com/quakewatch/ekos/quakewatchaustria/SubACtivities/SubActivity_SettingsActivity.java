package com.quakewatch.ekos.quakewatchaustria.SubACtivities;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.quakewatch.ekos.quakewatchaustria.R;

public class SubActivity_SettingsActivity extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
	}

}