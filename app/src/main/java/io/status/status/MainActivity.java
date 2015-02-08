package io.status.status;

import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;

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
    }

    @Override
    protected void onResume() {
        super.onResume();

        Repeater.doAfterDelay(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        Repeater.stop(this);
    }
}
