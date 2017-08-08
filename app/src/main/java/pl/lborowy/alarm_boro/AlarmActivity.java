package pl.lborowy.alarm_boro;

import android.content.Context;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;

public class AlarmActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        final Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        View mainView = findViewById(R.id.activity_alarm_view);

        if (Build.VERSION.SDK_INT >= 26) {
            vibrator.vibrate(VibrationEffect.createOneShot(2000, 1000));
        } else {
            long[] pattern = {0, 1000, 100}; // czekanie na wibracje / jak dlugo wibruje / powtarzalnosc
            vibrator.vibrate(pattern, 0); // 0 - powtarza cały czas
        }

        button = (Button) findViewById(R.id.alarm_activity_stop);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrator.cancel();
            }
        });

        final Animation animation = new AlphaAnimation(1, 0);
        animation.setInterpolator(new LinearInterpolator()); // zawsze musi być
        animation.setRepeatCount(Animation.INFINITE); // powtarzalnośc /do końca/
        animation.setRepeatMode(Animation.REVERSE); // ciemno-jasno-jasno-ciemno
        animation.setDuration(500);
        mainView.startAnimation(animation);

        View stopAlarmButton = findViewById(R.id.alarm_activity_stop);
        stopAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrator.cancel();
                animation.cancel();
                finish();
            }
        });
    }


}
