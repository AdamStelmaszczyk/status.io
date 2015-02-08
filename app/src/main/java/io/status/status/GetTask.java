package io.status.status;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

/**
 * @author Adam Stelmaszczyk
 */
public class GetTask extends AsyncTask<String, Void, String> {

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
        User user = gson.fromJson(response, User.class);
    }
}