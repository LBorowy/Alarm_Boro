package pl.lborowy.alarm_boro;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by RENT on 2017-08-07.
 */

public class BatteryBroadcastReceiver extends BroadcastReceiver {
    // odpala się, kiedy transmisja przyjdzie
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Charger pluged in", Toast.LENGTH_SHORT).show();
    }
}
