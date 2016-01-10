package com.quakewatch.ekos.quakewatchaustria.Tablayout_Fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.quakewatch.ekos.quakewatchaustria.R;

/**
 * Created by pkogler on 10.01.2016.
 */
public class FilterFragment extends DialogFragment {
    /**@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.dialogfragmentfilter, container,
                false);

        //getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        Spinner spinner = (Spinner) rootView.findViewById(R.id.spinnerMagnitud);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.magAa, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        return rootView;
    }**/
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialogfragmentfilter, null);
        Spinner spinner = (Spinner) view.findViewById(R.id.spinnerMagnitud);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.magAa, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Spinner spinner2 = (Spinner) view.findViewById(R.id.spinnerDate);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(),
                R.array.date, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        Spinner spinner3 = (Spinner) view.findViewById(R.id.spinnerDistance);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(getActivity(),
                R.array.distance, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);


        builder.setView(view);
        builder.setPositiveButton("OK", null);
        builder.setNegativeButton("Abbrechen",null);
        return builder.create();
    }
}
