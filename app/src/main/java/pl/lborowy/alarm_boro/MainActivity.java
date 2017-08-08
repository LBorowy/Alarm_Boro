package pl.lborowy.alarm_boro;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
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

        final Intent startAlarmIntent = new Intent(this, AlarmActivity.class);

        Button testButton = (Button) findViewById(R.id.main_activity_test_alarm);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent startAlarmIntent = new Intent(MainActivity.this, AlarmActivity.class);
                startActivity(startAlarmIntent);
            }
        });


        // jak znajdzie pending z kodem 0, to go podmieni (aplikacja przyjmuje kod 1)
        final PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, startAlarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // alarm manager - zarządza alarmami (usługa systemowa)
        final AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Button setAlarmButton = (Button) findViewById(R.id.main_activity_set_alarm);
        setAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // zarządzanie alarmami
                alarmManager.set(
                        AlarmManager.RTC_WAKEUP, // czas rzeczywisty + wybudzenie
                        System.currentTimeMillis() + 100,
                        pendingIntent);
            }
        });
    }


}
