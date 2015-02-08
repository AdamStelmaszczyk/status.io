package io.status.status;

/**
 * @author Adam Stelmaszczyk
 */
public class InputModel {

    public int accelerometer;
    public int silent;
    public int onCall;
    public String nextAlarm;

    InputModel(int accelerometer, int silent, int onCall, String nextAlarm) {
        this.accelerometer = accelerometer;
        this.silent = silent;
        this.onCall = onCall;
        this.nextAlarm = nextAlarm;
    }

}
