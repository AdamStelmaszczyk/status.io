package io.status.status.model;

import android.content.Context;
import android.media.AudioManager;
import android.provider.Settings;

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
        this.onCall = initOnCall(context);
        this.nextAlarm = initNextAlarm(context);
    }

    private int initAccelerometer(Context context) {
        Acceleration acceleration = new Acceleration(context);
        return acceleration.isAccelerating();
    }

    private int initSilent(Context context) {
        AudioManager am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        int ringerMode = am.getRingerMode();
        return (ringerMode == AudioManager.RINGER_MODE_SILENT || ringerMode == AudioManager.RINGER_MODE_VIBRATE) ? 1 : 0;
    }

    private int initOnCall(Context context) {
        AudioManager manager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        return (manager.getMode() == AudioManager.MODE_IN_CALL) ? 1 : 0;
    }

    private String initNextAlarm(Context context) {
        return Settings.System.getString(context.getContentResolver(), Settings.System.NEXT_ALARM_FORMATTED);
    }

    public List<? extends NameValuePair> getNameValuePairs() {
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("accelerometer", accelerometer + ""));
        nameValuePairs.add(new BasicNameValuePair("silent", silent + ""));
        nameValuePairs.add(new BasicNameValuePair("onCall", onCall + ""));
        nameValuePairs.add(new BasicNameValuePair("nextAlarm", nextAlarm + ""));
        return nameValuePairs;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getName() + ":\n");
        sb.append("accelerometer: ").append(accelerometer).append("\n");
        sb.append("silent: ").append(silent).append("\n");
        sb.append("onCall: ").append(onCall).append("\n");
        sb.append("nextAlarm: \"").append(nextAlarm).append("\"\n");
        return sb.toString();
    }
}
