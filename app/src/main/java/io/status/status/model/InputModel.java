package io.status.status.model;

import android.content.Context;
import android.media.AudioManager;
import android.provider.Settings;
import android.util.Log;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

import io.status.status.Acceleration;

/**
 * @author Adam Stelmaszczyk
 */
public class InputModel {

    public int accelerometer;
    public int silent;
    public int onCall;
    public String nextAlarm;

    public InputModel(Context context) {
        this.accelerometer = initAccelerometer(context);
        this.silent = initSilent(context);
        this.onCall = initOnCall();
        this.nextAlarm = initNextAlarm(context);
    }

    private int initAccelerometer(Context context) {
        Acceleration acceleration = new Acceleration(context);
        int accelerometer = acceleration.isAccelerating();
        Log.d("test", "accelerometer: " + accelerometer);
        return accelerometer;
    }

    private int initSilent(Context context) {
        AudioManager am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        int ringerMode = am.getRingerMode();
        int silent = (ringerMode == AudioManager.RINGER_MODE_SILENT || ringerMode == AudioManager.RINGER_MODE_VIBRATE) ? 1 : 0;
        Log.d("test", "silent: " + silent);
        return silent;
    }

    private int initOnCall() {
        return 0;
    }

    private String initNextAlarm(Context context) {
        String nextAlarm = Settings.System.getString(context.getContentResolver(), Settings.System.NEXT_ALARM_FORMATTED);
        Log.d("test", "nextAlarm: " + nextAlarm);
        return nextAlarm;
    }

    public List<? extends NameValuePair> getNameValuePairs() {
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("accelerometer", accelerometer + ""));
        nameValuePairs.add(new BasicNameValuePair("silent", silent + ""));
        nameValuePairs.add(new BasicNameValuePair("onCall", onCall + ""));
        nameValuePairs.add(new BasicNameValuePair("nextAlarm", nextAlarm + ""));
        return nameValuePairs;
    }
}
