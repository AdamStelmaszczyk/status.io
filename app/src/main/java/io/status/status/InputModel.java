package io.status.status;

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

    InputModel(int accelerometer, int silent, int onCall, String nextAlarm) {
        this.accelerometer = accelerometer;
        this.silent = silent;
        this.onCall = onCall;
        this.nextAlarm = nextAlarm;
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
