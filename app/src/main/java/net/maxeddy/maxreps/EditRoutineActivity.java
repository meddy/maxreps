package net.maxeddy.maxreps;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EditRoutineActivity extends Activity {

    private SeekBar numWorkoutsSlider;
    private TextView numWorkoutsText;
    private int numWorkouts = 1;
    private String[] workoutLabels = {"A", "B", "C", "D", "E", "F", "G"};
    private Spinner[] days = new Spinner[7];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_routine);
        initializeViews();
        setupNumWorkoutsSlider();
        updateWorkoutScheduleValues();
    }

    private void initializeViews() {
        numWorkoutsSlider = (SeekBar)findViewById(R.id.numWorkoutsSlider);
        numWorkoutsText = (TextView)findViewById(R.id.numWorkoutsText);
        days[0] = (Spinner)findViewById(R.id.dayOne);
        days[1] = (Spinner)findViewById(R.id.dayTwo);
        days[2] = (Spinner)findViewById(R.id.dayThree);
        days[3] = (Spinner)findViewById(R.id.dayFour);
        days[4] = (Spinner)findViewById(R.id.dayFive);
        days[5] = (Spinner)findViewById(R.id.daySix);
        days[6] = (Spinner)findViewById(R.id.daySeven);
    }

    private void setupNumWorkoutsSlider() {
        numWorkoutsSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                numWorkoutsText.setText(Integer.toString(progress + 1));
                numWorkouts = progress + 1;
                updateWorkoutScheduleValues();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Nothing to do
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Nothing to do
            }
        });
    }

    private void updateWorkoutScheduleValues() {
        List<String> list = new ArrayList<String>();
        list.add("Off");
        for (int i = 0; i < numWorkouts && i < workoutLabels.length; i++) {
            list.add(workoutLabels[i]);
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_item,
            list
        );
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        for (Spinner day: days) {
            day.setAdapter(dataAdapter);
        }
    }

    public void onCycleLengthRadioButtonClick(View view) {
        // TODO: Implement
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_routine, menu);
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
}
