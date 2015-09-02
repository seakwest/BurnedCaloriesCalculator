package com.evaristo.burnedcaloriescalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class BurnedCalories extends AppCompatActivity {

    //Variables for Widgets
    private EditText nameEditText;
    private TextView bMIView;
    private TextView weightView;
    private TextView milesView;
    private EditText caloriesBurned;
    private SeekBar milesSeekBar;
    private Spinner feetSpinner;
    private Spinner inchSpinner;

    //instance variables
    private String caloriesBurnedString = "";
    private String weightString = "";
    private String bMIString = "";
    private String milesString = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_burned_calories);

        //Get references by id
        nameEditText = (EditText) findViewById(R.id.nameEditText);
        bMIView = (TextView) findViewById(R.id.bMIView);
        milesSeekBar = (SeekBar) findViewById(R.id.milesSeekBar);
        weightView = (TextView) findViewById(R.id.weightView);
        milesView = (TextView) findViewById(R.id.milesView);
        caloriesBurned = (EditText) findViewById(R.id.caloriesBurned);
        feetSpinner = (Spinner) findViewById(R.id.feetSpinner);
        inchSpinner = (Spinner) findViewById(R.id.inchSpinner);





        //set listeners

        caloriesBurned.setOnEditorActionListener(editTextListener);
        caloriesBurned.setOnKeyListener(keyListener);
        milesSeekBar.setOnSeekBarChangeListener(seekBarListener);
        milesSeekBar.setOnKeyListener(keyListener);
        feetSpinner.setOnItemSelectedListener(spinnerListener);
        inchSpinner.setOnItemSelectedListener(spinnerListner);


        //get saved values
        savedValues = getSharedPreferences("SavedValues", MODE_PRIVATE);


    }

    public void calculateAndDisplay() {

        //get miles ran
        milesString = milesView.getText().toString();
        float miles;
        if (milesString.equals("")) {
            miles = 0;
        } else {
            miles = Float.parseFloat(milesString);
        }


        //get height


        //get calories burned
        //caloriesBurnedString = caloriesBurned.getText().toString();
        //float calories;
        //if (caloriesBurned.equals("")) {
        //   calories = 0;
        //}
        //else {
        //   calories = Float.parseFloat(caloriesBurnedString);
        //}

        //get weight
        weightString = weightView.getText().toString();
        float weight;
        if (weightString.equals("")) {
            weight = 0;
        } else {
            weight = Float.parseFloat(weightString);
        }

        //get BMI
        //bMIString = bMIView.getText().toString();
        //float bMI;
        //if (bMIString.equals("")) {
        //    bMI = 0;
        //}
        //else {
        //  bMI = Float.parseFloat(bMIString);

        //}

    }







    @Override
    public void onPause() {
        // save the instance variables
        Editor editor = savedValues.edit();

        editor.commit();

        super.onPause();
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_burned_calories, menu);
        return true;
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

    //Seekbar Listner
    private OnSeekBarChangeListener seekBarListener = new OnSeekBarChangeListener() {

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress,
                                      boolean fromUser) {
            milesView.setText(progress);
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            calculateAndDisplay();
        }
    };

    //spinner listener
    private OnItemSelectedListener spinnerListener = new OnItemSelectedListener() {

        @Override
        public void onItemSelected(AdapterView<?> parent, View v, int position,
                                   long id) {
            feetSpinner = position + 1;

            inchSpinner = position + 1;
            calculateAndDisplay();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
}
