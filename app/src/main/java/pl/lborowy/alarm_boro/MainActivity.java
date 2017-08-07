package pl.lborowy.alarm_boro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TimePicker timePicker = (TimePicker) findViewById(R.id.main_activity_time_picker);
        timePicker.setIs24HourView(true);

        Button testButton = (Button) findViewById(R.id.main_activity_test_alarm);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startAlarmIntent = new Intent(MainActivity.this, AlarmActivity.class);
                startActivity(startAlarmIntent);
            }
        });
    }
}
