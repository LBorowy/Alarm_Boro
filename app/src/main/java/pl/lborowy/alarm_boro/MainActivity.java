package pl.lborowy.alarm_boro;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private PendingIntent pendingIntent;
    private AlarmManager alarmManager;
    private Intent startAlarmIntent;
    private TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timePicker = (TimePicker) findViewById(R.id.main_activity_time_picker);
        timePicker.setIs24HourView(true);


        startAlarmIntent = new Intent(this, AlarmActivity.class);
        // jak znajdzie pending z kodem 0, to go podmieni (aplikacja przyjmuje kod 1)
        pendingIntent = PendingIntent.getActivity(this, 0, startAlarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        // alarm manager - zarządza alarmami (usługa systemowa)
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);





    }

    private long getMillisFromTimePicker() {
        int hour = getHour();
        int minutes = getMinutes();
        // calendar zwraca z milisekundach
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minutes);
        // ustawiamy na termin (następny dizeń)
//        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1);
        return calendar.getTimeInMillis();
    }



    private int getHour() {
//        int hour;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            hour = timePicker.getHour();
//        }
//        else {
//            hour = timePicker.getCurrentHour();
//        }
//        return hour;

//        // alternatywa1
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
//            return timePicker.getHour();
//        else
//            return timePicker.getCurrentHour();

        // alternatywa2
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ? timePicker.getHour() : timePicker.getCurrentHour();
    }

    private int getMinutes() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ? timePicker.getMinute() : timePicker.getCurrentMinute();
    }

    public void setAlarm(View view) {
        // zarządzanie alarmami
        alarmManager.set(
                AlarmManager.RTC_WAKEUP, // czas rzeczywisty + wybudzenie
                getMillisFromTimePicker(),
                pendingIntent);
    }

    public void runAlarm(View view) {
        startActivity(startAlarmIntent);
    }
}
