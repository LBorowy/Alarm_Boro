package pl.lborowy.alarm_boro;

import android.content.Context;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;

import static android.media.AudioManager.STREAM_ALARM;

public class AlarmActivity extends AppCompatActivity {

    private Vibrator vibrator;
    private Animation animation;
    private Ringtone ringtone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        View mainView = findViewById(R.id.activity_alarm_view);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        animation = setupAnimation();

        // zbieranie scieżki do pliku, które są w paczce
        Uri path = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.alarm);
        // ringtonemanager domyslny alarm
        RingtoneManager.setActualDefaultRingtoneUri(this, RingtoneManager.TYPE_ALARM, path);
        ringtone = RingtoneManager.getRingtone(this, path);
        ringtone.setStreamType(STREAM_ALARM);
        ringtone.play();

        startAlarm(mainView);

    }

    private void startAlarm(View mainView) {
        startVibration(vibrator);
        mainView.startAnimation(animation);
        ringtone.play();
    }

    public void stopAlarm(View view) { // View view, żeby było widoczne dla xml'a
        vibrator.cancel();
        animation.cancel();
        ringtone.stop();
        finish();
    }

    private void startVibration(Vibrator vibrator) {
        if (Build.VERSION.SDK_INT >= 26) {
            vibrator.vibrate(VibrationEffect.createOneShot(2000, 1000));
        } else {
            long[] pattern = {0, 1000, 100}; // czekanie na wibracje / jak dlugo wibruje / powtarzalnosc
            vibrator.vibrate(pattern, 0); // 0 - powtarza cały czas
        }
    }

    @NonNull
    private Animation setupAnimation() {
        final Animation animation = new AlphaAnimation(1, 0);
        animation.setInterpolator(new LinearInterpolator()); // zawsze musi być
        animation.setRepeatCount(Animation.INFINITE); // powtarzalnośc /do końca/
        animation.setRepeatMode(Animation.REVERSE); // ciemno-jasno-jasno-ciemno
        animation.setDuration(500);
        return animation;
    }


}
