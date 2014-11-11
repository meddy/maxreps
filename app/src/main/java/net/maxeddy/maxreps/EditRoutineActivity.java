package net.maxeddy.maxreps;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class EditRoutineActivity extends Activity {

    private SeekBar numWorkoutsSlider;
    private TextView numWorkoutsText;
    private ListView daysList;
    private int numWorkouts = 1;
    private String[] workoutLabels = {"A", "B", "C", "D", "E", "F", "G"};
    private int cycleLength = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_routine);
        initializeViews();
        setupNumWorkoutsSlider();
        updateScheduleDayList();
    }

    private void initializeViews() {
        numWorkoutsSlider = (SeekBar)findViewById(R.id.numWorkoutsSlider);
        numWorkoutsText = (TextView)findViewById(R.id.numWorkoutsText);
        daysList = (ListView)findViewById(R.id.daysList);
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

    private void updateScheduleDayList() {
        ArrayList<Day> days = new ArrayList<Day>();
        int numDays = cycleLength * 7;
        for(int i = 1; i <= numDays; i++) {
            days.add(new Day(i, "off"));
        }

        DayListAdapter dayAdapter = new DayListAdapter(this, days);
        daysList.setAdapter(dayAdapter);
    }

    private void updateWorkoutScheduleValues() {
        ArrayList<String> list = new ArrayList<String>();
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

        Spinner daySpinner = (Spinner)daysList.findViewById(R.id.daySpinner);
        daySpinner.setAdapter(dataAdapter);
    }

    private class DayListAdapter extends ArrayAdapter<Day> {

        private ArrayList<Day> items;

        public DayListAdapter(Context context, ArrayList<Day> items) {
            super(context, 0, items);
            this.items = items;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater)getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE
                );
                convertView = inflater.inflate(R.layout.edit_routine_day_item, parent, false);
            }

            Day day = items.get(position);
            TextView dayLabel = (TextView)convertView.findViewById(R.id.dayLabel);
            dayLabel.setText(day.getLabel());

            return convertView;
        }
    }

    public void onCycleLengthRadioButtonClick(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        if (!checked) {
            return;
        }

        switch (view.getId()) {
            case R.id.cycle_length_one:
                cycleLength = 1;
                break;

            case R.id.cycle_length_two:
                cycleLength = 2;
                break;
        }
        updateScheduleDayList();
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
