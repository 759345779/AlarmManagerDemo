package com.example.alarmmanagerdemo;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;


public class AlarmReceiver extends BroadcastReceiver {
    public static final String TAG = "AlarmReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.i(TAG, "log");
        if (action == MainActivity.INTENT_ALARM_LOG) {
            Log.i(TAG, "log log");
            Intent i = new Intent(MainActivity.INTENT_ALARM_LOG);
            i.setPackage(context.getPackageName());
            long currentTime = System.currentTimeMillis();
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context.getApplicationContext(), (int) currentTime, i, 0);;
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//>6.0 api 23
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, currentTime+MainActivity.TIME_30S, pendingIntent);
            }else {//SDK API >= 19 && SDK API < 23 4.4-6.0
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, currentTime+MainActivity.TIME_30S, pendingIntent);
            }

        }
    }
}
