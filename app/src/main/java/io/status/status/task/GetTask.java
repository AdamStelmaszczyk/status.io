package io.status.status.task;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.util.LinkedList;

import io.status.status.MainActivity;
import io.status.status.Utils;
import io.status.status.model.UserModel;

/**
 * @author Adam Stelmaszczyk
 */
public class GetTask extends AsyncTask<String, Void, String> {
    MainActivity caller;

    GetTask(MainActivity caller) {
        this.caller = caller;
    }

    protected String doInBackground(String... urls) {
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(urls[0]);
        HttpResponse response;
        try {
            response = client.execute(request);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return Utils.responseToString(response);
    }

    protected void onPostExecute(String response) {
        if (response == null) {
            return;
        }

        Log.d("test", "GET response: " + response);
        Gson gson = new Gson();
        UserModel user = gson.fromJson(response, UserModel.class);

        LinkedList<String> users = new LinkedList<>();
        for (UserModel u : user.friends) {
            users.push(u.name);
        }

        caller.setupList(users);
    }
}