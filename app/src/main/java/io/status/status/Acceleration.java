package io.status.status;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

/**
 * @author Adam Stelmaszczyk
 */
public class Acceleration implements SensorEventListener {

    private static final double THRESHOLD = 4.0;
    private static double ax, ay, az; // acceleration in x, y and z axis

    public Acceleration(Context context) {
        SensorManager sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }

    public int isAccelerating() {
        Log.d("test", "ax: " + ax + " ay: " + ay);
        return (Math.abs(ax) > THRESHOLD || Math.abs(ay) > THRESHOLD) ? 1 : 0;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            ax = event.values[0];
            ay = event.values[1];
            az = event.values[2];
//            Log.d("test", ax + " " + ay + " " + az);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}
