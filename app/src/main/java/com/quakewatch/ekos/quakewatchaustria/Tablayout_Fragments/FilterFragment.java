package com.quakewatch.ekos.quakewatchaustria.Tablayout_Fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.quakewatch.ekos.quakewatchaustria.Interfaces.onSpinnerClick;
import com.quakewatch.ekos.quakewatchaustria.R;

/**
 * Created by pkogler on 10.01.2016.
 */
public class FilterFragment extends DialogFragment {
    /**
     * @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
     * Bundle savedInstanceState) {
     * super.onCreate(savedInstanceState);
     * View rootView = inflater.inflate(R.layout.dialogfragmentfilter, container,
     * false);
     * <p/>
     * //getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
     * <p/>
     * Spinner spinner = (Spinner) rootView.findViewById(R.id.spinnerMagnitud);
     * ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
     * R.array.magAa, android.R.layout.simple_spinner_item);
     * adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
     * spinner.setAdapter(adapter);
     * <p/>
     * return rootView;
     * }
     **/
    private onSpinnerClick saveSpinner;
    private String[] spinnerValues;

    public FilterFragment(onSpinnerClick saveSpinner, String[] spinnerValues) {
        this.saveSpinner = saveSpinner;
        this.spinnerValues = spinnerValues;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialogfragmentfilter, null);
        final Spinner spinner = (Spinner) view.findViewById(R.id.spinnerMagnitud);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.magAa, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        final Spinner spinner2 = (Spinner) view.findViewById(R.id.spinnerDate);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(),
                R.array.date, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        final Spinner spinner4 = (Spinner) view.findViewById(R.id.spinnerLimit);
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(getActivity(),
                R.array.limit, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapter4);

        final Spinner spinner5 = (Spinner) view.findViewById(R.id.spinnerSort);
        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(getActivity(),
                R.array.sort, android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner5.setAdapter(adapter5);


        spinner.post(new Runnable() {
            @Override
            public void run() {
                spinner.setSelection(getRightMag());
            }
        });
        /*spinner2.post(new Runnable() {
            @Override
            public void run() {
                spinner2.setSelection(getRightDate());
            }
        });*/
        spinner4.post(new Runnable() {
            @Override
            public void run() {
                spinner4.setSelection(getRightLim());
            }
        });

        spinner4.post(new Runnable() {
            @Override
            public void run() {
                spinner5.setSelection(getRightSort());
            }
        });


        builder.setView(view);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String[] data = new String[4];
                data[0] = spinner.getSelectedItem().toString();
                data[1] = spinner2.getSelectedItem().toString();
                data[2] = spinner4.getSelectedItem().toString();
                data[3] = spinner5.getSelectedItem().toString();
                saveSpinner.saveSpinnerData(data);
            }
        });
        builder.setNegativeButton("Abbrechen", null);
        return builder.create();
    }

    private int getRightSort() {
        if (this.spinnerValues[3].equals("nach Datum")) return 1;
        return 0;
    }

    private int getRightLim() {
        switch (this.spinnerValues[2]) {
            case "10":
                return 0;
            case "20":
                return 1;
            case "50":
                return 2;
            case "100":
                return 3;
            default:
                return 0;
        }
    }

    private int getRightMag() {
        switch (this.spinnerValues[0]) {
            case "0.0+":
                return 0;
            case "1.0+":
                return 1;
            case "2.0+":
                return 2;
            case "3.0+":
                return 3;
            case "4.0+":
                return 4;
            case "5.0+":
                return 5;
            case "6.0+":
                return 6;
            case "7.0+":
                return 7;
            case "8.0+":
                return 8;
            case "9.0+":
                return 9;
            default:
                return 0;
        }
    }
}
