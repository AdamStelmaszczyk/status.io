package io.status.status.model;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.provider.Settings;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Adam Stelmaszczyk
 */
public class InputModel {

    public int accelerometer;
    public int silent;
    public int onCall;
    public String nextAlarm;

    InputModel(Context context) {
        this.accelerometer = initAccelerometer(context);
        this.silent = initSilent();
        this.onCall = initOnCall();
        this.nextAlarm = initNextAlarm(context);
    }

    private int initAccelerometer(Context context) {
        SensorManager sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        return 0;
    }



    private int initSilent() {
        return 0;
    }

    private int initOnCall() {
        return 0;
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

}
