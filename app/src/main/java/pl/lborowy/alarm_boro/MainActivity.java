package pl.lborowy.alarm_boro;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {

    private PendingIntent pendingIntent;
    private AlarmManager alarmManager;
    private Intent startAlarmIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TimePicker timePicker = (TimePicker) findViewById(R.id.main_activity_time_picker);
        timePicker.setIs24HourView(true);


        startAlarmIntent = new Intent(this, AlarmActivity.class);
        // jak znajdzie pending z kodem 0, to go podmieni (aplikacja przyjmuje kod 1)
        pendingIntent = PendingIntent.getActivity(this, 0, startAlarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        // alarm manager - zarządza alarmami (usługa systemowa)
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

    }

    public void setAlarm(View view) {
        // zarządzanie alarmami
        alarmManager.set(
                AlarmManager.RTC_WAKEUP, // czas rzeczywisty + wybudzenie
                System.currentTimeMillis() + 100,
                pendingIntent);
    }

    public void runAlarm(View view) {
        startActivity(startAlarmIntent);
    }
}
