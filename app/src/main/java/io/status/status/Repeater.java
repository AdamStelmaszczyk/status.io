package io.status.status;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;
import android.widget.ArrayAdapter;

import io.status.status.model.InputModel;
import io.status.status.task.GetTask;
import io.status.status.task.PostTask;

/**
 * @author Adam Stelmaszczyk
 */
public class Repeater extends IntentService {

    static UserListAdapter userListAdapter;

    public static int userId = 1;
    /**
     * Repetition delay in ms.
     */
    private static final int DELAY = 1000;
    private static final int INTENT_REQUEST_CODE = 666;
    private static final String BASE_URL = "http://178.62.45.23/";

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
        if (userListAdapter != null) {
            new GetTask(userListAdapter).execute(BASE_URL + "get/" + userId);
        }

        InputModel inputModel = new InputModel(context);
        Log.d("test", inputModel.toString());
        new PostTask(inputModel).execute(BASE_URL + "post/" + userId);

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
