package com.example.sinanli.timezoneconverter;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private GridView countryItems;
    private ArrayAdapter<String> countryAdapter;
    private String[] countryNames;

    private EditText textH;
    private EditText textM;
    private TextView textC;

    private int hour;
    private int minute;
    private int foreignH;
    private boolean dayBefore;
    private boolean dayAfter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_main, container, false);

        textH = fragmentView.findViewById(R.id.textHour);
        textM = fragmentView.findViewById(R.id.textMinute);
        textC = fragmentView.findViewById(R.id.textConverted);

        textH.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    hour = Integer.parseInt(s.toString());

                    if (hour > 23 || hour < 0) {
                        textH.setText("");
                        hour = 0;
                    }else if (s.length() > 2){
                        textH.setText("");
                        hour = 0;
                    }
                } catch (Exception e){

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        textM.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    minute = Integer.parseInt(s.toString());

                    if (minute > 59 || minute < 0) {
                        textM.setText("");
                        minute = 0;
                    }else if (s.length() > 2){
                        textM.setText("");
                        minute = 0;
                    }
                } catch (Exception e){

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        countryItems = (GridView) fragmentView.findViewById(R.id.countryItems);
        countryAdapter = new ArrayAdapter<String>(getContext(), R.layout.country_item);
        countryItems.setAdapter(countryAdapter);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        String choiceStr = prefs.getString("numSelection", "4");

        int number_of_choices = Integer.parseInt(choiceStr);
        countryNames = new String[number_of_choices];
        for (int i = 0; i < countryNames.length; ++i) {
            if (i == 0) {
                countryNames[i] = "America";
            } else if (i == 1){
                countryNames[i] = "Australia";
            } else if (i == 2){
                countryNames[i] = "Germany";
            } else if (i == 3){
                countryNames[i] = "Japan";
            } else if (i == 4){
                countryNames[i] = "Britain";
            } else if (i == 5){
                countryNames[i] = "Italy";
            }
        }

        countryAdapter.addAll(countryNames);

        countryItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(position);

                if (position == 0){
                    foreignH = hour - 12;

                    if (foreignH >= 24){
                        dayAfter = true;
                        foreignH -= 24;
                    } else if (foreignH < 0){
                        dayBefore = true;
                        foreignH = 24 + foreignH;
                    } else {
                        dayBefore = false;
                        dayAfter = false;
                    }

                    inputCheck();
                    prompt();

                } else if (position == 1){
                    foreignH = hour + 2;

                    if (foreignH >= 24){
                        dayAfter = true;
                        foreignH -= 24;
                    } else if (foreignH < 0){
                        dayBefore = true;
                        foreignH = 24 + foreignH;
                    } else {
                        dayBefore = false;
                        dayAfter = false;
                    }

                    inputCheck();
                    prompt();

                } else if (position == 2){
                    foreignH = hour - 6;

                    if (foreignH >= 24){
                        dayAfter = true;
                        foreignH -= 24;
                    } else if (foreignH < 0){
                        dayBefore = true;
                        foreignH = 24 + foreignH;
                    } else {
                        dayBefore = false;
                        dayAfter = false;
                    }

                    inputCheck();
                    prompt();

                } else if (position == 3){
                    foreignH = hour + 1;

                    if (foreignH >= 24){
                        dayAfter = true;
                        foreignH -= 24;
                    } else if (foreignH < 0){
                        dayBefore = true;
                        foreignH = 24 + foreignH;
                    } else {
                        dayBefore = false;
                        dayAfter = false;
                    }

                    inputCheck();
                    prompt();

                } else if (position == 4){
                    foreignH = hour - 7;

                    if (foreignH >= 24){
                        dayAfter = true;
                        foreignH -= 24;
                    } else if (foreignH < 0){
                        dayBefore = true;
                        foreignH = 24 + foreignH;
                    } else {
                        dayBefore = false;
                        dayAfter = false;
                    }

                    inputCheck();
                    prompt();

                } else if (position == 5){
                    foreignH = hour - 6;

                    if (foreignH >= 24){
                        dayAfter = true;
                        foreignH -= 24;
                    } else if (foreignH < 0){
                        dayBefore = true;
                        foreignH = 24 + foreignH;
                    } else {
                        dayBefore = false;
                        dayAfter = false;
                    }

                    inputCheck();
                    prompt();

                }
            }
        });

        return fragmentView;
    }

    public boolean inputCheck(){
        if (textH.getText().toString().isEmpty()){
            textC.setText("Please enter the hour");
            return false;
        } else if (textM.getText().toString().isEmpty()){
            textC.setText("Please enter the minute");
            return false;
        }else if (textM.getText().length() == 1){
            textC.setText("Please enter the currect format (E.g. 01)");
            return false;
        } else {
            return true;
        }
    }

    public void prompt() {
        if (inputCheck()){
            if (minute < 10){
                if (dayBefore){
                    textC.setText(foreignH + ":0" + minute + " (The day before)");
                } else if (dayAfter){
                    textC.setText(foreignH + ":0" + minute + " (The day after)");
                } else {
                    textC.setText(foreignH + ":0" + minute);
                }
            } else {
                if (dayBefore){
                    textC.setText(foreignH + ":" + minute + " (The day before)");
                } else if (dayAfter){
                    textC.setText(foreignH + ":" + minute + " (The day after)");
                } else {
                    textC.setText(foreignH + ":" + minute);
                }
            }
        }
    }
}
