package io.status.status;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

/**
 * @author Adam Stelmaszczyk
 */
public class Repeater extends IntentService {

    static MainActivity mainActivity;

    /**
     * Repetition delay in ms.
     */
    private static final int DELAY = 3000;
    private static final int INTENT_REQUEST_CODE = 666;

    public Repeater() {
        super("String name");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        doAfterDelay(this);
    }

    public static void doAfterDelay(Context context) {
        Log.d("test", "Repeater.doAfterDelay");

        // do your thing - GET and POST request
<<<<<<< HEAD
        if (mainActivity != null) {
            new GetTask(mainActivity).execute("http://178.62.45.23/get/3");
        }

        InputModel inputModel = new InputModel(0, 0, 0, "");
        new PostTask(inputModel).execute("http://178.62.45.23/input/1");
=======
        if (mainActivity != null)
            new GetTask(mainActivity).execute("http://178.62.45.23/get/3");

        InputModel inputModel = new InputModel(0);
        new PostTask(inputModel).execute("http://178.62.45.23/input/2");
>>>>>>> 17e2b62af5e02d3911928b024aa89a63ed715636

        // schedule next
        getAlarmManager(context).set(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + DELAY,
                getAlarmIntent(context));
    }

    private static AlarmManager getAlarmManager(Context context) {
        return (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    }

    private static PendingIntent getAlarmIntent(Context context) {
        return PendingIntent.getService(context, INTENT_REQUEST_CODE, createReceiverIntent(context), PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private static Intent createReceiverIntent(Context context) {
        return new Intent(context, Repeater.class);
    }

    public static void stop(Context context) {
        Log.d("test", "Repeater.stop");
        getAlarmManager(context).cancel(getAlarmIntent(context));
    }
}
