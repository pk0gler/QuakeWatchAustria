package com.quakewatch.ekos.quakewatchaustria.SubACtivities;

import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.quakewatch.ekos.quakewatchaustria.R;

public class SubActivity_SettingsActivity extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
		this.setContentView(R.layout.settings_help);
		ListView v = getListView();
		Button saveButton = (Button) findViewById(R.id.saveButton);
		saveButton.setText("Übernehmen");
		saveButton.setPadding(10, 30, 10, 30);
		saveButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		findPreference("email").setOnPreferenceChangeListener(
				new Preference.OnPreferenceChangeListener() {

					@Override
					public boolean onPreferenceChange(Preference preference, Object newValue) {
						EditTextPreference prefText = (EditTextPreference) preference;
						Log.d("isRichtigDiemal", prefText.getText()+"----"+isValidEmail((String)newValue));
						if (isValidEmail((String)newValue)) {
							return true;
						} else {
							Toast.makeText(getBaseContext(), "Ihre Email ist nicht gültig", Toast.LENGTH_LONG).show();
							return false;
						}
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