package io.status.status;

import android.os.AsyncTask;

import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.util.LinkedList;

/**
 * @author Adam Stelmaszczyk
 */
public class GetTask extends AsyncTask<String, Void, String> {
    MainActivity caller;

    GetTask(MainActivity caller){
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

        Gson gson = new Gson();
        User user = gson.fromJson(response, User.class);

        LinkedList<String> users = new LinkedList<String>();
        for (User u : user.friends) {
            users.push(u.name);
        }

        this.caller.setupList(users);
    }
}