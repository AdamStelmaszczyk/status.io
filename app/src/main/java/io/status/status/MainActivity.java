package io.status.status;

import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

/**
 * @author Adam Stelmaszczyk
 */
public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String nextAlarm = Settings.System.getString(getContentResolver(),
                Settings.System.NEXT_ALARM_FORMATTED);
//        Log.d("test", "nextAlarm:" + nextAlarm);

        new GetTask().execute("http://178.62.45.23/get/1");
        new PostTask(new InputModel(0)).execute("http://178.62.45.23/input/1");
    }


}
