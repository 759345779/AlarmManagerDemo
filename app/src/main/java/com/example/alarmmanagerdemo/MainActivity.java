package com.example.alarmmanagerdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static final String INTENT_ALARM_LOG = "intent_alarm_log";
    AlarmManager alarmManager = null;

    public static final long TIME_30S = 1000 * 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        findViewById(R.id.set_alarm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAlarm();
            }
        });
    }

    private void setAlarm() {
        Intent intent = new Intent(INTENT_ALARM_LOG);
        intent.setPackage(this.getPackageName());
        long currentTime = System.currentTimeMillis();
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, (int) currentTime, intent, 0);;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//>6.0 api 23
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, currentTime, pendingIntent);
        }else {//SDK API >= 19 && SDK API < 23 4.4-6.0
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, currentTime, pendingIntent);
        }

        Log.i(AlarmReceiver.TAG, "set 30s");
    }


}