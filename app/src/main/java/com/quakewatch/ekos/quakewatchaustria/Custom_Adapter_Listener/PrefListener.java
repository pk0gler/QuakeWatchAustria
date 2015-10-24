package com.quakewatch.ekos.quakewatchaustria.Custom_Adapter_Listener;

import android.content.Context;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by pkogler on 24.10.2015.
 */
public class PrefListener implements Preference.OnPreferenceChangeListener {

    Context context;

    public PrefListener(Context context) {
        this.context = context;
    }


    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        // Let's do something when my counter preference value changes
        Log.d("Sehr gut","");
        //Toast.makeText(context, "Seas", Toast.LENGTH_SHORT).show();
        if (preference instanceof EditTextPreference) {
            EditTextPreference editTextPreference = (EditTextPreference) preference;
            if (isValidEmail(editTextPreference.getText())) {
                Toast.makeText(context, "Correxct", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Incorrect", Toast.LENGTH_SHORT).show();
            }
        }
        return true;
    }

    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}
