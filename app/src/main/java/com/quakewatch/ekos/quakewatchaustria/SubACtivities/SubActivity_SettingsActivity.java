package com.quakewatch.ekos.quakewatchaustria.SubACtivities;

import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.util.Log;

import com.quakewatch.ekos.quakewatchaustria.R;

public class SubActivity_SettingsActivity extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
		//super.findPreference("email").setOnPreferenceChangeListener(new PrefListener(SubActivity_SettingsActivity.this));
		//super.findPreference("notifications").setOnPreferenceChangeListener(new PrefListener(SubActivity_SettingsActivity.this));
		findPreference("email").setOnPreferenceChangeListener(
				new Preference.OnPreferenceChangeListener() {

					@Override
					public boolean onPreferenceChange(Preference preference, Object newValue) {
						EditTextPreference prefText = (EditTextPreference) preference;
						Log.d("isRichtigDiemal", prefText.getText()+"----"+isValidEmail((String)newValue));
						return isValidEmail((String)newValue);
					}

				});
	}

	public final static boolean isValidEmail(CharSequence target) {
		if (target == null) {
			return false;
		} else {
			return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
		}
	}

}